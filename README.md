Currency Conversion Commission API
-----------
-----------
Project developed using IntelliJ IDEA Community Edition. 

Base code provided by [Spring Initializr](https://start.spring.io).

**Total time taken to complete: ~4/5 hours**

### Execute

On the root directory of the project, execute `gradle bootRun` if you have Gradle installed or `./gradlew bootRun` otherwise (`./gradlew.bat bootRun` if you are using Windows).

This will start the server on port 8080. You can then make post requests using your preferred methods. Example using CURL below, data in the JSON can be changed as wanted.

```bash
curl -X POST localhost:8080 -H 'Content-Type: application/json' \
-d '{ "date": "2021-01-01", "amount": "100.00", "currency": "USD", "client_id": 1 }'
```

### Test

On the root directory of the project, execute `gradle test` if you have Gradle installed or `./gradlew test` otherwise (`./gradlew.bat test` if you are using Windows).

### Configuration

Initial DB data is configured in `/src/main/resources/data.sql`. Before starting the service, this file can be modified so that the desired records are in the DB.


### Improvements

-----------

#### Operational
1. Implement retries on external dependency calls (Exchange rate API)
2. Implement caching of currency rate to reduce load to dependencies
3. More extensive unit testing
4. More extensive integration testing, mock external dependencies for testing
5. e2e testing
6. Error handling and input validation / Cleaning
7. Split integration and unit test suites

#### Functionality
1. Support additional currencies
2. Use real DB for persistence

### Assumptions

-----------
1. Data will arrive to the API on the correct format
2. These calls are informational. The transactions should not be stored on the DB
