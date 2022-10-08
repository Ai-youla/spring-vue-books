package com.books.App.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Data;



/**
 * 书籍
 * @TableName books
 */
@TableName(value ="books")
@Data
//@AllArgsConstructor
public class Books implements Serializable {
    /**
     * 书籍ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 书籍名
     */
    @ExcelProperty("书名")
    private String booksname;

    /**
     * 价格
     */
    @ExcelProperty("价格")
    private BigDecimal price;

    /**
     * 作者
     */
    @ExcelProperty("作者")
    private String author;

    /**
     * 出版日期
     */
//    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") //在时间属性上面加上该注解
    @ExcelProperty(value = "出版时间")
    private Date createTime;

    /**
     * 封面
     */
    @ExcelProperty(value = "封面")
    private String cover;

    /**
     * 用户ID
     */
    private Integer userid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}