package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utils.BaseDriver;

public class HomeTopNavigationSteps {

    WebDriver driver;
    HomePage homePage;

    public HomeTopNavigationSteps() {
        driver = BaseDriver.getDriver();
        homePage = new HomePage(driver);
    }

    @Then("User should see {string} menu item on top navigation")
    public void userShouldSeeMenuItemOnTopNavigation(String menu) {
        homePage.verifyTopMenuItemIsVisible(menu);
    }

    @When("User clicks on {string} menu item")
    public void userClicksOnMenuItem(String menu) {
        homePage.clickTopMenuItem(menu);
    }

    @Then("{string} should perform correct action")
    public void menuShouldPerformCorrectAction(String menu) {
        homePage.verifyTopMenuAction(menu);
    }
}
