package com.jhbim.bimvr.timer;

import com.jhbim.bimvr.utils.FileUploadUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时器
 * 客户发布截图图片时后端进行处理转变成一个zip文件，定时删除这个zip文件
 */
@Component
public class ScheduledTasks {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    //每次凌晨12删除
    @Scheduled(cron = "0 0 0 * * ?")
//    @Scheduled(fixedRate = 15000)
    public void reportCurrentTime() {
        System.out.println("现在时间：" +dateFormat.format(new Date()));
        String address="C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\ROOT\\Zip\\";
        FileUploadUtils.deleteDir(address);
    }
}
