package carrent.pages;

import carrent.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this); // инициализация всех @FindBy
    }

    @FindBy(xpath = "(//a[contains(@class,'text-white font-bold')])[2]")
    WebElement login;

    public void selectLogin() {
        click(login);
    }

    @FindBy(css = "input[name='startDateTime']")
    WebElement startDateTimeInput;

    @FindBy(css = "input[name='endDateTime']")
    WebElement endDateTimeInput;

    @FindBy(css = "button[type='submit']")
    WebElement searchButton;

    public void enterRentalDates(String startDate, String endDate) {
        startDateTimeInput.clear();
        startDateTimeInput.sendKeys(startDate);
        endDateTimeInput.clear();
        endDateTimeInput.sendKeys(endDate);
    }

    public SearchResultsPage submitSearch() {
        searchButton.click();
        return new SearchResultsPage(driver, wait);
    }
}
