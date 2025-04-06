package tests.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import core.ApplicationManager;
import pages.AccountPage;

public class TestBase {

    public final ApplicationManager app = new ApplicationManager();

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        logger.info("Starting test: {}", testInfo.getDisplayName());
        app.init();
    }

    public boolean shouldRunTearDown  = true; // Флаг для контроля выполнения закрытия браузера после прохождения тестов

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        if (shouldRunTearDown) {
            logger.info("Finished test: {}", testInfo.getDisplayName());
            app.stop();
        }else {
            logger.info("Skipped test: {}", testInfo.getDisplayName());
        }
    }
}
