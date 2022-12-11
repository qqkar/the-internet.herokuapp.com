import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContextMenuTest {
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
    public void contextMenu() {
        WebElement contextMenuPage = driver.findElement(By.xpath("//a[normalize-space()='Context Menu']"));
        contextMenuPage.click();

        Actions actions = new Actions(driver);
        WebElement box = driver.findElement(By.id("hot-spot"));
        actions.contextClick(box).perform();

        String alertMessage = driver.switchTo().alert().getText();
        Assert.assertTrue(alertMessage.contains("You selected a context menu"));
        driver.switchTo().alert().accept();
    }
}
