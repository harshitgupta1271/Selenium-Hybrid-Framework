package BaseTestClass;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups = {"regression", "sanity", "dataDriven", "master"})
    @Parameters({"os", "browser"})
    public void setUp(String os, String br) throws IOException, InterruptedException {
    	
    	
    	
    	
        logger = LogManager.getLogger(BaseClass.class);
        FileReader file = new FileReader("src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        DesiredCapabilities cap = new DesiredCapabilities();

        if (p.getProperty("Execution_env").equalsIgnoreCase("remote")) {

            if (os.equalsIgnoreCase("mac")) {
                cap.setPlatform(Platform.MAC);
            } else if (os.equalsIgnoreCase("linux")) {
                cap.setPlatform(Platform.LINUX);
            } else {
                System.out.println("No matching OS found.");
                return;
            }

            switch (br.toLowerCase()) {
                case "chrome":
                    cap.setBrowserName("chrome");
                    break;
                case "edge":
                	 System.setProperty("webdriver.edge.driver", "/usr/local/bin/msedgedriver");
                    cap.setBrowserName("MicrosoftEdge");
                    break;
                case "safari":
                    cap.setBrowserName("safari");
                    break;
                case "firefox":
                    cap.setBrowserName("firefox");
                    break;
                default:
                    System.out.println("No matching browser found for input: " + br);
                    return;
            }

            driver = new RemoteWebDriver(new URL("http://192.168.1.10:4444/wd/hub"), cap);

        } else {

            switch (br.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    System.setProperty("webdriver.edge.driver", "/usr/local/bin/msedgedriver");
                    driver = new EdgeDriver();
                    break;
                case "safari":
                        driver = new SafariDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + br);
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        System.out.println("Launching browser: " + br);
        driver.get(p.getProperty("appUrl1"));
        Thread.sleep(2000);
        driver.manage().window().maximize();

        logger.info("Browser launched and URL opened.");
    }

    @AfterClass(groups = {"regression", "sanity", "dataDriven", "master"})
    public void tearDown() throws InterruptedException {
    	
            driver.quit();
            logger.info("Browser closed.");
       
    }
  


    public String randomString() {
        return RandomStringUtils.randomAlphabetic(4);
    }

    public String randomNumbers() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomPassword() {
        return RandomStringUtils.randomAlphabetic(4) + "@" + RandomStringUtils.randomNumeric(4);
    }

    public String captureScreen(String testName) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);
        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }
}
