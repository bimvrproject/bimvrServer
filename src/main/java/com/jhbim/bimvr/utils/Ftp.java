package com.jhbim.bimvr.utils;

import com.jcraft.jsch.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.*;

public class Ftp {
    //打印log日志
    private static final Log logger = LogFactory.getLog(Ftp.class);

    private static Date last_push_date = null;

    private Session sshSession;

    private ChannelSftp channel;

    private static ThreadLocal<Ftp> sftpLocal = new ThreadLocal<Ftp>();

    private static final String host="192.168.6.145";

    private static final int port=26;

    private static final String username="Administrator";

    private static final String password="Jhkj991102";

    private  Ftp() throws Exception {
        JSch jsch = new JSch();
        jsch.getSession(username, host, port);
        //根据用户名，密码，端口号获取session
        sshSession = jsch.getSession(username, host, port);
        sshSession.setPassword(password);
        //修改服务器/etc/ssh/sshd_config 中 GSSAPIAuthentication的值yes为no，解决用户不能远程登录
        sshSession.setConfig("userauth.gssapi-with-mic", "no");
        //为session对象设置properties,第一次访问服务器时不用输入yes
        sshSession.setConfig("StrictHostKeyChecking", "no");
        sshSession.connect();
        //获取sftp通道
        channel = (ChannelSftp)sshSession.openChannel("sftp");
        channel.connect();
        logger.info("连接ftp成功!" + sshSession);
    }

    /**
     * 是否已连接
     *
     * @return
     */
    private boolean isConnected() {
        return null != channel && channel.isConnected();
    }

    /**
     * 获取本地线程存储的sftp客户端
     *
     * @return
     * @throws Exception
     */
    public static Ftp getSftpUtil() throws Exception {
        //获取本地线程
        Ftp sftpUtil = sftpLocal.get();
        if (null == sftpUtil || !sftpUtil.isConnected()) {
            //将新连接防止本地线程，实现并发处理
            sftpLocal.set(new Ftp());
        }
        return sftpLocal.get();
    }

    /**
     * 释放本地线程存储的sftp客户端
     */
    public static void release() {
        if (null != sftpLocal.get()) {
            sftpLocal.get().closeChannel();
            logger.info("关闭连接" + sftpLocal.get().sshSession);
            sftpLocal.set(null);
        }
    }

    /**
     * 关闭通道
     *
     * @throws Exception
     */
    public void closeChannel() {
        if (null != channel) {
            try {
                channel.disconnect();
            } catch (Exception e) {
                logger.error("关闭SFTP通道发生异常:", e);
            }
        }
        if (null != sshSession) {
            try {
                sshSession.disconnect();
            } catch (Exception e) {
                logger.error("SFTP关闭 session异常:", e);
            }
        }
    }

    /**
     * @param directory  上传ftp的目录
     * @param uploadFile 本地文件目录
     *
     */
    public void upload(String directory, String uploadFile) throws Exception {
        try {
            // 执行列表展示ls 命令
            channel.ls(directory);
            //执行盘符切换cd 命令
            channel.cd(directory);
            List<File> files = getFiles(uploadFile, new ArrayList<File>());
            for (int i = 0; i < files.size(); i++) {
                File file = files.get(i);
                InputStream input = new BufferedInputStream(new FileInputStream(file));
                channel.put(input, file.getName(),ChannelSftp.OVERWRITE);
                try {
                    if (input != null) input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(file.getName() + "关闭文件时.....异常!" + e.getMessage());
                }
                if (file.exists()) {
                    boolean b = file.delete();
                    logger.info(file.getName() + "文件上传完毕!删除标识:" + b);
                }
                if(file.isDirectory()){
                    file.delete();
                }
            }
        }catch (Exception e) {
            channel.mkdir(directory);
        }
    }
    //获取文件
    public   List<File> getFiles(String realpath, List<File> files) {
        File realFile = new File(realpath);
        if (realFile.isDirectory()) {
            File[] subfiles = realFile.listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    if (null == last_push_date ) {
                        return true;
                    } else {
                        long modifyDate = file.lastModified();
                        return modifyDate > last_push_date.getTime();
                    }
                }
            });
            for (File file : subfiles) {
                if (file.isDirectory()) {

                    getFiles(file.getAbsolutePath(), files);
                } else {
                    files.add(file);
                }
                if (null == last_push_date) {
                    last_push_date = new Date(file.lastModified());
                } else {
                    long modifyDate = file.lastModified();
                    if (modifyDate > last_push_date.getTime()) {
                        last_push_date = new Date(modifyDate);
                    }
                }
            }
        }
        return files;
    }


    /**
     * 判断目录是否存在
     * @param directory 目录
     * @return
     */
    public  boolean isDirExist(String directory) {
        try {
            Vector<?> vector = this.channel.ls(directory);
            if (null == vector) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 删除stfp文件
     * @param directory 目录
     */
    public  void deleteSFTP(String directory) {
        try {
            if (isDirExist(directory)) {
                Vector<ChannelSftp.LsEntry> vector = this.channel.ls(directory);
                if (vector.size() == 1) { // 文件，直接删除
                    this.channel.rm(directory);
                } else if (vector.size() == 2) { // 空文件夹，直接删除
                    this.channel.rmdir(directory);
                } else {
                    String fileName = "";
                    // 删除文件夹下所有文件
                    for (ChannelSftp.LsEntry en : vector) {
                        fileName = en.getFilename();
                        if (".".equals(fileName) || "..".equals(fileName)) {
                            continue;
                        } else {
                            deleteSFTP(directory + "/" + fileName);
                        }
                    }
                    this.channel.rmdir(directory);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  boolean GenerateImage(String imgData, String imgFilePath) throws IOException { // 对字节数组字符串进行Base64解码并生成图片
        if (imgData == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        OutputStream out = null;
        try {
            out = new FileOutputStream(imgFilePath);
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgData);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            out.write(b);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
            return true;
        }
    }

}
