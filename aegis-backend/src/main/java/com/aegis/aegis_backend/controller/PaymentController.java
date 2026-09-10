package com.aegis.aegis_backend.controller;



import com.aegis.aegis_backend.dto.PaymentResponse;
import com.aegis.aegis_backend.entity.Payment;
import com.aegis.aegis_backend.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{id}")
    public PaymentResponse getPayment(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        return PaymentResponse.fromEntity(payment);
    }
}
