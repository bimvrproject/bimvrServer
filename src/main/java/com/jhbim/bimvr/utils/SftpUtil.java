package com.jhbim.bimvr.utils;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;

import java.io.File;

public class SftpUtil {
    private SftpUtil() {
    }
    public static void uploadFilesToServer(String srcPath, String dst) throws Exception {
        ChannelSftp sftp = upload(srcPath, dst);
        if (sftp != null) {
            sftp.quit();
            sftp.disconnect();
            System.out.println(" SFTP disconnect successfully!");
        }
        ChannelSftpSingleton.getInstance().closeChannel();
    }
    private static ChannelSftp upload(String path, String dst) throws SftpException {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        ChannelSftp chSftp = null;
        try {
             chSftp = ChannelSftpSingleton.getInstance().getChannelSftp();
        } catch (JSchException e) {
            e.printStackTrace();
        }
        if (chSftp == null) {
            return null;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null || files.length <= 0) {
                return null;
            }
            for (File f : files) {
                String fp = f.getAbsolutePath();
                if (f.isDirectory()) {
                    String mkdir = dst + "/" + f.getName();
                    System.out.println(mkdir);
                    try {
                        chSftp.cd(mkdir);
                    } catch (Exception e) {
                        try {
                            System.out.println("创建文件夹");
                            System.out.println(mkdir);
                            chSftp.mkdir(mkdir);
                        } catch (SftpException e1) {
                            e1.printStackTrace();
                        }
                    }

                    upload(fp, mkdir);
                } else {
                    chSftp.put(fp, dst, ChannelSftp.OVERWRITE);
                }
            }
        } else {
            String fp = file.getAbsolutePath();
            chSftp.put(fp, dst, ChannelSftp.OVERWRITE);
        }
        return chSftp;
    }

}

