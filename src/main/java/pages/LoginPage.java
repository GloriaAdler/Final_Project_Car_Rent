package pages;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this); // инициализация всех @FindBy
    }

    @FindBy(css = "input[name='email']")
    WebElement userEmail;

    @FindBy(css = "input[name='password']")
    WebElement userPassword;

    @FindBy(css = "button[type='submit']")
    WebElement loginButton;

    // Вводим email в поле
    public void enterEmail(String email) {
        userEmail.sendKeys(email);
    }

    // Вводим пароль в поле
    public void enterPassword(String password) {
        userPassword.sendKeys(password);
    }

    // Кликаем на кнопку логин
    public void clickLoginButton() {
        loginButton.click();
    }
}
