package ru.learnhub.commondto.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.learnhub.commondto.entity.Tariff;

import java.math.BigDecimal;

@Entity
@Table(name = "phone_numbers")
@Data
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "balance")
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn
    private Tariff tariff;
}
