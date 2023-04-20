package ru.learnhub.brt.service.messaging;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.learnhub.brt.service.BRTService;
import ru.learnhub.commondto.dto.CallDataRecord;

import java.util.List;

@Service
public class BRTMessageListener {

    private final BRTService brtService;

    public BRTMessageListener(BRTService brtService) {
        this.brtService = brtService;
    }

    @JmsListener(destination = "${cdr.mq}")
    public void processCdrMq(@Payload List<CallDataRecord> cdrList) {
        brtService.sendCDRPlus(brtService.convertCDRToCDRPlus(cdrList));
    }
}
