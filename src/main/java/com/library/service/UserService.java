package com.library.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.library.entity.User;

public interface UserService extends JpaRepository<User, String> {

}
