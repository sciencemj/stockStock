package com.sciencemj.stock.domain.bank;

import com.sciencemj.stock.domain.stock.Stock;
import com.sciencemj.stock.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long ownerId;

    @Column(nullable = false)
    private Banks bank;

    @Column(nullable = false)
    private Double debt;

    @Column(nullable = false)
    private Double money;

    @Column
    private Stock[] stocks;
}
