package ru.learnhub.brt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.learnhub.brt.dao.ClientDao;
import ru.learnhub.brt.entity.PhoneNumber;
import ru.learnhub.commondto.dto.CallDataRecord;
import ru.learnhub.commondto.dto.CallDataRecordPlus;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BRTService {

    private final ClientDao clientDao;

    public BRTService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    // Преобразуем CDR в CDR+
    public List<CallDataRecordPlus> convertCDRToCDRPlus(List<CallDataRecord> cdrList) {
        // Получаем из БД номера клиентов и преобразуем в HashMap для дальнейшей сортировки
        Map<String, PhoneNumber> clientsNums = clientDao.getAllUsersPhoneNums().stream()
                .collect(Collectors.toMap(PhoneNumber::getPhoneNumber, Function.identity()));
        // Сортируем CDRs по номеру и балансу на нём и создаём список CDR+
        return cdrList.stream().filter(
                        cdr -> clientsNums.containsKey(cdr.getPhoneNumber()) &&
                                clientsNums.get(cdr.getPhoneNumber()).getBalance().compareTo(BigDecimal.ZERO) > 0
                ).map(cdr -> new CallDataRecordPlus(cdr, clientsNums.get(cdr.getPhoneNumber()).getTariff().getId()))
                .toList();
    }

    public void paySubscriptionFee(List<CallDataRecordPlus> cdrPlusList) {
        //TODO Реализовать обновление баланса
//        cdrPlusList.forEach(cdrPlus -> clientDao.updateUserBalance(
//                cdrPlus.getCallDataRecord().getPhoneNumber(),
//                cdrPlus.ge));
    }
}
