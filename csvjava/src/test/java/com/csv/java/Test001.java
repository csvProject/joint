package com.csv.java;

import com.csv.java.common.tool.StringFormatForSQL;
import com.csv.java.entity.CsvTemplateDetailDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test001 {
    @Test
    public void test1() {
        String pattern = "你好:${:name}+${:age}-${:name}";
        Map<String,String> arguments = new HashMap<String, String>(){{
            put("name", "name");
            put("age",  "age");
        }};

        String msg = StringFormatForSQL.ruleFormat("",pattern, arguments);

        System.out.println(msg);

    }

    @Test
    public void sqlSrc(){
        // table.info
        //select
        String sql = "";
        List<CsvTemplateDetailDto> list = new ArrayList<>();
        CsvTemplateDetailDto csv = new CsvTemplateDetailDto();
        csv.setFieldKey("name");
        csv.setFieldValue("wkm name：${:setCn}");
        CsvTemplateDetailDto csv2 = new CsvTemplateDetailDto();
        csv2.setFieldKey("age");
        csv2.setFieldValue("wkm age：{:INFO_ID}");
        CsvTemplateDetailDto csv3 = new CsvTemplateDetailDto();
        csv3.setFieldKey("sex");
        csv3.setFieldValue("${:pm_cn}");
        list.add(csv);
        list.add(csv2);
        list.add(csv3);

        Map<String,String> arguments = new HashMap<String, String>(){{
            put("setCn", "set_cn");
            put("pm_cn", "pm_cn");
        }};

       /* for(int i=0;i<list.size();i++){
            CsvTemplateDetailDto c = list.get(i);
            String k = c.getFieldKey();
            String v = c.getFieldValue();
            String sqlSrc = StringFormatForSQL.ruleFormat(k,v, arguments);
            System.out.println(sqlSrc);
        }*/
       sql = StringFormatForSQL.fieldListFormat(list,arguments);
        System.out.println(sql);
    }

    public static void main(String[] args) {
        String str = "\"{\"TaxCode\":\"91321000071018179B\",\"RetMsg\":\"1011-XXX开启成功[0000,]\"," +
                "\"MachineNo\":\"0\",\"TaxClock\":\"2016-11-02 11:21:30\",\"RetCode\":\"1011\"," +
                "\"IsInvEmpty\":\"0\",\"CorpName\":\"XXXX分公司\",\"CheckCode\":\"661XXX4681\"," +
                "\"IsLockReached\":\"0\",\"InvLimit\":\"0.0\",\"IsRepReached\":\"0\"}\"";
        if (str.startsWith("\"")) {
            str = str.substring(1);
        }
        if (str.endsWith("\"")) {
            str = str.substring(0,str.length() - 1);
        }
        System.out.println(str);
    }
}
