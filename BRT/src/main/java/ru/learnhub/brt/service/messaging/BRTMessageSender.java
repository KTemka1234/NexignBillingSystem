package ru.learnhub.brt.service.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import ru.learnhub.commondto.dto.CallDataRecordPlus;

import java.util.List;

@Service
public class BRTMessageSender {

    private final JmsTemplate jmsTemplate;
    private final String cdrPlusMq;

    public BRTMessageSender(JmsTemplate jmsTemplate, @Value("${cdrplus.mq}") String cdrPlusMq) {
        this.jmsTemplate = jmsTemplate;
        this.cdrPlusMq = cdrPlusMq;
    }

    public void sendMessage(List<CallDataRecordPlus> listCdr) {
        jmsTemplate.convertAndSend(cdrPlusMq, listCdr);
    }
}
