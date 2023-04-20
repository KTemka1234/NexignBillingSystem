package ru.learnhub.switchboard;

import dto.CallDataRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnhub.switchboard.service.CDRFileGenerator;
import ru.learnhub.switchboard.service.messaging.SwitchboardMessageSender;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@SpringBootApplication
public class SwitchboardApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SwitchboardApplication.class, args);
        CDRFileGenerator generator = context.getBean(CDRFileGenerator.class);
        SwitchboardMessageSender messageSender = context.getBean(SwitchboardMessageSender.class);

        List<CallDataRecord> generatedCDRList = generator.generateCDRList(1000);
        messageSender.sendMessage(generatedCDRList);

        try {
            generator.generateCDRFile("cdr.txt", generatedCDRList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
