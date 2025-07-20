package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Registration extends BasePage{

	public Registration(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;

	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;

	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;

	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;

	@FindBy(xpath="//input[@name='agree']")
	WebElement chkdPolicy;

	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;

	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	// Action Methods for each element
    public void enterFirstName(String fname) {
        txtFirstname.clear();
        txtFirstname.sendKeys(fname);
    }

    public void enterLastName(String lname) {
        txtLastname.clear();
        txtLastname.sendKeys(lname);
    }

    public void enterEmail(String email) {
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }

    public void enterTelephone(String phone) {
        txtTelephone.clear();
        txtTelephone.sendKeys(phone);
    }

    public void enterPassword(String pwd) {
        txtPassword.clear();
        txtPassword.sendKeys(pwd);
    }
    public void enterConfirmPassword(String cpwd) {
        txtConfirmPassword.clear();
        txtConfirmPassword.sendKeys(cpwd);
    }
    public void checkPolicy() {
        if (!chkdPolicy.isSelected()) {
            chkdPolicy.click();
        }
    }
    public void clickContinue() {
        btnContinue.click();
    }
    public String getConfirmationMessage() {
    	
    	try {
    		return msgConfirmation.getText();
    	}catch(Exception e) {
    		return (e.getMessage());
    	}
    }
}
