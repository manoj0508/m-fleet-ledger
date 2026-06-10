package com.fleet.ledger.app.service;

import com.fleet.ledger.app.dto.UserRequest;
import com.fleet.ledger.app.dto.UserResponse;
import com.fleet.ledger.app.entity.User;
import com.fleet.ledger.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserResponse createNewUser(UserRequest userRequest) {
        UserResponse userResponse = new UserResponse();
        User user = new User();
        user.setUserType(userRequest.getUserType());
        user.setFirstName(userRequest.getFName());
        user.setLastName(userRequest.getLName());
        user.setEmailId(userRequest.getEmail());
        user.setMobileNo(userRequest.getMobile());


        if (null != userRequest) {
            User savedUser = userRepository.save(user);

            userResponse.setUserId(savedUser.getId());
            userResponse.setFName(savedUser.getFirstName());
            userResponse.setMobileNo(savedUser.getMobileNo());
        }

        return userResponse;
    }

    @Override
    public UserResponse updateUser(Integer userId, UserRequest userRequest) {

        UserResponse userResponse = new UserResponse();

        if (userRequest != null && userId != null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            user.setUserType(userRequest.getUserType());
            user.setFirstName(userRequest.getFName());
            user.setLastName(userRequest.getLName());
            user.setEmailId(userRequest.getEmail());
            user.setMobileNo(userRequest.getMobile());

            User updatedUser = userRepository.save(user);

            userResponse.setUserId(updatedUser.getId());
            userResponse.setFName(updatedUser.getFirstName());
            userResponse.setMobileNo(updatedUser.getMobileNo());
        }
        return userResponse;
    }

    @Override
    public UserResponse getUser(Integer userId) {
        UserResponse userResponse = new UserResponse();
        Optional<User> userById = userRepository.findById(userId);
        if (userById.isPresent()) {
            User user = userById.get();
            userResponse.setUserId(user.getId());
            userResponse.setFName(user.getFirstName());
            userResponse.setMobileNo(user.getMobileNo());
        }

        return userResponse;
    }

    @Override
    public List<UserResponse> getUsers(String mobileNo) {
        List<UserResponse> userResponseList = new ArrayList<>();
        UserResponse userResponse = null;

        List<User> userList = userRepository.findByMobileNo(mobileNo);
        if (null != userList) {

            for (User user : userList) {
                userResponse = new UserResponse();
                userResponse.setUserId(user.getId());
                userResponse.setFName(user.getFirstName());
                userResponse.setMobileNo(user.getMobileNo());

                userResponseList.add(userResponse);
            }

        }

        return userResponseList;
    }


}
