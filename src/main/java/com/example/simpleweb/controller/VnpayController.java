package com.example.simpleweb.controller;

import com.example.simpleweb.service.VnPayService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;



@RestController
@RequestMapping("/api/v1/vnpay")
public class VnpayController {

    @Autowired
    private VnPayService vnpayService;

    @PostMapping("/create-payment")
    public Map<String, String> createPayment(@RequestParam("amount") int amount) {
        return vnpayService.createPayment(amount);
    }

    @GetMapping("/return")
    public String handleReturn(HttpServletRequest request) {
        return vnpayService.handleVnpayReturn(request);
    }
}