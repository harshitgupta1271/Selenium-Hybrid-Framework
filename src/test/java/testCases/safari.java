package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class safari {

    @Test
    void method() {
        WebDriver driver = new  ChromeDriver();

        

        // Proceed with automation
        driver.get("https://www.youtube.com/");

        try {
            Thread.sleep(3000);  // Optional wait
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();  // Always quit to avoid session lock
        System.out.println("Safari closed successfully.");
    }

    // Kill stuck safaridriver processes
    
}
