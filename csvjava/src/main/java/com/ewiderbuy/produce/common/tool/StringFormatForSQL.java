package com.ewiderbuy.produce.common.tool;

import com.ewiderbuy.produce.dao.CsvCustomFieldDao;
import com.ewiderbuy.produce.dao.SysCodeDao;
import com.ewiderbuy.produce.entity.CsvCustomFieldDto;
import com.ewiderbuy.produce.entity.CsvTemplateDetailDto;
import com.ewiderbuy.produce.entity.ReplacementSrc;
import com.ewiderbuy.produce.entity.SysCodeDto;
import com.ewiderbuy.produce.entity.ReplacementSrc;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.ewiderbuy.produce.common.tool.MYSQLEncoder.encode;
import static com.ewiderbuy.produce.config.ConstantConfig.FIELD_MATCHING_TABLE;


public class StringFormatForSQL{
    public static String ruleFormat(String key,String value, Map<String,String> arguments){
        String formatedStr = value;
        for (String k : arguments.keySet()) {
            String regex = "<span class=\"stop-propagation\" contenteditable=\"false\" inval=\""+arguments.get(k).toString()+"\">"+k;
            formatedStr =
                    formatedStr.replaceAll(
                            regex,
                            "%`~"+ arguments.get(k).toString()+"^%`~");
            /*String regex1 = "<span class=\"stop-propagation1\" contenteditable=\"false\" inval=\""+arguments.get(k).toString()+"\">"+k;
            formatedStr =
                    formatedStr.replaceAll(
                            regex1,
                            "%`~"+ arguments.get(k).toString()+"^%`~");*/
        }
        formatedStr = stripHtml(formatedStr);
        if(formatedStr.endsWith("\r\n")){
            formatedStr = formatedStr.substring(0,formatedStr.length() - 2);
        }else{

        }
        if(formatedStr.endsWith("\n")){
            formatedStr = formatedStr.substring(0,formatedStr.length() - 1);
        }else{

        }
        if(formatedStr.endsWith("\r")){
            formatedStr = formatedStr.substring(0,formatedStr.length() - 1);
        }else{

        }
        String[] strings = formatedStr.split("%`~");

        String ret = "CONCAT(";
        for (int i =0;i<strings.length;i++){
            String s = strings[i];
            if(s.endsWith("^")){
                s = s.substring(0,s.length() - 1);
            }else{
//                s = "@'"+encode(s)+"@'";
                s = "@'"+s+"@'";
            }
            ret = ret +""+s+ ",";
        }
        if (ret.endsWith(",")) {
            ret = ret.substring(0,ret.length() - 1) + ") AS @'"+key+"@'";
        }else{
            ret = ret + ") AS @'"+key+"@'";
        }
        return ret;
    }

    public static String fieldListFormat(List<CsvTemplateDetailDto> list,Map<String,String> arguments){
        if (list == null){
            return "";
        }
        String ret = "SELECT \n  ";
        for(int i=0;i<list.size();i++){
            CsvTemplateDetailDto c = list.get(i);
            String k = c.getFieldKey();
            String v = c.getFieldValue();
            if(i != list.size()-1){
                ret = ret + StringFormatForSQL.ruleFormat(k,v, arguments) + ",\n  ";
            }else{
                ret = ret + StringFormatForSQL.ruleFormat(k,v, arguments) + " \n";
            }
        }

        ret = ret + "FROM \n  " + FIELD_MATCHING_TABLE;
        ret = ret.replaceAll("@'","'")
                .replaceAll("&lt;","<")
                .replaceAll("&gt;",">");
        System.out.println("**********************************************************");

        System.out.println(ret);

        System.out.println("**********************************************************");
        return ret;
    }


    //方法一
    public static String stripHtml(String content) {
        // <p>段落替换为换行
        content = content.replaceAll("<p .*?>", "\r\n");
        // <br><br/>替换为换行
        content = content.replaceAll("<br\\s*/?>", "\r\n");
        // 去掉其它的<>之间的东西
        content = content.replaceAll("\\<.*?>", "");
        // 去掉空格
         content = content.replaceAll(" ", " ");
        return content;
    }

    //方法二
    public static String delHtmlTag(String str){
        String newstr = "";
        newstr = str.replaceAll("<[.[^>]]*>","");
        newstr = newstr.replaceAll(" ", "");
        return newstr;
    }

    public static String csvExportSql(String sql,Map<String,String> arguments){
        for (String k : arguments.keySet()) {
            String regex = k;
            String v = arguments.get(k);
            if(v == null){
                v = "";
            }
            sql =sql.replaceAll(regex,v.toString());
        }
        sql = sql.replaceAll("#","");
        String ret = "";
        String reg = "t_csvcustom_field[^t_csvcustom_field]*t_csvcustom_field";
        ret = sql.replaceAll(reg, "''");
        return ret;
    }


    /* 复制操作 */
    public static List<CsvTemplateDetailDto> CustomReplacement(List<CsvTemplateDetailDto> dtoList,Integer oldId ,Integer newId,String oldKey,String newKey){
        if(dtoList == null || dtoList.size()<=0){
            return null;
        }
        ReplacementSrc fields = new ReplacementSrc();
        fields.setField1("t_csvcustom_field."+oldId.toString()+"t_csvcustom_field");
        fields.setField2("t_csvcustom_field."+newId.toString()+"t_csvcustom_field");
        fields.setField3(oldKey);
        fields.setField4(newKey);
        List<ReplacementSrc> fieldList = new ArrayList<>();
        fieldList.add(fields);
        for(CsvTemplateDetailDto csvTemplateDetailDto: dtoList){
            csvTemplateDetailDto.setFieldValue(SrcReplacement(csvTemplateDetailDto.getFieldValue(),fieldList));
        }
        return dtoList;
    }
    public static String SrcReplacement(String src,List<ReplacementSrc> fieldList){
        for (ReplacementSrc fields : fieldList) {
            String regex0 = "<span class=\"stop-propagation\" contenteditable=\"false\" inval=\""+fields.getField1().toString()+"\">"+fields.getField3().toString();
            String res0 = "<span class=\"stop-propagation\" contenteditable=\"false\" inval=\""+fields.getField2().toString()+"\">"+fields.getField4().toString();
            src =src.replaceAll(regex0,res0);
            if ("-1".equals(fields.getField2())){
                String regex1 = "<span class=\"stop-propagation\" contenteditable=\"false\" inval=\""+fields.getField1().toString()+"\">"+fields.getField3().toString();
                String res1 = "<span class=\"stop-propagation1\" contenteditable=\"false\" inval=\""+fields.getField2().toString()+"\">"+fields.getField3().toString();
                src =src.replaceAll(regex1,res1);
            }
        }
        return src;
    }

    /**
     * 数据库入库日期 约定的格式：yyyy-MM-dd   yyyy-MM-dd HH:mm:ss
     * @param geShi
     * @return
     */
    public static String changeDateFmt(String str,String startGeShi,String geShi){
        SimpleDateFormat formatter = new SimpleDateFormat(startGeShi);
        try {
            formatter.setLenient(false);
            Date newDate = formatter.parse(str);
            formatter = new SimpleDateFormat(geShi);
            return formatter.format(newDate);
        }catch (Exception e){
            return "0000-00-00";
        }
    }
}

