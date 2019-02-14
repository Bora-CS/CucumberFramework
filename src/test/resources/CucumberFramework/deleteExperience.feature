Feature: Delete Experience
  Delete user experience from user profile

  @API
  Scenario: Delete user experience
    Given I get the user Id by adding experience
      | email    | kxirnay@gmail.com |
      | password |           1234567 |
    And I add a user experience
      | title       | IT Teacher                               |
      | company     | Bora Tech                                |
      | location    | Seattle, Washington                      |
      | from        | 2018-09-30                               |
      | to          | 2019-12-30                               |
      | description | Very stressful, really hate working here |
    When I put in the Id and delete specific experience:
      | Id | id |
    Then I should see my profile with the updated user information
      | StatusCode | 200 |
