package com.example.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventProducer {

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;


    public void publishEvent(Events event){
        String customerId = event.getCustomerId();
        kafkaTemplate.send("events", customerId, event);
        log.info("Produced event with key: {} " , customerId);

    }

}
