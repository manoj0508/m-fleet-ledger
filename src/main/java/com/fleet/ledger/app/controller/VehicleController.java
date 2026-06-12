package com.fleet.ledger.app.controller;

import com.fleet.ledger.app.dto.VehicleRequest;
import com.fleet.ledger.app.dto.VehicleResponse;
import com.fleet.ledger.app.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
@Tag(name = "Vehicle Management", description = "APIs for creating and managing vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vehicle created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public ResponseEntity createNewVehicle(@RequestBody @Valid VehicleRequest vehicleRequest){
        vehicleService.createNewVehicle(vehicleRequest);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle fetched successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity getAllVehicle(){
        List<VehicleResponse> vehicleResponseList = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicleResponseList);
    }


    @GetMapping(params = "vehicleId")
    public ResponseEntity getVehicle(Integer vehicleId){
        VehicleResponse vehicleResponse = vehicleService.getVehicle(vehicleId);
        return ResponseEntity.ok(vehicleResponse);
    }


    @GetMapping(params = "vehicleNo")
    public ResponseEntity getVehicle(String vehicleNo){
        VehicleResponse vehicleResponse = vehicleService.getVehicle(vehicleNo);
        return ResponseEntity.ok(vehicleResponse);
    }

    @DeleteMapping
    @Operation(summary = "delete existing vehicle by vehicle id")
    public ResponseEntity delete(Integer vehicleId){
         vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.ok(HttpStatus.OK);
    }



}
