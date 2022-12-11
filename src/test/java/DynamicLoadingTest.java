import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;


public class DynamicLoadingTest {

    private WebDriver driver;

    @BeforeMethod
    private void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "E:\\Program Files\\webdriver\\chromedriver.exe");

        driver = new ChromeDriver();
        String url = "https://the-internet.herokuapp.com/";

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @AfterMethod
    private void closeDriver() {
        driver.close();
    }

    @Test
    public void dynamicLoading() {
        WebElement dynamicLoadingPage = driver.findElement(By.xpath("//a[normalize-space()='Dynamic Loading']"));
        dynamicLoadingPage.click();

        WebElement example1 = driver.findElement(By.xpath("//a[normalize-space()='Example 1: Element on page that is hidden']"));
        example1.click();

        WebElement startButton = driver.findElement(By.xpath("//button[normalize-space()='Start']"));
        startButton.click();

        WebElement helloWorld = driver.findElement(By.xpath("//h4[normalize-space()='Hello World!']"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(helloWorld));

        Assert.assertTrue(helloWorld.isDisplayed());
    }
}



