package com.finance.finance.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

    private String description;
    private BigDecimal registerValue;
    private LocalDate date;
    RegisterType registerType;
    CostType costType;

    public Transaction(String description, BigDecimal registerValue, LocalDate date, RegisterType registerType, CostType costType) {
        this.description = description;
        this.registerValue = registerValue;
        this.date = date;
        this.registerType = registerType;
        this.costType = costType;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getRegisterValue() {
        return registerValue;
    }

    public LocalDate getDate() {
        return date;
    }

    public RegisterType getRegisterType() {
        return registerType;
    }

    public CostType getCostType() {
        return costType;
    }
}
