package com.csv.java.common.tool;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileUtils {
    /**
     * 压缩文件
     * @param filePaths 需要压缩的文件路径集合
     * @throws IOException
     */
    private static String zipFile( List<String> filePaths,ZipOutputStream zos) throws IOException {
        //循环读取文件路径集合，获取每一个文件的路径
        for(String filePath : filePaths){
            File inputFile = new File(filePath);  //根据文件路径创建文件
            if(inputFile.exists()) { //判断文件是否存在
                if (inputFile.isFile()) {  //判断是否属于文件，还是文件夹
                    //创建输入流读取文件
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));

                    //将文件写入zip内，即将文件进行打包
                    zos.putNextEntry(new ZipEntry(inputFile.getName()));

                    //写入文件的方法，同上
                    int size = 0;
                    byte[] buffer = new byte[1024];  //设置读取数据缓存大小
                    while ((size = bis.read(buffer)) > 0) {
                        zos.write(buffer, 0, size);
                    }
                    //关闭输入输出流
                    zos.closeEntry();
                    bis.close();

                } else {  //如果是文件夹，则使用穷举的方法获取文件，写入zip
                    try {
                        File[] files = inputFile.listFiles();
                        List<String> filePathsTemp = new ArrayList<String>();
                        for (File fileTem:files) {
                            filePathsTemp.add(fileTem.toString());
                        }
                        return zipFile(filePathsTemp,zos);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    /**
     * 压缩文件
     * @param zipFilePath 临时压缩文件路径
     * @param filePaths 需要压缩的文件路径集合
     * @throws IOException
     */
    public static String zipPath(String zipFilePath, List<String> filePaths)throws IOException{
//        String zipFilePath = zipBasePath+zipName;
        //压缩文件
        File zip = new File(zipFilePath);
        File parent = zip.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        if (!zip.exists()){
            zip.createNewFile();
        }
        //创建zip文件输出流
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip));
        zipFile(filePaths,zos);
        zos.close();
        return null;
    }


    public static void main(String[] args) {
        String zipFileName = "123.zip";

        String basePath = "C:\\csvFile\\";

        String zipFilePath = basePath + zipFileName;

        String file1 = "1536114557636.CSV";
        String file2 = "1536114725418.CSV";

        List<String> filePaths = new ArrayList<String>();
        filePaths.add(basePath+file1);
        filePaths.add(basePath+file2);
        try {
            zipPath(zipFilePath,filePaths);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
