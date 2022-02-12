package com.finance.finance.repository;

import com.finance.finance.entity.CostType;
import com.finance.finance.entity.RegisterType;
import com.finance.finance.entity.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface TransactionRepository {
    List<Transaction> transactions = new ArrayList<>();

    public void insertIncome(Transaction newRegister);

    public void insertExpense(Transaction newRegister, BigDecimal sumValueEntries);

    public BigDecimal getIncomeSubtractExpense();

    public BigDecimal getSumByRegisterType(RegisterType registerType);

    public BigDecimal getSumByCostType(CostType costType);

    public BigDecimal getPercentageToExpenseByRegisterType(BigDecimal sumByValueType, RegisterType registerType);

}
