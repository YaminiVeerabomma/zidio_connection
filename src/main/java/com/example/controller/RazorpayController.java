//package com.example.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.example.service.RazorPayService;
//import com.razorpay.Utils;
//
//@RestController
//@RequestMapping("/api/razorpay")
//public class RazorpayController {
//
//    @Autowired
//    private RazorPayService razorpayService;
//
//    @PostMapping("/webhook")
//    public ResponseEntity<String> handleWebhook(@RequestBody String payload,
//        @RequestHeader("X-Razorpay-Signature") String signature) {
//
//        String secret = "YOUR_WEBHOOK_SECRET";
//        try {
//            boolean isValid = Utils.verifyWebhookSignature(payload, signature, secret);
//            if (!isValid) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signature");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Signature verification failed");
//        }
//
//        return ResponseEntity.ok("Webhook received");
//    }
//
//    @PostMapping("/create-order")
//    public ResponseEntity<String> createOrder(@RequestParam Double amount,
//        @RequestParam String currency,
//        @RequestParam String receipt) {
//
//        try {
//            String orderJson = razorpayService.createOrder(amount, currency, receipt);
//            return ResponseEntity.ok(orderJson);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                                 .body("Failed to create order");
//        }
//    }
//}
