package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.pojo.UserKey;

public interface UserMapper {
    int deleteByPrimaryKey(UserKey key);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(UserKey key);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findByUserName(String userName);

    User getByPhone(String phone);

    User getByOpenId(String openId);

    int updatePwd(User user);



}