Feature: Product Filter
  Scenario: User applies product filters
    Given I am logged into the application
    When I apply the "Price (low to high)" filter
    Then I should see the products sorted by price in ascending order
    