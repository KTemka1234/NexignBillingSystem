package ru.learnhub.brt.service.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.learnhub.brt.service.BRTService;
import ru.learnhub.commondto.dto.CallDataRecord;
import ru.learnhub.commondto.dto.CallDataRecordPlus;

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
    public void processCDRMq(@Payload List<CallDataRecord> cdrList) {
        log.info("--- Получение CDR-файла от коммутатора в BRT сервисе ---");
        messageSender.sendMessage(brtService.convertCDRToCDRPlus(cdrList));
    }

    @JmsListener(destination = "${cdrplus.mq}")
    public void processCDRPlusMq(@Payload List<CallDataRecordPlus> cdrPlusList) {
        log.info("--- Получение CDR+ файла от HRS в BRT сервисе ---");
        brtService.paySubscriptionFee(cdrPlusList);
    }
}
