package com.fede.commissionCalculator.config

import com.fede.commissionCalculator.adapter.CurrencyExchangeRateAdapter
import com.fede.commissionCalculator.service.MonthlyTransactionsService
import com.fede.commissionCalculator.persistence.TransactionRepository
import com.fede.commissionCalculator.rule.*
import com.fede.commissionCalculator.service.CommissionSelectionService
import com.fede.commissionCalculator.service.CurrencyConversionService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.http.HttpClient

@Configuration
class Config {

    @Bean
    fun provideHttpClient(): HttpClient {
        return HttpClient.newHttpClient()
    }

    @Bean
    fun provideExchangeRateAdapter(httpClient: HttpClient): CurrencyExchangeRateAdapter {
        return CurrencyExchangeRateAdapter(httpClient)
    }

    @Bean
    fun provideCurrencyConversionService(currencyExchangeRateAdapter: CurrencyExchangeRateAdapter): CurrencyConversionService {
        return CurrencyConversionService(currencyExchangeRateAdapter)
    }

    @Bean
    fun provideCommissionCalculationRules(monthlyTransactionsService: MonthlyTransactionsService): List<ICommissionCalculationRule> {
        return listOf(
            DefaultCommissionRule(),
            ClientDiscountCommissionRule(),
            HighTurnoverCommissionRule(monthlyTransactionsService)
        )
    }

    @Bean
    fun provideCommissionSelectionRule(): ICommissionSelectionRule {
        return MinCommissionSelectionRule()
    }

    @Bean
    fun provideCommissionSelectionService(
        commissionCalculationRules: List<ICommissionCalculationRule>,
        commissionSelectionRule: ICommissionSelectionRule
    ): CommissionSelectionService {
        return CommissionSelectionService(commissionCalculationRules, commissionSelectionRule)
    }

    @Bean
    fun provideTransactionHistoryService(db: TransactionRepository): MonthlyTransactionsService {
        return MonthlyTransactionsService(db)
    }
}