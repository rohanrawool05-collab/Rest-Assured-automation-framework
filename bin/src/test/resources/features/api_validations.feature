Feature: API Response Validations and Assertions

  @Smoke
  Scenario: Validate response structure for created device
    Given User creates device payload with name "Sony PlayStation 5" year "2023" and price "500"
    When User sends "POST" request to "CREATE_OBJECT"
    Then Response status code should be 200
    And Response should contain field "id"
    And Response should contain field "name"
    And Response should contain field "data"
    And Response should contain field "createdAt"
    And Response field "name" should be "Sony PlayStation 5"

  @Regression
  Scenario: Validate response time for GET request
    When User sends "GET" request to "GET_ALL_OBJECTS"
    Then Response status code should be 200
    And Response time should be less than 2000 ms

  @Regression
  Scenario: End to End workflow - Create, Update, Retrieve, Delete
    Given User creates device payload with name "Microsoft Surface Pro" year "2023" and price "1400"
    When User sends "POST" request to "CREATE_OBJECT"
    Then Response status code should be 200
    And User saves the device id from response
    And Response field "name" should be "Microsoft Surface Pro"
    
    Given User creates update payload with name "Microsoft Surface Pro 9" and price "1500"
    When User sends "PUT" request to "UPDATE_OBJECT"
    Then Response status code should be 200
    And Response field "name" should be "Microsoft Surface Pro 9"
    
    When User sends "GET" request to "GET_OBJECT_BY_ID"
    Then Response status code should be 200
    And Response field "name" should be "Microsoft Surface Pro 9"
    
    When User sends "DELETE" request to "DELETE_OBJECT"
    Then Response status code should be 200
