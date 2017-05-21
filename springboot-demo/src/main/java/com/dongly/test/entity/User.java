package com.dongly.test.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 * Created by tiger on 17-5-21.
 */

@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Integer userId;

    @NotBlank
    @Column(length = 30)
    @ColumnDefault(value = "''")
    private String username;

    @NotBlank
    @ColumnDefault(value = "0")
    private Integer age;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
