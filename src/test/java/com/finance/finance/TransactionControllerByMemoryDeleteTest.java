package com.finance.finance;

import com.finance.finance.controller.TransactionControllerByMemory;
import com.finance.finance.entity.CostType;
import com.finance.finance.entity.RegisterType;
import com.finance.finance.entity.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class TransactionControllerByMemoryDeleteTest {

    @Test
    public void should_remove_some_register(){
        TransactionControllerByMemory controller = new TransactionControllerByMemory();
        RegisterType register = RegisterType.VALUE_ENTRY;
        CostType costType = CostType.INCOME;

        controller.insertIncome(
                new Transaction("Salario 1", new BigDecimal("5000"), LocalDate.now(), register, costType));
        controller.insertIncome(
                new Transaction("Salario 2", new BigDecimal("5000"), LocalDate.now(), register, costType));
        controller.insertExpense(
                new Transaction("Conta de luz", new BigDecimal("200"), LocalDate.now(), RegisterType.FIXED_EXPENSE, CostType.EXPENSE),
                controller.getSumByRegisterType(RegisterType.VALUE_ENTRY));
        controller.insertExpense(
                new Transaction("Aluguel", new BigDecimal("1000"), LocalDate.now(), RegisterType.FIXED_EXPENSE, CostType.EXPENSE),
                controller.getSumByRegisterType(RegisterType.VALUE_ENTRY));

        controller.removeTransaction("Aluguel");

        BigDecimal expectedValue = new BigDecimal("9800");
        BigDecimal finalValue = controller.getIncomeSubtractExpense();

        assertEquals(expectedValue, finalValue);
    }
}
