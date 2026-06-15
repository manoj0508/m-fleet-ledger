package com.fleet.ledger.app.service;

import com.fleet.ledger.app.entity.Bill;
import com.fleet.ledger.app.entity.Trip;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


@Component
public class InvoiceService {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);

    private static final String invoiceTile = "Manoj Transport Service";
    private static final String invoiceFooter = "Thank you for choosing our transport service.";


    public byte[] generateBillPdf(Bill bill, Trip trip) {

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            Document document = new Document();
            PdfWriter.getInstance(document, baos);

            generateBillDocument(document, bill, trip);

            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }


    public void saveBillPdfFile(Bill bill, Trip trip,String filePath) {

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            generateBillDocument(document, bill, trip);
        } catch (FileNotFoundException ex) {
            logger.error("unable to generate invoice as file path is invalid {} ", ex.getStackTrace());
        }

    }

    private void generateBillDocument(Document document, Bill bill, Trip trip) {
        document.open();

        // Title
        invoiceTitle(document);

        // Bill Information
        document.add(invoiceInfo(trip));

        document.add(new Paragraph(" "));
        document.add(sectionTitle("Trip Information"));
        document.add(tripInfo(trip));

        document.add(new Paragraph(" "));
        document.add(sectionTitle("Vehicle Information"));
        document.add(vehicleInfo(trip));

        document.add(new Paragraph(" "));
        document.add(sectionTitle("Driver Information"));
        document.add(driverInfo(trip));

        document.add(new Paragraph(" "));
        document.add(sectionTitle("Customer Information"));
        document.add(customerInfo(trip));

        document.add(new Paragraph(" "));
        document.add(sectionTitle("Charge Details"));
        document.add(sectionCharges(bill));

        invoiceFooter(document);
        document.close();
    }

    private void invoiceTitle(Document document) {
        Font titleFont = new Font(
                Font.HELVETICA,
                20,
                Font.BOLD,
                Color.WHITE);

        PdfPTable header = new PdfPTable(1);
        header.setWidthPercentage(100);

        PdfPCell cell = new PdfPCell(new Phrase(invoiceTile, titleFont));

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(15);
        cell.setBackgroundColor(new Color(0, 102, 204));
        cell.setBorder(Rectangle.NO_BORDER);

        header.addCell(cell);

        document.add(header);

        Paragraph subtitle =
                new Paragraph(
                        "TRANSPORT INVOICE",
                        new Font(Font.HELVETICA, 14, Font.BOLD));

        subtitle.setAlignment(Element.ALIGN_CENTER);
        subtitle.setSpacingAfter(20);
        document.add(subtitle);
    }

    private void invoiceFooter(Document document) {

        Paragraph line =
                new Paragraph(
                        "--------------------------------------------------");

        line.setAlignment(Element.ALIGN_CENTER);

        document.add(line);

        Paragraph footer =
                new Paragraph(
                        invoiceFooter,
                        new Font(Font.HELVETICA, 10));

        footer.setAlignment(Element.ALIGN_CENTER);

        document.add(footer);
    }

    private PdfPTable sectionCharges(Bill bill)  {
        Font headerFont =
                new Font(Font.HELVETICA, 12, Font.BOLD);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{4, 2});

        PdfPCell c1 =
                new PdfPCell(new Phrase("Description", headerFont));

        PdfPCell c2 =
                new PdfPCell(new Phrase("Amount (₹)", headerFont));

        c1.setBackgroundColor(Color.LIGHT_GRAY);
        c2.setBackgroundColor(Color.LIGHT_GRAY);

        table.addCell(c1);
        table.addCell(c2);

        table.addCell("Tariff Charge");
        table.addCell("₹ " + bill.getTariffCharge());

        table.addCell("Driver Charge");
        table.addCell("₹ " + bill.getDriverCharge());

        table.addCell("Toll");
        table.addCell("₹ " + bill.getToll());

        table.addCell("Parking");
        table.addCell("₹ " + bill.getParking());

        Font totalFont =
                new Font(Font.HELVETICA, 12, Font.BOLD);

        table.addCell(
                new PdfPCell(new Phrase("TOTAL", totalFont)));

        table.addCell(
                new PdfPCell(
                        new Phrase(
                                "₹ " + bill.getTotal(),
                                totalFont)));

        return table;
    }


    private Paragraph sectionTitle(String title) {

        Font font =
                new Font(Font.HELVETICA, 14, Font.BOLD);

        Paragraph paragraph =
                new Paragraph(title, font);

        paragraph.setSpacingBefore(10);
        paragraph.setSpacingAfter(10);

        return paragraph;
    }


    private PdfPTable customerInfo(Trip trip) {

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        table.addCell("Customer");
        table.addCell(trip.getCustomer().getFirstName());

        table.addCell("Mobile");
        table.addCell(trip.getCustomer().getMobileNo());

        return table;
    }


    private PdfPTable driverInfo(Trip trip) {

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        table.addCell("Driver");
        table.addCell(trip.getDriver().getFirstName());

        return table;
    }

    private PdfPTable vehicleInfo(Trip trip) {

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        table.addCell("Vehicle");
        table.addCell(trip.getVehicle().getName());

        table.addCell("Vehicle No");
        table.addCell(trip.getVehicle().getVehicleNo());

        table.addCell("Vehicle Type");
        table.addCell(trip.getVehicle().getVehicleType());

        table.addCell("Seating Capacity");
        table.addCell(
                String.valueOf(
                        trip.getVehicle().getSeatingCapacity()));

        return table;
    }

    private PdfPTable tripInfo(Trip trip) {

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        table.addCell("Source");
        table.addCell(trip.getSource());

        table.addCell("Destination");
        table.addCell(trip.getDestination());

        table.addCell("Journey Date");
        table.addCell(String.valueOf(trip.getJourneyDate()));

        table.addCell("Start Time");
        table.addCell(String.valueOf(trip.getJourneyStartTime()));

        table.addCell("End Time");
        table.addCell(String.valueOf(trip.getJourneyEndTime()));

        table.addCell("Total Distance");
        table.addCell(trip.getDistance() + " KM");

        return table;
    }

    private PdfPTable invoiceInfo(Trip trip) {

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        table.addCell("Invoice No");
        table.addCell("INV-" + trip.getTripId());

        table.addCell("Bill Date");
        table.addCell(String.valueOf(trip.getJourneyDate()));

        return table;
    }

}
