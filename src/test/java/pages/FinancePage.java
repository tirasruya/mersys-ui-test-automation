package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.base.BasePage;
import utils.BaseDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class FinancePage extends BasePage {

    WebDriver driver = BaseDriver.getDriver();


    public FinancePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[normalize-space()='Students Fees']")
    private WebElement studentsFeesHeader;

    @FindBy(xpath = "//span[contains(@class,'mdc-tab__text-label') and normalize-space()='Fee/Balance Detail']")
    private WebElement feeBalanceDetailTab;

    @FindBy(xpath = "//mat-panel-title[normalize-space()='Fees']")
    private WebElement feesTitle;

    @FindBy(xpath = "//table")
    private WebElement feesTable;

    @FindBy(xpath = "//table/tbody/tr")
    private List<WebElement> feesTableRows;

    @FindBy(xpath = "//button[contains(@class,'mat-mdc-menu-trigger') and @aria-haspopup='menu']")
    private WebElement reportOptionsMenuBtn;

    private final By reportMenuPanel =
            By.cssSelector("div.mat-mdc-menu-panel");

    private final By excelOptionBtn =
            By.xpath("//div[contains(@class,'mat-mdc-menu-panel')]//span[normalize-space()='Excel Export']/ancestor::button[1]");

    private final By pdfOptionBtn =
            By.xpath("//div[contains(@class,'mat-mdc-menu-panel')]//span[normalize-space()='Pdf Export']/ancestor::button[1]");


    public void verifyStudentFeePageOpened() {
        wait.until(d -> studentsFeesHeader.isDisplayed());
        Assert.assertTrue(studentsFeesHeader.isDisplayed(), "Student Fee page is not opened");
    }


    public void clickStudentRowByName(String name) {
        By studentName = By.xpath("//td[contains(@class,'mat-column-student')]//span[normalize-space()='" + name + "']");
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(studentName));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        wait.until(ExpectedConditions.elementToBeClickable(el)).click();
    }


    public void verifyFinancePageOpened() {
        LOGGER.info("Verifying Student Finance page is opened");
        wait.until(ExpectedConditions.visibilityOf(studentsFeesHeader));
        Assert.assertTrue(studentsFeesHeader.isDisplayed(),
                "Student Finance page is not opened");
    }


    public boolean isFinancePageOpened() {
        try {
            return isDisplayed(studentsFeesHeader);
        } catch (Exception e) {
            return false;
        }
    }

    public void openReportOptionsMenu() {
        wait.until(ExpectedConditions.visibilityOf(reportOptionsMenuBtn));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", reportOptionsMenuBtn);

        wait.until(ExpectedConditions.elementToBeClickable(reportOptionsMenuBtn));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", reportOptionsMenuBtn);
    }
    public void downloadReport(String type) {
        String t = type.trim().toLowerCase(Locale.ROOT);

        if (driver.findElements(reportMenuPanel).isEmpty()) {
            openReportOptionsMenu();
            wait.until(ExpectedConditions.presenceOfElementLocated(reportMenuPanel));
        }

        By target = t.equals("pdf") ? pdfOptionBtn : excelOptionBtn;

        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(target));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }


    public boolean isReportDownloaded(String type) {
        String t = type.trim().toLowerCase(Locale.ROOT);
        String expectedExt = t.equals("pdf") ? ".pdf" : ".xlsx";

        Path downloadsDir = Paths.get(System.getProperty("user.home"), "Downloads");

        long timeoutMs = 15000;
        long start = System.currentTimeMillis();

        while (System.currentTimeMillis() - start < timeoutMs) {
            Optional<Path> newestFile = getNewestFile(downloadsDir);
            if (newestFile.isPresent()
                    && newestFile.get().toString().toLowerCase().endsWith(expectedExt)) {
                return true;
            }
            sleep(500);
        }
        return false;
    }

    private Optional<Path> getNewestFile(Path dir) {
        try {
            if (!Files.exists(dir)) return Optional.empty();

            return Files.list(dir)
                    .filter(Files::isRegularFile)
                    .max(Comparator.comparingLong(p -> p.toFile().lastModified()));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }


}
