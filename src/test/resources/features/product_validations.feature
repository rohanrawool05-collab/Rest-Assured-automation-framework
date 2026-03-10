Feature: Product API Response Validations

  @Smoke
  Scenario: Validate response structure for created product
    Given User creates product payload with title "MacBook Pro 16" description "High performance laptop" price 2499.99 category "laptops" and brand "Apple"
    When User sends "POST" request to "CREATE_PRODUCT"
    Then Response status code should be 201
    And Response should contain field "id"
    And Response should contain field "title"
    And Response should contain field "price"
    And Response field "title" should be "MacBook Pro 16"

  @Regression
  Scenario: Validate response time for GET all products
    When User sends "GET" request to "GET_ALL_PRODUCTS"
    Then Response status code should be 200
    And Response time should be less than 10000 ms

  @Regression
  Scenario: Get product by ID
    When User sends "GET" request to "GET_PRODUCT_BY_ID" with id "1"
    Then Response status code should be 200
    And Response should contain field "id"
    And Response should contain field "title"
    And Response time should be less than 10000 ms

