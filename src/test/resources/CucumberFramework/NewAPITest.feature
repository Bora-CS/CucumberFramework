Feature: Add Experience 
	Add work Experience details to a user profile
  
@HappyPath 
Scenario: Add Experience with valid data 
	Given I log in to BoraAlumniConnector 
		|email|huxiaowen.venus@gmail.com|
		|password |123456|
	
	Then Delete the following experience profile: 
		|Id|id|
  