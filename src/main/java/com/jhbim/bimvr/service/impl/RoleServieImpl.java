package com.jhbim.bimvr.service.impl;

import com.jhbim.bimvr.dao.entity.pojo.Role;
import com.jhbim.bimvr.dao.mapper.RoleMapper;
import com.jhbim.bimvr.service.IRoleServie;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleServieImpl implements IRoleServie {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public Role selectByPrimaryKey(Long roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }
}
