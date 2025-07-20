package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTestClass.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass {
	
	
	@Test(groups= {"sanity","master"})
	public void verifyLoginTest() {
		
		
		try {
        
		logger.info("*** Started login test  ***");
		
		// HomePage
		HomePage hp = new HomePage(driver);
		
		logger.info("Clicking my account and login ");
		hp.clickMyAccount();
		hp.clickMyLogin();
		
		logger.info("filling login credentials using config.properties file");
		
		// LoginPage
		LoginPage lp = new LoginPage(driver);
		
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		// MyAccountPage
		logger.info("verifying if my account message exists");
		MyAccountPage myAccPage = new MyAccountPage(driver);
		
	    if(myAccPage.isMyAccountExist()) {
	    	AssertJUnit.assertTrue(true);
	    	logger.info("My Account Existed");
	    }else {
	    	AssertJUnit.assertTrue(false);
	    	logger.info("My Account does not Existed");
	    	
	    }
	    
		}
		catch(Exception e) {
			
			AssertJUnit.fail();
			
		}
		logger.info("*** Finished login test  ***");
		
		
		
		
		
	}
	

}
