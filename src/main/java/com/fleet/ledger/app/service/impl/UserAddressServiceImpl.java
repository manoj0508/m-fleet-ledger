package com.fleet.ledger.app.service.impl;

import com.fleet.ledger.app.dto.UserAddressRequest;
import com.fleet.ledger.app.dto.UserAddressResponse;
import com.fleet.ledger.app.entity.User;
import com.fleet.ledger.app.entity.UserAddress;
import com.fleet.ledger.app.repository.UserAddressRepository;
import com.fleet.ledger.app.repository.UserRepository;
import com.fleet.ledger.app.service.UserAddressService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    private static final Logger logger = LoggerFactory.getLogger(UserAddressService.class);

    private final UserAddressRepository userAddressRepository;

    private final UserRepository userRepository;

    public UserAddressServiceImpl(UserAddressRepository userAddressRepository, UserRepository userRepository) {
        this.userAddressRepository = userAddressRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    @Override
    public void addNewAddress(UserAddressRequest userAddressRequest) {
        if (null != userAddressRequest) {
            UserAddress userAddress = new UserAddress();
            userAddress.setLine(userAddressRequest.line());
            userAddress.setCity(userAddressRequest.city());
            userAddress.setState(userAddressRequest.state());
            userAddress.setPincode(userAddressRequest.pincode());

            User userReference = userRepository.getReferenceById(userAddressRequest.userId());

            userAddress.setUser(userReference);

            userAddressRepository.save(userAddress);

        }
    }

    @Override
    public void deleteAddress(Integer addressId) {
        userAddressRepository.deleteById(addressId);
        logger.info("user address deleted for user id {}", addressId);
    }

    @Override
    public List<UserAddressResponse> getAllAddress() {
        List<UserAddressResponse> addressResponseList = new ArrayList<>();
        List<UserAddress> addressList = userAddressRepository.findAll();
        addressResponseList = addressList.stream().map(this::mapToResponse).toList();
        return addressResponseList;
    }


    private UserAddressResponse mapToResponse(UserAddress address) {
        return new UserAddressResponse(
                address.getId(),
                address.getLine(),
                address.getCity(),
                address.getState(),
                address.getPincode()
        );
    }
}
