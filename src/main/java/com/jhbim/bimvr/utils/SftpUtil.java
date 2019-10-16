package com.jhbim.bimvr.utils;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;

import java.io.File;

public class SftpUtil {
     private SftpUtil() {
    }

    public static void uploadFilesToServer(String srcPath, String dst, SftpProgressMonitor monitor) throws Exception {
        ChannelSftp sftp = upload(srcPath, dst, monitor);
        if (sftp != null) {
            sftp.quit();
            sftp.disconnect();
            System.out.println(" SFTP disconnect successfully!");
        }
        ChannelSftpSingleton.getInstance().closeChannel();
    }


    private static ChannelSftp upload(String path, String dst, SftpProgressMonitor monitor) throws SftpException {
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
                System.out.println(fp);
                if (f.isDirectory()) {
                    String mkdir = dst + "/" + f.getName();
                    try {
                        chSftp.cd(mkdir);
                    } catch (Exception e) {
                        chSftp.mkdir(mkdir);
                    }
                    upload(fp, mkdir, monitor);
                } else {
                    chSftp.put(fp, dst, monitor, ChannelSftp.OVERWRITE);
                }
            }
        } else {
            String fp = file.getAbsolutePath();
            chSftp.put(fp, dst, monitor, ChannelSftp.OVERWRITE);
        }
        return chSftp;
    }

}

