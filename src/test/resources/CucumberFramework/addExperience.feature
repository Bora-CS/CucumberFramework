Feature: Add Experience
  	Add work experience details to a user profile

  @HappyPath
  Scenario: Add Experience with valid data
    Given I log in to BoraAlumniConnector
      | email    | shirinayk81@gmail.com |
      | password | Xirnay81#             |
    When I add experience with valid data
      | title         | Automation                                    |
      | company       | Google                                        |
      | location      | San Francisco                                 |
      | from          | 1992-9-15                                     |
      | to            | 1998-06-30                                    |
      | description   | Nice Enviornment,really enjoying working here |
    Then I should see my profile with the updated experience information
      | StatusCode |        200 |
      | Title      | Automation |
