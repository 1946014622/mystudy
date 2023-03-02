package com.example.springboot_mysql.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;
import org.jboss.logging.Field;

/**
 * 用户表
 * @TableName t_user
 */
@Data
@ToString
public class TUser implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 电话
     */
    private String phone;

    /**
     * 部门id
     */
    @TableField("DEPT_ID")
    private Integer deptId;

    private static final long serialVersionUID = 1L;
}