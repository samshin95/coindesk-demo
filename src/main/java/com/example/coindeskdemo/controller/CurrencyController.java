package com.example.coindeskdemo.controller;

import com.example.coindeskdemo.entity.Currency;
import com.example.coindeskdemo.repository.CurrencyRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {
    @Autowired private CurrencyRepository repo;

    @GetMapping
    public List<Currency> list() { return repo.findAll(Sort.by("code")); }

    @GetMapping("/{code}")
    public ResponseEntity<Currency> get(@PathVariable String code) {
        return repo.findById(code).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Currency create(@RequestBody Currency currency) { return repo.save(currency); }

    @PutMapping("/{code}")
    public ResponseEntity<Currency> update(@PathVariable String code, @RequestBody Currency newC) {
        return repo.findById(code).map(c -> {
            c.setNameZh(newC.getNameZh());
            repo.save(c);
            return ResponseEntity.ok(c);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable String code) {
        if (repo.existsById(code)) {
            repo.deleteById(code);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
