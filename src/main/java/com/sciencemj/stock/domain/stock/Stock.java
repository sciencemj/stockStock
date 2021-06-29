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

    @Column(nullable = false)
    private Double[] history = new Double[10];
    @Builder
    public Stock(String name, Double price){
        this.name = name;
        this.price = price;
        this.change = 0D;
        history[9] = price;
    }

    public Stock changePrice(Double changed){
        this.change = changed;
        price += changed;
        for (int i =0;i < 9;i++) {
            history[i] = history[i + 1];
        }
        history[9] = price;
        return this;
    }
}
