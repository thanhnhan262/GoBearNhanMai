package interview.gobear.object.pages;

import interview.gobear.object.pages.sub.DatePicker;
import interview.gobear.object.pages.sub.PlanCard;
import interview.gobear.object.pages.sub.Slider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import sun.rmi.runtime.NewThreadAction;
import utils.common.CommonUtil;

import java.util.List;

public class TravelInsuranceResultPage extends BasePage {

    public TravelInsuranceResultPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='travel-quote-list']/div[1]")
    WebElement icoLoading;

    @FindBy(xpath = "//div[@id='travel-quote-list']/div[@style='']/div/div")
    List<WebElement> lstResult;

    @FindBy(xpath = "//label[contains(text(),'Promotions')]/following-sibling::div/div")
    List<WebElement> radPromotions;

    @FindBy(xpath = "//*[@data-gb-name='filter-bar']//a[contains(text(),'SEE MORE')]")
    WebElement btnSeeMore;

    @FindBy(xpath = "//div[@data-gb-name='filter-bar']//div[./label[contains(text(),'Trip cancellation')]]")
    WebElement sldTripCancel;

    @FindBy(xpath = "//label[contains(text(),'Insurers')]/following-sibling::div/div/div")
    List<WebElement> ckbInsurers;

    @FindBy(xpath = "//div[@data-gb-name='sort-bar']//div[contains(@class,'gb-radio-group')]/div")
    List<WebElement> radSortOptions;

    @FindBy(xpath = "//div[@data-gb-name='destinations']/div[1]/div")
    WebElement ddlDetailDestination;

    @FindBy(xpath = "//div[contains(@class,'dropdown-menu')]/ul/li")
    List<WebElement> lstDetailDestinations;

    @FindBy(xpath = "//div[contains(@class, 'travel-date-field')]//input[@name='dates-startdate']")
    WebElement dtpStartDate;

    @FindBy(xpath = "//div[contains(@class,'datepicker-dropdown')]")
    WebElement tblDate;

    @FindBy(xpath = "//div[@data-gb-name='travel-nav-data']//p")
    WebElement txtTravelData;

    public int getNumberOfResult() {
        return lstResult.size();
    }

    public void waitResultLoaded(int timeOut) {
        boolean isLoading = true;
        while(isLoading && timeOut > 0){
            timeOut--;
            isLoading = icoLoading.getText().equals("Loading...");
        }
    }

    public void selectFilterPromotion(String text) {
        for (WebElement ele: radPromotions
             ) {
            if (ele.getText().equals(text)) {
                ele.click();
            }
        }
    }

    public boolean isFilterPromotionSelected(String text) {
        boolean isSelected = false;
        for (WebElement ele: radPromotions
        ) {
            if (ele.getText().equals(text)) {
                isSelected = ele.findElement(By.tagName("input")).isSelected();
            }
        }
        return isSelected;
    }

    public void expandFilter() {
        btnSeeMore.click();
    }

    public void filterTripCancelMin(int min) {
        Slider slider = new Slider(driver, sldTripCancel);
        slider.selectMin(min);
    }

    public boolean isTripCancelMinMax(int min, int max) {
        Slider slider = new Slider(driver, sldTripCancel);
        return slider.getMin() == min && slider.getMax() == max;
    }

    public void filterTripCancelMax(int max) {
        Slider slider = new Slider(driver, sldTripCancel);
        slider.selectMax(max);
    }

    public void selectFilterInsurer(String text) {
        for (WebElement ele: ckbInsurers
             ) {
            if (ele.getText().equals(text)) {
                ele.click();
            }
        }
    }

    public boolean isFilterInsurerSelected(String text) {
        boolean isSelected = false;
        for (WebElement ele: ckbInsurers
        ) {
            if (ele.getText().equals(text)) {
                isSelected = ele.findElement(By.tagName("input")).isSelected();
            }
        }
        return isSelected;
    }

    public void selectSortOption(String text) {
        for (WebElement ele: radSortOptions
        ) {
            if (ele.getText().equals(text)) {
                ele.click();
            }
        }
    }

    public boolean isSortOptionSelected(String text) {
        boolean isSelected = false;
        for (WebElement ele: radSortOptions
        ) {
            if (ele.getText().equals(text)) {
                isSelected = ele.findElement(By.tagName("input")).isSelected();
            }
        }
        return isSelected;
    }

    public void selectDetailsDestination(String text) {
        ddlDetailDestination.click();
        for (WebElement item: lstDetailDestinations
             ) {
            if (item.getText().equals(text)) {
                item.click();
            }
        }
    }

    public boolean isDetailsDestinationSelected(String text) {
        boolean isSelected = false;
        isSelected = ddlDetailDestination.findElement(By.tagName("span")).getText().equals(text);
        return  isSelected;
    }

    public void selectDetailsStartDate(String dateString) {
        dtpStartDate.click();
        DatePicker datePickup = new DatePicker(tblDate);
        datePickup.pickADate(dateString);
    }

    public boolean isDetailsStartDateSelected(String dateString) {
        boolean isSelected = false;
        isSelected = dtpStartDate.getAttribute("value").equals(dateString);
        return  isSelected;
    }

    public boolean isResultBelongToInsurances(List<String> expectedInsurances) {
        String actualInsurance;
        for (WebElement ele: lstResult
             ) {
            boolean found = false;
            PlanCard planCard = new PlanCard(ele);
            actualInsurance = planCard.getInsurerName();
            for (String exp: expectedInsurances
                 ) {
                if (actualInsurance.equals(exp)) {
                    found = true;
                }
            }
            if (!found) {
                System.out.println(">>> Insurance '" + planCard.getInsurerName() + "' is not expected <<<");
                return false;
            }
        }
        return true;
    }

    public boolean isTravelDataContainInfo(String info) {
        return txtTravelData.getText().contains(info);
    }

    public boolean isPriceSortedLowToHigh() {
        boolean isSorted = true;
        for (int i = 0; i < lstResult.size() - 1; i++) {
            PlanCard curCard = new PlanCard(lstResult.get(i));
            PlanCard nextCard = new PlanCard(lstResult.get(i+1));
            if (curCard.getPrice() > nextCard.getPrice()) {
                isSorted = false;
            }
        }
        return isSorted;
    }
}
