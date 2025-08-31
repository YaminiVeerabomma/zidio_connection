package com.example.controller;

import com.example.DTO.InvoiceDTO;
import com.example.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/generate/{subscriptionId}")
    @Operation(summary = "Generate Invoice", description = "Generate invoice for a subscription and download PDF")
    public ResponseEntity<byte[]> generateInvoice(
            @Parameter(description = "ID of the subscription", required = true, example = "1")
            @PathVariable Long subscriptionId,
            @RequestParam String userEmail,
            @RequestParam String paymentMethod) {

        byte[] pdfBytes = invoiceService.generateInvoice(subscriptionId, userEmail, paymentMethod);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.attachment().filename("Invoice.pdf").build());

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
    
}
