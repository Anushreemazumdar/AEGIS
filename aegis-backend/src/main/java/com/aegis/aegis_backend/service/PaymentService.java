package com.aegis.aegis_backend.service;

import com.aegis.aegis_backend.entity.Mission;
import com.aegis.aegis_backend.entity.Payment;
import com.aegis.aegis_backend.exception.ResourceNotFoundException;
import com.aegis.aegis_backend.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Value("${payment.asset}")
    private String defaultAsset;

    @Value("${payment.network}")
    private String defaultNetwork;

    @Value("${payment.pay-to}")
    private String payToAddress;

    @Value("${payment.price-per-request}")
    private Double pricePerRequest;

    @Value("${x402.network}")
    private String network;

    @Value("${x402.scheme}")
    private String scheme;

    @Value("${x402.price}")
    private String price;

    @Value("${x402.usdc-asset-id}")
    private String usdcAssetId;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public Payment createPendingPayment(Mission mission) {

        Payment payment = new Payment();

        payment.setMission(mission);
        payment.setAmount(pricePerRequest);
        payment.setAsset(defaultAsset);
        payment.setNetwork(defaultNetwork);
        payment.setStatus(Payment.PaymentStatus.PENDING);

        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(Long id) {

        return paymentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payment not found with id: " + id
                        )
                );
    }

    public String getPayToAddress() {
        return payToAddress;
    }

    /**
     * Builds the payment requirements object.
     */
    public Map<String, Object> buildPaymentRequirements(Mission mission) {

        Map<String, Object> extra = new HashMap<>();

        extra.put("asset", usdcAssetId);

        Map<String, Object> requirement = new HashMap<>();

        requirement.put("scheme", scheme);
        requirement.put("network", network);
        requirement.put("payTo", payToAddress);
        requirement.put("price", price);
        requirement.put(
                "description",
                "Fresh intelligence for mission " + mission.getId()
        );
        requirement.put("extra", extra);

        return requirement;
    }

    @Transactional
    public Payment getOrCreatePendingPayment(Mission mission) {

        return paymentRepository
                .findFirstByMissionIdAndStatusOrderByCreatedAtDesc(
                        mission.getId(),
                        Payment.PaymentStatus.PENDING
                )
                .orElseGet(() -> createPendingPayment(mission));
    }

    @Transactional
    public Payment markSettled(
            Payment payment,
            String transactionId,
            String rawFacilitatorResponse) {

        payment.setStatus(Payment.PaymentStatus.COMPLETED);
        payment.setTransactionId(transactionId);
        payment.setFacilitatorResponseRaw(rawFacilitatorResponse);

        return paymentRepository.save(payment);
    }

    @Transactional
    public Payment markFailed(
            Payment payment,
            String rawFacilitatorResponse) {

        payment.setStatus(Payment.PaymentStatus.FAILED);
        payment.setFacilitatorResponseRaw(rawFacilitatorResponse);

        return paymentRepository.save(payment);
    }
}