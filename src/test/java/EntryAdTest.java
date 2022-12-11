import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class EntryAdTest {

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
    public void entryAd() {
        WebElement entryAdPage = driver.findElement(By.xpath("//a[normalize-space()='Entry Ad']"));
        entryAdPage.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[normalize-space()='This is a modal window']")));

        WebElement modalContainer = driver.findElement(By.xpath("//div[@class='modal-body']//p[1]"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(modalContainer.isDisplayed());

        WebElement closeModal = driver.findElement(By.xpath("//p[normalize-space()='Close']"));
        closeModal.click();
    }
}




