package com.jhbim.bimvr.service;

import com.jhbim.bimvr.dao.entity.pojo.Document;
import com.jhbim.bimvr.dao.entity.pojo.Download;

import java.util.List;

/**
 * @author shuihu
 * @create 2019-09-17 14:04
 */
public interface IDownloadService {
    List<Download> select();

    Boolean insert(Download download);

    Download selectByPrimaryKey(Long id);

    Boolean deleteByPrimaryKey(Long id);

}
