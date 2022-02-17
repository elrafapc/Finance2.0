package com.finance.finance;
import com.finance.finance.controller.TransactionCRUDControllerByMemory;
import com.finance.finance.entity.CostType;
import com.finance.finance.entity.RegisterType;
import com.finance.finance.entity.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class TransactionControllerByMemoryIncomeTest {

    @Test
    public void should_insert_new_value_entry(){
        TransactionCRUDControllerByMemory insertNewTransaction = new TransactionCRUDControllerByMemory();
        RegisterType register = RegisterType.VALUE_ENTRY;
        CostType costType = CostType.INCOME;

        insertNewTransaction.insertIncome(new Transaction("Salario 1", new BigDecimal("5000"), LocalDate.now(), register, costType));
        insertNewTransaction.insertIncome(new Transaction("Salario 2", new BigDecimal("5000"), LocalDate.now(), register, costType));

        BigDecimal expectedValue = new BigDecimal("10000");
        BigDecimal sumValueEntry = insertNewTransaction.getSumByRegisterType(register);

        assertEquals(expectedValue, sumValueEntry);
    }

    @Test
    public void should_insert_new_benefit(){
        TransactionCRUDControllerByMemory insertNewTransaction = new TransactionCRUDControllerByMemory();
        RegisterType register = RegisterType.BENEFIT;
        CostType costType = CostType.INCOME;

        insertNewTransaction.insertIncome(new Transaction("VA/VR", new BigDecimal("1000"), LocalDate.now(), register, costType));

        BigDecimal expectedValue = new BigDecimal("1000");
        BigDecimal sumValues = insertNewTransaction.getSumByRegisterType(register);

        assertEquals(expectedValue, sumValues);
    }

    @Test
    public void should_sum_all_incomes(){
        TransactionCRUDControllerByMemory insertNewTransaction = new TransactionCRUDControllerByMemory();
        CostType costType = CostType.INCOME;

        insertNewTransaction.insertIncome(new Transaction("Salario 1", new BigDecimal("5000"), LocalDate.now(), RegisterType.VALUE_ENTRY, costType));
        insertNewTransaction.insertIncome(new Transaction("VA/VR", new BigDecimal("1000"), LocalDate.now(), RegisterType.BENEFIT, costType));

        BigDecimal expectedValue = new BigDecimal("6000");
        BigDecimal sumValues = insertNewTransaction.getSumByCostType(costType);

        assertEquals(expectedValue, sumValues);
    }


}
