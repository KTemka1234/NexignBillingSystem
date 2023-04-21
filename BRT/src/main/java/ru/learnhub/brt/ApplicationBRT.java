package ru.learnhub.brt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("ru.learnhub.*")
@SpringBootApplication
public class ApplicationBRT {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBRT.class, args);
    }
}
