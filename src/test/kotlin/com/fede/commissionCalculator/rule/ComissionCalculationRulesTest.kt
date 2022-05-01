package com.fede.commissionCalculator.rule

import com.fede.commissionCalculator.model.CommissionCalculatorRequest
import com.fede.commissionCalculator.service.MonthlyTransactionsService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.math.BigDecimal

class CommissionCalculationRulesTest {

    private val monthlyTransactionsService: MonthlyTransactionsService = mock()
    private val highTurnoverCommissionRule = HighTurnoverCommissionRule(monthlyTransactionsService)

    companion object {
        private const val CLIENT_ID = 42
        private const val TRANSACTION_DATE = "2020-01-02"
    }


    @Test
    fun `test that commission is correct when client has exactly enough transactions`() {
        whenever(monthlyTransactionsService.getAmountForClientAndMonth(CLIENT_ID, TRANSACTION_DATE))
            .thenReturn(BigDecimal("1000"))

        assertEquals(
            BigDecimal("0.03"),
            highTurnoverCommissionRule.getCommission(
                BigDecimal("1"),
                CommissionCalculatorRequest(
                    date = TRANSACTION_DATE,
                    amount = "2",
                    currency = "EUR",
                    clientId = CLIENT_ID
                )
            )
        )
    }

    @Test
    fun `test that commission is correct when client has right above transactions`() {
        whenever(monthlyTransactionsService.getAmountForClientAndMonth(CLIENT_ID, TRANSACTION_DATE))
            .thenReturn(BigDecimal("1001"))

        assertEquals(
            BigDecimal("0.03"),
            highTurnoverCommissionRule.getCommission(
                BigDecimal("1"),
                CommissionCalculatorRequest(
                    date = TRANSACTION_DATE,
                    amount = "2",
                    currency = "EUR",
                    clientId = CLIENT_ID
                )
            )
        )
    }

    @Test
    fun `test that commission is null when client has below transactions`() {
        whenever(monthlyTransactionsService.getAmountForClientAndMonth(CLIENT_ID, TRANSACTION_DATE))
            .thenReturn(BigDecimal("999"))

        assertNull(
            highTurnoverCommissionRule.getCommission(
                BigDecimal("1"),
                CommissionCalculatorRequest(
                    date = TRANSACTION_DATE,
                    amount = "2",
                    currency = "EUR",
                    clientId = CLIENT_ID
                )
            )
        )
    }
}