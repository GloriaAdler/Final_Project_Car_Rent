package pages;

import core.BasePage;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage extends BasePage {

    public SearchResultsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h3[normalize-space(text())='Filter Cars']")  // Пример локатора для списка машин
    WebElement carListContainer;

    // Метод для проверки, что список автомобилей отображается
    public boolean isCarListDisplayed() {
        try {
            return carListContainer.isDisplayed();

        } catch (Exception e) {if (driver instanceof TakesScreenshot) {
            String screenshotPath = takeScreenshot(); // Метод из TestBase
            System.out.println("Error checking the item. Screenshot:" + screenshotPath);
        }
            return false;
        }
    }
}
