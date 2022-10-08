package com.books.App.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.books.App.entity.News;
import com.books.App.service.NewsService;
import com.books.App.mapper.NewsMapper;
import org.springframework.stereotype.Service;

/**
* @author 28925
* @description 针对表【news(新闻)】的数据库操作Service实现
* @createDate 2022-10-08 06:37:21
*/
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News>
    implements NewsService{

}




