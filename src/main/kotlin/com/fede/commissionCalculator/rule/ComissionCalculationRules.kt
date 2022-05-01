package com.fede.commissionCalculator.rule

import com.fede.commissionCalculator.model.CommissionCalculatorRequest
import com.fede.commissionCalculator.service.MonthlyTransactionsService
import java.math.BigDecimal

class DefaultCommissionRule : ICommissionCalculationRule {
    override fun getCommission(amount: BigDecimal, request: CommissionCalculatorRequest): BigDecimal? {
        return amount.multiply(COMMISSION_PERCENTAGE).max(MIN_COMMISSION)
    }

    companion object {
        private val COMMISSION_PERCENTAGE = BigDecimal("0.005")
        private val MIN_COMMISSION = BigDecimal("0.05")
    }
}

class ClientDiscountCommissionRule : ICommissionCalculationRule {

    override fun getCommission(amount: BigDecimal, request: CommissionCalculatorRequest): BigDecimal? {
        return if (request.clientId in CLIENT_IDS_WITH_DISCOUNT) COMMISSION else null
    }

    companion object {
        private val CLIENT_IDS_WITH_DISCOUNT = setOf(42)
        private val COMMISSION = BigDecimal("0.05")
    }
}

class HighTurnoverCommissionRule(private val monthlyTransactionsService: MonthlyTransactionsService) :
    ICommissionCalculationRule {
    override fun getCommission(amount: BigDecimal, request: CommissionCalculatorRequest): BigDecimal? {
        val monthlyAmount = monthlyTransactionsService.getAmountForClientAndMonth(request.clientId, request.date)
        return if (monthlyAmount >= MONTHLY_AMOUNT_FOR_DISCOUNT) COMMISSION else null
    }

    companion object {
        private val MONTHLY_AMOUNT_FOR_DISCOUNT = BigDecimal("1000")
        private val COMMISSION = BigDecimal("0.03")
    }
}