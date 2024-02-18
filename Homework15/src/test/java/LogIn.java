import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;

public class LogIn {
    WebDriver driver;

    @BeforeClass
    public void driver(){driver = new ChromeDriver();}

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void logIn(){
        driver.get("http://shop.pragmatic.bg/admin");
        WebElement username = driver.findElement(By.id("input-username"));
        WebElement password = driver.findElement(By.id("input-password"));
        WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));

        username.sendKeys("admin");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        password.sendKeys("parola123!");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        submit.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login Failed");
    }
    }

