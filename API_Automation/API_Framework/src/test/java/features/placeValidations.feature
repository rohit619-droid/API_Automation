Feature: validating place API's; 
@Addplace @Regression 
Scenario Outline: Verify if added place is sucessfully added using addplaceAPI; 
	Given add place payload with "<lat>" "<log>" "<Accuracy>" "<Name>" "<PhoneNumber>" "<Address>" "<types0>" "<types1>" "<Website>" "<Language>" 
	When user calls the "AddPlaceAPI" with "Post" http request 
	Then user should get a response body with status code 200 
	And "status" of the response body should be "OK" 
	And "scope" of the response body should be "APP" 
	And update the "<Address>" using "UpdatePlaceAPI" 
	And Verify the place_id created maps "<Address>" using "GetPlaceAPI" 
	
	Examples: 
		|lat	  |log	    |Accuracy	|Name	|PhoneNumber	|Address	  			  |types0	|types1	|Website		 |Language	|
		|-38.38349|33.427362|30			|kohna	|993344422444	|29, side layout, cohen 09|bakery	|shoe	|www.facebook.com|English	|
		#|-34.38349|3w.427362|30			|senju	|9543344422444	|23, rolayout, frai 09	  |umbrella	|flower	|www.google.com|tamil	|
		
		
		@Deleteplace @Regression 
		Scenario: Verify if place is sucessfully deleted using deleteplaceAPI; 
			Given Delete Payload 
			When user calls the "DeletePlaceAPI" with "Delete" http request 
			Then user should get a response body with status code 200 
			And "status" of the response body should be "OK" 