package com.csctracker.bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.csctracker.securitycore", "com.csctracker.bff"})
public class CscTrackerBffApplication {

    public static void main(String[] args) {
        SpringApplication.run(CscTrackerBffApplication.class, args);
    }

}
