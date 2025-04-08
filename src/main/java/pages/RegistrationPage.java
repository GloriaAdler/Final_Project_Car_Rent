package pages;

import core.BasePage;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this); // инициализация всех @FindBy
    }

    @FindBy(css = "input[name='firstName']")
    WebElement userFirstName;
    @FindBy(css = "input[name='lastName']")
    WebElement userLastName;
    @FindBy(css = "input[name='email']")
    WebElement userEmail;
    @FindBy(css = "input[name='password']")
    WebElement userPassword;
    @FindBy(css = "input[type='checkbox']")
    WebElement termsCheckbox;
    @FindBy(css = "button[type='submit']")
    WebElement createButton;

    public RegistrationPage enterPersonalData(String firstName, String lastName, String email, String password) {
        type(userFirstName, firstName);
        type(userLastName, lastName);
        type(userEmail, email);
        type(userPassword, password);
        return this;
    }

    public RegistrationPage agreeToTerms() {
        Actions actions = new Actions(driver);
        actions.moveToElement(termsCheckbox).click().perform();
        return this;
    }

    public RegistrationPage clickCreateButton() {
        createButton.click();
        return this;
    }

    // Метод для ожидания элемента "Registration successful! ..." об успешной регистрации
    @FindBy(xpath = "(//h3[text()='Success']/following-sibling::p)[2]")
    WebElement successMessage;

    public RegistrationPage verifySuccessMessage(String messageText) {
        assert successMessage.getText().contains(messageText);
        return this;
    }

    @FindBy(xpath = "(//button[@type='button'])[2]")
    WebElement okButton;

    public void clickOkButton() {
        okButton.click();
    }

    //Метод для ожидания элемента "Error. Customer already exists" об ошибке регистрации (негативные тесты)
    @FindBy(xpath = "//h3[normalize-space(text())='Error']")
    WebElement errorMessage;

    public RegistrationPage verifyErrorMessage(String error) {
        assert errorMessage.getText().contains(error);
        return this;
    }

    @FindBy(css = "button[type='button']")
    WebElement cancelButton;

    public void clickCancelButton() {
        click(cancelButton);
    }

    //* Check UnSuccessfully Registration
    @FindBy(xpath = "//a[normalize-space(text())='Log in']")
    WebElement logIn;

    public void checkLogIn() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOf(logIn));
            assertTrue(element.isDisplayed(), "'Log In' элемент виден на экране!");
        } catch (TimeoutException e) {
            throw new AssertionError("The 'Log In' element did not appear during the waiting time");
        }
    }

    @FindBy(xpath = "//a[normalize-space(text())='Log in']")
    private WebElement logInElement;

    // Метод для ожидания элемента "Log in" на экране
    public boolean isLogInVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(logInElement)); //ожидания видимости элемента
            return logInElement.isDisplayed();
        } catch (Exception e) {
            if (driver instanceof TakesScreenshot) {
                String screenshotPath = takeScreenshot(); // Метод из TestBase
                System.out.println("Error checking the item. Screenshot:" + screenshotPath);
            }
            return false;
        }
    }

    @FindBy(xpath = "//div[normalize-space(text())='Password must include an uppercase letter, a number, and a special character (# ? ! @ $ % ^ & * -)']")
    private WebElement incorrectPasswordMessageElement;

    // Метод для ожидания сообщения о том что пароль должен содержать определенный набор символов  "Password must include an uppercase letter, a number, and a special character (# ? ! @ $ % ^ & * -)"
    public boolean isPasswordMessageVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(incorrectPasswordMessageElement)); //ожидания видимости элемента
            return incorrectPasswordMessageElement.isDisplayed();
        } catch (Exception e) {
            if (driver instanceof TakesScreenshot) {
                String screenshotPath = takeScreenshot(); // Метод из TestBase
                System.out.println("Error checking the item. Screenshot:" + screenshotPath);
            }
            return false;
        }
    }

    @FindBy(xpath = "//div[normalize-space(text())='Password must be at least 8 characters']")
    private WebElement incorrectLittlePasswordMessageElement;

    // Метод для ожидания сообщения о пароле менее 8 символов "Password must be at least 8 characters"
    public boolean isLittlePasswordMessageVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(incorrectLittlePasswordMessageElement)); //ожидания видимости элемента
            return incorrectLittlePasswordMessageElement.isDisplayed();
        } catch (Exception e) {
            if (driver instanceof TakesScreenshot) {
                String screenshotPath = takeScreenshot(); // Метод из TestBase
                System.out.println("Error checking the item. Screenshot:" + screenshotPath);
            }
            return false;
        }
    }

    public boolean isCreateButtonEnabled() {
        return createButton.isEnabled();//Включен
    }

}
