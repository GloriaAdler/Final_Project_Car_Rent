package carrent.rent_page;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import carrent.pages.AccountPage;
import carrent.pages.HomePage;
import carrent.pages.LoginPage;
import carrent.core.TestBase;
import static org.junit.jupiter.api.Assertions.*;

public class LoginPageTests extends TestBase {

    public WebDriver driver;
    public WebDriverWait wait;

    AccountPage accountPage = new AccountPage(app.driver, app.wait);

    @BeforeEach
    public void preCondition(){
        // Переходим на домашнюю страницу
        HomePage homePage = app.getHomePage();
        homePage.selectLogin();
    }

    @Test
    public void authorizationPositiveTest() {
        // Переходим на страницу логина
        LoginPage loginPage = app.getLoginPage();
        loginPage.enterEmail("test43@gmail.com");
        loginPage.enterPassword("Password1@");
        loginPage.clickLoginButton();
        // Проверяем видимость элемента "My Account"
        AccountPage accountPage = app.getAccountPage(); // получаем новую версию после логина
        assertTrue(accountPage.isMyAccountVisible(), "The 'My Account' element is visible");
        shouldRunTearDown = false;  // Выключаем выполнение tearDown - оставляем браузер открытым
    }

    @Test
    public void authorizationWithIncorrectLoginNegativeTest() {
        // Переходим на страницу логина
        LoginPage loginPage = app.getLoginPage();
        loginPage.enterEmail("incorrectEmail@gmail.com");
        loginPage.enterPassword("Password@1");
        loginPage.clickLoginButton();
        // Проверяем, что элемент "My Account" не виден
        assertFalse(accountPage.isMyAccountVisible(), "The 'My Account' element is visible with incorrect login");
        shouldRunTearDown = false;
    }

    @Test
    public void authorizationWithIncorrectPasswordNegativeTest() {
        // Переходим на страницу логина
        LoginPage loginPage = app.getLoginPage();
        loginPage.enterEmail("test43Test1@gmail.com");
        loginPage.enterPassword("IncorrectPassword");
        loginPage.clickLoginButton();
        // Проверяем, что элемент "My Account" не виден
        assertFalse(accountPage.isMyAccountVisible(), "The 'My Account' element is visible with incorrect password");
        shouldRunTearDown = false;
    }

    @Test
    public void authorizationWithInvalidLoginNegativeTest() {
        // Переходим на страницу логина
        LoginPage loginPage = app.getLoginPage();
        loginPage.enterEmail("invalidEmail");
        loginPage.enterPassword("Password@1");
        loginPage.clickLoginButton();
        // Проверяем, что элемент "My Account" не виден
        assertFalse(accountPage.isMyAccountVisible(), "The 'My Account' element is visible with invalid login");
        shouldRunTearDown = false;
    }

    @Test
    public void authorizationWithInvalidPasswordNegativeTest() {
        // Переходим на страницу логина
        LoginPage loginPage = app.getLoginPage();
        loginPage.enterEmail("test43Test1@gmail.com");
        loginPage.enterPassword("123");
        loginPage.clickLoginButton();
        // Проверяем, что элемент "My Account" не виден
        assertFalse(accountPage.isMyAccountVisible(), "The 'My Account' element is visible with invalid password");
        shouldRunTearDown = false;
    }

    @Test
    public void emailFieldIsNotFilledInNegativeTest() {
        // Переходим на страницу логина
        LoginPage loginPage = app.getLoginPage();
        loginPage.enterEmail(""); // Оставляем поле пустым
        loginPage.enterPassword("Password@1");
        loginPage.clickLoginButton();
        // Проверяем, что элемент "My Account" не виден
        assertFalse(accountPage.isMyAccountVisible(), "The 'My Account' element is visible when email is not filled");
        shouldRunTearDown = false;
    }

    @Test
    public void passwordFieldIsNotFilledInNegativeTest() {
        // Переходим на страницу логина
        LoginPage loginPage = app.getLoginPage();
        loginPage.enterEmail("test43Test1@gmail.com");
        loginPage.enterPassword(""); // Оставляем поле пустым
        loginPage.clickLoginButton();
        // Проверяем, что элемент "My Account" не виден
        assertFalse(accountPage.isMyAccountVisible(), "The 'My Account' element is visible when password is not filled");
        shouldRunTearDown = false;
    }
}
