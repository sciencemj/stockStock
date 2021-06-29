package com.sciencemj.stock.web;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class MessageHandler {

    @MessageMapping("/message")
    public Double[] message(){

        return null;
    }


}