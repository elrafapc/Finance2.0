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

    public BigDecimal getIncomeSumByValueType(RegisterType registerType) {
        BigDecimal value = BigDecimal.ZERO;

        for(Transaction transaction : this.register){
            if(transaction.getRegisterType().equals(registerType)){
                value = value.add(
                        transaction.getRegisterValue());
            }
        }

        return value;
    }

    public BigDecimal getSumAllIncomes() {
        BigDecimal value = BigDecimal.ZERO;

        for(Transaction transaction : this.register){
            if(transaction.getCostType().equals(CostType.INCOME)){
                value = value.add(
                        transaction.getRegisterValue());
            }
        }

        return value;
    }
}
