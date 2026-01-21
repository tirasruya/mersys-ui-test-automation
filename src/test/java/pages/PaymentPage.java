package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.base.BasePage;
import utils.BaseDriver;

public class PaymentPage extends BasePage {

    @FindBy(xpath = "//*[normalize-space()='Students Fees']")
    private WebElement studentsFeesHeader;

    @FindBy(xpath = "//span[contains(@class,'mdc-tab__text-label') and normalize-space()='Fee/Balance Detail']")
    private WebElement feeBalanceDetailTab;

    @FindBy(xpath = "//table")
    private WebElement installmentTable;

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    WebDriver driver = BaseDriver.getDriver();
    public void verifyStudentFeePageOpened() {
        wait.until(d -> studentsFeesHeader.isDisplayed());
        Assert.assertTrue(studentsFeesHeader.isDisplayed(), "Student Fee page is not opened");
    }

    public void clickFeeBalanceDetailTab() {
        By tab = By.xpath("//*[normalize-space()='Fee/Balance Detail']");
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(tab));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        el.click();
    }

    public void openFeesPanelIfClosed() {
        By feesHeader = By.xpath("//*[normalize-space()='Fees']");
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(feesHeader));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", header);

        header.click();
    }

    public void verifyInstallmentTableDisplayed() {
        openFeesPanelIfClosed();

        By dateHeader = By.xpath("//*[normalize-space()='Date']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateHeader));

        By rows = By.xpath("//table//tbody//tr");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(rows, 0));
    }

}
