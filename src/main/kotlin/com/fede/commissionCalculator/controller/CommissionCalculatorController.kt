package com.fede.commissionCalculator.controller

import com.fede.commissionCalculator.model.CommissionCalculatorRequest
import com.fede.commissionCalculator.model.CommissionCalculatorResponse
import com.fede.commissionCalculator.service.MonthlyTransactionsService
import com.fede.commissionCalculator.service.CommissionSelectionService
import com.fede.commissionCalculator.service.CurrencyConversionService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

private val log = KotlinLogging.logger {}

@RestController
class CommissionCalculatorController @Autowired constructor(
    val conversionService: CurrencyConversionService,
    val commissionSelectionService: CommissionSelectionService
) {

    @PostMapping(value = ["/"], consumes = ["application/json"], produces = ["application/json"])
    fun handleRequest(@RequestBody request: CommissionCalculatorRequest): CommissionCalculatorResponse {
        log.info("Received request: $request")
        val convertedAmount = conversionService.convertCurrency(
            date = request.date,
            amount = request.amount,
            fromCurrencyCode = request.currency
        )
        val commission = commissionSelectionService.getCommission(convertedAmount, request)
        return CommissionCalculatorResponse(amount = commission.toPlainString(), currency = "EUR")
    }
}