package com.books.App.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.books.App.common.Result;
import com.books.App.entity.User;
import com.books.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("userAdd")
    public Result Userinsret(@RequestBody User user){

        int i=0;
        if (user.getPassword()==null){
            user.setPassword("123456");
            if (user.getUsername()!=null){
                i=userService.getBaseMapper().insert(user);
            }
        }
        return Result.success();
    }

    @RequestMapping("findPage")
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<User> wrapper= Wrappers.<User>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
             wrapper.like(User::getNickName,search)
                    .or()
                    .like(User::getAddress,search);
        }
        Page<User> page=userService.getBaseMapper().selectPage(new Page<>(pageNum,pageSize),wrapper);

//        为null时查不出
//        Page<User> page=userService.getBaseMapper().selectPage(new Page<>(pageNum,pageSize),
//                Wrappers.<User>lambdaQuery()
//                        .like(User::getNickName,search)
//                        .or()
//                        .like(User::getAddress,search));
        return Result.success().put("data",page);
    }

    @RequestMapping("/userUpata")
    public Result userUpdata(@RequestBody User user){
        int tt=userService.getBaseMapper().updateById(user);
        User user1=userService.getBaseMapper().selectById(user.getId());

        return Result.success().put("data",user1);
    }
    @RequestMapping("/userDelete")
    public Result userDelete(@RequestParam Integer id){
        userService.getBaseMapper().deleteById(id);
        return Result.success();
    }
//    登录
    @RequestMapping("/userLogin11")
    public Result userLogin(@RequestParam String username,@RequestParam String password){
//        Map<String, Object> map=new HashMap<>();
//        map.put("username",username);
//        map.put("password",password);
//        List<User> yy= userService.getBaseMapper().selectByMap(map);
//        yy.forEach(System.out::println);
//        System.out.println(user.getUsername());
//        User user=new User();
//        user.setUsername("琴");
//        user.setPassword("123456");
        System.out.println(username);
       User user1=userService.getBaseMapper().selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUsername,username)
                .eq(User::getPassword,password));
        System.out.println(user1);
        if(user1==null){
            return Result.error("账号或密码不正确");
        }
        return Result.success().put("data",user1);
    }
    //    登录
    @PostMapping("/userLogin")
    public Result userLogin1(@RequestBody User user){
//        Map<String, Object> map=new HashMap<>();
//        map.put("username",username);
//        map.put("password",password);
//        List<User> yy= userService.getBaseMapper().selectByMap(map);
//        yy.forEach(System.out::println);
//        System.out.println(user.getUsername());
//        User user=new User();
//        user.setUsername("琴");
//        user.setPassword("123456");
        System.out.println(user.getUsername());
        User user1=userService.getBaseMapper().selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUsername,user.getUsername())
                .eq(User::getPassword,user.getPassword()));
        System.out.println(user1);
        if(user1==null){
            return Result.error("账号或密码不正确");
        }
        return Result.success().put("data",user1);
    }
    @RequestMapping("/Register")
    public Result userRegister(@RequestBody  User user){
        if(userService.getBaseMapper().selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUsername,user.getUsername())) !=null) {
            return Result.error("用户已存在");
        }
        userService.getBaseMapper().insert(user);
        return Result.success();
    }
}
