package com.fleet.ledger.app.service.impl;

import com.fleet.ledger.app.dto.BodyType;
import com.fleet.ledger.app.dto.FuelType;
import com.fleet.ledger.app.dto.TripBill;
import com.fleet.ledger.app.entity.Bill;
import com.fleet.ledger.app.entity.Trip;
import com.fleet.ledger.app.entity.Vehicle;
import com.fleet.ledger.app.repository.TripBillRepository;
import com.fleet.ledger.app.repository.TripRepository;
import com.fleet.ledger.app.service.BillingService;
import com.fleet.ledger.app.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

import static com.fleet.ledger.app.utils.Utils.isNotNUll;

@Service
public class BillingServiceImpl implements BillingService {


    private static final Map<String, Integer> vehicleBaseFare = Map.of("HATCHBACK", 700, "SEDAN", 800, "MUV", 1000);

    private TripBillRepository billRepository;

    private TripRepository tripRepository;

    private InvoiceService invoiceService;

    public BillingServiceImpl(TripBillRepository billRepository, TripRepository tripRepository, InvoiceService invoiceService) {
        this.billRepository = billRepository;
        this.tripRepository = tripRepository;
        this.invoiceService = invoiceService;
    }


    @Override
    public void createNewBill(Long tripId) {

        Optional<Trip> tripOptional = tripRepository.findById(tripId);
        Integer totalFare = 0;

        if (tripOptional.isPresent()) {
            Trip trip = tripOptional.get();
            Vehicle vehicle = trip.getVehicle();
            String vehicleType = vehicle.getVehicleType();
            BodyType bodyType = vehicle.getBodyType();

            FuelType fuelType = vehicle.getFuelType();

            Integer distance = trip.getDistance();
            Integer baseFare = vehicleBaseFare.get(bodyType.toString());

            if (BodyType.HATCHBACK.equals(bodyType)) {
                Integer distanceFare = (distance / 15) * 106;
                totalFare = distanceFare + baseFare;
            }

            Bill bill = new Bill();
            bill.setTripId(tripId);
            bill.setBillDate(trip.getJourneyDate());
            bill.setDriverCharge(400);
            bill.setTotal(totalFare);
            bill.setParking(0);
            bill.setTariffCharge(baseFare);
            bill.setToll(0);

            billRepository.save(bill);
        }


    }

    @Override
    public TripBill getBill(Long tripId) {
        Bill bill = billRepository.findByTripId(tripId);
        TripBill tripBill = new TripBill();

        if (isNotNUll(bill)) {
            tripBill.setBillId(bill.getBillId());
            tripBill.setTripId(bill.getTripId());
            tripBill.setBaseCharge(bill.getTariffCharge());
            tripBill.setTotal(bill.getTotal());
        }
        return tripBill;
    }

    @Override
    public void deleteBill(Long billId) {
        billRepository.deleteById(billId);
    }

    @Override
    public byte[] generateBillPdf(Long billId) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        Optional<Trip> tripOptional = tripRepository.findById(bill.getTripId());
        Trip trip = null;
        if(tripOptional.isPresent()){
            trip = tripOptional.get();
        }


        byte[] pdf = invoiceService.generateBillPdf(bill, trip);

        return pdf;
    }

    @Override
    public void generateBillPdfFileInPath(Long billId) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        Optional<Trip> tripOptional = tripRepository.findById(bill.getTripId());
        Trip trip = null;
        if(tripOptional.isPresent()){
            trip = tripOptional.get();
        }

        String filePath = "BillInvoice.pdf";

        invoiceService.saveBillPdfFile(bill,trip,filePath);

    }

}
