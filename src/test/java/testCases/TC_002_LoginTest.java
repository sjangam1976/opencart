package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

	@Test(groups= {"sanity", "master"})
	public void verify_Login() 
	{
		logger.info("************Starting TC_002_LoginTest*************");
		logger.debug("Capturing application debug logs");
		try {
		
		HomePage hp = new HomePage(driver);
		
		logger.info("Clicked on MyAccount link");
		hp.clickMyAccount();
		logger.info("Clicked on login link");
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		logger.info("entering valid email address");
		lp.setEmail(prop.getProperty("validemail"));
		logger.info("entering valid password");
		lp.setPassword(prop.getProperty("validpassword"));
		logger.info("Clicking login button");
		lp.clickLogin();
		
		MyAccountPage myap = new MyAccountPage(driver); 
			
		boolean targetPage = myap.isMyAccountPageDisplayed();
		
		if(targetPage==true) {
			logger.info("Login Test Passed...");
			Assert.assertTrue(true);
		}
		else {
			
			logger.error("login test failed");
			Assert.fail();
		}
		}catch(Exception e) {
			
			Assert.fail();
		}
		logger.info("************Finished TC_002_LoginTest*************");
	}
}
