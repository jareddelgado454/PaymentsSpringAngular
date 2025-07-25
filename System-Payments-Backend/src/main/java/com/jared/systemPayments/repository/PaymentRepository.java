package com.jared.systemPayments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jared.systemPayments.entities.Payment;
import com.jared.systemPayments.enums.StatusPayment;
import com.jared.systemPayments.enums.TypePayment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
    List<Payment> findByStudentCode (String code);

    List<Payment> findByStatus(StatusPayment status);

    List<Payment> findByType(TypePayment type);
}
