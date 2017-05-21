package com.dongly.test.repository;

import com.dongly.test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tiger on 17-5-22.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
