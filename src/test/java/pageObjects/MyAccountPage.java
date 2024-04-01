package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
		
	}
	
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement pageHeading;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement lnkLogOut;
	
	
	public boolean isMyAccountPageDisplayed() {
		
		try {
			return(pageHeading.isDisplayed());
		}catch(Throwable e) {
			
			return(false);
		}
	}

	
	public void clickLogOut() {
		
		lnkLogOut.click();
		
	}
}
