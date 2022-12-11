import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicAuthTest {

    private WebDriver driver;

    @Test
    public void basicAuth() {
        System.setProperty("webdriver.chrome.driver", "E:\\Program Files\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        WebElement text = driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]"));
        Assert.assertEquals(text.getText(), "Congratulations! You must have the proper credentials.");
        driver.close();
    }
}
