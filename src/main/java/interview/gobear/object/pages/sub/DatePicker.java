package interview.gobear.object.pages.sub;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DatePicker {
    WebElement webElement;

    public DatePicker(WebElement webElement) {
        this.webElement = webElement;
    }

    public boolean pickADate(String dateString) {
        boolean isSuccessfulPick = true;
        try {
            String[] date = dateString.split("-");
            int day = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int year = Integer.parseInt(date[2]);

            WebElement btnMonthYear = webElement.findElement(By.xpath("//*[@class='datepicker-days']//thead//th[@class='datepicker-switch']"));
            btnMonthYear.click();

            // select target year
            WebElement btnYear = webElement.findElement(By.xpath("//*[@class='datepicker-months']//thead//th[@class='datepicker-switch']"));
            int displayedYear = Integer.parseInt(btnYear.getText());
            WebElement btnBackYear = webElement.findElement(By.xpath("//*[@class='datepicker-months']//thead//th[text()='«']"));
            WebElement btnNextYear = webElement.findElement(By.xpath("//*[@class='datepicker-months']//thead//th[text()='»']"));
            int yearDiff = year - displayedYear;
            if (yearDiff > 0) {
                while (yearDiff > 0) {
                    btnNextYear.click();
                    yearDiff--;
                }
            }
            if (yearDiff < 0) {
                while (yearDiff < 0) {
                    btnBackYear.click();
                    yearDiff++;
                }
            }

            //select target month
            List<WebElement> btnMonths = webElement.findElements(By.xpath("//*[@class='datepicker-months']//tbody//span"));
            btnMonths.get(month - 1).click();

            //select target day
            List<WebElement> btnDays = webElement.findElements(By.xpath("//*[@class='datepicker-days']//tbody//td[not(contains(@class,'old')) and not(contains(@class,'new'))]"));
            btnDays.get(day - 1).click();

        }catch (Exception e) {
            isSuccessfulPick = false;
            e.printStackTrace();
        }

        return isSuccessfulPick;
    }
}
