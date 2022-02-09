package com.finance.finance.services;

import com.finance.finance.entities.CostType;
import com.finance.finance.entities.RegisterType;
import com.finance.finance.entities.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InsertNewTransactionService {

    private List<Transaction> register = new ArrayList<>();

    public void insertRegister(Transaction transaction) {
        register.add(transaction);
    }

    public BigDecimal getSumByValueType(RegisterType registerType) {
        BigDecimal value = BigDecimal.ZERO;

        for(Transaction transaction : this.register){
            if(transaction.getRegisterType().equals(registerType)){
                value = value.add(
                        transaction.getRegisterValue());
            }
        }

        return value;
    }

    public BigDecimal getSumByCostType(CostType costType) {
        BigDecimal value = BigDecimal.ZERO;

        for(Transaction transaction : this.register){
            if(transaction.getCostType().equals(costType)){
                value = value.add(
                        transaction.getRegisterValue());
            }
        }

        return value;
    }
}
