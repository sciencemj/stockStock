package com.sciencemj.stock.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double money;

    @Column(nullable = false)
    private Double debt;

    @Column(nullable = false)
    private String password;

    @Builder
    public User(String name, Double money, String password, Double debt){
        this.name = name;
        this.money = money;
        this.password = password;
        this.debt = debt;
    }

    public User update(Double money){
        this.money = money;
        return this;
    }
}
