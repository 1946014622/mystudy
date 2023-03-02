package com.example.springboot_mysql.mapper;

import com.example.springboot_mysql.domain.TUser;
import org.apache.ibatis.annotations.Mapper;

/**
* @author admin
* @description 针对表【t_user(用户表)】的数据库操作Mapper
* @createDate 2023-02-07 17:30:18
* @Entity com.example.springboot_mysql.domain.TUser
*/
@Mapper
public interface TUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

}
