package com.jhbim.bimvr;

import com.jhbim.bimvr.dao.entity.pojo.Printscreen;
import com.jhbim.bimvr.dao.mapper.PrintscreenMapper;
import com.jhbim.bimvr.utils.FileUploadUtils;
import com.jhbim.bimvr.utils.ZipFiles;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class test {
//    @Test
//    public void aa(){
//        File[] srcFiles = { new File("E:\\tomcat9\\apache-tomcat-9.0.26-windows-x64\\apache-tomcat-9.0.26\\webapps\\ROOT\\Printscreen\\3\\93c15916-337f-4c21-bf65-055d5d3a21ca.jpg"),
//                new File("E:\\tomcat9\\apache-tomcat-9.0.26-windows-x64\\apache-tomcat-9.0.26\\webapps\\ROOT\\Printscreen\\3\\f5aff436-2e20-4a20-b2be-44b01cefd0fb.jpg"),
//                new File("E:\\tomcat9\\apache-tomcat-9.0.26-windows-x64\\apache-tomcat-9.0.26\\webapps\\ROOT\\Printscreen\\3\\d814fc36-f73f-4aa0-ac7e-0bc5abd021ab.jpg") };
//        File zipFile = new File("E:\\ZipFile.zip");
//        ZipFiles.zipFiles(srcFiles,zipFile);
//    }
//    @Resource
//    PrintscreenMapper printscreenMapper;
//    @Test
//    public void bb(){
//        Integer [] ids ={157,158};
//        List<Printscreen> list = printscreenMapper.dynamicForeachTest(ids);
//        for (Printscreen p:list) {
//            System.out.println(p.getImages());
//        }
//    }
    @Test
    public void cc(){
        List<File> list=new ArrayList<>();
        list.add(new File("E:\\tomcat9\\apache-tomcat-9.0.26-windows-x64\\apache-tomcat-9.0.26\\webapps\\ROOT\\Printscreen/3/b2d89540-3ed0-4e0e-827e-c3b9c9092df4.jpg"));
        list.add( new File("E:\\tomcat9\\apache-tomcat-9.0.26-windows-x64\\apache-tomcat-9.0.26\\webapps\\ROOT\\Printscreen/3/b47f2bba-e1b2-4784-bef9-567e91531f3d.jpg"));
        list.add(new File("E:\\tomcat9\\apache-tomcat-9.0.26-windows-x64\\apache-tomcat-9.0.26\\webapps\\ROOT\\Printscreen/3/f1cd8852-a3da-4fa6-9efe-264e475ba82c.jpg"));
        File zipFile = new File("E:\\ZipFile12.zip");
        ZipFiles.toZip(list,zipFile);
    }
    @Test
    public void  dd(){
        String aa="E:\\tomcat9\\apache-tomcat-9.0.26-windows-x64\\apache-tomcat-9.0.26\\webapps\\ROOT\\Zip\\";
        FileUploadUtils.deleteDir(aa);
    }
    @Test
    public void  ee() {
        String path="E:\\tomcat9\\apache-tomcat-9.0.26-windows-x64\\apache-tomcat-9.0.26\\webapps\\ROOT\\Zip\\8d39ea33-d298-48bb-9146-528578fa7080.zip";
        String oo=path.substring(path.lastIndexOf("\\",path.lastIndexOf("\\")-1)+1);
        //"+1"代表在定位时往后取一位,即去掉"/"
        System.out.println(oo);
    }

}
