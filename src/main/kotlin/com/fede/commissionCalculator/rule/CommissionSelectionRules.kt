package com.fede.commissionCalculator.rule

import java.math.BigDecimal
import java.util.Collections.min

class MinCommissionSelectionRule : ICommissionSelectionRule {
    override fun selectCommission(commissionList: List<BigDecimal>): BigDecimal {
        return min(commissionList)
    }
}