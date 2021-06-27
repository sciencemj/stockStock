package com.sciencemj.stock.web.dto;

import com.sciencemj.stock.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long id;
    private String name;
    private Double money;
    private String password;
    private Double debt;
    public UserResponseDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.money = user.getMoney();
        this.password = user.getPassword();
        this.debt = user.getDebt();
    }

}
