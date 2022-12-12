import org.openqa.selenium.Alert;
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

public class JavaScriptAletrsTest {
    private WebDriver driver;

    @BeforeMethod
    private void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "E:\\Program Files\\webdriver\\chromedriver.exe");

        driver = new ChromeDriver();
        String url = "https://the-internet.herokuapp.com/";

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        WebElement jsAlertPage = driver.findElement(By.xpath("//a[normalize-space()='JavaScript Alerts']"));
        jsAlertPage.click();
    }

    @AfterMethod
    private void closeDriver() {
        driver.close();
    }

    @Test
    public void jsAlerts() {
        WebElement jsAlert = driver.findElement(By.xpath("//button[@onclick='jsAlert()']"));
        jsAlert.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        Alert alert = driver.switchTo().alert();
        alert.accept();

        WebElement result = driver.findElement(By.id("result"));
        Assert.assertTrue(result.isDisplayed());
    }

    @Test
    public void jsConfirmCanel() {
        WebElement jsConfirm = driver.findElement(By.xpath("//button[@onclick='jsConfirm()']"));
        jsConfirm.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.switchTo().alert().dismiss();

        WebElement resultCanel = driver.findElement(By.xpath("//p[@id='result']"));
        resultCanel.isDisplayed();
    }

    @Test
    public void jsConfirmOk() {
        WebElement jsConfirm = driver.findElement(By.xpath("//button[@onclick='jsConfirm()']"));
        jsConfirm.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement resultOk = driver.findElement(By.xpath("//p[@id='result']"));
        resultOk.isDisplayed();
    }

    @Test
    public void jsPromptText() {
        WebElement jsPrompt = driver.findElement(By.xpath("//button[@onclick='jsPrompt()']"));
        jsPrompt.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());


        alert.sendKeys("simple text");
        alert.accept();

        String actualText = driver.findElement(By.xpath("//p[@id='result']")).getText();
        Assert.assertEquals(actualText.contains("simple text"), true);
    }
}
