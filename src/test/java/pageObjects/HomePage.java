package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath="//a[@title='My Account']") WebElement myAccount;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement myRegistration;
	@FindBy(xpath="//a[normalize-space()='Login']") WebElement myLogin;
	
	 
	
	public void clickMyAccount() {
		myAccount.click();
	}
	public void clickMyRegistration() {
		myRegistration.click();
		
	}
	public void clickMyLogin() {
		myLogin.click();
	}

}