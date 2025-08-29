Feature: Snapdeal E-commerce Flow

  Scenario: Login and add the first available product to the cart
    Given user is on Snapdeal homepage
    When user logs in with mobile number
    And user adds the first available product to the cart
    Then user should see the product in the cart