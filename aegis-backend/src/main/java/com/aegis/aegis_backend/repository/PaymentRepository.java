package com.aegis.aegis_backend.repository;


import com.aegis.aegis_backend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByMissionId(Long missionId);
    java.util.Optional<Payment> findFirstByMissionIdAndStatusOrderByCreatedAtDesc(Long missionId, Payment.PaymentStatus status);
}
