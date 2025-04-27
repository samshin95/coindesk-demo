package com.example.coindeskdemo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.coindeskdemo.entity.Currency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CurrencyRepositoryCrudTest {

  @Autowired
  private CurrencyRepository repo;

  @Test
  void testCreateReadUpdateDelete() {
    // —— Create ——
    Currency c = new Currency("TWD", "新臺幣");
    repo.save(c);
    // 確認已儲存
    assertTrue(repo.existsById("TWD"));

    // —— Read ——
    Currency fetched = repo.findById("TWD").orElseThrow();
    assertEquals("新臺幣", fetched.getNameZh());

    // —— Update ——
    fetched.setNameZh("台幣");
    repo.save(fetched);
    Currency updated = repo.findById("TWD").orElseThrow();
    assertEquals("台幣", updated.getNameZh());

    // —— Delete ——
    repo.deleteById("TWD");
    assertFalse(repo.existsById("TWD"));
  }
}
