package strategy.regression;

import globals.Const;
import interview.gobear.object.pages.TravelInsuranceResultPage;
import interview.gobear.object.pages.TravelInsuranceSearchPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.common.CommonUtil;

import java.util.Arrays;
import java.util.List;

public class TravelInsuranceResultTest extends BaseTest {

    @BeforeMethod
    public void startTest() {
        driver.get(Const.URL);
    }

    @Test
    public void testFilterTripCancellation() {
        TravelInsuranceSearchPage travelInsuranceSearchPage = new TravelInsuranceSearchPage(driver);
        travelInsuranceSearchPage.search();

        TravelInsuranceResultPage resultPage = new TravelInsuranceResultPage(driver);
        resultPage.expandFilter();
        int min = 10000;
        int max = 90000;
        resultPage.filterTripCancelMin(min);
        resultPage.filterTripCancelMax(max);
        Assert.assertTrue(resultPage.isTripCancelMinMax(min, max), "Verify filter trip cancellation range");
    }

    @Test
    public void testFilterInsurance() {
        TravelInsuranceSearchPage travelInsuranceSearchPage = new TravelInsuranceSearchPage(driver);
        travelInsuranceSearchPage.search();

        TravelInsuranceResultPage resultPage = new TravelInsuranceResultPage(driver);
        List<String> filteredInsurances = Arrays.asList("Pacific Cross", "Standard Insurance");
        for (String ins: filteredInsurances
        ) {
            resultPage.selectFilterInsurer(ins);
            Assert.assertTrue(resultPage.isFilterInsurerSelected(ins), "Verify insurance '" + ins + "' is selected");
        }
        Assert.assertTrue(resultPage.isResultBelongToInsurances(filteredInsurances), "Verify insurances are filtered correctly");
    }

    @Test
    public void testSort() {
        TravelInsuranceSearchPage travelInsuranceSearchPage = new TravelInsuranceSearchPage(driver);
        travelInsuranceSearchPage.search();

        TravelInsuranceResultPage resultPage = new TravelInsuranceResultPage(driver);
        String sort = "Price: Low to high";
        resultPage.selectSortOption(sort);
        Assert.assertTrue(resultPage.isSortOptionSelected(sort), "Verify sort by '" + sort + "' is selected");
        Assert.assertTrue(resultPage.isPriceSortedLowToHigh(), "Verify result is sorted from low to high price");
    }

    @Test
    public void testSelectingDetails() {
        TravelInsuranceSearchPage travelInsuranceSearchPage = new TravelInsuranceSearchPage(driver);
        travelInsuranceSearchPage.search();

        TravelInsuranceResultPage resultPage = new TravelInsuranceResultPage(driver);
        String des = "Singapore";
        resultPage.selectDetailsDestination(des);
        Assert.assertTrue(resultPage.isDetailsDestinationSelected(des), "Verify destination '" + des + "' is selected");
        Assert.assertTrue(resultPage.isTravelDataContainInfo("travel to " + des), "Verify changing destination works correctly");

        String dateString = "20-11-2021";
        resultPage.selectDetailsStartDate(dateString);
        Assert.assertTrue(resultPage.isDetailsStartDateSelected(dateString), "Verify date '" + dateString + "' is selected");
        String convertedDate = CommonUtil.convertDateString(dateString, "dd-MM-yyyy", "dd MMM yyyy");
        Assert.assertTrue(resultPage.isTravelDataContainInfo("from " + convertedDate), "Verify changing From Date works correctly");
    }
}
