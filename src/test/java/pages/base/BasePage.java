package pages.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected final Logger LOGGER = LogManager.getLogger(this.getClass());
    WebDriver driver;
    public WebDriverWait wait;

    public BasePage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void clickElement(final WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        try {
            element.click();
            LOGGER.debug("Clicked element: {}", element);
        } catch (Exception e1) {
            try {
                new Actions(driver)
                        .moveToElement(element)
                        .click()
                        .perform();
                LOGGER.debug("Element clicked with Actions: {}",element);
            } catch (Exception e2){
                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                    LOGGER.debug("Element clicked with Javascript: {}", element);
                } catch (Exception e3) {
                    throw new RuntimeException("None of the click methods worked.");
                }
            }
        }
    }

    public void sendKeysToElement(final WebElement element, final String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        try {
            element.clear();
            element.sendKeys(text);
            LOGGER.debug("Text '{}' sent to element: {}", text, element);
        } catch (Exception e1) {
            try {
                new Actions(driver)
                        .moveToElement(element)
                        .click()
                        .sendKeys(text)
                        .build()
                        .perform();
                LOGGER.debug("Text '{}' sent to element '{}' with Actions", text, element);
            } catch (Exception e2) {
                try {
                    ((JavascriptExecutor) driver)
                            .executeScript("arguments[0].value = arguments[1];", element, text);
                    LOGGER.debug("Text '{}' sent to element '{}' with Javascript", text, element);
                } catch (Exception e3) {
                    LOGGER.error("Failed to click element using all methods", e3);
                    throw new RuntimeException("None of the click methods worked.", e3);
                }
            }
        }
    }

    public boolean isDisplayed(final WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }

    public void switchToNewTab() {
        LOGGER.info("Waiting for new tab to open");

        String currentWindow = driver.getWindowHandle();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(currentWindow)) {
                driver.switchTo().window(window);
                LOGGER.info("Switched to new tab");
                break;
            }
        }
    }
}