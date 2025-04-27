package com.example.coindeskdemo.dto;

import java.util.List;

public class CoindeskDto {
    private String updateTime;
    private List<CurrencyRate> rates;
    public CoindeskDto() {}
    public CoindeskDto(String updateTime, List<CurrencyRate> rates) {
        this.updateTime = updateTime; this.rates = rates;
    }
    public String getUpdateTime() { return updateTime; }
    public void setUpdateTime(String updateTime) { this.updateTime = updateTime; }
    public List<CurrencyRate> getRates() { return rates; }
    public void setRates(List<CurrencyRate> rates) { this.rates = rates; }
}
