package com.books.App.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.books.App.common.Result;
import com.books.App.entity.Books;
import com.books.App.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BooksService booksService;


    //添加书籍
    @PostMapping ("booksAdd")
    public Result Userinsret(@RequestBody Books books){
        System.out.println(books.getCreateTime());
        System.out.println(books.getBooksname());
        booksService.getBaseMapper().insert(books);
        return Result.success();
    }

    //书籍列表
    @RequestMapping("findPage")
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Books> wrapper= Wrappers.<Books>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
             wrapper.like(Books::getBooksname,search)
                    .or()
                    .like(Books::getAuthor,search);
        }
        Page<Books> page=booksService.getBaseMapper().selectPage(new Page<>(pageNum,pageSize),wrapper);

        return Result.success().put("data",page);
    }

    //更新书籍
    @RequestMapping("/booksUpdate")
    public Result userUpdata(@RequestBody Books books){
        System.out.println(books.getCover());
        int tt=booksService.getBaseMapper().updateById(books);
        Books books1=booksService.getBaseMapper().selectById(books.getId());

        return Result.success().put("data",books1);
    }

    //删除书籍
    @RequestMapping("/booksDelete")
    public Result userDelete(@RequestParam Integer id){
        booksService.getBaseMapper().deleteById(id);
        return Result.success();
    }
}
