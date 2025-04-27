package com.example.coindeskdemo.service;

import com.example.coindeskdemo.entity.Currency;
import com.example.coindeskdemo.repository.CurrencyRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository repository;

    public String getChineseName(String code) {
        Optional<Currency> opt = repository.findById(code);
        return opt.map(Currency::getNameZh).orElse("");
    }
}
