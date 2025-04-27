package com.example.coindeskdemo.service;

import com.example.coindeskdemo.dto.CoindeskDto;
import com.example.coindeskdemo.dto.CurrencyRate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;

@Service
public class CoindeskService {
    private static final String API_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";
    private static final Logger logger = LoggerFactory.getLogger(CoindeskService.class);
    @Autowired private CurrencyService currencyService;
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    public CoindeskDto getTransformedData() {
        String json;
        try {
            logger.info("Fetching data from CoinDesk API");
            json = restTemplate.getForObject(API_URL, String.class);
        } catch (Exception ex) {
            logger.error("Failed to fetch CoinDesk API, using mock data", ex);
            json = loadMockData();
        }
        return transform(json);
    }
    private String loadMockData() {
        try {
            var res = new ClassPathResource("mock/coindesk-mock.json");
            byte[] bytes = res.getInputStream().readAllBytes();
            return new String(bytes);
        } catch (IOException e) {
            logger.error("Failed to load mock data", e);
            return "{}";
        }
    }
    private CoindeskDto transform(String json) {
        try {
            JsonNode root = objectMapper.readTree(json);
            String iso = root.path("time").path("updatedISO").asText();
            OffsetDateTime odt = OffsetDateTime.parse(iso);
            String formatted = odt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
            JsonNode bpi = root.path("bpi");
            List<CurrencyRate> rates = new ArrayList<>();
            Iterator<String> it = bpi.fieldNames();
            while (it.hasNext()) {
                String code = it.next();
                JsonNode node = bpi.get(code);
                double rate = node.path("rate_float").asDouble();
                String name = currencyService.getChineseName(code);
                rates.add(new CurrencyRate(code, name, rate));
            }
            return new CoindeskDto(formatted, rates);
        } catch (Exception e) {
            logger.error("Error transforming data", e);
            return new CoindeskDto();
        }
    }
    @Scheduled(fixedRate = 3600000)
    public void scheduledSync() {
        CoindeskDto data = getTransformedData();
        logger.info("Scheduled sync data: {}", data);
    }
}
