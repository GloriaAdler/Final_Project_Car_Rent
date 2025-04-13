package carrent.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    static class TestResultLogger implements AfterTestExecutionCallback {
        private static final Logger logger = LoggerFactory.getLogger(TestResultLogger.class);

        @Override
        public void afterTestExecution(ExtensionContext context) {
            String testName = context.getDisplayName();
            boolean testPassed = context.getExecutionException().isEmpty();

            if (testPassed) {
                logger.info("Test is PASSED: [" + testName + "]");
            } else {
                logger.error("Test is FAILED: [" + testName + "]");
            }
        }
    }
}
