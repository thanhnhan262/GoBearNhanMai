package interview.gobear.object.pages.sub;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class Slider {
    WebDriver driver;
    WebElement webElement;


    public Slider(WebDriver driver, WebElement webElement) {
        this.driver = driver;
        this.webElement = webElement;
    }

    public void selectMin(int min) {
        WebElement btnMin = webElement.findElement(By.className("min-slider-handle"));
        Actions move = new Actions(driver);
        int currentMin = Integer.parseInt(btnMin.getAttribute("aria-valuenow"));
        while(min > currentMin) {
            move.moveToElement(btnMin).clickAndHold().moveByOffset(1, 0).release().perform();
            btnMin = webElement.findElement(By.className("min-slider-handle"));
            currentMin = Integer.parseInt(btnMin.getAttribute("aria-valuenow"));
        }
    }

    public int getMin() {
        WebElement btnMin = webElement.findElement(By.className("min-slider-handle"));
        int currentMin = Integer.parseInt(btnMin.getAttribute("aria-valuenow"));
        return currentMin;
    }

    public void selectMax(int max) {
        WebElement btnMax = webElement.findElement(By.className("max-slider-handle"));
        Actions move = new Actions(driver);
        int currentMax = Integer.parseInt(btnMax.getAttribute("aria-valuenow"));
        while(max < currentMax) {
            move.moveToElement(btnMax).clickAndHold().moveByOffset(-1, 0).release().perform();
            btnMax = webElement.findElement(By.className("max-slider-handle"));
            currentMax = Integer.parseInt(btnMax.getAttribute("aria-valuenow"));
        }
    }

    public int getMax() {
        WebElement btnMax = webElement.findElement(By.className("max-slider-handle"));
        int currentMax = Integer.parseInt(btnMax.getAttribute("aria-valuenow"));
        return currentMax;
    }
}
