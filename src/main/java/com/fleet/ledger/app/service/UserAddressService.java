package com.fleet.ledger.app.service;

import com.fleet.ledger.app.dto.UserAddressRequest;
import com.fleet.ledger.app.dto.UserAddressResponse;

import java.util.List;

public interface UserAddressService {

    void addNewAddress(UserAddressRequest userAddressRequest);

    void deleteAddress(Integer addressId);

    List<UserAddressResponse> getAllAddress();

}
