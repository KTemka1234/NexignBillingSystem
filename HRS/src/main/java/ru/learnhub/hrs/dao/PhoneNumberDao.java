package ru.learnhub.hrs.dao;

import org.springframework.stereotype.Component;
import ru.learnhub.commondto.entity.PhoneNumber;
import ru.learnhub.hrs.repository.PhoneNumberRepository;

import java.math.BigDecimal;

@Component
public class PhoneNumberDao {

    private final PhoneNumberRepository repository;

    public PhoneNumberDao(PhoneNumberRepository repository) {
        this.repository = repository;
    }

    public PhoneNumber getPhoneNumber(String phoneNumber) {
        return repository.findPhoneNumberByPhoneNumber(phoneNumber);
    }
}
