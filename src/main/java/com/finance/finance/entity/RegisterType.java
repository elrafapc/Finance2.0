package com.finance.finance.entity;

import java.math.BigDecimal;

public enum RegisterType {
    VALUE_ENTRY {
        @Override
        public BigDecimal destinedPercentage(BigDecimal valueEntry){
            return valueEntry.multiply(BigDecimal.valueOf(0));
        }
    },
    BENEFIT {
        @Override
        public BigDecimal destinedPercentage(BigDecimal valueEntry){
            return valueEntry.multiply(BigDecimal.valueOf(0));
        }
    },
    SAVINGS_ACCOUNT {
        @Override
        public BigDecimal destinedPercentage(BigDecimal valueEntry){
            return valueEntry.multiply(BigDecimal.valueOf(0.20));
        }
    },
    FIXED_EXPENSE {
        @Override
        public BigDecimal destinedPercentage(BigDecimal valueEntry){
            return valueEntry.multiply(BigDecimal.valueOf(0.27));
        }
    },
    VARIABLE_EXPENSE{
        @Override
        public BigDecimal destinedPercentage(BigDecimal valueEntry){
            return valueEntry.multiply(BigDecimal.valueOf(0.38));
        }
    },
    FIXED_CONTRIBUTION {
        @Override
        public BigDecimal destinedPercentage(BigDecimal valueEntry){
            return valueEntry.multiply(BigDecimal.valueOf(0.10));
        }
    },
    VARIABLE_CONTRIBUTION {
        @Override
        public BigDecimal destinedPercentage(BigDecimal valueEntry){
            return valueEntry.multiply(BigDecimal.valueOf(0.05));
        }
    };

    public abstract BigDecimal destinedPercentage(BigDecimal valueEntry);
}
