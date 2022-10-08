package com.books.App.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.books.App.entity.Books;
import com.books.App.service.BooksService;
import com.books.App.mapper.BooksMapper;
import org.springframework.stereotype.Service;

/**
* @author 28925
* @description 针对表【books(书籍)】的数据库操作Service实现
* @createDate 2022-09-22 02:03:20
*/
@Service
public class BooksServiceImpl extends ServiceImpl<BooksMapper, Books>
    implements BooksService{

}




