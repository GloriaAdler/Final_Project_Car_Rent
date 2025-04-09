package carrent.rent_page;

import carrent.core.BasePage;
import org.junit.jupiter.api.Test;
import carrent.core.TestBase;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTest extends TestBase{

    @Test
    public void openSiteTest() {
        String actualUrl = app.driver.getCurrentUrl();
        BasePage.logger.info("Checking that the site has opened. URL: {}", actualUrl);
        shouldRunTearDown = false;  // Выключаем выполнение tearDown - оставляем браузер открытым

        assertTrue(actualUrl.contains("car-rental-cymg8.ondigitalocean.app"),
                "The wrong URL has been opened or the page has not loaded.");
    }
}
