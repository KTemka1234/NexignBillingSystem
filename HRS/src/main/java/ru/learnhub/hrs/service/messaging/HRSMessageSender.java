package ru.learnhub.hrs.service.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import ru.learnhub.commondto.entity.PhoneNumber;

import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class HRSMessageSender {

    private final JmsTemplate jmsTemplate;
    private final String phoneBalanceMQ;

    public HRSMessageSender(JmsTemplate jmsTemplate, @Value("${phone-balance.mq}") String phoneBalanceMQ) {
        this.jmsTemplate = jmsTemplate;
        this.phoneBalanceMQ = phoneBalanceMQ;
    }

    public void sendMessage(HashMap<String, PhoneNumber> billingMap) {
        log.info("--- Передача Billing файла от HRS в BRT сервис ---");
        jmsTemplate.convertAndSend(phoneBalanceMQ, billingMap);
    }
}
