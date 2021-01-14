package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification reqSpec;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		if(reqSpec==null) {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		// creating request spec
		reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalProperty("baseUrl")).addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON)
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return reqSpec;
		}
		
		return reqSpec;
	}
	
	public static String getGlobalProperty(String propKey) throws IOException {
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\pavne\\testWorkspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(propKey);
	}
	
	public String getJsonPath(Response response, String key) {
		String resp= response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
		
	}

}
