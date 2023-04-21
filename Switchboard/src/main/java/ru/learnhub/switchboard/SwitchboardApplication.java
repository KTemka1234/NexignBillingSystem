package ru.learnhub.switchboard;

import ru.learnhub.commondto.dto.CallDataRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnhub.commondto.dto.CallType;
import ru.learnhub.switchboard.service.CDRFileGenerator;
import ru.learnhub.switchboard.service.messaging.SwitchboardMessageSender;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class SwitchboardApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SwitchboardApplication.class, args);
        CDRFileGenerator generator = context.getBean(CDRFileGenerator.class);
        SwitchboardMessageSender messageSender = context.getBean(SwitchboardMessageSender.class);

        List<CallDataRecord> generatedCDRList = generator.generateCDRList(100000);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.plusSeconds(100);
        generatedCDRList.add(new CallDataRecord(CallType.INCOMING, "71918937736", now, end));
        messageSender.sendMessage(generatedCDRList);

        try {
            generator.generateCDRFile("cdr.txt", generatedCDRList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
