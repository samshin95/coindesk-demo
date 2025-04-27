package com.example.coindeskdemo.controller;

import com.example.coindeskdemo.entity.Currency;
import com.example.coindeskdemo.repository.CurrencyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CurrencyController.class)
class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyRepository repo;

    @Test
    void testList() throws Exception {
        when(repo.findAll(Sort.by("code")))
            .thenReturn(Arrays.asList(new Currency("B","幣B"), new Currency("A","幣A")));
        mockMvc.perform(get("/currencies").accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].code").value("B"))
               .andExpect(jsonPath("$[1].nameZh").value("幣A"));
    }
}
