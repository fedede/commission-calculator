package com.fede.commissionCalculator.rule

import com.fede.commissionCalculator.model.CommissionCalculatorRequest
import java.math.BigDecimal

interface ICommissionCalculationRule {

    fun getCommission(amount: BigDecimal, request: CommissionCalculatorRequest): BigDecimal?
}