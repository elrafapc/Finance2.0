package com.finance.finance;

import com.finance.finance.controller.TransactionCRUDControllerByMemory;
import com.finance.finance.entity.CostType;
import com.finance.finance.entity.RegisterType;
import com.finance.finance.entity.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class TransactionControllerByMemoryEditTest {

    @Test
    public void should_edite_some_register(){
        TransactionCRUDControllerByMemory controller = new TransactionCRUDControllerByMemory();
        RegisterType register = RegisterType.VALUE_ENTRY;
        CostType costType = CostType.INCOME;

        controller.insertIncome(
                new Transaction("Salario", new BigDecimal("5000"), LocalDate.now(), register, costType));

        controller.editTransaction("Salario",
                new Transaction("Salario Corrigido", new BigDecimal("10000"), LocalDate.now(), register, costType));

        BigDecimal expectedValue = new BigDecimal("10000");
        BigDecimal finalValue = controller.getIncomeSubtractExpense();

        assertEquals(expectedValue, finalValue);
    }
}
