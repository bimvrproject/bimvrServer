package com.jhbim.bimvr.controller.pc.download;

import java.io.File;

/**
 * @author shuihu
 * @create 2019-08-29 13:03
 */
public class Test {
    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        Process p = null;
        String fileLac = "";
        try {
            fileLac = "D:/model/WindowsNoEditor/sunrise.exe";// 要调用的程序路径
            p = rt.exec(fileLac);
        } catch (Exception e) {
            System.out.println("open failure");
        }
//        String  str = "D:/model/model1";
//        System.out.println(str.substring(0,str.lastIndexOf("/")));

//        File file = new File("D:/model/model1");
//        delAllFile(file);
    }
    public static void delAllFile(File directory){
        if (!directory.isDirectory()){
            directory.delete();
        } else{
            File [] files = directory.listFiles();

            // 空文件夹
            if (files.length == 0){
                directory.delete();
                System.out.println("删除" + directory.getAbsolutePath());
                return;
            }

            // 删除子文件夹和子文件
            for (File file : files){
                if (file.isDirectory()){
                    delAllFile(file);
                } else {
                    file.delete();
                    System.out.println("删除" + file.getAbsolutePath());
                }
            }

            // 删除文件夹本身
            directory.delete();
            System.out.println("删除" + directory.getAbsolutePath());
        }
    }

}
