package com.sciencemj.stock.domain.stock;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double change;
/*
    @Column(nullable = false)
    private Double[] history = new Double[10];
*/
    @Column(nullable = false, length = 50000)
    private String historyS = "";
    @Builder
    public Stock(String name, Double price){
        this.name = name;
        this.price = price;
        this.change = 0D;
        //history[9] = price;
        historyS += price;
    }
    public Stock changePrice(Double changed){
        this.change = changed;
        price += changed;
        /*for (int i =0;i < 9;i++) {
            history[i] = history[i+1];
        }
        history[9] = price;*/
        if (historyS.split(" ").length < 100) {
            historyS += " " + price;
        }else {
            StringBuilder h = new StringBuilder();
            String[] s = historyS.split(" ");
            for (int i = 1;i < s.length;i++){
                h.append(s[i]).append(" ");
            }
            h.append(price);
            historyS = h.toString();
        }
        return this;
    }
}
