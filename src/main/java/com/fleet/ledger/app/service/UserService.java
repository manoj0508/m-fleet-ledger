package com.fleet.ledger.app.service;

import com.fleet.ledger.app.dto.UserRequest;
import com.fleet.ledger.app.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createNewUser(UserRequest userRequest);

    UserResponse updateUser(Integer userId, UserRequest userRequest);

    UserResponse getUser(Integer userId);

    List<UserResponse> getUsers(String mobileNo);

    void deleteUser(Integer userId);


}
