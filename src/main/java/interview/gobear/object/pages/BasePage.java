package interview.gobear.object.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver1) {
        this.driver = driver1;
        System.out.println("dev add 1");
        System.out.println("dev add 2");
        PageFactory.initElements(driver1, this);
        System.out.println("dev add 3");
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
