package com.finance.finance.repository;

import com.finance.finance.entity.Transaction;
import java.math.BigDecimal;


public interface TransactionCRUDRepository {
    public void insertIncome(Transaction newRegister);

    public void insertExpense(Transaction newRegister, BigDecimal sumValueEntries);

    public void removeTransaction(String registerName);

    public void editTransaction(String register, Transaction dataToUpdate);

    public void listTransactions();
}
