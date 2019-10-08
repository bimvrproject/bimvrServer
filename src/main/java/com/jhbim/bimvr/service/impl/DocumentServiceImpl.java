package com.jhbim.bimvr.service.impl;

import com.jhbim.bimvr.dao.entity.pojo.Document;
import com.jhbim.bimvr.dao.mapper.DocumentMapper;
import com.jhbim.bimvr.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shuihu
 * @create 2019-09-16 15:33
 */
@Service
public class DocumentServiceImpl implements IDocumentService {
    @Autowired
    private DocumentMapper documentMapper;

    @Override
    public List<Document> select() {
        List<Document> list = documentMapper.select();
        return list;
    }

    @Override
    public Document selectByPrimaryKey(Long id) {
        return documentMapper.selectByPrimaryKey(id);
    }
}

