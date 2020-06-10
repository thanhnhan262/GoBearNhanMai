package interview.gobear.object.pages.sub;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Listbox {
    WebElement webElement;
    public Listbox(WebElement webElement) {
        this.webElement = webElement;
    }

    public void selectItem(String itemName) {
        List<WebElement> items = webElement.findElements(By.tagName("li"));
        for (WebElement ele: items
        ) {
            if (ele.getText().equals(itemName)) {
                ele.click();
            }
        }
    }

    public void selectRandomItem() {
        
    }
}
