package com.csv.java.common.tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;


/**
 * 创建CSV文件
 */
public class CSVUtils {

    /**
     * 创建CSV文件
     */
    public static void createCSV( List<Object> headList ,List<List<Object>> dataList,String fileName,
                                  String filePath,int limitCount) {

        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(filePath + fileName);
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();
            // GB2312使正确读取分隔符","UNICODELITTLE
            FileOutputStream fos = new FileOutputStream(csvFile);
            csvWtriter = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));

            if(headList != null){

                // 写入文件头部
                writeRow(headList, csvWtriter);
                csvWtriter.newLine();
            }

            // 写入文件内容
            int count  = 0 ;
            //导出数大于限制数，则只导出限制数，多余不导出
            if (dataList.size() > limitCount) {
                count = limitCount;
            }else{
                count = dataList.size();
            }
            for (int i =0 ;i<count;i++) {
                writeRow(dataList.get(i), csvWtriter);
                if (i != dataList.size() -1){
                    csvWtriter.newLine();
                }
            }
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 写一行数据
     * @param row 数据列表
     * @param csvWriter
     * @throws IOException
     */
    private static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        for (int i =0 ;i<row.size();i++) {
            StringBuffer sb = new StringBuffer();
            String rowStr = "";
            //特殊日文字符处理
            String tmp = row.get(i).toString().replace("・","·");
            if (i == row.size() -1){
                rowStr = sb.append("\"").append(tmp).append("\"").toString();
            }else{
                rowStr = sb.append("\"").append(tmp).append("\",").toString();
            }

            csvWriter.write(rowStr);
        }

    }
}