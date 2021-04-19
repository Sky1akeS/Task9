package browserTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TestDifferentBrowser {
    WebDriver driver;
    DriverType driverType = DriverType.valueOf("CHROME");

    String StartURL = "https://www.google.com/";
    By inputSearch = By.xpath("//input[@title='Поиск']");
    By searchResults = By.cssSelector(".iUh30");
    String query = "SoftServe";

    @Test
    public void test1(){
        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");
        System.setProperty("webdriver.ie.driver", "browserDrivers/IEDriverServer.exe");
        driver = new InternetExplorerDriver();
        driver.manage().window().maximize();
        driver.get(StartURL);

        Boolean isPresent = driver.findElements(By.xpath("//a[@href='https://www.softserveinc.com/uk-ua']")).size() > 0;
        System.out.println(isPresent);

        WebElement searchInput = driver.findElement(inputSearch);
        searchInput.sendKeys(query);
        searchInput.sendKeys(Keys.ENTER);
        WebElement resultsSearch = driver.findElement(searchResults);
        resultsSearch.click();

        String URL = driver.getCurrentUrl();
        Assert.assertTrue(URL.contains("https://www.softserveinc.com/uk-ua"));
        System.out.println(URL);

    }
    @AfterMethod
    public void quitBrowser(){
        driver.quit();
    }
}
