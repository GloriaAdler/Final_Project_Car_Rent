package carrent.rent_page;

import carrent.core.BasePage;
import carrent.core.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import carrent.pages.HomePage;
import carrent.pages.LoginPage;
import carrent.pages.SearchResultsPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchResultsCarsTests extends TestBase {

    @BeforeEach
    public void preCondition() {
        HomePage homePage = app.getHomePage();
//        homePage.selectLogin();
//        LoginPage loginPage = app.getLoginPage();
//        loginPage.enterEmail("admin@gmail.com");
//        loginPage.enterPassword("Yyyyyyy12345!");
//        BasePage basePage = app.getLoginPage();
//        basePage.scrollToTop();
//        loginPage.clickLoginButton();
    }

    @Test
    public void searchForCarsPositiveTest() {
         HomePage homePage = app.getHomePage();
        String startDate = "15-04-2025T10:30";
        String endDate = "20-04-2025T10:30";
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


