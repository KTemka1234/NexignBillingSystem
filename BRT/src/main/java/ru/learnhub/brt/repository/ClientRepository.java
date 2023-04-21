package ru.learnhub.brt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.learnhub.brt.entity.PhoneNumber;

import java.math.BigDecimal;


@Repository
public interface ClientRepository extends JpaRepository<PhoneNumber, Long> {
    // Используем интерфейс JpaRepository, а не CrudRepository,
    // т.к. findAll() возвращает коллекцию, а не итератор

    @Modifying
    @Query("UPDATE PhoneNumber SET balance = ?1 WHERE phoneNumber = ?2")
    void updateBalance(BigDecimal balance, String phoneNumber);
}
