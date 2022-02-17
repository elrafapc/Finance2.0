package com.finance.finance.repository;

import com.finance.finance.entity.CostType;
import com.finance.finance.entity.RegisterType;
import java.math.BigDecimal;


public interface TransactionSumRepository {

    public BigDecimal getIncomeSubtractExpense();

    public BigDecimal getSumByRegisterType(RegisterType registerType);

    public BigDecimal getSumByCostType(CostType costType);

    public BigDecimal getPercentageToExpenseByRegisterType(BigDecimal sumByValueType, RegisterType registerType);

}
