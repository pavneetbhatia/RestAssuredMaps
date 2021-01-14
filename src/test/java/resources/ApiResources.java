package resources;

public enum ApiResources {
	postPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPT("/maps/api/place/delete/json");
	String resource;
	
	ApiResources (String resource){
		this.resource=resource;
	}
	
	public String getResource() {
		return resource;
	}
	

}
