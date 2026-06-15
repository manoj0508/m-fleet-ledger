package com.fleet.ledger.app.service;

import com.fleet.ledger.app.dto.TripBill;

public interface BillingService {

    void createNewBill(Long tripId);

    TripBill getBill(Long tripId);

    void deleteBill(Long billId);

    byte[] generateBillPdf(Long billId);

    void generateBillPdfFileInPath(Long billId);


}
