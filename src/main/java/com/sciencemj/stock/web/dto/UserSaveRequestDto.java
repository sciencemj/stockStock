package com.sciencemj.stock.web.dto;

import com.sciencemj.stock.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String name;
    private Double money;

    @Builder
    public UserSaveRequestDto(String name, Double money) {
        this.name = name;
        this.money = money;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .money(money)
                .build();
    }
}
