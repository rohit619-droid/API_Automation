package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

@RunWith(Cucumber.class)
public class stepDefinition extends Utils {
	RequestSpecification ap;
	Response res;
	static String place_id;
	String newAddress;
	TestDataBuild data = new TestDataBuild();

	@Given("^add place payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_place_payload_with(double lat, double log, int accuracy, String name, String phonenumber,
			String address, String types0, String types1, String website, String language) throws Throwable {
		ap = given().log().all().spec(requestSpec()).body(
				data.addPlacePojo(log, log, accuracy, name, phonenumber, address, types0, types1, website, language));

	}

	@When("^user calls the \"([^\"]*)\" with \"([^\"]*)\" http request$")
	public void user_calls_the_something_with_something_http_request(String apiRes, String httpRequest)
			throws Throwable {
		APIResources resources = APIResources.valueOf(apiRes);
		System.out.println(resources.getResources());

		if (httpRequest.equalsIgnoreCase("Post"))
			res = ap.when().post(resources.getResources());
		else if (httpRequest.equalsIgnoreCase("Put"))
			res = ap.when().put(resources.getResources());
		else if (httpRequest.equalsIgnoreCase("Get"))
			res = ap.when().get(resources.getResources());
		else if (httpRequest.equalsIgnoreCase("Delete"))
			res = ap.when().delete(resources.getResources());

	}

	@Then("^user should get a response body with status code 200$")
	public void user_should_get_a_response_body_with_status_code_200() throws Throwable {

		assertEquals(res.getStatusCode(), 200);
	}

	@And("^\"([^\"]*)\" of the response body should be \"([^\"]*)\"$")
	public void something_of_the_response_body_should_be_something(String key, String expectedValue) throws Throwable {

		assertEquals(getJsonPath(res, key), expectedValue);

	}

	@And("^Verify the place_id created maps \"([^\"]*)\" using \"([^\"]*)\"$")
	public void verify_the_place_id_created_maps_using(String expectedAddress, String resource) throws Throwable {

		// place_id = getJsonPath(res, "place_id");
		ap = given().spec(requestSpec()).queryParam("place_id", place_id);
		user_calls_the_something_with_something_http_request(resource, "Get");
		String actualAddress = getJsonPath(res, "address");
		//assertEquals(actualAddress, expectedAddress);
		System.out.println("ActualAddress==========" + actualAddress);
		System.out.println("ExpectedName==========" + expectedAddress);

	}

	@Given("^Delete Payload$")
	public void delete_payload() throws Throwable {

		ap = given().spec(requestSpec()).body(data.deletePayload(place_id));

	}

	@And("^update the \"([^\"]*)\" using \"([^\"]*)\"$")
	public void update_the_something_using_something(String expectedAddress, String resource) throws Throwable {
		newAddress = "East blue 20th avenue fairhaven street 1";
		place_id = getJsonPath(res, "place_id");
		ap = given().spec(requestSpec()).queryParam("place_id", place_id)
				.body(TestDataBuild.updatePlaceJson(place_id, newAddress));
		user_calls_the_something_with_something_http_request(resource, "Put");
		System.out.println("goes in the put method-------" + newAddress);
		// assertEquals(newName, expectedName);

	}

}