package com.books.App.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.books.App.common.Result;
import com.books.App.entity.News;
import com.books.App.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/*
* 新闻
* */
@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    //添加新闻
    @PostMapping("newsAdd")
    public Result newsAdd(@RequestBody News news){

        newsService.getBaseMapper().insert(news);
        return Result.success();
    }

    //新闻列表
    @RequestMapping("findPage")
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<News> wrapper= Wrappers.<News>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(News::getContent,search)
                    .or()
                    .like(News::getTitle,search);
        }
        Page<News> page=newsService.getBaseMapper().selectPage(new Page<>(pageNum,pageSize),wrapper);

        return Result.success().put("data",page);
    }

    //更新新闻
    @RequestMapping("/newsUpdate")
    public Result newsUpdate(@RequestBody News news){

        int tt=newsService.getBaseMapper().updateById(news);
        News news1=newsService.getBaseMapper().selectById(news.getId());

        return Result.success().put("data",news1);
    }

    //删除新闻
    @RequestMapping("/newsDelete")
    public Result newsDelete(@RequestParam Integer id){
        newsService.getBaseMapper().deleteById(id);
        return Result.success();
    }
}
