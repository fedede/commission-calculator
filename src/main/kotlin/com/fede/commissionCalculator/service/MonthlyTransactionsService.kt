package com.fede.commissionCalculator.service

import com.fede.commissionCalculator.persistence.TransactionRepository
import java.math.BigDecimal
import java.time.LocalDate

class MonthlyTransactionsService(private val db: TransactionRepository) {

    fun getAmountForClientAndMonth(clientId: Int, date: String): BigDecimal {
        return db.findAmountForMonth(clientId, LocalDate.parse(date)) ?: BigDecimal.ZERO
    }

}