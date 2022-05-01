package com.fede.commissionCalculator.persistence

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.math.BigDecimal
import java.time.LocalDate

interface TransactionRepository : CrudRepository<TransactionPersistenceModel, Int> {

    @Query(
        "select SUM(amount) from transactions " +
                "WHERE client_id=:client_id " +
                "AND year(date) = year(:date) " +
                "AND month(date)=month(:date)"
    )
    fun findAmountForMonth(
        @Param("client_id") clientId: Int,
        @Param("date") date: LocalDate
    ): BigDecimal?
}