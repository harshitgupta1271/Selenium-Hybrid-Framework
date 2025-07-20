package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@id='input-email']") WebElement myEmail;
	@FindBy(xpath="//input[@id='input-password']") WebElement myPassword;
	@FindBy(xpath="//input[@value='Login']") WebElement clickLoginBtn;
	
	public void setEmail(String email) {
		myEmail.sendKeys(email);
	}
	public void setPassword(String pwrd) {
		myPassword.sendKeys(pwrd);
	}
	public void clickLogin() {
		clickLoginBtn.click();
	}
	
	
	

}
