package com.finance.finance;
import com.finance.finance.entity.CostType;
import com.finance.finance.entity.RegisterType;
import com.finance.finance.entity.Transaction;
import com.finance.finance.controller.TransactionController;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AllRegistersServiceTest {

    @Test
    public void should_insert_all_registers_and_return_final_balance(){
        TransactionController insertNewTransaction = new TransactionController();

        insertNewTransaction.insertIncome(new Transaction("Salario 1", new BigDecimal("5000"), LocalDate.now(), RegisterType.VALUE_ENTRY, CostType.INCOME));
        insertNewTransaction.insertIncome(new Transaction("Salario 2", new BigDecimal("5000"), LocalDate.now(), RegisterType.VALUE_ENTRY, CostType.INCOME));

        insertNewTransaction.insertIncome(new Transaction("VA/VR", new BigDecimal("1000"), LocalDate.now(), RegisterType.BENEFIT, CostType.INCOME));

        insertNewTransaction.insertExpense(new Transaction("Conta de luz", new BigDecimal("200"), LocalDate.now(), RegisterType.FIXED_EXPENSE, CostType.EXPENSE), insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY));
        insertNewTransaction.insertExpense(new Transaction("Aluguel", new BigDecimal("1000"), LocalDate.now(), RegisterType.FIXED_EXPENSE, CostType.EXPENSE), insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY));
        insertNewTransaction.insertExpense(new Transaction("Condominio", new BigDecimal("300"), LocalDate.now(), RegisterType.FIXED_EXPENSE, CostType.EXPENSE), insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY));

        insertNewTransaction.insertExpense(new Transaction("Cartão de Crédito", new BigDecimal("500"), LocalDate.now(), RegisterType.VARIABLE_EXPENSE, CostType.EXPENSE), insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY));
        insertNewTransaction.insertExpense(new Transaction("Despesas com Carro", new BigDecimal("1000"), LocalDate.now(), RegisterType.VARIABLE_EXPENSE, CostType.EXPENSE), insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY));

        insertNewTransaction.insertExpense(new Transaction("Contribuição Social - Fixa", new BigDecimal("500"), LocalDate.now(), RegisterType.FIXED_CONTRIBUTION, CostType.EXPENSE), insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY));

        insertNewTransaction.insertExpense(new Transaction("Contribuição Social - variável", new BigDecimal("100"), LocalDate.now(), RegisterType.VARIABLE_CONTRIBUTION, CostType.EXPENSE), insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY));

        insertNewTransaction.insertExpense(new Transaction("Poupança", new BigDecimal("2000"), LocalDate.now(), RegisterType.SAVINGS_ACCOUNT, CostType.SAVING), insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY));

        BigDecimal expectedValue = new BigDecimal("5400");
        BigDecimal sumValueEntry = insertNewTransaction.getIncomeSubtractExpense();

        assertEquals(expectedValue, sumValueEntry);
    }

    @Test
    public void should_return_the_percentual_available_considering_the_value_entry_income(){
        TransactionController insertNewTransaction = new TransactionController();
        RegisterType valueEntry = RegisterType.VALUE_ENTRY;

        insertNewTransaction.insertIncome(new Transaction("Salario 1", new BigDecimal("5000"), LocalDate.now(), valueEntry, CostType.INCOME));
        insertNewTransaction.insertIncome(new Transaction("Salario 2", new BigDecimal("5000"), LocalDate.now(), valueEntry, CostType.INCOME));

        BigDecimal fixedExpense = insertNewTransaction.getPercentageToExpenseByRegisterType(insertNewTransaction.getSumByRegisterType(valueEntry), RegisterType.FIXED_EXPENSE);
        BigDecimal fixedExpenseValueExpected = new BigDecimal("2700.00");

        assertEquals(fixedExpenseValueExpected, fixedExpense);
    }

    @Test
    public void should_return_an_exception_by_high_spend(){
        TransactionController insertNewTransaction = new TransactionController();

        insertNewTransaction.insertIncome(new Transaction("Salario 1", new BigDecimal("5000"), LocalDate.now(), RegisterType.VALUE_ENTRY, CostType.INCOME));

        Transaction transaction = new Transaction("Conta de luz", new BigDecimal("4000"), LocalDate.now(), RegisterType.FIXED_EXPENSE, CostType.EXPENSE);

        assertThrows(IllegalArgumentException.class, ()-> insertNewTransaction.insertExpense(transaction, insertNewTransaction.getSumByRegisterType(RegisterType.VALUE_ENTRY)));
    }


}
