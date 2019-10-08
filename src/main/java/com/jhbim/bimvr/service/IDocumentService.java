package com.jhbim.bimvr.service;

import com.jhbim.bimvr.dao.entity.pojo.Document;

import java.util.List;

/**
 * @author shuihu
 * @create 2019-09-16 15:32
 */
public interface IDocumentService {
    List<Document> select();
    Document selectByPrimaryKey(Long id);
}
