package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.BaseDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Hooks {

    private static final Logger LOGGER =
            LogManager.getLogger(Hooks.class);

    @Before
    public void setUp() {

        String browser = System.getProperty("browser");
        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }

        BaseDriver.setBrowser(browser);

        BaseDriver.getDriver()
                .get(ConfigReader.getProperty("baseUrl"));

        Allure.label("browser", browser);
        Allure.label("thread", Thread.currentThread().getName());
        Allure.label("suite", System.getProperty("testng.suite.name", "UI Tests"));

        try {
            Properties props = new Properties();
            props.setProperty("Browser", ConfigReader.getProperty("browser"));
            props.setProperty("Base URL", ConfigReader.getProperty("baseUrl"));
            props.setProperty("Headless", ConfigReader.getProperty("headless"));
            props.setProperty("Environment", ConfigReader.getProperty("env"));
            props.setProperty("OS", System.getProperty("os.name"));

            File file = new File(System.getProperty("user.dir") + "/target/allure-results/environment.properties");
            file.getParentFile().mkdirs();
            try (FileOutputStream fos = new FileOutputStream(file)) {
                props.store(fos, "Allure Environment Properties");
            }
            LOGGER.info("Allure environment.properties has been created.");
        } catch (Exception e) {
            LOGGER.error("Failed to create environment.properties.", e);
        }
    }

    @After
    public void tearDown(Scenario scenario) {

        WebDriver driver = BaseDriver.getDriver();

        if (driver == null) {
            LOGGER.warn("Driver is null, skipping teardown.");
            return;
        }

        try {
            if (scenario.isFailed()) {

                String timestamp =
                        new SimpleDateFormat("yyyyMMdd_HHmmss")
                                .format(new Date());

                String scenarioName =
                        scenario.getName().replaceAll(" ", "_");

                String folderPath =
                        System.getProperty("user.dir")
                                + "/target/screenshots/";

                File folder = new File(folderPath);
                if (!folder.exists()) folder.mkdirs();

                byte[] screenshot =
                        ((TakesScreenshot) driver)
                                .getScreenshotAs(OutputType.BYTES);

                File file = new File(
                        folderPath + scenarioName + "_" + timestamp + ".png"
                );

                try (FileOutputStream fos =
                             new FileOutputStream(file)) {
                    fos.write(screenshot);
                }

                scenario.attach(
                        screenshot,
                        "image/png",
                        "Failure Screenshot"
                );
            }

        } catch (Exception e) {
            LOGGER.error("Screenshot failed", e);
        } finally {
            BaseDriver.quitDriver();
        }
    }
}