package com.books.App.service.impl;

//import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.books.App.entity.User;
import com.books.App.service.UserService;
import com.books.App.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 28925
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-09-16 18:32:01
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




