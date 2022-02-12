package com.finance.finance;
import com.finance.finance.entity.CostType;
import com.finance.finance.entity.RegisterType;
import com.finance.finance.entity.Transaction;
import com.finance.finance.controller.TransactionController;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class RegisterExpenseServiceTest {

    @Test
    public void should_insert_new_fixed_expense(){
        TransactionController insertNewTransaction = new TransactionController();
        RegisterType register = RegisterType.FIXED_EXPENSE;
        CostType costType = CostType.EXPENSE;

        insertNewTransaction.insertExpense(new Transaction("Conta de luz", new BigDecimal("200"), LocalDate.now(), register, costType), insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY));
        insertNewTransaction.insertExpense(new Transaction("Aluguel", new BigDecimal("1000"), LocalDate.now(), register, costType), insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY));
        insertNewTransaction.insertExpense(new Transaction("Condominio", new BigDecimal("300"), LocalDate.now(), register, costType), insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY));

        BigDecimal expectedValue = new BigDecimal("1500");
        BigDecimal sumValue = insertNewTransaction.getSumByRegisterType(register);

        assertEquals(expectedValue, sumValue);
    }

    @Test
    public void should_insert_new_variable_expense(){
        TransactionController insertNewTransaction = new TransactionController();
        RegisterType register = RegisterType.VARIABLE_EXPENSE;
        CostType costType = CostType.EXPENSE;

        insertNewTransaction.insertExpense(new Transaction("Cartão de Crédito", new BigDecimal("500"), LocalDate.now(), register, costType), insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY));
        insertNewTransaction.insertExpense(new Transaction("Despesas com Carro", new BigDecimal("1000"), LocalDate.now(), register, costType), insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY));

        BigDecimal expectedValue = new BigDecimal("1500");
        BigDecimal sumValue = insertNewTransaction.getSumByRegisterType(register);

        assertEquals(expectedValue, sumValue);
    }

    @Test
    public void should_insert_new_fixed_contribution(){
        TransactionController insertNewTransaction = new TransactionController();
        RegisterType register = RegisterType.FIXED_CONTRIBUTION;
        CostType costType = CostType.EXPENSE;

        insertNewTransaction.insertExpense(new Transaction("Contribuição Social - Fixa", new BigDecimal("500"), LocalDate.now(), register, costType), insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY));

        BigDecimal expectedValue = new BigDecimal("500");
        BigDecimal sumValue = insertNewTransaction.getSumByRegisterType(register);

        assertEquals(expectedValue, sumValue);
    }

    @Test
    public void should_insert_new_variable_contribution(){
        TransactionController insertNewTransaction = new TransactionController();
        RegisterType register = RegisterType.FIXED_CONTRIBUTION;
        CostType costType = CostType.EXPENSE;

        insertNewTransaction.insertExpense(new Transaction("Contribuição Social - variável", new BigDecimal("100"), LocalDate.now(), register, costType), insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY));

        BigDecimal expectedValue = new BigDecimal("100");
        BigDecimal sumValue = insertNewTransaction.getSumByRegisterType(register);

        assertEquals(expectedValue, sumValue);
    }

    @Test
    public void should_sum_all_expenses(){
        TransactionController insertNewTransaction = new TransactionController();
        CostType costType = CostType.EXPENSE;

        insertNewTransaction.insertExpense(new Transaction("Conta de luz", new BigDecimal("200"), LocalDate.now(), RegisterType.FIXED_EXPENSE, costType), insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY));
        insertNewTransaction.insertExpense(new Transaction("Aluguel", new BigDecimal("1000"), LocalDate.now(), RegisterType.FIXED_EXPENSE, costType), insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY));
        insertNewTransaction.insertExpense(new Transaction("Condominio", new BigDecimal("300"), LocalDate.now(), RegisterType.FIXED_EXPENSE, costType), insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY));

        insertNewTransaction.insertExpense(new Transaction("Cartão de Crédito", new BigDecimal("500"), LocalDate.now(), RegisterType.VARIABLE_EXPENSE, costType), insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY));
        insertNewTransaction.insertExpense(new Transaction("Despesas com Carro", new BigDecimal("1000"), LocalDate.now(), RegisterType.VARIABLE_EXPENSE, costType), insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY));

        insertNewTransaction.insertExpense(new Transaction("Contribuição Social - Fixa", new BigDecimal("500"), LocalDate.now(), RegisterType.FIXED_CONTRIBUTION, costType), insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY));

        insertNewTransaction.insertExpense(new Transaction("Contribuição Social - variável", new BigDecimal("100"), LocalDate.now(), RegisterType.VARIABLE_CONTRIBUTION, costType), insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY));

        BigDecimal expectedValue = new BigDecimal("3600");
        BigDecimal sumValues = insertNewTransaction.getSumByCostType(costType);

        assertEquals(expectedValue, sumValues);
    }

}
