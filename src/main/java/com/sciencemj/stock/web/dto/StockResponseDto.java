package com.sciencemj.stock.web.dto;

import com.sciencemj.stock.domain.bank.Banks;
import com.sciencemj.stock.domain.stock.Stock;
import com.sciencemj.stock.domain.user.User;
import lombok.Getter;

import java.util.List;

@Getter
public class StockResponseDto {
    private Long id;
    private String name;
    private Double price;
    private Double[] history;
    public StockResponseDto(Stock stock){
        this.id = stock.getId();
        this.name = stock.getName();
        this.price = stock.getPrice();
        this.history = stock.getHistory();
    }
}
