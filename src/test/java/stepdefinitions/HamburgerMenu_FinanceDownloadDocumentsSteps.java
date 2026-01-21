package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.FinancePage;
import utils.BaseDriver;

public class HamburgerMenu_FinanceDownloadDocumentsSteps {

    WebDriver driver;
    FinancePage financePage;

    public HamburgerMenu_FinanceDownloadDocumentsSteps() {
        driver = BaseDriver.getDriver();
        financePage = new FinancePage(driver);
    }

    @And("User opens report options menu")
    public void userOpensReportOptionsMenu() {
        financePage.openReportOptionsMenu();
    }

    @And("User downloads report as {string}")
    public void userDownloadsReportAs(String type) {
        financePage.downloadReport(type);
    }

    @Then("Finance report should be downloaded as {string}")
    public void financeReportShouldBeDownloadedAs(String type) {
        Assert.assertTrue(
                financePage.isReportDownloaded(type),
                "Finance report was not downloaded as " + type
        );
    }
}
