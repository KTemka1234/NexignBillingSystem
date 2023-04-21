package ru.learnhub.hrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.learnhub.commondto.entity.PhoneNumber;


@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {

    PhoneNumber findByPhoneNumber(String phoneNumber);
}
