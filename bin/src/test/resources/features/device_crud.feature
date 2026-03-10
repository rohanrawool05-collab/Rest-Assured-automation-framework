Feature: Device API CRUD Operations

  @Smoke
  Scenario: Create a new device
    Given User creates device payload with name "Apple MacBook Pro 16" year "2023" and price "2500"
    When User sends "POST" request to "CREATE_OBJECT"
    Then Response status code should be 200
    And Response should contain field "id"
    And Response should contain field "name"
    And Response should contain field "createdAt"
    And Response time should be less than 3000 ms

  @Regression
  Scenario: Get all devices
    When User sends "GET" request to "GET_ALL_OBJECTS"
    Then Response status code should be 200
    And Response time should be less than 3000 ms

  @Regression
  Scenario: Create device and retrieve by ID
    Given User creates device payload with name "Dell XPS 15" year "2023" and price "1800"
    When User sends "POST" request to "CREATE_OBJECT"
    Then Response status code should be 200
    And User saves the device id from response
    When User sends "GET" request to "GET_OBJECT_BY_ID"
    Then Response status code should be 200
    And Response field "name" should be "Dell XPS 15"

  @Regression
  Scenario: Create device, update and verify
    Given User creates device payload with name "HP Pavilion" year "2023" and price "1200"
    When User sends "POST" request to "CREATE_OBJECT"
    Then Response status code should be 200
    And User saves the device id from response
    Given User creates update payload with name "HP Pavilion Updated" and price "1300"
    When User sends "PUT" request to "UPDATE_OBJECT"
    Then Response status code should be 200
    And Response field "name" should be "HP Pavilion Updated"

  @Regression
  Scenario: Create device and delete
    Given User creates device payload with name "Lenovo ThinkPad" year "2023" and price "1500"
    When User sends "POST" request to "CREATE_OBJECT"
    Then Response status code should be 200
    And User saves the device id from response
    When User sends "DELETE" request to "DELETE_OBJECT"
    Then Response status code should be 200
