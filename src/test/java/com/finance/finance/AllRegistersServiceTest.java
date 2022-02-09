package com.finance.finance;
import com.finance.finance.entities.CostType;
import com.finance.finance.entities.RegisterType;
import com.finance.finance.entities.Transaction;
import com.finance.finance.services.InsertNewTransactionService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class AllRegistersServiceTest {

    @Test
    public void should_insert_all_registers_and_return_final_balance(){
        InsertNewTransactionService insertNewTransaction = new InsertNewTransactionService();

        insertNewTransaction.insertRegister(new Transaction("Salario 1", new BigDecimal("5000"), LocalDate.now(), RegisterType.VALUE_ENTRY, CostType.INCOME));
        insertNewTransaction.insertRegister(new Transaction("Salario 2", new BigDecimal("5000"), LocalDate.now(), RegisterType.VALUE_ENTRY, CostType.INCOME));

        insertNewTransaction.insertRegister(new Transaction("VA/VR", new BigDecimal("1000"), LocalDate.now(), RegisterType.BENEFIT, CostType.INCOME));

        insertNewTransaction.insertRegister(new Transaction("Conta de luz", new BigDecimal("200"), LocalDate.now(), RegisterType.FIXED_EXPENSE, CostType.EXPENSE));
        insertNewTransaction.insertRegister(new Transaction("Aluguel", new BigDecimal("1000"), LocalDate.now(), RegisterType.FIXED_EXPENSE, CostType.EXPENSE));
        insertNewTransaction.insertRegister(new Transaction("Condominio", new BigDecimal("300"), LocalDate.now(), RegisterType.FIXED_EXPENSE, CostType.EXPENSE));

        insertNewTransaction.insertRegister(new Transaction("Cartão de Crédito", new BigDecimal("500"), LocalDate.now(), RegisterType.VARIABLE_EXPENSE, CostType.EXPENSE));
        insertNewTransaction.insertRegister(new Transaction("Despesas com Carro", new BigDecimal("1000"), LocalDate.now(), RegisterType.VARIABLE_EXPENSE, CostType.EXPENSE));

        insertNewTransaction.insertRegister(new Transaction("Contribuição Social - Fixa", new BigDecimal("500"), LocalDate.now(), RegisterType.FIXED_CONTRIBUTION, CostType.EXPENSE));

        insertNewTransaction.insertRegister(new Transaction("Contribuição Social - variável", new BigDecimal("100"), LocalDate.now(), RegisterType.VARIABLE_CONTRIBUTION, CostType.EXPENSE));

        BigDecimal expectedValue = new BigDecimal("7400");
        BigDecimal sumValueEntry = insertNewTransaction.getFinalRegisterBalance();

        assertEquals(expectedValue, sumValueEntry);
    }


}
