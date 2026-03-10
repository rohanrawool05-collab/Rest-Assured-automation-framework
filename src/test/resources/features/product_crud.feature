Feature: Product API CRUD Operations

  @Smoke
  Scenario: Create a new product
    Given User creates product payload with title "iPhone 15 Pro" description "Latest iPhone model" price 999.99 category "smartphones" and brand "Apple"
    When User sends "POST" request to "CREATE_PRODUCT"
    Then Response status code should be 201
    And Response should contain field "id"
    And Response should contain field "title"
    And Response time should be less than 10000 ms

  @Regression
  Scenario: Get all products
    When User sends "GET" request to "GET_ALL_PRODUCTS"
    Then Response status code should be 200
    And Response should contain field "products"
    And Response time should be less than 10000 ms

  @Regression
  Scenario: Update existing product
    Given User creates update payload with title "Updated Product Title" and price 999.99
    When User sends "PUT" request to "UPDATE_PRODUCT" with id "1"
    Then Response status code should be 200
    And Response field "title" should be "Updated Product Title"
    And Response field "price" should be "999.99"

