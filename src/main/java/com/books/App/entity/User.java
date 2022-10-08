package com.books.App.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * @TableName 用户
 */
@TableName(value ="user")

@Data
public class User implements Serializable {
    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)

    private Integer id;

    /**
     * 姓名
     */
    @ExcelProperty("客户姓名")
    private String username;

    /**
     * 密码
     */
    @ExcelProperty("客户密码")
    private String password;

    /**
     * 昵称
     */
    @ExcelProperty("客户昵称")
    private String nickName;

    /**
     * 年龄
     */
    @ExcelProperty("客户年龄")
    private Integer age;

    /**
     * 性别
     */
    @ExcelProperty("客户性别")
    private String sex;

    /**
     * 地址
     */
    @ExcelProperty("客户地址")
    private String address;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}