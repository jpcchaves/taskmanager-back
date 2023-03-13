package com.ws.taskmanager.configs;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;
import java.util.spi.TimeZoneNameProvider;

@Configuration
public class AppConfig {

    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone(""));   // It will set UTC timezone
    }
}
