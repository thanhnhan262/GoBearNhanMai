package strategy.regression;

import globals.Const;
import interview.gobear.object.pages.TravelInsuranceResultPage;
import interview.gobear.object.pages.TravelInsuranceSearchPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TravelInsuranceSearchTest extends BaseTest{

    @BeforeMethod
    public void startTest() {
        driver.get(Const.URL);
    }

    @Test
    public void testSearch() {
        TravelInsuranceSearchPage travelInsuranceSearchPage = new TravelInsuranceSearchPage(driver);
        travelInsuranceSearchPage.selectCriteriaAndSearch("single trip", "2 persons", "Thailand", "01-06-2020", "05-06-2020");

        TravelInsuranceResultPage resultPage = new TravelInsuranceResultPage(driver);
        resultPage.waitResultLoaded(Const.SHORT_TIMEOUT_IN_SECOND);
        Assert.assertEquals(resultPage.getPageTitle(), "Search and compare the best travel insurance in the Philippines | GoBear");
        Assert.assertTrue(resultPage.getNumberOfResult() > 3, "Verify the number of result is greater 3");

    }
}
