package api.test;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProvider;
import io.restassured.response.Response;

public class DDTest {
	public Logger logger = LogManager.getLogger(this.getClass());;
	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProvider.class)
	public void createUser(String userID, String userName, String fname, String lname, String email,String pwd, String ph)
	{
		
		logger.info("********** started the execution of create user ******************");
		logger.info("data from excel ", ph, ph, userID, userName, fname, lname, email, pwd, ph);
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setLastName(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response = UserEndPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("********** end of the execution of create user******************");
	}
	
	@Test(priority = 2,dataProvider = "userNames", dataProviderClass = DataProvider.class)
	public void deleteUser(String name)
	{
		logger.info("********** started the execution of delete te user ******************");
		logger.info("User data", name);
		Response response = UserEndPoints.deleteUser(name);
		response.then().log().body();
		logger.info("********** end of the execution of delete te user ******************");
	}
}
