package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;
import stepDefinations.stepDefination;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		stepDefination sf = new stepDefination();
			if(stepDefination.placeId==null) {
				sf.add_place_payload_with("pavneet","UK_English","London");
				sf.user_calls_with_http_request("postPlaceAPI", "POST");
				sf.verify_place_id_created_maps_to_using("pavneet", "getPlaceAPI");
			}
	}

}
