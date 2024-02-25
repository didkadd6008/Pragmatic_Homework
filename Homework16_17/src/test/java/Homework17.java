import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class Homework17 {
    WebDriver driver;

    @BeforeClass
    public void startup(){
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

    @Test
    public void testWait(){
        try {
            driver.get("http://pragmatic.bg/automation/ajax-waits/ajax.html");
            WebElement button = driver.findElement(By.xpath("//div[@id='demo']//button"));
            button.click();

            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(driver -> driver.findElement(By.xpath("//h1[@id='ajax']")));
            WebElement button1 = driver.findElement(By.xpath("//div[@id='demo']//button"));
            button1.click();

            Wait<WebDriver> wait1 = new WebDriverWait(driver, Duration.ofSeconds(60));
            WebElement message = wait.until(driver -> driver.findElement(By.xpath("//div[@id='anotherDoc']/div")));

            Assert.assertEquals(message.getText(),"You did a good job!");
        }catch (NoSuchElementException e ) {
          Assert.fail("No such element!");
          e.printStackTrace();
        }finally {
            driver.quit();
        }
    }
}
