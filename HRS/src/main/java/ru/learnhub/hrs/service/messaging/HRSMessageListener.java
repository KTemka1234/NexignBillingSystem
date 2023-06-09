package ru.learnhub.hrs.service.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.learnhub.commondto.dto.CallDataRecordPlus;
import ru.learnhub.hrs.service.HRSService;

import java.util.List;

@Service
@Slf4j
public class HRSMessageListener {

    private final HRSService hrsService;
    private final HRSMessageSender messageSender;

    public HRSMessageListener(HRSService hrsService, HRSMessageSender messageSender) {
        this.hrsService = hrsService;
        this.messageSender = messageSender;
    }

    @JmsListener(destination = "${cdrplus.mq}")
    public void processCDRPlusMQ(@Payload List<CallDataRecordPlus> cdrPlusList) {
        log.info("--- Получение CDR+ файла от BRT в HRS сервисе ---");
        messageSender.sendMessage(hrsService.calcSubscriptionFee(cdrPlusList));
    }
}
