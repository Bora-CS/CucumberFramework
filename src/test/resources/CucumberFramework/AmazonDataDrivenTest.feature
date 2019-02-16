@Amazon
Feature: Search

  Scenario Outline: Search by item name
    Given I'm on the amazon.com homepage
    When I search for an "<Item>"
    And I get some results related to my search
    Then I will be able to find out the highest price, lowset price and the average price

    Examples: 
      | Item           |
      | iPhone Charger |
