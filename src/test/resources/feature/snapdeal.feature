Feature: Snapdeal login and shopping automation

  Scenario: Login and add product to cart
    Given user is on Snapdeal homepage
    When user logs in with mobile number
    And user searches for "Shoes"
    And user adds first product to the cart
    Then user should see the product in the cart
