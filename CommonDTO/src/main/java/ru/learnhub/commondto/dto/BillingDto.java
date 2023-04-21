package ru.learnhub.commondto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BillingDto {

    String phone_number;
    BigDecimal newBalance;
}
