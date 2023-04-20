package ru.learnhub.switchboard.service.messaging;

import dto.CallDataRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Slf4j
public class SwitchboardMessageSender {

    private final JmsTemplate jmsTemplate;
    private final String cdrMq;

    public SwitchboardMessageSender(JmsTemplate jmsTemplate, @Value("${cdr.mq}") String cdrMq) {
        this.jmsTemplate = jmsTemplate;
        this.cdrMq = cdrMq;
    }

    public void sendMessage(List<CallDataRecord> records) {
        log.info("--- Передача CDR-файла из коммутатора в сервис BRT ---");
        jmsTemplate.convertAndSend(cdrMq, records);
    }
}
