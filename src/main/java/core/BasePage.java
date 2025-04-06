package core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;

    public static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {
        element.click();
    }

    public String takeScreenshot() {
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("src/test_screenshots/screen-" + System.currentTimeMillis() + ".png");
        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Screenshot saved to: [" + screenshot.getAbsolutePath() + "]");
        return screenshot.getAbsolutePath();
    }
}
