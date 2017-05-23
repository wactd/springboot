package com.dongly.test.service.impl;

import com.dongly.test.entity.User;
import com.dongly.test.mapper.UserMapper;
import com.dongly.test.service.UserService;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tiger on 17-5-23.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectById(Integer userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public Object selectAll() {
        Page<User> page = PageHelper.startPage(1, 2);
        List<User> users = userMapper.selectAll();

//        PageInfo<Object> pageInfo = PageHelper.startPage(1, 10).doSelectPageInfo(new ISelect() {
//            @Override
//            public void doSelect() {
//                userMapper.selectAll();
//            }
//        });

        return users;
    }
}
