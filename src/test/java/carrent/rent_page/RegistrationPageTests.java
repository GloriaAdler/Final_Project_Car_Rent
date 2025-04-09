package carrent.rent_page;

import carrent.core.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import carrent.pages.HomePage;
import carrent.pages.LoginPage;
import carrent.pages.RegistrationPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationPageTests extends TestBase {

    public WebDriver driver;
    public WebDriverWait wait;
    RegistrationPage registrationPage = new RegistrationPage(app.driver, app.wait);

    @BeforeEach
    public void preCondition() {
        // Переходим на домашнюю страницу
        HomePage homePage = app.getHomePage();
        homePage.selectLogin();
        LoginPage loginPage = app.getLoginPage();
        loginPage.clickSignUp();
    }

    @Test
    public void registrationPositiveTest() {
        new RegistrationPage(app.driver, app.wait)
                .enterPersonalData("John", "Snow", "johnsnow"+System.currentTimeMillis()+"@gmail.com", "Password1@")
                .agreeToTerms()
                .clickCreateButton()
                .verifySuccessMessage("Registration successful! Please check your email to confirm your registration.")
                .clickOkButton()
        ;
        shouldRunTearDown = false;
    }

    @Test
    public void registrationWithAnAlreadyExistingEmailAddressNegativeTest() {
        new RegistrationPage(app.driver, app.wait)
                .enterPersonalData("John", "Snow", "test43@gmail.com", "Password1@")
                .agreeToTerms()
                .clickCreateButton()
                .verifyErrorMessage("Error") //Customer already exists
                .clickCancelButton()
        ;
        shouldRunTearDown = false;
    }

    @Test
    public void registrationWithInvalidEmailNegativeTest() {
        new RegistrationPage(app.driver, app.wait)
                .enterPersonalData("John", "Snow", "test43gmail.com", "Password1@")
                .agreeToTerms()
                .clickCreateButton();
        // Прокрутка наверх
        app.getBasePage().scrollToTop();
        // Проверяем видимость элемента "Log In"
        RegistrationPage registrationPage = app.getRegistrationPage();
        assertTrue(registrationPage.isLogInVisible(), "The 'Log In' element is visible");
        shouldRunTearDown = false;
    }

    @Test
    public void registrationWithInvalidPasswordNegativeTest() {
        new RegistrationPage(app.driver, app.wait)
                .enterPersonalData("John", "Snow", "johnsnow"+System.currentTimeMillis()+"@gmail.com", "Password")
                .agreeToTerms()
                .clickCreateButton();
        RegistrationPage registrationPage = app.getRegistrationPage();
        assertTrue(registrationPage.isPasswordMessageVisible());
        shouldRunTearDown = false;
    }

    @Test
    public void registrationWithInvalidLittlePasswordNegativeTest() {
        new RegistrationPage(app.driver, app.wait)
                .enterPersonalData("John", "Snow", "johnsnow"+System.currentTimeMillis()+"@gmail.com", " Pass@1")
                .agreeToTerms()
                .clickCreateButton();
        RegistrationPage registrationPage = app.getRegistrationPage();
        assertTrue(registrationPage.isLittlePasswordMessageVisible());
        shouldRunTearDown = false;
    }

    @Test
    public void registrationInvalidFirstNameNegativeTest() {
        RegistrationPage registrationPage = new RegistrationPage(app.driver, app.wait);
        registrationPage
                .enterPersonalData("", "Snow", "test_123456@gmail.com", "Password@1")
                .agreeToTerms();
        assertFalse(registrationPage.isCreateButtonEnabled(), "The 'Create' button should be inactive if the field is empty.");
        shouldRunTearDown = false;
    }

    @Test
    public void registrationInvalidLastNameNegativeTest() {
        RegistrationPage registrationPage = new RegistrationPage(app.driver, app.wait);
        registrationPage
                .enterPersonalData("John", "", "test_123457@gmail.com", "Password@")
                .agreeToTerms();
        assertFalse(registrationPage.isCreateButtonEnabled(), "The 'Create' button should be inactive if the field is empty.");
        shouldRunTearDown = false;
    }

    @Test
    public void registrationEmptyEmailNegativeTest() {
        RegistrationPage registrationPage = new RegistrationPage(app.driver, app.wait);
        registrationPage
                .enterPersonalData("John", "Snow", "", "Password@")
                .agreeToTerms();
        assertFalse(registrationPage.isCreateButtonEnabled(), "The 'Create' button should be inactive if the field is empty.");
        shouldRunTearDown = false;
    }

    @Test
    public void registrationEmptyPasswordNegativeTest() {
        RegistrationPage registrationPage = new RegistrationPage(app.driver, app.wait);
        registrationPage
                .enterPersonalData("John", "Snow", "test_123458@gmail.com", "")
                .agreeToTerms();
        assertFalse(registrationPage.isCreateButtonEnabled(), "The 'Create' button should be inactive if the field is empty.");
        shouldRunTearDown = false;
    }
}
