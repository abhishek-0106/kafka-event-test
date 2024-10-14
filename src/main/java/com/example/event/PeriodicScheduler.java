package com.example.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PeriodicScheduler {

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    EventListener eventListener;

    @Scheduled(cron = "0 0 * * * *")
    public void publishMessageHourly(){
        int currentHour = (int) ((System.currentTimeMillis() / (1000 * 3600)) % 24);
        eventListener.getCustomerData().forEach((customerId, hourlyData) -> {
            kafkaTemplate.send("hourly-publish", customerId, hourlyData[currentHour]);
            log.info("Data sent to customer id - {} on topic hourly-publish is : {}", customerId, hourlyData[currentHour]);
        });
    }

}
