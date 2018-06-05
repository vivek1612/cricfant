package com.cricfant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.cricfant")
public class CricfantApplication {

    public static void main(String[] args) {
        SpringApplication.run(CricfantApplication.class, args);
    }
}
