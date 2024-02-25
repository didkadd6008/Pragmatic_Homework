import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.By;

import javax.swing.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DropDownMenu {
    private WebDriver driver ;

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

    @Test(priority = 1)
    public  void dropDownMenu(){
        driver.get("http://shop.pragmatic.bg/admin");
        WebElement username = driver.findElement(By.id("input-username"));
        WebElement password = driver.findElement(By.id("input-password"));
        WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));


        username.sendKeys("admin");
        password.sendKeys("parola123!");
        submit.click();


        WebElement sale = driver.findElement(By.id("menu-sale"));
        sale.click();

        List<WebElement> items = driver.findElements(By.xpath("//li[@id='menu-sale']//ul[@id='collapse4']/li"));

       for (WebElement order : items){

           if (order.getText().equals("Orders")){
               order.click();
               break;
           }

       }


       Select statuses = new Select(driver.findElement(By.name("filter_order_status_id")));
       List<String> exp_options = Arrays.asList(new String[]{" ","Missing Orders","Canceled","Canceled Reversal","Chargeback","Complete","Denied","Expired","Failed","Pending","Processed","Processing","Refunded","Reversed","Shipped","Voided"});
       List<String> act_options = new ArrayList<>();

       for (WebElement status : statuses.getOptions()){
           act_options.add( status.getText().isEmpty()  ? " " : status.getText());
       }

       Assert.assertEquals(act_options,exp_options);
    }

    @Test(priority = 2)
     public void actionClass(){
        driver.get("http://pragmatic.bg/automation/lecture13/Config.html");
        WebElement airBags = driver.findElement(By.name("airbags"));
        WebElement parkingSensor = driver.findElement(By.name("parksensor"));

        Actions selector = new Actions(driver);
        selector.click(airBags).click(parkingSensor).perform();

        Assert.assertTrue(airBags.isSelected() && parkingSensor.isSelected());
    }


}

