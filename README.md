# Price-List Finder

This application determines the price-tariff applicable to a product on a specified date.


## REST endpoints

It is implemented in SpringBoot and provides these REST query endpoints
* **/v1/price-lists/** 

accepting as input query parameters: 
* **effectiveDate** date when the tariff becomes applicable
* **productId** product identifier
* **brandId** business group chain

and returning as output data: 
* **productId**
* **brandId**
* **priceListId** Identifier of the applicable price-tariff
* **startDate** initial date range to apply the price-tarif
* **endDate** final date range to apply the price-tariff
* **price** final price
* **currency** 

Note: If two tariffs overlap in a date range, the one with the highest *priority* (higher numeric value) is applied.


## Embedded H2 Database

This project utilizes an embedded H2 database for local development and testing purposes. The database is automatically configured by Spring Boot and can be accessed through the H2 web console.

Connecting to the H2 Console:
* http://localhost:8080/h2-console
* **Username:** sa
* **Password:** (Leave blank)
* **JDBC url:** jdbc:h2:mem:testdb


## Liquibase Migration Management

Liquibase is used to handle database migrations by applying changes defined in the file
* **classpath:/db/changelog/db.changelog-master.xml** 

(This file contains SQL statements and instructions for managing database schema changes)


## OpenAPI

This project utilizes OpenAPI 3 to provide comprehensive and standardized documentation for its RESTful APIs.

To access the OpenAPI documentation for our APIs, navigate to the following URL:

* http://localhost:8080/v3/api-docs

For exploring the APIs directly:

* http://localhost:8080/swagger-ui/index.html


## End-to-end Testing

The API is tested using Cucumber and Gherkin.

The end-to-end tests are located in *'src/test/resources/features'*.
