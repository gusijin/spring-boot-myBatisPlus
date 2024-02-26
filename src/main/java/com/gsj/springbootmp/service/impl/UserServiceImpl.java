package com.gsj.springbootmp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gsj.springbootmp.entity.User;
import com.gsj.springbootmp.mapper.UserMapper;
import com.gsj.springbootmp.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
