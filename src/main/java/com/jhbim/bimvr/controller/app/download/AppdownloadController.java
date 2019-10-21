package com.jhbim.bimvr.controller.app.download;

import com.jhbim.bimvr.dao.entity.pojo.App;
import com.jhbim.bimvr.dao.entity.pojo.AppDownload;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.pub.Response;
import com.jhbim.bimvr.service.IAppDownloadService;
import com.jhbim.bimvr.service.IAppService;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author shuihu
 * @create 2019-10-21 11:31
 */
@RestController
@RequestMapping("/${version}/app")
public class AppdownloadController {

    @Autowired
    private IAppService iAppService;

    @Autowired
    private IAppDownloadService iAppDownloadService;

    /**
     * 添加下载记录
     */
    @GetMapping("/appAdd")
    public Response appDownload( Long projectId, Integer type) throws ParseException {
        App app = iAppService.selectByProjectType( projectId,type);
        AppDownload appDownload = new AppDownload();

        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        Date date =  dateFormat.parse(dateFormat.format(calendar.getTime()));
        appDownload.setDownloadTime(date);
        User user= ShiroUtil.getUser();
        appDownload.setUserId(user.getPhone());
        appDownload.setProjectId(projectId);
        appDownload.setType(type);
        appDownload.setScheme(app.getScheme());
        appDownload.setName(app.getName());
        return Response.success().setData(iAppDownloadService.insert(appDownload));
    }


    /**
     *查询用户下载的app
     *
     */
    @GetMapping("/selectApp")
    public Response selectApp(){
        User user = ShiroUtil.getUser();
        List<AppDownload> appDownloads = iAppDownloadService.selectByUserId(user.getPhone());
        return Response.success().setData(appDownloads);
    }
}
