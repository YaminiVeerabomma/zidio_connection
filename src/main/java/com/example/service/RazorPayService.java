package com.example.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RazorPayService {

    @Value("${razorpay.key}")
    private String key;

    @Value("${razorpay.secret}")
    private String secret;

    public String createOrder(Double amount, String currency, String receipt) {
        try {
            RazorpayClient client = new RazorpayClient(key, secret);

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", (int) (amount * 100)); // Razorpay expects amount in paise
            orderRequest.put("currency", currency);
            orderRequest.put("receipt", receipt);
            orderRequest.put("payment_capture", 1);

            Order order = client.orders.create(orderRequest);
            return order.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error creating Razorpay order", e);
        }
    }
}
