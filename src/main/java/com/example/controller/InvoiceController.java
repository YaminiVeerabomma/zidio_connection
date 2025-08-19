package com.example.controller;

import com.example.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoice")
@Tag(name = "Invoice API", description = "Endpoints to generate and download invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/download/{subscriptionId}")
    @Operation(summary = "Download Invoice", description = "Download PDF invoice for a given subscription ID")
    public ResponseEntity<byte[]> downloadInvoice(
            @Parameter(description = "ID of the subscription for which invoice is generated", required = true, example = "1")
            @PathVariable Long subscriptionId) throws Exception {

        byte[] pdfBytes = invoiceService.generateInvoice(subscriptionId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.attachment().filename("Invoice.pdf").build());

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
