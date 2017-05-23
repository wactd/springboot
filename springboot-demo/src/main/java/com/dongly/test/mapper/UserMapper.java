package com.dongly.test.mapper;

import com.dongly.test.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by tiger on 17-5-23.
 */
public interface UserMapper {

    @Select(value = "select user_id userId, age, username from t_user where user_id = #{userId}")
    User selectById(@Param("userId") Integer userId);

    List<User> selectAll();
}
