package ru.learnhub.hrs.service;

import org.springframework.stereotype.Service;
import ru.learnhub.commondto.dto.CallDataRecord;
import ru.learnhub.commondto.dto.CallDataRecordPlus;
import ru.learnhub.commondto.entity.PhoneNumber;
import ru.learnhub.commondto.TariffType;
import ru.learnhub.hrs.dao.PhoneNumberDao;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;

@Service
public class HRSService {

    private final PhoneNumberDao dao;

    public HRSService(PhoneNumberDao dao) {
        this.dao = dao;
    }

    public HashMap<String, PhoneNumber> calcSubscriptionFee(List<CallDataRecordPlus> cdrPlusList) {
        HashMap<String, PhoneNumber> billingMap = new HashMap<>();
        for (CallDataRecordPlus cdrPlus : cdrPlusList) {
            CallDataRecord cdr = cdrPlus.getCallDataRecord();
            if (!billingMap.containsKey(cdr.getPhoneNumber())) {
                String phoneNumber = cdr.getPhoneNumber();
                billingMap.put(phoneNumber, dao.getPhoneNumber(phoneNumber));
            }
            PhoneNumber bill = billingMap.get(cdr.getPhoneNumber());
            TariffType tariffType = TariffType.getInstanceById(bill.getTariff().getId());
            String callTypeIndex = cdr.getCallType().getIndex();
            int callDurationMinutes = calcCallDurationMinutes(cdr);

            BigDecimal callCost = BigDecimal.valueOf(tariffType.applyTariff(callDurationMinutes, callTypeIndex));
            BigDecimal newBalance = bill.getBalance().subtract(callCost);
            bill.setBalance(newBalance);
        }
        return billingMap;
    }

    // Считаем длительность разговора в минутах
    private int calcCallDurationMinutes(CallDataRecord cdr) {
        long seconds =  ChronoUnit.SECONDS.between(cdr.getCallStartTime(), cdr.getCallEndTime());
        return (int) Math.ceil(seconds / 60.0);
    }
}
