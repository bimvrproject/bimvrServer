package com.jhbim.bimvr.service;

import com.jhbim.bimvr.dao.entity.pojo.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> getPermission(String permissionId);
}
