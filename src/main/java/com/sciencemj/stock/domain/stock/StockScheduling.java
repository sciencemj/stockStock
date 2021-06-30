package com.sciencemj.stock.domain.stock;

import com.sciencemj.stock.service.StockService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.JsonbMessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Component
public class StockScheduling {
    private final StockService stockService;

    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public void setMessagingTemplate(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    //@Scheduled(fixedRate = 60000)
    @Scheduled(fixedRate = 5000)
    public void task1(){
        if (stockService.all() != null) {
            List<Stock> stocks = stockService.all();
            Random r = new Random();
            int change;
            for (Stock s : stocks) {
                change = r.nextInt(101) - 50;
                s.changePrice((double) change);
                stockService.update(s);
                try {
                    messagingTemplate.setMessageConverter(new JsonbMessageConverter());
                    messagingTemplate.convertAndSend("/topic/graph/" + s.getName(), s.getHistory());
                    //System.out.println("/topic/graph/" + s.getName());
                    //System.out.println(s.getHistoryS());
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("error while sending message");
                }
            }

        }
        System.out.println("printprint");
    }
}
