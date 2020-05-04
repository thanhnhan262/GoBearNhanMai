package strategy.regression;

import globals.Const;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.common.CommonUtil;
import utils.selenium.DriverFactory;

public class BaseTest {
    protected WebDriver driver;
    @Parameters("browser")
    @BeforeTest
    public void startApp(@Optional(Const.BROWSER_CHOME)String browserType) {
        driver = DriverFactory.getWebDriver(browserType);
    }

    @AfterTest
    public void stopApp() {
        driver.quit();
    }
}
