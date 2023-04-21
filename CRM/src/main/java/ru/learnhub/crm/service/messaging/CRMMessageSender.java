package ru.learnhub.crm.service.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class CRMMessageSender {

    private final JmsTemplate jmsTemplate;
    private final String switchboardMQ;

    public CRMMessageSender(JmsTemplate jmsTemplate, @Value("${switchboard.mq}") String switchboardMQ) {
        this.jmsTemplate = jmsTemplate;
        this.switchboardMQ = switchboardMQ;
    }

    public void sendMessage(String message) {
        jmsTemplate.convertAndSend(switchboardMQ, message);
    }


}
