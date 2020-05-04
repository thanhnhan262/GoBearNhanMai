package utils.selenium;

import globals.Const;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementAction {
    WebDriver driver;
    WebElement webElement;

    public WebElementAction(WebDriver driver, WebElement webElement) {
        this.driver = driver;
        this.webElement = webElement;
    }

    public void waitAndClick() {
        WebDriverWait wait = new WebDriverWait(driver, Const.SHORT_TIMEOUT_IN_SECOND);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }
}
