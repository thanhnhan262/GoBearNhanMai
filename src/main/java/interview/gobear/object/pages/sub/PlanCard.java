package interview.gobear.object.pages.sub;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.sql.SQLOutput;
import java.util.List;

public class PlanCard {
    WebElement webElement;
    public PlanCard(WebElement webElement) {
        this.webElement = webElement;
    }

    public String getInsurerName() {
        WebElement ele = webElement.findElement(By.xpath("//div[@class='card-brand']/h4"));
        return ele.getText();
    }

    public int getPrice() {
        WebElement ele = webElement.findElement(By.className("value"));
        return Integer.parseInt(ele.getText().replace(",",""));
    }

    public void selectCompare() {

    }

    public void viewDetail() {

    }

    public String getDetailInfo(String text) {
        String xpath = "//*[contains(@class,'card-content-details row')]/div[./p[contains(text()," + text + ")]]";
        WebElement key = webElement.findElement(By.xpath(xpath));
        WebElement value = key.findElement(By.xpath("//p[2]"));
        return value.getText();
    }

}
