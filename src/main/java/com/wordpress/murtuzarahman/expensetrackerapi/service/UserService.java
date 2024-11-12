package com.wordpress.murtuzarahman.expensetrackerapi.service;

import com.wordpress.murtuzarahman.expensetrackerapi.entity.User;
import com.wordpress.murtuzarahman.expensetrackerapi.entity.UserModel;

public interface UserService {

    User createUser(UserModel user);

    User readUser();

    User updateUser(UserModel user);

    void deleteUser();

    User getLoggedInUser();
}
