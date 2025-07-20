package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTestClass.BaseClass;
import pageObjects.HomePage;
import pageObjects.Registration;

public class TC001_AccountRegistrationTest extends BaseClass {

    @Test(groups= {"regression","master"})
    public void verify_account_registration() {

        try {
            logger.info("****** Starting TC001_AccountRegistrationTest ********");

            HomePage hp = new HomePage(driver);
            Registration reg = new Registration(driver);

            hp.clickMyAccount();
            hp.clickMyRegistration();

            logger.info("Providing registration details");

            reg.enterFirstName(randomString().toUpperCase());
            reg.enterLastName(randomString().toUpperCase());
            reg.enterEmail(randomString() + "@gmail.com");
            reg.enterTelephone(randomNumbers());

            String randomPass = randomPassword();
            reg.enterPassword(randomPass);
            reg.enterConfirmPassword(randomPass);
            reg.checkPolicy();
            reg.clickContinue();

            logger.info("Checking confirmation message");

            String cfmmsg = reg.getConfirmationMessage();

            if (cfmmsg.equals("Your Account Has Been Created!")) {  
                Assert.assertTrue(true);
                logger.info("Registration successful");
            } else {
                logger.error("Test Failed: Confirmation message not matched");
                logger.debug("Expected message: Your Account Has Been Created!!, but got: " + cfmmsg);
                Assert.assertTrue(false);
            }

        } catch (Exception e) {
            Assert.fail();
        }

        logger.info("****** Finished TC001_AccountRegistrationTest ********");
    }
}
