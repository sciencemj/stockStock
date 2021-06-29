package com.sciencemj.stock.service;

import com.sciencemj.stock.domain.stock.Stock;
import com.sciencemj.stock.domain.stock.StockRepository;
import com.sciencemj.stock.domain.user.User;
import com.sciencemj.stock.web.dto.StockResponseDto;
import com.sciencemj.stock.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StockService {
    private final StockRepository stockRepository;

    @Transactional
    public Long save(Stock stock){
        return stockRepository.save(stock).getId();
    }

    @Transactional
    public Long update(Stock stock){
        stockRepository.deleteById(stock.getId());
        return stockRepository.save(stock).getId();
    }

    @Transactional
    public List<Stock> all(){
        return stockRepository.findAll();
    }

    @Transactional(readOnly = true)
    public StockResponseDto findByName(String name) {
        Stock entity = stockRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. name=" + name));
        return new StockResponseDto(entity);
    }
    @Transactional(readOnly = true)
    public StockResponseDto findById(Long id) {
        Stock entity = stockRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 stock가 없습니다. id=" + id));
        return new StockResponseDto(entity);
    }
}
