package com.jhbim.bimvr.service;

import com.jhbim.bimvr.dao.entity.pojo.AppDownload;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shuihu
 * @create 2019-10-21 12:09
 */
public interface IAppDownloadService  {
    //添加下载记录
    Boolean insert(AppDownload appDownload);

    //查询用户下载
    List<AppDownload> selectByUserId(String userId);

}
