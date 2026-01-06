package stepdefinitions;

import io.cucumber.java.en.*;
import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.*;
import utils.BaseDriver;
import java.io.File;
import java.time.Duration;

public class Attendance_FeatureSteps {

    private final WebDriver driver = BaseDriver.getDriver();
    private final AttendancePage ap = new AttendancePage(driver);
    private final HamburgerMenuPage hp = new HamburgerMenuPage(driver);
    private static final Logger LOGGER = LogManager.getLogger(Attendance_FeatureSteps.class);

    @When("the user navigates to the {string} section from the side menu")
    public void navigatesToAttendance(String menu) {
        ap.clickElement(ap.attendanceMenuItem);
    }

    @Then("the {string} menu should expand successfully")
    public void menuExpands(String menu) {
        ap.isDisplayed(ap.attendanceExcusesBtn);
    }

    @When("the user clicks on the {string} button to access the attendance page")
    public void clicksExcuses(String btn) {
        ap.clickElement(ap.attendanceExcusesBtn);
    }

    @And("the user clicks {string} icon to create a new excuse")
    public void clicksPlus(String icon) {
        ap.clickElement(ap.plusIcon);
    }

    @And("the user selects excuse type")
    public void selectsType() {
        ap.clickElement(ap.excuseTypeDropdown);
        ap.clickElement(ap.fullDayOption);
    }

    @And("the user selects date on the calendar")
    public void selectsDate() {
        ap.clickElement(ap.calendarInput);
        try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        ap.clickElement(ap.todayDate);
    }

    @And("the user enters {string} as excuse description")
    public void entersDesc(String desc) {
        ap.sendKeysToElement(ap.descriptionField, desc);
    }

    @And("the user attaches a file from path {string} if provided")
    public void attachesFile(String filePath) {
        if (filePath != null && !filePath.isEmpty()) {
            try {
                WebElement hiddenFileInput = driver.findElement(By.cssSelector("input[type='file']"));
                hiddenFileInput.sendKeys(new File(filePath).getAbsolutePath());
                LOGGER.info("Selenium sendKeys ile dosya yüklendi (Pencere açılmadı).");
            } catch (Exception e) {
                LOGGER.warn("Selenium yüklemesi başarısız, Robot deneniyor...");
                ap.clickElement(ap.attachmentBtn);
                ap.uploadFileWithRobot(new File(filePath).getAbsolutePath());
            }
        }
    }


    @And("the user clicks the {string} button")
    public void clicksSend(String btn) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(ap.sendBtn));
            ap.clickElement(ap.sendBtn);
            LOGGER.info("Gönder butonuna tıklandı.");
        } catch (Exception e) {
            LOGGER.info("Normal tıklama başarısız, JavaScript ile zorlanıyor...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ap.sendBtn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ap.sendBtn);
        }
    }

    @Then("the {string} message should be displayed")
    public void verifyMessage(String msg) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Süreyi 15 sn'ye çıkardım

        try {
            WebElement successElem = wait.until(ExpectedConditions.visibilityOf(hp.successMessage));
            String actualText = successElem.getText();
            LOGGER.info("Ekranda görünen mesaj: " + actualText);

            Assert.assertTrue(actualText.toLowerCase().contains("success") ||
                            actualText.toLowerCase().contains("successfully") ||
                            actualText.toLowerCase().contains("sent"),
                    "Başarı mesajı beklenen içeriğe sahip değil! Gelen: " + actualText);

        } catch (TimeoutException e) {
            LOGGER.error("Başarı mesajı belirlenen sürede ekranda belirmedi!");
            Assert.fail("Test başarısız: 'Success' mesajı görünmedi.");
        }
    }
}