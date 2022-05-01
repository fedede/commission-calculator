package com.fede.commissionCalculator

import com.fede.commissionCalculator.model.CommissionCalculatorRequest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.*
import java.net.URI


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [CommissionCalculatorApplication::class],
)
class CommissionCalculatorApplicationTests {

    @LocalServerPort
    private val port = 0

    val restTemplate = TestRestTemplate()

    val headers: HttpHeaders = HttpHeaders()

    @Test
    fun contextLoads() {
        println("EXECUTING TESTS XXXX in port $port and headers: $headers")
        val request = CommissionCalculatorRequest(date = "2022-01-01", clientId = 3, currency = "EUR", amount = "100")
        val entity = HttpEntity<CommissionCalculatorRequest>(request, headers)

        val response: ResponseEntity<String> = restTemplate.exchange(
            URI(createURLWithPort("")!!), HttpMethod.POST, entity, String::class.java
        )
        println("Result of test $response")
    }

    private fun createURLWithPort(uri: String): String? {
        return "http://localhost:$port$uri"
    }

}
