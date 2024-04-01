package testCases;


import org.testng.Assert;
import org.testng.annotations.*;
import org.apache.logging.log4j.Logger;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass
{
	//Logger logger = null;
	@Test(groups= {"regression","master"})
	public void verify_account_registration() 
	{
		logger.info("******Starting TC_001_AccountRegistrationTest******");
		logger.debug("application logs");
		try {
		
		
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link");
		hp.clickRegister();
		logger.info("Clicked on Registration Link");
		
		
		logger.info("****Entering new customer information****");
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");
		regpage.setTelephone(randomNumber());
		
		String password=randomAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		String confmsg = regpage.getConfirmation();
		if(confmsg.equals("Your Account Has Been Created!")) {
			logger.info("test passed....");
			Assert.assertTrue(true);
		}
		else {
			
			logger.info("test failed....");
			Assert.fail();
		}
		}
		catch (Exception e) {
			
			logger.error("test failed");
			Assert.fail();
		}
		logger.info("******Finished TC_001_AccountRegistrationTest******");

	}
	
	
	
}





