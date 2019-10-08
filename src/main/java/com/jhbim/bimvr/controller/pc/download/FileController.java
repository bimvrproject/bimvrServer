package com.jhbim.bimvr.controller.pc.download;

import com.jcraft.jsch.*;
import com.jhbim.bimvr.dao.entity.pojo.Document;
import com.jhbim.bimvr.dao.entity.pojo.Download;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.pub.Response;
import com.jhbim.bimvr.service.IDocumentService;
import com.jhbim.bimvr.service.IDownloadService;
import com.jhbim.bimvr.stauts.unti.UUIDUtils;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;



/**
 * @author shuihu
 * @create 2019-08-29 14:45
 */
@RequestMapping("/${version}/file")
@Controller
public class FileController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FileController.class);
    static ChannelSftp channelSftp = null;
    static Session session = null;
    static Channel channel = null;
    static String PATHSEPARATOR = "/";

    @Autowired
    private IDocumentService iDocumentService;

    @Autowired
    private IDownloadService iDownloadService;

    @PostMapping("/test")
    @ResponseBody
    public Object test( Long modelid) throws ParseException {
        Document document = iDocumentService.selectByPrimaryKey(modelid);
        Download download = new Download();
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        Date date =  dateFormat.parse(dateFormat.format(calendar.getTime()));
        download.setCreateTime(date);
        User user= ShiroUtil.getUser();
        download.setUserId(user.getPhone());
        download.setFileId(modelid);
        Boolean b=iDownloadService.insert(download);
        return b;
    }

    /**
     * 发起下载请求
     * @param args
     */
    @PostMapping("/download")
    @ResponseBody
    public Response demand( Long modelid) throws ParseException {
        String SFTPHOST = "192.168.37.131";
        int SFTPPORT = 22;
        String SFTPUSER = "root";
        String SFTPPASS = "123456";
        String SFTPWORKINGDIR;
//        String LOCALDIRECTORY = "D:\\qq";

        Document document = iDocumentService.selectByPrimaryKey(modelid);
        SFTPWORKINGDIR=document.getVisitUrl();
        String  str = document.getLocalUrl();


        String path =str.substring(0,str.lastIndexOf("/")); //所创建文件目录
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
        /**
         * 插入数据
         */
        Download download = new Download();
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        Date date =  dateFormat.parse(dateFormat.format(calendar.getTime()));
        download.setCreateTime(date);
        User user= ShiroUtil.getUser();
        download.setUserId(user.getPhone());
        download.setFileId(modelid);
        download.setName(document.getName());
        iDownloadService.insert(download);

        return Response.success().setData("下载成功");
    }




    /**
     * 调用程序
     * @return
     */
    @PostMapping("/openexe")
    @ResponseBody
    public Response openexe(Long id){
//        Download download = iDownloadService.selectByPrimaryKey(id);
//        Document document = new Document();
        Document url = iDocumentService.selectByPrimaryKey(id);
        if (url != null){
            String str = url.getLocalUrl();
        }else {
            return Response.fail().setMsg("本地没有该模型请下载");
        }


        Runtime rt = Runtime.getRuntime();
        Process p = null;
        String fileLac = "";
        try {
            fileLac = url.getLocalUrl();// 要调用的程序路径
            p = rt.exec(fileLac);
        } catch (Exception e) {
            logger.info("启动异常：",e);
        }
        return Response.success().setMsg("打开成功");

    }

    /**
     * 查询所有模型程序
     * @param id
     * @return
     */

    @GetMapping("/getAllList")
    @ResponseBody
    public List<Document> getAllList(){
            return iDocumentService.select();
    }


    /**
     * 删除模型exe
     * @param id
     * @return
     */
    @PostMapping("/remove")
    @ResponseBody
    public Response remove(Long id){

        Download download = iDownloadService.selectByPrimaryKey(id);
        Document document = new Document();
        Document url = iDocumentService.selectByPrimaryKey(download.getFileId());
        String str = url.getLocalUrl();

        File file = new File(str.substring(0,str.lastIndexOf("/")));
        delAllFile(file);

        return Response.success().setData(iDownloadService.deleteByPrimaryKey(id));
    }


    /**
     * 查询我的下载
     * @return
     */
    @GetMapping("/getList")
    @ResponseBody
    public List<Download> getList(){
        return iDownloadService.select();
    }


    /**
     * 批量下载文件
     * @param SFTPWORKINGDIR：远程下载目录
     * @param LOCALDIRECTORY：本地保存目录
     * @return
     */
    private static void recursiveFolderDownload(String sourcePath, String destinationPath) throws SftpException {
        Vector<ChannelSftp.LsEntry> fileAndFolderList = channelSftp.ls(sourcePath);


        for (ChannelSftp.LsEntry item : fileAndFolderList) {

            if (!item.getAttrs().isDir()) {
                if (!(new File(destinationPath + PATHSEPARATOR + item.getFilename())).exists()
                        || (item.getAttrs().getMTime() > Long
                        .valueOf(new File(destinationPath + PATHSEPARATOR + item.getFilename()).lastModified()
                                / (long) 1000)
                        .intValue())) { // Download only if changed later.

                    new File(destinationPath + PATHSEPARATOR + item.getFilename());
                    channelSftp.get(sourcePath + PATHSEPARATOR + item.getFilename(),
                            destinationPath + PATHSEPARATOR + item.getFilename());
                }
            } else if (!(".".equals(item.getFilename()) || "..".equals(item.getFilename()))) {
                new File(destinationPath + PATHSEPARATOR + item.getFilename()).mkdirs();
                recursiveFolderDownload(sourcePath + PATHSEPARATOR + item.getFilename(),
                        destinationPath + PATHSEPARATOR + item.getFilename());
            }
        }
    }
    public  void delAllFile(File directory){
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
