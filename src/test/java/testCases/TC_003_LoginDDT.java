package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass 
{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class)
	public void verifyLoginDDT(String email, String password, String expectedResult) 
	{
		logger.info("************Starting TC_003_LoginDDT*************");
		logger.debug("Capturing application debug logs");
		
		try {
		HomePage hp = new HomePage(driver);
		
		logger.info("Clicked on MyAccount link");
		hp.clickMyAccount();
		logger.info("Clicked on login link");
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		logger.info("entering valid email address");
		lp.setEmail(email);
		logger.info("entering valid password");
		lp.setPassword(password);
		logger.info("Clicking login button");
		lp.clickLogin();
		
		MyAccountPage myap = new MyAccountPage(driver); 	
		boolean targetPage = myap.isMyAccountPageDisplayed();
		
		if(expectedResult.equalsIgnoreCase("valid")) 
		{
			if(targetPage==true) 
			{
				myap.clickLogOut();
				Assert.assertTrue(true);
			}
			else 
			{
				Assert.assertTrue(false);
			}
		}
		
		
		
		if(expectedResult.equalsIgnoreCase("invalid")) 
		{
			if(targetPage==true) 
			{
				myap.clickLogOut();
				Assert.assertTrue(false);
			}
			else 
			{
				Assert.assertTrue(true);
			}
		}
		
		}catch (Exception e) {
			
			Assert.fail();
		}

		logger.info("************Finished TC_003_LoginDDT*************");
	}
}
