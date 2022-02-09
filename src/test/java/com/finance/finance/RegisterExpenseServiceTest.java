package com.finance.finance;
import com.finance.finance.entities.CostType;
import com.finance.finance.entities.RegisterType;
import com.finance.finance.entities.Transaction;
import com.finance.finance.services.InsertNewTransactionService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class RegisterExpenseServiceTest {

    @Test
    public void should_insert_new_fixed_expense(){
        InsertNewTransactionService insertNewTransaction = new InsertNewTransactionService();
        RegisterType register = RegisterType.FIXED_EXPENSE;
        CostType costType = CostType.EXPENSE;

        insertNewTransaction.insertRegister(new Transaction("Conta de luz", new BigDecimal("200"), LocalDate.now(), register, costType));
        insertNewTransaction.insertRegister(new Transaction("Aluguel", new BigDecimal("1000"), LocalDate.now(), register, costType));
        insertNewTransaction.insertRegister(new Transaction("Condominio", new BigDecimal("300"), LocalDate.now(), register, costType));

        BigDecimal expectedValue = new BigDecimal("1500");
        BigDecimal sumValue = insertNewTransaction.getSumByValueType(register);

        assertEquals(expectedValue, sumValue);
    }

    @Test
    public void should_insert_new_variable_expense(){
        InsertNewTransactionService insertNewTransaction = new InsertNewTransactionService();
        RegisterType register = RegisterType.VARIABLE_EXPENSE;
        CostType costType = CostType.EXPENSE;

        insertNewTransaction.insertRegister(new Transaction("Cartão de Crédito", new BigDecimal("500"), LocalDate.now(), register, costType));
        insertNewTransaction.insertRegister(new Transaction("Despesas com Carro", new BigDecimal("1000"), LocalDate.now(), register, costType));

        BigDecimal expectedValue = new BigDecimal("1500");
        BigDecimal sumValue = insertNewTransaction.getSumByValueType(register);

        assertEquals(expectedValue, sumValue);
    }

    @Test
    public void should_insert_new_fixed_contribution(){
        InsertNewTransactionService insertNewTransaction = new InsertNewTransactionService();
        RegisterType register = RegisterType.FIXED_CONTRIBUTION;
        CostType costType = CostType.EXPENSE;

        insertNewTransaction.insertRegister(new Transaction("Contribuição Social - Fixa", new BigDecimal("500"), LocalDate.now(), register, costType));

        BigDecimal expectedValue = new BigDecimal("500");
        BigDecimal sumValue = insertNewTransaction.getSumByValueType(register);

        assertEquals(expectedValue, sumValue);
    }

    @Test
    public void should_insert_new_variable_contribution(){
        InsertNewTransactionService insertNewTransaction = new InsertNewTransactionService();
        RegisterType register = RegisterType.FIXED_CONTRIBUTION;
        CostType costType = CostType.EXPENSE;

        insertNewTransaction.insertRegister(new Transaction("Contribuição Social - variável", new BigDecimal("100"), LocalDate.now(), register, costType));

        BigDecimal expectedValue = new BigDecimal("100");
        BigDecimal sumValue = insertNewTransaction.getSumByValueType(register);

        assertEquals(expectedValue, sumValue);
    }

    @Test
    public void should_sum_all_expenses(){
        InsertNewTransactionService insertNewTransaction = new InsertNewTransactionService();
        CostType costType = CostType.EXPENSE;

        insertNewTransaction.insertRegister(new Transaction("Conta de luz", new BigDecimal("200"), LocalDate.now(), RegisterType.FIXED_EXPENSE, costType));
        insertNewTransaction.insertRegister(new Transaction("Aluguel", new BigDecimal("1000"), LocalDate.now(), RegisterType.FIXED_EXPENSE, costType));
        insertNewTransaction.insertRegister(new Transaction("Condominio", new BigDecimal("300"), LocalDate.now(), RegisterType.FIXED_EXPENSE, costType));

        insertNewTransaction.insertRegister(new Transaction("Cartão de Crédito", new BigDecimal("500"), LocalDate.now(), RegisterType.VARIABLE_EXPENSE, costType));
        insertNewTransaction.insertRegister(new Transaction("Despesas com Carro", new BigDecimal("1000"), LocalDate.now(), RegisterType.VARIABLE_EXPENSE, costType));

        insertNewTransaction.insertRegister(new Transaction("Contribuição Social - Fixa", new BigDecimal("500"), LocalDate.now(), RegisterType.FIXED_CONTRIBUTION, costType));

        insertNewTransaction.insertRegister(new Transaction("Contribuição Social - variável", new BigDecimal("100"), LocalDate.now(), RegisterType.VARIABLE_CONTRIBUTION, costType));

        BigDecimal expectedValue = new BigDecimal("3600");
        BigDecimal sumValues = insertNewTransaction.getSumByCostType(costType);

        assertEquals(expectedValue, sumValues);
    }

}
