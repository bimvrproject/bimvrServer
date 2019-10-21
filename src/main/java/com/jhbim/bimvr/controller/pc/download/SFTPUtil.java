package com.jhbim.bimvr.controller.pc.download;

import com.jcraft.jsch.*;
import com.jhbim.bimvr.controller.pc.alipay.AlipayPagePayController;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author shuihu
 * @create 2019-10-17 9:46
 */
public class SFTPUtil {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SFTPUtil.class);
    private ChannelSftp sftp;

    private Session session;
    /**
     * SFTP 登录用户名
     */
    private String username;
    /**
     * SFTP 登录密码
     */
    private String password;
    /**
     * 私钥
     */
    private String privateKey;
    /**
     * SFTP 服务器地址IP地址
     */
    private String host;
    /**
     * SFTP 端口
     */
    private int port;


    /**
     * 构造基于密码认证的sftp对象
     */
    public SFTPUtil(String username, String password, String host, int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    /**
     * 构造基于秘钥认证的sftp对象
     */
    public SFTPUtil(String username, String host, int port, String privateKey) {
        this.username = username;
        this.host = host;
        this.port = port;
        this.privateKey = privateKey;
    }

    public SFTPUtil() {
    }

    /**
     * 连接sftp服务器
     */
    public void login() {
        try {
            JSch jsch = new JSch();
            if (privateKey != null) {
                jsch.addIdentity(privateKey);// 设置私钥
            }

            session = jsch.getSession(username, host, port);

            if (password != null) {
                session.setPassword(password);
            }
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");

            session.setConfig(config);
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();

            sftp = (ChannelSftp) channel;
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接 server
     */
    public void logout() {
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }

    public boolean isExist(String serverPath, String folderName) {
        try {
            sftp.cd(serverPath);
            // 判断子目录文件夹是否存在，不存在即创建
            SftpATTRS attrs = null;
            attrs = sftp.stat(folderName);
            if (attrs != null) {
                return true;
            }

        } catch (Exception e) {
            e.getMessage();
            return false;
        }
        return false;
    }

    public boolean isConnect() {
        if (null != session) {
            return session.isConnected();
        }
        return false;
    }

    public InputStream download(String directory) throws SftpException {
        return sftp.get(directory);
    }

}
