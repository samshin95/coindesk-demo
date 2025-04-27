package com.example.coindeskdemo.repository;

import com.example.coindeskdemo.entity.Currency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class CurrencyRepositoryTest {

    @Autowired
    private CurrencyRepository repo;

    @Test
    void testFindAllSorted() {
        repo.save(new Currency("C","幣C"));
        repo.save(new Currency("A","幣A"));
        repo.save(new Currency("B","幣B"));
        List<Currency> sorted = repo.findAllSorted();
        assertThat(sorted).extracting(Currency::getCode).containsExactly("A","B","C");
    }
}
