package com.cookie.service;

import com.cookie.integration.User;

/**
 * Created by FochMaiden
 */
public interface UserService {
    public User findByEmail( String email);
    public void saveUser(User user);
}
