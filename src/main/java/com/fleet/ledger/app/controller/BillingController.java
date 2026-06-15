package com.fleet.ledger.app.controller;

import com.fleet.ledger.app.dto.TripBill;
import com.fleet.ledger.app.service.BillingService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bills")
public class BillingController {

    private BillingService billingService;


    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }


    @PostMapping
    public ResponseEntity createBill(@RequestParam Long tripId) {
        billingService.createNewBill(tripId);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getBill(@RequestParam Long tripId) {
        TripBill bill = billingService.getBill(tripId);

        return ResponseEntity.ok(bill);
    }

    @DeleteMapping
    public ResponseEntity deleteBill(@RequestParam Long billId) {

        billingService.deleteBill(billId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{billId}/pdf")
    public ResponseEntity<byte[]> downloadBill(@PathVariable Long billId) {

        byte[] pdf = billingService.generateBillPdf(billId);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=bill-" + billId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }


    @GetMapping("/file/{billId}/pdf")
    public ResponseEntity saveBillFile(@PathVariable Long billId) {

         billingService.generateBillPdfFileInPath(billId);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
