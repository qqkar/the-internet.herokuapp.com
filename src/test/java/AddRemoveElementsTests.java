import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class AddRemoveElementsTests {
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

//    @AfterMethod
//    private void closeDriver() {
//        driver.close();
//    }

    @Test
    public void addElement() {
        WebElement addRemoveElementPage = driver.findElement(By.xpath("//a[normalize-space()='Add/Remove Elements']"));
        addRemoveElementPage.click();

        SoftAssert softAssert = new SoftAssert();
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertEquals(currentUrl, "https://the-internet.herokuapp.com/add_remove_elements/");

        WebElement addElementButton = driver.findElement(By.xpath("//button[@onclick='addElement()']"));

        for (int i = 0; i < 10; i++) {
            addElementButton.click();
        }

        List<WebElement> deleteButton = new ArrayList<>();
        deleteButton = driver.findElements(By.xpath("//div[@id='elements']/button"));
        int listSize = deleteButton.size();

        System.out.println(listSize);

        softAssert.assertEquals(listSize, 10);
    }

    @Test
    public void deleteElement() {
        addElement();
        SoftAssert softAssert = new SoftAssert();

        WebElement deleteButton3 = driver.findElement(By.xpath("//button[3]"));
        WebElement deleteButton7 = driver.findElement(By.xpath("//button[7]"));

        deleteButton3.click();
        deleteButton7.click();

        List<WebElement> deleteButton = new ArrayList<>();
        deleteButton = driver.findElements(By.xpath("//div[@id='elements']/button"));
        int listSize = deleteButton.size();

        System.out.println(listSize);

        softAssert.assertEquals(listSize, 8);
    }

}
