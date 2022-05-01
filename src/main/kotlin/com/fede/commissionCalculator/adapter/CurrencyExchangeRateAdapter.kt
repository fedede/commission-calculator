package com.fede.commissionCalculator.adapter

import mu.KotlinLogging
import org.json.JSONObject
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse


private val log = KotlinLogging.logger { }

class CurrencyExchangeRateAdapter(private val httpClient: HttpClient) {

    fun getExchangeRatesForDate(date: String): Map<String, String> {
        val request = HttpRequest.newBuilder().uri(URI("https://api.exchangerate.host/$date")).build()
        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
        val ratesJSON = JSONObject(response.body()).getJSONObject("rates")
        log.info("Conversion rate for date $date is ${convertJsonToMap(ratesJSON)}")
        return convertJsonToMap(ratesJSON)
    }

    private fun convertJsonToMap(jsonObject: JSONObject): Map<String, String> {
        val keys = jsonObject.keys()
        val map = emptyMap<String, String>().toMutableMap()
        keys.forEachRemaining { map[it.toString()] = jsonObject.getString(it.toString()) }
        return map
    }
}