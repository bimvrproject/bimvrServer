package com.jhbim.bimvr.service.impl;

import com.jhbim.bimvr.dao.entity.pojo.Download;
import com.jhbim.bimvr.dao.mapper.DownloadMapper;
import com.jhbim.bimvr.service.IDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shuihu
 * @create 2019-09-17 14:05
 */
@Service
public class DownloadServiceImpl implements IDownloadService {


    @Autowired
    private DownloadMapper downloadMapper;

    @Override
    public List<Download> select() {
        return downloadMapper.select();
    }

    @Override
    public Boolean insert(Download download) {
        boolean flag=false;
        try{
            downloadMapper.insert(download);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public Download selectByPrimaryKey(Long id) {
        return downloadMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean deleteByPrimaryKey(Long id) {
        boolean flag=false;
        try{
            downloadMapper.deleteByPrimaryKey(id);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
