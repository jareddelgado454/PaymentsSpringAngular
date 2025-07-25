package com.jared.systemPayments.dto;

import java.time.LocalDate;

import com.jared.systemPayments.enums.TypePayment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class NewPaymentDto {
    private double amount;
    private TypePayment type;
    private LocalDate date;
    private String studentCode;
}
