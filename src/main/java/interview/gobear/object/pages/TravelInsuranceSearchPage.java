package interview.gobear.object.pages;

import interview.gobear.object.pages.sub.DatePicker;
import interview.gobear.object.pages.sub.Listbox;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.selenium.WebElementAction;

public class TravelInsuranceSearchPage extends BasePage {

    public TravelInsuranceSearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(@class,'tabs-products')]//li[@data-gb-name='Insurance']")
    WebElement tabInsurance;

    @FindBy(xpath = "//div[@id='Insurance']//li[@data-gb-name='Travel']")
    WebElement tabTravel;

    @FindBy(xpath = "//div[@data-gb-name='trip-type']/div[1]")
    WebElement lstSelectTripType;

    @FindBy(xpath = "//div[@data-gb-name='trip-type']/div[2]")
    WebElement lstSelectTraveller;

    @FindBy(xpath = "//div[contains(text(),\"I'm going to\")]/div[1]")
    WebElement lstSelectDestination;

    @FindBy(className = "date-from")
    WebElement dtpDateFrom;

    @FindBy(className = "date-to")
    WebElement dtpDateTo;

    @FindBy(xpath = "//div[contains(@class,'datepicker-dropdown')]")
    WebElement tblDate;

    @FindBy(xpath = "//button[normalize-space(text())='Show my results']")
    WebElement btnSearch;

    public void search() {
        tabInsurance.click();
        tabTravel.click();
        WebElementAction action = new WebElementAction(driver, btnSearch);
        action.waitAndClick();
    }

    public void selectCriteriaAndSearch(String tripType, String traveler, String des, String dateFrom, String dateTo) {
        tabInsurance.click();
        tabTravel.click();
        if (tripType != null) {
            lstSelectTripType.click();
            Listbox listbox = new Listbox(lstSelectTripType);
            listbox.selectItem(tripType);
        }
        if (traveler != null) {
            lstSelectTraveller.click();
            Listbox listbox = new Listbox(lstSelectTraveller);
            listbox.selectItem(traveler);
        }
        if (des != null) {
            lstSelectDestination.click();
            Listbox listbox = new Listbox(lstSelectDestination);
            listbox.selectItem(des);
        }
        if (dateFrom != null) {
            dtpDateFrom.click();
            DatePicker datePicker = new DatePicker(tblDate);
            datePicker.pickADate(dateFrom);
        }
        if (dateTo != null) {
            dtpDateTo.click();
            DatePicker datePicker = new DatePicker(tblDate);
            datePicker.pickADate(dateTo);
        }
        WebElementAction action = new WebElementAction(driver, btnSearch);
        action.waitAndClick();
    }
}
