package com.jhbim.bimvr.service;

import com.jhbim.bimvr.dao.entity.pojo.Role;

import java.util.List;

public interface IRoleServie {
    Role selectByPrimaryKey(Long roleId);
}
