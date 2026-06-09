package com.fleet.ledger.app.controller;

import com.fleet.ledger.app.dto.UserRequest;
import com.fleet.ledger.app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Management", description = "APIs for creating and managing users")
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping
    @Operation(summary = "Create User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    public ResponseEntity.BodyBuilder create(@Valid @RequestBody UserRequest userRequest) {

        userService.createNewUser(userRequest);

        return ResponseEntity.status(HttpStatus.CREATED);

    }


}
