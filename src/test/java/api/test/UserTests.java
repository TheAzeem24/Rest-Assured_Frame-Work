package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	Faker fake;
	User userPayload;
	public Logger logger;
	@BeforeClass
	public void setUp()
	{
		fake = new Faker();
		userPayload = new User();
		logger = LogManager.getLogger();
		userPayload.setId(fake.number().hashCode());
		userPayload.setUsername(fake.name().username());
		userPayload.setFirstName(fake.name().firstName());
		userPayload.setLastName(fake.name().lastName());
		userPayload.setEmail(fake.internet().emailAddress());
		userPayload.setPassword(fake.internet().password(5, 10));
		userPayload.setPhone(fake.phoneNumber().cellPhone());
	}
	
	@Test(priority = 1)
	public void testPostReq()
	{
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2)
	public void testGetReq()
	{
		Response response = UserEndPoints.getUser(this.userPayload.getUsername());
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void testPutReq()
	{
		userPayload.setFirstName(fake.name().firstName());
		userPayload.setLastName(fake.name().lastName());
		userPayload.setEmail(fake.internet().safeEmailAddress());
		
		Response UserResponse = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		UserResponse.then().log().body();
		Response updateeRsponse = UserEndPoints.getUser(this.userPayload.getUsername());
		Assert.assertEquals(updateeRsponse.getStatusCode(), 200);
		updateeRsponse.then().log().body();
	}
	
	@Test(priority = 4)
	public void testDeleteReq()
	{
		Response deleteUserResponse = UserEndPoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(deleteUserResponse.getStatusCode(), 200);
	}
}
