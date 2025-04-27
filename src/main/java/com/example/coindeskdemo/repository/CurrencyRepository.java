package com.example.coindeskdemo.repository;

import com.example.coindeskdemo.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
    default List<Currency> findAllSorted() {
        return findAll(Sort.by("code"));
    }
}
