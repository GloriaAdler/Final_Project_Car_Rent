package carrent.admin_tests;

import carrent.admin_pages.AdminPage;
import carrent.core.TestBase;
import carrent.pages.HomePage;
import carrent.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdminLoginTests extends TestBase {

    @BeforeEach
    public void setUp() {
        new HomePage(app.driver, app.wait).selectLogin();
    }

    @Test
    public void testAdminAuthorizationPositiveTest() {
        new LoginPage(app.driver, app.wait)
                .adminLogIn("admin@gmail.com", "Yyyyyyy12345!");

        // Проверяем видимость элемента "My Account"
        AdminPage adminPage = app.getAdminPage(); // получаем новую версию после логина
        assertTrue(adminPage.isMyAccountAdminVisible(), "The 'My Account' element is visible");
        shouldRunTearDown = false;

    }

    @Test
    public void testAdminAuthorizationEmptyEmailNegativeTest() {
        // Переходим на страницу логина
        LoginPage loginPage = app.getLoginPage();
        loginPage.enterEmail("");
        loginPage.enterPassword("Yyyyyyy12345!");
        loginPage.clickLoginButton();
        // Проверяем, что элемент "Admin" не виден
        AdminPage adminPage = app.getAdminPage();
        assertFalse(adminPage.isMyAccountAdminVisible(), "The 'Admin' element is visible with incorrect email");
        shouldRunTearDown = false;
    }

    @Test
    public void testAdminAuthorizationEmptyPasswordNegativeTest() {
        // Переходим на страницу логина
        LoginPage loginPage = app.getLoginPage();
        loginPage.enterEmail("admin@gmail.com");
        loginPage.enterPassword("");
        loginPage.clickLoginButton();
        // Проверяем, что элемент "Admin" не виден
        AdminPage adminPage = app.getAdminPage();
        assertFalse(adminPage.isMyAccountAdminVisible(), "The 'Admin' element is visible with incorrect password");
        shouldRunTearDown = false;
    }

    @Test
    public void testAdminAuthorizationInvalidCredentialsNegativeTest() {
        // Переходим на страницу логина
        LoginPage loginPage = app.getLoginPage();
        loginPage.enterEmail("admi@gmail.com");
        loginPage.enterPassword("Yyyyyyy12345!");
        loginPage.clickLoginButton();
        // Проверяем, что элемент "Admin" не виден
        AdminPage adminPage = app.getAdminPage();
        // Check interface
        assertFalse(adminPage.isMyAccountAdminVisible(), "The 'My Account' element is visible with incorrect credentials");
        // Check error message
        String expectedMessage = "Password or email incorrect";
        String actualMessage = app.getLoginPage().getErrorMessage();
        assertEquals(expectedMessage, actualMessage, "Error message should match expected text for invalid login");
        shouldRunTearDown = false;
    }
}
