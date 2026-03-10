package hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import runners.ReportGenerator;
import utils.RequestResponseLogger;

import java.io.File;

/**
 * Cucumber Hooks for Pre and Post conditions
 */
public class Hooks {
    
    private static final Logger logger = LogManager.getLogger(Hooks.class);
    
    @BeforeAll
    public static void beforeAll() {
        // Reset the logger to ensure fresh log file for this test run
        RequestResponseLogger.resetLogger();
        logger.info("Test suite starting - API logger reset");
    }
    
    @Before
    public void beforeScenario(Scenario scenario) {
        logger.info("========================================");
        logger.info("Starting Scenario: " + scenario.getName());
        logger.info("========================================");
    }
    
    @After
    public void afterScenario(Scenario scenario) {
        logger.info("========================================");
        logger.info("Scenario Status: " + scenario.getStatus());
        logger.info("Completed Scenario: " + scenario.getName());
        logger.info("========================================");
    }
    
    @AfterAll
    public static void afterAll() {
        try {
            // Close the API logger
            RequestResponseLogger.closeLogger();
            logger.info("API execution log saved to: target/api-logs/api-execution.txt");
            
            // Wait for cucumber.json to be fully written
            Thread.sleep(2000);
            
            File jsonReport = new File("target/cucumber.json");
            
            // Wait up to 5 seconds for the file to exist and have content
            int attempts = 0;
            while (attempts < 10 && (!jsonReport.exists() || jsonReport.length() == 0)) {
                Thread.sleep(500);
                attempts++;
            }
            
            if (jsonReport.exists() && jsonReport.length() > 0) {
                logger.info("Generating Cucumber HTML report...");
                logger.info("JSON report size: " + jsonReport.length() + " bytes");
                ReportGenerator.generateReport();
                logger.info("Cucumber HTML report generated successfully!");
                logger.info("Report location: target/cucumber-reports/cucumber-html-reports/overview-features.html");
            } else {
                logger.warn("Cucumber JSON report not found or empty after waiting. Skipping HTML report generation.");
            }
        } catch (Exception e) {
            logger.error("Failed to generate report: " + e.getMessage(), e);
        }
    }
}
