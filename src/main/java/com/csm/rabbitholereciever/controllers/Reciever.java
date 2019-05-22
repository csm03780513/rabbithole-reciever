package com.csm.rabbitholereciever.controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;


@RestController
@Component
@RequestMapping(value = "/rabbithole/")
public class Reciever {

    private CountDownLatch latch = new CountDownLatch(1);

    // @GetMapping(value = "/consume")
    public void receiveMessage(byte[] message) {
        System.out.println("Received <" + message+ ">");
        latch.countDown();
        //return message;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
