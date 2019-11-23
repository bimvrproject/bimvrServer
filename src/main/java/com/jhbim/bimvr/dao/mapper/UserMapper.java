package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(String phone);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String phone);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findByUserName(String phone);

    User getByPhone(String phone);

    User getByOpenId(String openId);

    int updatePwd(User user);
    // 修改昵称
    int updatausername( @Param("userName") String userName,@Param("phone") String phone);
    // 修改所属公司
    int updatacompanyname(@Param("companyname") String companyname, @Param("phone") String phone);
    // 修改职位
    int updataposition(@Param("position") String position,@Param("phone") String phone);
    //修改备注
    int updataremarks(@Param("remarks") String remarks,@Param("phone") String phone);



}