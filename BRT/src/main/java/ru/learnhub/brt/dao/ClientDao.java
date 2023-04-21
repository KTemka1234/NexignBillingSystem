package ru.learnhub.brt.dao;

import org.springframework.stereotype.Component;
import ru.learnhub.brt.entity.PhoneNumber;
import ru.learnhub.brt.repository.ClientRepository;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ClientDao {

    private final ClientRepository clientRepository;

    public ClientDao(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<PhoneNumber> getAllUsersPhoneNums() {
        return clientRepository.findAll();
    }

    public void updateUserBalance(String phoneNumber, BigDecimal balance) {
        clientRepository.updateBalance(balance, phoneNumber);
    }
}
