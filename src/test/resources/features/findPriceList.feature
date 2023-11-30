
Feature: Price-Tariff API

  Scenario: Retrieve price-tariff for product 35455 on brand 1 at different days and times
    Given the effective dates are as follows:
      | effective date |
      |---|
      | 2020-06-14T10:00:00 |
      | 2020-06-14T16:00:00 |
      | 2020-06-14T21:00:00 |
      | 2020-06-15T10:00:00 |
      | 2020-06-16T21:00:00 |
    And the product ID is 35455
    And the brand ID is 1
    When I request the price-tariff
    Then the response status code should be 200
    And the priceListResponse should have the following details:
      | product id | brand id | price list id | price | currency | start date | end date |
      |---|---|---|---|---|---|---|
      | 35455 | 1 | 1 | 35.50 | EUR | 2020-06-14T00:00:00 | 2020-12-31T23:59:59 |
      | 35455 | 1 | 2 | 25.45 | EUR | 2020-06-14T15:00:00 | 2020-06-14T18:30:00 |
      | 35455 | 1 | 1 | 35.50 | EUR | 2020-06-14T00:00:00 | 2020-12-31T23:59:59 |
      | 35455 | 1 | 3 | 30.50 | EUR | 2020-06-15T00:00:00 | 2020-06-15T11:00:00 |
      | 35455 | 1 | 4 | 38.95 | EUR | 2020-06-15T16:00:00 | 2020-12-31T23:59:59 |