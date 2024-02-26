package com.gsj.springbootmp.controller;

import com.gsj.springbootmp.entity.User;
import com.gsj.springbootmp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("list")
    public List<User> list(){
        return userService.list();
    }

    @RequestMapping("getUser/{id}")
    public User getUser(@PathVariable String id){
        return userService.getById(id);
    }

    /**
     * 插入学生信息
     * @param User
     */
    @RequestMapping("/insert")
    public void insertInfo(User User){

        User info=new User();
        info.setName(User.getName());
        info.setAge(User.getAge());
        userService.save(info);
    }

    /**
     * 根据id更新学生表信息
     * @param User
     */
    @RequestMapping("/update")
    public void updateById(User User){

        User info=new User();
        info.setId(User.getId());
        info.setName(User.getName());
        info.setAge(User.getAge());
        userService.updateById(info);
    }

    /**
     * 根据id删除学生信息
     * @param id
     */
    @RequestMapping("/delete")
    public void deleteById(String id){
        userService.removeById(id);
    }
}
