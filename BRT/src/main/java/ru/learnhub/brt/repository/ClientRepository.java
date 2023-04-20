package ru.learnhub.brt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.learnhub.brt.entity.PhoneNumber;

@Repository
public interface ClientRepository extends CrudRepository<PhoneNumber, Long> {
}
