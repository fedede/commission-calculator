package com.fede.commissionCalculator.persistence

import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDate

@Table("transactions")
data class TransactionPersistenceModel(
    val clientId : Int,
    val date: LocalDate,
    val currency: String,
    val amount: BigDecimal
)
