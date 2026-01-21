package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.FinancePage;
import pages.HomePage;
import utils.BaseDriver;

public class HamburgerMenu_FinanceNavigationSteps {

    WebDriver driver;
    HomePage homePage;
    FinancePage financePage;

    @When("User opens Hamburger menu")
    public void userOpensHamburgerMenu() {
        driver = BaseDriver.getDriver();
        homePage = new HomePage(driver);
        financePage = new FinancePage(driver);

        homePage.openHamburgerMenu();
        Assert.assertTrue(homePage.isHamburgerMenuOpened(), "Hamburger menu is not opened!");
    }

    @And("User clicks on Finance menu item")
    public void userClicksOnFinanceMenuItem() {
        homePage.clickFinanceFromHamburger();
    }
    @And("User clicks on My Finance menu item")
    public void userClicksOnMyFinanceMenuItem() {
        homePage.clickMyFinanceFromHamburger();
    }

    @Then("Finance page should be displayed")
    public void financePageShouldBeDisplayed() {
        financePage.verifyFinancePageOpened();
    }
}
