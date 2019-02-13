Feature: Add Experience
  Add work experience details to a user profile

  @API
  Scenario: Add experience with valid data
    Given I log in to BoraAlumniConnector
      | email    | miller.muradil@gmail.com |
      | password | murad001                 |
    When I add an experience with valid data
      | title         | Automation Expert - Level V                           |
      | company       | Facebook                                              |
      | location      | San francisco                                         |
      | from          | 1992-9-15                                             |
      | to            | 1998-06-30                                            |
      | description   | Really stressful, not enough salary, not happy at all |
    Then I should see my profile with the updated experience information
      | StatusCode |                         200 |
      | Title      | Automation Expert - Level V |
