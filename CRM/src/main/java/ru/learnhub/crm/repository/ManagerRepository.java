package ru.learnhub.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.learnhub.commondto.entity.PhoneNumber;
import ru.learnhub.crm.dto.AbonentDto;

@Repository
public interface ManagerRepository extends JpaRepository<PhoneNumber, Long> {

//    @Modifying
//    @Query("INSERT INTO PhoneNumber ()")
//    void createNewAbonent(AbonentDto abonent);
}
