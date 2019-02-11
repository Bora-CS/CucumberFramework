@Etsy
Feature: Etsy.com Search Function
  I want to search for items on Etsy.com 
  and see the results related to my search

  Scenario Outline: Seach items from homepage
    Given I navigate to Etsy.com homepage
    When I search for "<item>"
    Then I should see results related to my search

    Examples: 
      | item        |
      | iPhone Case |
      | Neckless    |
