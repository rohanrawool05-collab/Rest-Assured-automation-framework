Feature: Parameterized API Tests with Data Driven Approach

  @DataDriven
  Scenario Outline: Create multiple devices with different data sets
    Given User creates device payload with name "<deviceName>" year "<year>" and price "<price>"
    When User sends "POST" request to "CREATE_OBJECT"
    Then Response status code should be 200
    And Response should contain field "id"
    And Response field "name" should be "<deviceName>"
    And Response time should be less than 3000 ms

    Examples:
      | deviceName              | year | price |
      | Apple iPhone 14 Pro     | 2023 | 1200  |
      | Samsung Galaxy S23      | 2023 | 1000  |
      | Google Pixel 7 Pro      | 2023 | 900   |
      | OnePlus 11              | 2023 | 800   |
      | Xiaomi 13 Pro           | 2023 | 750   |
