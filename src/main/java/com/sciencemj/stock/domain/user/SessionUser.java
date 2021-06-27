package com.sciencemj.stock.domain.user;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private Double money;
    private String password;
    private Double debt;
    //private String picture;

    public SessionUser(){
        super();
    }
    public SessionUser(User user) {
        this.name = user.getName();
        this.money = user.getMoney();
        this.password = user.getPassword();
        this.debt = user.getDebt();
    }
}
