package com.finance.finance.controller;

import com.finance.finance.entity.CostType;
import com.finance.finance.entity.RegisterType;
import com.finance.finance.entity.Transaction;
import com.finance.finance.repository.TransactionRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionController implements TransactionRepository {

    private List<Transaction> register = new ArrayList<>();

    public void insertIncome(Transaction transaction) {
        register.add(transaction);
    }

    public void insertExpense(Transaction transaction, BigDecimal sumValueEntries) {
        RegisterType registerType = transaction.getRegisterType();
        BigDecimal intendedExpense = getPercentageToExpenseByRegisterType(getSumByRegisterType(registerType),registerType);

        int expenseGreaterThanIncome = transaction.getRegisterValue().compareTo(intendedExpense);
        BigDecimal registerValue = transaction.getRegisterValue();

        if( expenseGreaterThanIncome == 1){
            throw new IllegalArgumentException("Valor excede o planejado");
        }else{
            register.add(transaction);}
    }

    public BigDecimal getIncomeSubtractExpense() {
        BigDecimal valueIncome = BigDecimal.ZERO;
        BigDecimal valueExpense = BigDecimal.ZERO;

        for(Transaction transaction : this.register){
            if(transaction.getCostType().equals(CostType.INCOME)){
                valueIncome = valueIncome.add(
                        transaction.getRegisterValue());
            }else{
                valueExpense = valueExpense.add(
                        transaction.getRegisterValue());
            }
        }
        return valueIncome.subtract(valueExpense);
    }

    public BigDecimal getSumByRegisterType(RegisterType registerType) {
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

    public BigDecimal getPercentageToExpenseByRegisterType(BigDecimal sumByValueType, RegisterType registerType) {
         return registerType.destinedPercentage(sumByValueType);
    }
}
