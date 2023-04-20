package ru.learnhub.brt.service;

import org.springframework.stereotype.Service;
import ru.learnhub.brt.repository.ClientRepository;
import ru.learnhub.brt.service.messaging.BRTMessageSender;
import ru.learnhub.commondto.dto.CallDataRecord;
import ru.learnhub.commondto.dto.CallDataRecordPlus;

import java.util.List;

@Service
public class BRTService {

    private final ClientRepository clientRepository;
    private final BRTMessageSender messageSender;

    public BRTService(ClientRepository clientRepository, BRTMessageSender messageSender) {
        this.clientRepository = clientRepository;
        this.messageSender = messageSender;
    }

    public List<CallDataRecordPlus> convertCDRToCDRPlus(List<CallDataRecord> cdrList) {
        //TODO
        return null;
    }

    public void sendCDRPlus(List<CallDataRecordPlus> cdrPlusList) {
        messageSender.sendMessage(cdrPlusList);
    }
}
