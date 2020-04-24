package stepDefinitions;

import cucumber.api.java.Before;

public class hooks {

	@Before("@Deleteplace")
	public void beforeScenario() throws Throwable {

		stepDefinition m = new stepDefinition();
		if (stepDefinition.place_id == null) {
			m.add_place_payload_with(33.33, 34.33, 44, "uchiha", "994422442222", "33 hairven, side way, G1",
					"Game shop", "doctor", "www.electro-x.com", "malayalam");
			m.user_calls_the_something_with_something_http_request("AddPlaceAPI", "Post");
			m.verify_the_place_id_created_maps_using("uchiha", "GetPlaceAPI");
			
		}

	}
}
