package com.example.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
@Slf4j
public class Controller {

    @Autowired
    EventProducer eventProducer;


    @PostMapping("/event")
    public void produceEvent(@RequestBody Events event){
        log.info("Inside produce event");
         eventProducer.publishEvent(event);
    }
}
