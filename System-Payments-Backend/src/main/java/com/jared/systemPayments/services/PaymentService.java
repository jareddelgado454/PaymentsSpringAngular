package com.jared.systemPayments.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jared.systemPayments.entities.Payment;
import com.jared.systemPayments.entities.Student;
import com.jared.systemPayments.enums.StatusPayment;
import com.jared.systemPayments.enums.TypePayment;
import com.jared.systemPayments.repository.PaymentRepository;
import com.jared.systemPayments.repository.StudentRepository;

@Service
@Transactional

public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Payment savePayment(MultipartFile file, double amount, TypePayment type, LocalDate date,
            String studentCode) {
        Path folderPath = Paths.get(System.getProperty("user.home"), "enset-data", "payments");
        try {
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }

            String fileName = UUID.randomUUID().toString();
            Path filePath = Paths.get(System.getProperty("user.home"), "enset-data", "payments", fileName + ".pdf");
            Files.copy(file.getInputStream(), filePath);

            Student student = studentRepository.findByCode(studentCode);
            Payment payment = Payment.builder()
                    .type(type)
                    .status(StatusPayment.CREATED)
                    .date(date)
                    .student(student)
                    .amount(amount)
                    .file(filePath.toUri().toString())
                    .build();

            return paymentRepository.save(payment);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar el archivo", e);
        }
    }
}
