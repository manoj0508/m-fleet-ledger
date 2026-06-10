package com.fleet.ledger.app.controller;

import com.fleet.ledger.app.dto.UserRequest;
import com.fleet.ledger.app.dto.UserResponse;
import com.fleet.ledger.app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Management", description = "APIs for creating and managing users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    @Operation(summary = "Create User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest userRequest) {
        UserResponse newUser = userService.createNewUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }


    @GetMapping(params = "userId")
    @Operation(summary = "get User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User fetched successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<UserResponse> getUser(@Valid Integer userId) {
        UserResponse user = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    @GetMapping(params = "mobileNo")
    @Operation(summary = "get Users by mobile no lookup")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User fetched successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<List<UserResponse>> getUsers(@Valid String mobileNo) {
        List<UserResponse> user = userService.getUsers(mobileNo);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
