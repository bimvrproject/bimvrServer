package com.jhbim.bimvr.service.impl;

import com.jhbim.bimvr.dao.entity.pojo.AppDownload;
import com.jhbim.bimvr.dao.mapper.AppDownloadMapper;
import com.jhbim.bimvr.service.IAppDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shuihu
 * @create 2019-10-21 13:58
 */
@Service
public class AppDownloadImpl implements IAppDownloadService {
    @Autowired
    private AppDownloadMapper appDownloadMapper;

    @Override
    public Boolean insert(AppDownload appDownload) {
        return appDownloadMapper.insert(appDownload);
    }

    @Override
    public List<AppDownload> selectByUserId(String userId) {
        return appDownloadMapper.selectByUserId(userId);
    }
}
