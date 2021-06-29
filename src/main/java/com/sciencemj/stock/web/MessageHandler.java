package com.sciencemj.stock.web;

import com.sciencemj.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class MessageHandler {
    private final StockService stockService;

    /*@GetMapping("/graph/{name}")
    @SendTo("/topic/graph/{name}")
    public Double[] graph(@PathVariable String name){
        return stockService.findByName(name).getHistory();
    }*/


}