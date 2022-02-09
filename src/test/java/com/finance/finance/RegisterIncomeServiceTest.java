package com.finance.finance;
import com.finance.finance.entities.CostType;
import com.finance.finance.entities.RegisterType;
import com.finance.finance.entities.Transaction;
import com.finance.finance.services.InsertNewTransactionService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class RegisterIncomeServiceTest {

    @Test
    public void should_insert_new_value_entry(){
        InsertNewTransactionService insertNewTransaction = new InsertNewTransactionService();
        RegisterType register = RegisterType.VALUE_ENTRY;
        CostType costType = CostType.INCOME;

        insertNewTransaction.insertRegister(new Transaction("Salario 1", new BigDecimal("5000"), LocalDate.now(), register, costType));
        insertNewTransaction.insertRegister(new Transaction("Salario 2", new BigDecimal("5000"), LocalDate.now(), register, costType));

        BigDecimal expectedValue = new BigDecimal("10000");
        BigDecimal sumValueEntry = insertNewTransaction.getSumByValueType(register);

        assertEquals(expectedValue, sumValueEntry);
    }

    @Test
    public void should_insert_new_benefit(){
        InsertNewTransactionService insertNewTransaction = new InsertNewTransactionService();
        RegisterType register = RegisterType.BENEFIT;
        CostType costType = CostType.INCOME;

        insertNewTransaction.insertRegister(new Transaction("VA/VR", new BigDecimal("1000"), LocalDate.now(), register, costType));

        BigDecimal expectedValue = new BigDecimal("1000");
        BigDecimal sumValues = insertNewTransaction.getSumByValueType(register);

        assertEquals(expectedValue, sumValues);
    }

    @Test
    public void should_sum_all_incomes(){
        InsertNewTransactionService insertNewTransaction = new InsertNewTransactionService();
        CostType costType = CostType.INCOME;

        insertNewTransaction.insertRegister(new Transaction("Salario 1", new BigDecimal("5000"), LocalDate.now(), RegisterType.VALUE_ENTRY, costType));
        insertNewTransaction.insertRegister(new Transaction("VA/VR", new BigDecimal("1000"), LocalDate.now(), RegisterType.BENEFIT, costType));

        BigDecimal expectedValue = new BigDecimal("6000");
        BigDecimal sumValues = insertNewTransaction.getSumByCostType(costType);

        assertEquals(expectedValue, sumValues);
    }


}
