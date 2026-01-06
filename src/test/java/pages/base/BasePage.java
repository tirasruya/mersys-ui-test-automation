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



import java.awt.*;

import java.awt.datatransfer.StringSelection;

import java.time.Duration;

import java.awt.AWTException;

import java.awt.Robot;

import java.awt.Toolkit;

import java.awt.event.KeyEvent;

import java.io.File;





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

    public void verifyContainsText(WebElement element, String value) {
        wait.until(ExpectedConditions.visibilityOf(element));
        String actualText = element.getText();
        if (actualText.contains(value)) {
            LOGGER.info("Text verification passed. Expected to contain: {}, Actual: {}", value, actualText);
        } else {
            LOGGER.error("Text verification failed! Expected: {}, Actual: {}", value, actualText);
            throw new RuntimeException("Text verification failed. Actual text: " + actualText);
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



    public void wait(int second) {

        try {

            Thread.sleep(second * 1000L);

        } catch (InterruptedException e) {

            LOGGER.error("Wait interrupted: ", e);

            Thread.currentThread().interrupt();

        }

    }

    public void uploadFileWithRobot(String filePath) {
        try {
            LOGGER.info("Robot Class: Mac dosya yükleme penceresi yönetiliyor...");
            Robot robot = new Robot();
            robot.setAutoDelay(500);

            // 1. "Go to Folder" penceresini aç (CMD + SHIFT + G)
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_G);
            robot.keyRelease(KeyEvent.VK_G);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_META);
            robot.delay(1500); // Pencerenin açılması için yeterli süre

            // 2. Dosya yolunu panoya kopyala ve yapıştır
            StringSelection selection = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_META);
            robot.delay(1000); // Yapıştırma sonrası bekleme

            // 3. ENTER: "Go to Folder" kutusunu onayla
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(1500); // Klasöre gitmesi için bekleme

            // 4. ENTER: Seçilen dosyayı onaylayıp pencereyi kapat
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(1000);

            // 5. EKSTRA GÜVENLİK: Eğer pencere hala takılı kaldıysa ESC ile çıkmayı dene
            // Ancak genellikle 4. adımda kapanmış olmalı.
            LOGGER.info("Robot Class: İşlem tamamlandı.");

        } catch (Exception e) {
            LOGGER.error("Robot Class hatası: " + e.getMessage());
        }
    }
}