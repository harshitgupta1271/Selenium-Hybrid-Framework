package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTestClass.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDTest extends BaseClass {
	
	
	@Test(dataProvider="Logindata",dataProviderClass=DataProviders.class,groups= {"dataDriven","master"})
	public void verifyDDLogin(String email,String password, String exp) {
		
		try {
		
		HomePage  hp = new HomePage(driver);
		
		logger.info("datadriven Test Started");
		
		hp.clickMyAccount();
		hp.clickMyLogin();
		
		LoginPage lp = new LoginPage(driver);
		
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickLogin();
		
		MyAccountPage myAccPage = new MyAccountPage(driver);
		Boolean checkLoggedIn = myAccPage.isMyAccountExist();
		
		logger.info("datadriven Validations");
		
		if(exp.equalsIgnoreCase("valid")) {
			if(checkLoggedIn==true) {
				logger.info("datadriven Test Passed");
				myAccPage.clickLogout();
				
				Assert.assertTrue(true);
				
			}else {
				logger.info("datadriven Test failed");
				Assert.fail();
			}
		}else {
				
				if(checkLoggedIn==true) {
					logger.info("datadriven Test failed");
					myAccPage.clickLogout();
					
					Assert.fail();
					
				}else {
					logger.info("datadriven Test Passed");
					Assert.assertTrue(true);
				}
		}
		
		}catch(Exception e) {
			
			logger.info("datadriven Test failed");
			Assert.fail();
		}
		logger.info("datadriven Test Finished");
		
	}
	
	

}
