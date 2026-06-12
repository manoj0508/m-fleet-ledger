package com.fleet.ledger.app.controller;

import com.fleet.ledger.app.dto.UserAddressRequest;
import com.fleet.ledger.app.dto.UserAddressResponse;
import com.fleet.ledger.app.service.UserAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
@Tag(name = "User Address Management", description = "APIs for creating and managing user address")
public class UserAddressController {

    private final UserAddressService addressService;

    public UserAddressController(UserAddressService userAddressService) {
        this.addressService = userAddressService;
    }


    @PostMapping
    @Operation(summary = "Create User Address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Address created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
    })
    public ResponseEntity createNewAddress(@RequestBody UserAddressRequest userAddressRequest) {

        if (null != userAddressRequest) {
            addressService.addNewAddress(userAddressRequest);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("address saved");
    }

    @DeleteMapping(params = "addressId")
    @Operation(summary = "delete User Address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
    })
    public ResponseEntity deleteAddress(Integer addressId) {
        addressService.deleteAddress(addressId);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping
    @Operation(summary = "get all user Address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address fetched successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
    })
    public ResponseEntity getAllAddress() {
        List<UserAddressResponse> allAddress = addressService.getAllAddress();
        return ResponseEntity.ok(allAddress);
    }


}
