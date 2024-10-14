package com.example.event;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class EventListener {

    private final ConcurrentHashMap<String, int[]> dataMap;

    EventListener(){
        dataMap = new ConcurrentHashMap<>();
    }

    @KafkaListener(topics = "event", groupId = "event-consumer-group")
    public void listenEvent(String key, Events event){
        String customerId = event.getCustomerId();
        int value = event.getValue();
        int hourOfDay = (int) (event.getTimestamp() % (24 * 3600)) / 3600;
        dataMap.computeIfAbsent(customerId, k -> new int[24])[hourOfDay]+=value;
    }

    public ConcurrentHashMap<String, int[]> getCustomerData() {
        return dataMap;
    }
}
