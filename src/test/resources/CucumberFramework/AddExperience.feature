Feature: Add Experience 
	Add work experience details to a user profile

@HappyPath 
Scenario: Add Experience with valid data 
	Given I log in to BoraAlumniConnector 
		| email    |huxiaowen.venus@gmail.com  |
		| password |  123456           |
	When I add experience with valid data 
		| Authorization | Token                                         |
		| Content-Type  | application/json                              |
		| title         | Automation Expert - level V                   |
		| company       | Google                                        |
		| location      | San Francisco                                 |
		| from          | 1992-9-15                                     |
		| to            | 1998-06-30                                    |
		| description   | Nice Enviornment,really enjoying working here |
	Then I should see my profile with the updated experience information 
		| StatusCode |        200 |
		| Title      | Automation Expert - level V |