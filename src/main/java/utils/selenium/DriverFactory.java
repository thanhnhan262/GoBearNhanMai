package utils.selenium;

import globals.Const;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.WebDriverManagerException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;


public class DriverFactory {
    
    public static WebDriver getWebDriver(String browserName){
        WebDriver driver;
        switch (browserName) {
            case Const.BROWSER_CHOME:
                WebDriverManager.getInstance(CHROME).setup();
                driver = new ChromeDriver();
                break;

            case Const.BROWSER_FIREFOX:
                WebDriverManager.getInstance(FIREFOX).setup();
                driver = new FirefoxDriver();
                break;

            default:
                throw new WebDriverManagerException("Browser is not supported: " + browserName);

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Const.SHORT_TIMEOUT_IN_SECOND, TimeUnit.SECONDS);
        return driver;
    }
}
