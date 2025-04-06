package car_rent.tests;

import car_rent.core.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchResultsPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddingCarTests extends TestBase {

    @BeforeEach
    public void preCondition() {
        // Переходим на домашнюю страницу
        HomePage homePage = app.getHomePage();
        homePage.selectLogin();
        LoginPage loginPage = app.getLoginPage();
        loginPage.enterEmail("test43Test1@gmail.com");
        loginPage.enterPassword("Password@1");
        loginPage.clickLoginButton();
    }

    @Test
    public void carSearchPositiveTest() {
        // Переход к странице поиска (HomePage)
         HomePage homePage = app.getHomePage();
        // Вводим даты аренды
        String startDate = "15.04.2025 10 00";
        String endDate = "16.04.2025 10 00";
        // Вводим даты аренды
        homePage.enterRentalDates(startDate, endDate);
        // Нажимаем на кнопку поиска
        SearchResultsPage searchResultsPage = homePage.submitSearch();
        // Проверяем, что список доступных автомобилей отобразился
        assertTrue(searchResultsPage.isCarListDisplayed(), "Список доступных автомобилей не отображается.");
        shouldRunTearDown = false;
    }
}
