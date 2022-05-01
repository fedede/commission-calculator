package com.fede.commissionCalculator.model

import com.fasterxml.jackson.annotation.JsonProperty

data class CommissionCalculatorRequest(
    val date: String,
    val amount: String,
    val currency: String,
    @JsonProperty("client_id")
    val clientId: Int
)
