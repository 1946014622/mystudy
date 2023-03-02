package com.atguigu.spring_security.domain.mapper;

import com.atguigu.spring_security.domain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author admin
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2023-03-01 16:33:38
* @Entity com.atguigu.spring_security.domain.entity.SysUser
*/
@Mapper
public interface SysUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser queryByUserName(@Param("userName") String username);
}
