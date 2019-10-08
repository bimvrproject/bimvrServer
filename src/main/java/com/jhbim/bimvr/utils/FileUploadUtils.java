package com.jhbim.bimvr.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUploadUtils {
    public static void saveMultiFile(String basePath, MultipartFile[] filess){
        if (filess != null && filess.length >0) {
            String filePath=null;
            for (MultipartFile files : filess) {
                String fileRealName = files.getOriginalFilename();// 获得原始文件名;
                filePath = basePath + "/"+ fileRealName;
                makeDir(filePath);
                File dest = new File(filePath);
                try {

                    files.transferTo(dest);
                } catch (IllegalStateException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 创建文件夹
     * @param filePath
     */
    private static void makeDir(String filePath) {
        if (filePath.lastIndexOf('/') > 0) {
            String dirPath = filePath;
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.getParentFile().mkdirs();
            }
        }
    }

    /**
     * 删除文件夹
     * @param dirPath
     */
    public static void deleteDir(String dirPath)
    {
        File file = new File(dirPath);
        if(file.isFile())
        {
            file.delete();
        }else
        {
            File[] files = file.listFiles();
            if(files == null)
            {
                file.delete();
            }else
            {
                for (int i = 0; i < files.length; i++)
                {
                    deleteDir(files[i].getAbsolutePath());
                }
                file.delete();
            }
        }
    }
}
