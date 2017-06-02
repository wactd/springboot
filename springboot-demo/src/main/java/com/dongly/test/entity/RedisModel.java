package com.dongly.test.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tiger on 17-6-2.
 */
public class RedisModel implements Serializable{
    private static final long serialVersionUID = -7745349559130712256L;

    private String redisKey;
    private String username;
    private Date birthday;
    private Integer age;

    public RedisModel() {
    }

    public RedisModel(String redisKey, String username, Date birthday, Integer age) {
        this.redisKey = redisKey;
        this.username = username;
        this.birthday = birthday;
        this.age = age;
    }

    public String getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(String redisKey) {
        this.redisKey = redisKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "RedisModel{" +
                "redisKey='" + redisKey + '\'' +
                ", username='" + username + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                '}';
    }
}
