package com.example.simpleweb.service;

import com.example.simpleweb.utils.VnPayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class VnPayService {

    @Value("${vnpay.tmnCode}")
    private String vnpTmnCode;

    @Value("${vnpay.hashSecret}")
    private String vnpHashSecret;

    @Value("${vnpay.payUrl}")
    private String vnpPayUrl;

    @Value("${vnpay.returnUrl}")
    private String vnpReturnUrl;

    public Map<String, String> createPayment(int amount) {
        String vnp_TxnRef = String.valueOf(System.currentTimeMillis());
        String vnp_OrderInfo = "Thanh toan don hang: " + vnp_TxnRef;

        String vnp_Amount = String.valueOf(amount * 100);
        String vnp_Command = "pay";
        String vnp_CurrCode = "VND";

        String vnp_IpAddr = "127.0.0.1";

        String vnp_TmnCode = this.vnpTmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", "2.1.0");
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", vnp_Amount);
        vnp_Params.put("vnp_CurrCode", vnp_CurrCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
        vnp_Params.put("vnp_OrderType", "other");
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", vnpReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String vnp_CreateDate = LocalDateTime.now().format(formatter);
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        String queryUrl = VnPayUtils.getPaymentUrl(vnp_Params, vnpHashSecret, vnpPayUrl);

        Map<String, String> result = new HashMap<>();
        result.put("payUrl", queryUrl);
        return result;
    }
    public String handleVnpayReturn(HttpServletRequest request) {
        // Tạo map chứa các tham số cần xác thực
        Map<String, String> fields = new HashMap<>();
        for (Enumeration<String> params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = params.nextElement();
            String fieldValue = request.getParameter(fieldName);

            // Bỏ qua 2 tham số không dùng để xác thực
            if (!fieldName.equals("vnp_SecureHash") && !fieldName.equals("vnp_SecureHashType")) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        boolean valid = VnPayUtils.verifyPayment(fields, vnp_SecureHash, vnpHashSecret);
        
        System.out.println("Fields: " + fields);
        System.out.println("vnp_SecureHash: " + vnp_SecureHash);

        if (valid) {
            return "Thanh toán thành công!";
        } else {
            return "Thanh toán không hợp lệ!";
        }
    }


}