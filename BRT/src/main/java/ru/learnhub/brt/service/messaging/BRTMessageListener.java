package ru.learnhub.brt.service.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.learnhub.brt.service.BRTService;
import ru.learnhub.commondto.dto.CallDataRecord;
import ru.learnhub.commondto.dto.CallDataRecordPlus;
import ru.learnhub.commondto.entity.PhoneNumber;

import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class BRTMessageListener {

    private final BRTMessageSender messageSender;
    private final BRTService brtService;

    public BRTMessageListener(BRTMessageSender messageSender, BRTService brtService) {
        this.messageSender = messageSender;
        this.brtService = brtService;
    }

    @JmsListener(destination = "${cdr.mq}")
    public void processCDRMQ(@Payload List<CallDataRecord> cdrList) {
        log.info("--- Получение CDR-файла от коммутатора в BRT сервисе ---");
        messageSender.sendMessage(brtService.convertCDRToCDRPlus(cdrList));
    }

    @JmsListener(destination = "${phone-balance.mq}")
    public void processCDRPlusMQ(@Payload HashMap<String, PhoneNumber> billingMap) {
        log.info("--- Получение файла обновления баланса от HRS в BRT сервисе ---");
        //brtService.paySubscriptionFee(billingMap);
    }
}
