package com.example.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Events {
    private String eventId;
    private String customerId;
    private String transactionId;
    private int value;
    private long timestamp;
}
