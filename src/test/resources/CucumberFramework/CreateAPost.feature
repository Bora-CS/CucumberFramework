Feature: Create a post endpoint

  @API
  Scenario Outline: Creat a post with valid data
    Given I log in
      | email    | jam1993625@gmail.com |
      | password |               123456 |
    When I create a post with valid data
      | text | <content> |
    Then I should see response body with correct data
      | status code |       200 |
      | text        | <content> |

    Examples: 
      | content                                                                                                                                                                                                                                                                                                      |
      | Let me shine like the sun                                                                                                                                                                                                                                                                                    |
      |                                                                                                                                                                                                                                                                                                   1234567890 |
      | 123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890 |
