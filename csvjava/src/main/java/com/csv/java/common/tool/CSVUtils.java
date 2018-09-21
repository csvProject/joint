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
    public static void createCSV( List<Object> headList ,List<List<Object>> dataList,String fileName,String filePath) {

        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(filePath + fileName);
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();
            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UNICODELITTLE"), 1024);

            if(headList != null){

                // 写入文件头部
                writeRow(headList, csvWtriter);
                csvWtriter.newLine();
            }

            // 写入文件内容

            for (int i =0 ;i<dataList.size();i++) {
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
            if (i == row.size() -1){
                rowStr = sb.append("\"").append(row.get(i).toString()).append("\"").toString();
            }else{
                rowStr = sb.append("\"").append(row.get(i).toString()).append("\",").toString();
            }

            csvWriter.write(rowStr);
        }

    }
}