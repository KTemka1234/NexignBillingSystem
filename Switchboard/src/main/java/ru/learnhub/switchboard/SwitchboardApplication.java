package ru.learnhub.switchboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SwitchboardApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SwitchboardApplication.class, args);
    }

}
