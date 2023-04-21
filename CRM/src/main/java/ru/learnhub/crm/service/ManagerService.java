package ru.learnhub.crm.service;

import org.springframework.stereotype.Service;
import ru.learnhub.commondto.TariffType;
import ru.learnhub.crm.dto.AbonentDto;

import java.text.ParseException;

@Service
public class ManagerService {

    public void createAbonent(AbonentDto abonent) throws ParseException {
        checkAbonent(abonent);
    }

    private void checkAbonent(AbonentDto abonent) throws ParseException {
        checkPhoneNumber(abonent.getNumberPhone());
        checkTariff(abonent.getTariff_id());
        if (Double.parseDouble(abonent.getBallance()) < 0.0) {
            throw new ParseException("Balance must be greater than 0!", 0);
        }
    }

    private void checkPhoneNumber(String phoneNumber) throws ParseException {
        if (phoneNumber.length() != 11) {
            throw new ParseException("Invalid number length!", 0);
        } else if (!phoneNumber.startsWith("7")) {
            throw new ParseException("Invalid first digit of number!", 0);
        }
    }

    private void checkTariff(String tariff_id) throws ParseException {
        if (TariffType.getInstanceById(tariff_id) == null) {
            throw new ParseException("Invalid tariff_id!", 0);
        }
    }

}
