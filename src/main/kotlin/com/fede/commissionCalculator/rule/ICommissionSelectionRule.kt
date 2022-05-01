package com.fede.commissionCalculator.rule

import java.math.BigDecimal

interface ICommissionSelectionRule {

    fun selectCommission(commissionList: List<BigDecimal>): BigDecimal

}