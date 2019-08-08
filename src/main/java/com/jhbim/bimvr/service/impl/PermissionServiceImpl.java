package com.jhbim.bimvr.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jhbim.bimvr.dao.entity.pojo.Permission;
import com.jhbim.bimvr.dao.mapper.PermissionMapper;
import com.jhbim.bimvr.service.IPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Resource
    PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermission(String permissionIdArr) {
        List<Permission> list = new ArrayList<Permission>();
        for (Object permissionId: JSONObject.parseArray(permissionIdArr)){
            Permission permission = permissionMapper.selectByPrimaryKey(Long.parseLong(permissionId.toString()));
            list.add(permission);
        }
        return list;
    }
}
