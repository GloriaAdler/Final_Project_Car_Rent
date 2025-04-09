package car_rent.tests;

import car_rent.core.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchResultsPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchResultsCarsTests extends TestBase {

    @BeforeEach
    public void preCondition() {
        HomePage homePage = app.getHomePage();
        homePage.selectLogin();
        LoginPage loginPage = app.getLoginPage();
        loginPage.enterEmail("admin@gmail.com");
        loginPage.enterPassword("Yyyyyyy12345!");
        loginPage.clickLoginButton();
    }

    @Test
    public void searchForCarsPositiveTest() {
         HomePage homePage = app.getHomePage();
        String startDate = "15.04.2025 10 00";
        String endDate = "20.04.2025 10 00";
        homePage.enterRentalDates(startDate, endDate);
        SearchResultsPage searchResultsPage = homePage.submitSearch();
        assertTrue(searchResultsPage.isCarListDisplayed(), "Список доступных автомобилей не отображается.");
        shouldRunTearDown = false;
    }

    @Test
    public void searchForCarsNegativeTest() {
        HomePage homePage = app.getHomePage();
        String startDate = "15.01.2025 10 00";
        String endDate = "20.01.2025 10 00";
        homePage.enterRentalDates(startDate, endDate);
        SearchResultsPage searchResultsPage = homePage.submitSearch();
        assertTrue(searchResultsPage.isErrorMessageStart());
        shouldRunTearDown = false;
    }
}


