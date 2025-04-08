package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;

import java.time.Duration;

public class ApplicationManager {

    public WebDriver driver;
    public WebDriverWait wait;
    public BasePage basePage;
    public HomePage homePage;
    public LoginPage loginPage;
    public RegistrationPage registrationPage;


    public void init() {

        String browser = System.getProperty("browser", "chrome");
        // Проверяет значение переменной browser и в зависимости от результата инициализирует нужный драйвер
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default: // Это резервный сценарий на случай, если значение browser не совпадает ни с одним из указанных случаев
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }
        driver.get("https://car-rental-cymg8.ondigitalocean.app/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // неявное
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5)); // ожидание загрузки страницы
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        basePage = new BasePage(driver, wait);
        homePage = new HomePage(driver, wait);
        loginPage = new LoginPage(driver, wait);
        registrationPage = new RegistrationPage(driver, wait);
    }

    public BasePage getBasePage() {
        return basePage;
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public AccountPage getAccountPage() {
        return new AccountPage(driver, wait); // чтобы каждый раз был новый DOM
    }

    public RegistrationPage getRegistrationPage() {
        return new RegistrationPage(driver, wait); // чтобы каждый раз был новый DOM
    }

    public void stop() {
        driver.quit();
    }
}
