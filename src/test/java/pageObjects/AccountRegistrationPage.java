package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{
	
	WebDriver driver;
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);//invoking parent class contructor (basepage)
	}
	
	@FindBy(id="input-firstname")
	WebElement txtfirstname;
	
	@FindBy(id="input-lastname")
	WebElement txtlastame;
	
	@FindBy(id="input-email")
	WebElement txtemail;
	
	@FindBy(id="input-telephone")
	WebElement txttelephone;
	
	@FindBy(id="input-password")
	WebElement txtpassword;

	@FindBy(id="input-confirm")
	WebElement txtconfirmpassword;
	
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkdPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btncontinue;
	
	@FindBy(xpath ="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	
	public void setFirstName(String fname) {
		
		txtfirstname.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		
		txtlastame.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		
		txtemail.sendKeys(email);
	}
	public void setTelephone(String phone) {
		
		txttelephone.sendKeys(phone);
	}
	
	public void setPassword(String password) {
		
		txtpassword.sendKeys(password);
	}
	
	public void setConfirmPassword(String password) {
		
		txtconfirmpassword.sendKeys(password);
	}
	public void setPrivacyPolicy() {
		
		chkdPolicy.click();
	}
	
	public void clickContinue() {
		
		btncontinue.click();
	}
	
	public String getConfirmation() {
		
		try {
			return(msgConfirmation.getText());
		} catch (Exception e) {
			return(e.getMessage());
			
		}
	}
}
