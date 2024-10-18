package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class UserEndPoints {
	
	public static Response createUser(User payload) {
		Response response = given()
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.body(payload)
							.when()
								.post(Routes.postUrl);
		return response;
	}
	
	public static Response getUser(String userName)
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("userName", userName)
		.when()
			.get(Routes.getUrl);
		
		return response;
	}
	public static Response updateUser(String userName, User payload)
	{
	Response responce =	given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("userName", userName)
			.body(payload)
		.when()
			.put(Routes.putUrl);
	
	return responce;
	}
	
	public static Response deleteUser(String userName)
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("userName", userName)
		.when()
			.delete(Routes.getUrl);
		
		return response;
	}
}
