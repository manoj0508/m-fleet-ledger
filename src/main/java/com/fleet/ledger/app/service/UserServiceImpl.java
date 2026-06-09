package com.fleet.ledger.app.service;

import com.fleet.ledger.app.dto.UserRequest;
import com.fleet.ledger.app.entity.User;
import com.fleet.ledger.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createNewUser(UserRequest userRequest) {

        User user = new User();
        user.setUserType(userRequest.getUserType());
        user.setFirstName(userRequest.getFName());
        user.setLastName(userRequest.getLName());
        user.setEmailId(userRequest.getEmail());
        user.setMobileNo(userRequest.getMobile());



        if (null != userRequest){
            userRepository.save(user);
        }

    }

    @Override
    public void updateUser(Integer userId, UserRequest userRequest) {

    }
}
