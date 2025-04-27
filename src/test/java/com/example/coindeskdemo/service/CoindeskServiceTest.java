package com.example.coindeskdemo.service;

import com.example.coindeskdemo.dto.CoindeskDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoindeskServiceTest {

    @Autowired
    private CoindeskService service;

    @MockBean
    private CurrencyService currencyService;

    @BeforeEach
    void setup() {
        when(currencyService.getChineseName("USD")).thenReturn("美元");
        when(currencyService.getChineseName("GBP")).thenReturn("英鎊");
        when(currencyService.getChineseName("EUR")).thenReturn("歐元");
    }

    @Test
    void testGetTransformedData() {
        CoindeskDto dto = service.getTransformedData();
        assertNotNull(dto.getUpdateTime());
        assertEquals(3, dto.getRates().size());
    }
}
