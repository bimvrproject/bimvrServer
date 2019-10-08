package com.jhbim.bimvr.controller.pc.download;

import com.jcraft.jsch.*;
import com.jhbim.bimvr.stauts.unti.UUIDUtils;

import java.io.File;
import java.util.Vector;

/**
 * @author shuihu
 * @create 2019-08-29 11:50
 */
public class DownloadRecursiveFolderFromSFTP {

    static ChannelSftp channelSftp = null;
    static Session session = null;
    static Channel channel = null;
    static String PATHSEPARATOR = "/";

    /**
     * @param args
     */
    public static void main(String[] args) {
        String SFTPHOST = "192.168.37.131";
        int SFTPPORT = 22;
        String SFTPUSER = "root";
        String SFTPPASS = "123456";
        String SFTPWORKINGDIR = "/home/wsh/model/WindowsNoEditor";
//        String LOCALDIRECTORY = "D:\\qq";
        UUIDUtils uuidUtils = new UUIDUtils();
        String str = uuidUtils.findkeyUtil();
        String path ="D:\\model\\WindowsNoEditor"; //所创建文件目录
        File f = new File(path);
        if(!f.exists()){
            f.mkdirs(); //创建目录
        }
        String  LOCALDIRECTORY=path;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
            session.setPassword(SFTPPASS);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect(); // Create SFTP Session
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
            channelSftp.cd(SFTPWORKINGDIR);

            recursiveFolderDownload(SFTPWORKINGDIR, LOCALDIRECTORY);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (channelSftp != null)
                channelSftp.disconnect();
            if (channel != null)
                channel.disconnect();
            if (session != null)
                session.disconnect();

        }

    }

    /**
     * This method is called recursively to download the folder content from SFTP server
     *
     * @param sourcePath
     * @param destinationPath
     * @throws SftpException
     */
    @SuppressWarnings("unchecked")
    private static void recursiveFolderDownload(String sourcePath, String destinationPath) throws SftpException {
        Vector<ChannelSftp.LsEntry> fileAndFolderList = channelSftp.ls(sourcePath); // Let list of folder content

        //Iterate through list of folder content
        for (ChannelSftp.LsEntry item : fileAndFolderList) {

            if (!item.getAttrs().isDir()) { // Check if it is a file (not a directory).
                if (!(new File(destinationPath + PATHSEPARATOR + item.getFilename())).exists()
                        || (item.getAttrs().getMTime() > Long
                        .valueOf(new File(destinationPath + PATHSEPARATOR + item.getFilename()).lastModified()
                                / (long) 1000)
                        .intValue())) { // Download only if changed later.

                    new File(destinationPath + PATHSEPARATOR + item.getFilename());
                    channelSftp.get(sourcePath + PATHSEPARATOR + item.getFilename(),
                            destinationPath + PATHSEPARATOR + item.getFilename()); // Download file from source (source filename, destination filename).
                }
            } else if (!(".".equals(item.getFilename()) || "..".equals(item.getFilename()))) {
                new File(destinationPath + PATHSEPARATOR + item.getFilename()).mkdirs(); // Empty folder copy.
                recursiveFolderDownload(sourcePath + PATHSEPARATOR + item.getFilename(),
                        destinationPath + PATHSEPARATOR + item.getFilename()); // Enter found folder on server to read its contents and create locally.
            }
        }
    }

}
