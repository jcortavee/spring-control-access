package com.umg.accesscontrol.services;

import com.umg.accesscontrol.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User createUser(User user);
    User updateUser(Long userId, User user);
    void deleteUser(Long userId);
    boolean userExists(User user);
    User getUser(String username);
    User getUser(Long userId);
    List<User> getUsers();

}
