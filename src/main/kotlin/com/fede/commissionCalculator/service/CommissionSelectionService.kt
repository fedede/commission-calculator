package com.fede.commissionCalculator.service

import com.fede.commissionCalculator.model.CommissionCalculatorRequest
import com.fede.commissionCalculator.rule.ICommissionCalculationRule
import com.fede.commissionCalculator.rule.ICommissionSelectionRule
import java.math.BigDecimal

class CommissionSelectionService(
    private val commissionCalculationRules: List<ICommissionCalculationRule>,
    private val commissionSelectionRule: ICommissionSelectionRule
) {
    fun getCommission(amount: BigDecimal, request: CommissionCalculatorRequest): BigDecimal {
        val possibleCommissions: List<BigDecimal> =
            commissionCalculationRules.map { it.getCommission(amount, request) }.filterNotNull()
        return commissionSelectionRule.selectCommission(possibleCommissions)
