package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefination extends Utils {

	RequestSpecification response;
	Response res;
	ResponseSpecification respSpec;
	TestDataBuild data = new TestDataBuild();
	public static String placeId;
	JsonPath js;

	@Given("Add Place Payload")
	public void add_place_payload() throws IOException {
		// creating response spec
		response = given().log().all().spec(requestSpecification()).body(data.addDataPayload());
	}
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		// creating response spec
		response = given().log().all().spec(requestSpecification()).body(data.addDataPayload(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resString, String httpMethod) {
		
		ApiResources apiRes=ApiResources.valueOf(resString);
		System.out.println(apiRes.getResource());
		
		respSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		if(httpMethod.equalsIgnoreCase("POST"))
		    res = response.when().post(apiRes.getResource());
		else if (httpMethod.equalsIgnoreCase("GET"))
			res = response.when().get(apiRes.getResource());
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer expStatusCode) {
		assertEquals(res.statusCode(), 200);
	}

	@Then("{string} in response body id {string}")
	public void in_response_body_id(String keyValue, String expectedValue) {
		assertEquals(getJsonPath(res, keyValue), expectedValue);
	}
	
	@Then("verify place_id created  maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String name, String resString) throws IOException {
		
		//create resSpec
		placeId = getJsonPath(res, "place_id");
		response = given().log().all().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_with_http_request(resString, "GET");		
		String actualName = getJsonPath(res, "name");
		assertEquals(actualName, name);
	}
	
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
		// creating response spec
		response = given().log().all().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
	}

}
