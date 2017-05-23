package com.dongly.test.service;

import com.dongly.test.entity.User;
import com.github.pagehelper.Page;

/**
 * Created by tiger on 17-5-23.
 */
public interface UserService {

    User selectById(Integer userId);

    Object selectAll();

}
