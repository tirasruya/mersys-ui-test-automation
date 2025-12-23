package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BaseDriver {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<String> browserThread = new ThreadLocal<>();

    private BaseDriver() {}

    public static void setBrowser(String browser) {
        browserThread.set(browser);
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(createDriver(browserThread.get()));
        }
        return driver.get();
    }

    public static String getBrowser() {
        return browserThread.get();
    }

    private static WebDriver createDriver(String browser) {

        boolean headless =
                Boolean.parseBoolean(ConfigReader.getProperty("headless"));

        if (browser == null || browser.isEmpty()) {
            browser = ConfigReader.getProperty("browser");
        }

        switch (browser.toLowerCase()) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");

                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }
                return new ChromeDriver(chromeOptions);

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (headless) {
                    firefoxOptions.addArguments("--headless");
                    firefoxOptions.addArguments("--width=1920");
                    firefoxOptions.addArguments("--height=1080");
                }

                WebDriver firefoxDriver = new FirefoxDriver(firefoxOptions);
                firefoxDriver.manage().window().maximize();
                return firefoxDriver;

            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
        browserThread.remove();
    }
}