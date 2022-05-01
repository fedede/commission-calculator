package com.fede.commissionCalculator.service

import com.fede.commissionCalculator.adapter.CurrencyExchangeRateAdapter
import mu.KotlinLogging
import java.math.BigDecimal

private val log = KotlinLogging.logger { }

class CurrencyConversionService(private val exchangeRatesAdapter: CurrencyExchangeRateAdapter) {

    fun convertCurrency(date: String, amount: String, fromCurrencyCode: String, toCurrencyCode: String = "EUR"): BigDecimal {
        if (toCurrencyCode !in SUPPORTED_CURRENCIES) {
            throw NotImplementedError("Can't convert to this currency at the time")
        }
        val rates = exchangeRatesAdapter.getExchangeRatesForDate(date)
        val inputAmountDecimal = BigDecimal(amount)
        val rateDecimal = BigDecimal(rates[fromCurrencyCode])
        return inputAmountDecimal.divide(rateDecimal, 4)
    }

    companion object {
        private val SUPPORTED_CURRENCIES = setOf("EUR")
    }
}