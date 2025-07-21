//package com.example.controller;
//
//import com.example.DTO.InvoiceDTO;
//import com.example.entity.Invoice;
//import com.example.service.BillingService;
//import com.example.service.RazorPayService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/invoice")
//public class BillingController {
//
//    @Autowired
//    private BillingService billingService;
//
//    @Autowired
//    private RazorPayService razorPayService;
//
//    // 1. Create an invoice
//    @PostMapping(value="/pay",produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Invoice> payForService(@RequestBody InvoiceDTO dto) {
//        Invoice invoice = billingService.createInvoice(dto);
//        return ResponseEntity.ok(invoice);
//    }
//
//    // 2. Get invoice history by email
//    @GetMapping(value="/history/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Invoice>> getHistory(@PathVariable String email) {
//        List<Invoice> invoices = billingService.getInvoice(email);
//        return ResponseEntity.ok(invoices);
//    }
//
//    // 3. Create Razorpay Order
//    @PostMapping("/create-order")
//    public ResponseEntity<String> createOrder(@RequestBody Double amount) {
//        String order = razorPayService.createOrder(amount, "INR", "rcpt-" + System.currentTimeMillis());
//        return ResponseEntity.ok(order);
//    }
//}
