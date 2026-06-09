package com.fleet.ledger.app.service;

import com.fleet.ledger.app.dto.UserRequest;

public interface UserService {

    public void createNewUser(UserRequest userRequest);

    public void updateUser(Integer userId, UserRequest userRequest);


}
