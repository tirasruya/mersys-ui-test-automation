package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utils.BaseDriver;

public class HomeTopNavigationSteps {

    WebDriver driver;
    HomePage homePage;

    @Then("User should see {string} menu item on top navigation")
    public void userShouldSeeMenuItemOnTopNavigation(String menu) {
        driver = BaseDriver.getDriver();
        homePage = new HomePage(driver);

        homePage.verifyMenuVisibleAndClickable(menu);
    }

    @When("User clicks on {string} menu item")
    public void userClicksOnMenuItem(String menu) {
        homePage.clickMenuItem(menu);
    }

    @Then("{string} should perform correct action")
    public void menuShouldPerformCorrectAction(String menu) {
        homePage.verifyMenuAction(menu);
    }
}