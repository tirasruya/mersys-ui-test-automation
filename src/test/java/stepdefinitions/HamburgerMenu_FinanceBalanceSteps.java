package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.FinancePage;
import pages.HomePage;
import pages.PaymentPage;
import utils.BaseDriver;

public class HamburgerMenu_FinanceBalanceSteps {

    private final WebDriver driver = BaseDriver.getDriver();
    private final FinancePage financePage = new FinancePage(driver);
    private final PaymentPage paymentPage = new PaymentPage(driver);


    @And("User clicks on student row on Finance page")
    public void userClicksOnStudentRowOnFinancePage() {
        financePage.clickStudentRowByName("Taner Ozcelik");
    }

    @Then("Student Fee page should be displayed")
    public void studentFeePageShouldBeDisplayed() {
        financePage.verifyStudentFeePageOpened();
    }

    @And("User clicks on Fee Balance Detail tab")
    public void userClicksOnFeeBalanceDetailTab() {
        paymentPage.clickFeeBalanceDetailTab();
    }

    @Then("Installment table should be displayed")
    public void installmentTableShouldBeDisplayed() {
        paymentPage.verifyInstallmentTableDisplayed();
    }
}
