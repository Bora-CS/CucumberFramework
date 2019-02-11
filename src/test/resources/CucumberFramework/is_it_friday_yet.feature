Feature: Is it Friday yet?
  Everybody wants to know when it’s Friday

  Scenario Outline: Today is or is not Friday
    Given today is "<Someday>"
    When I ask whether it's Friday yet
    Then I should be told "<Answer>"

    Examples: 
      | Someday        | Answer |
      | Monday         | Nope   |
      | Tuesday        | Nope   |
      | Saturday       | Nope   |
      | Any other day! | Nope   |
      | Friday         | TGIF   |
