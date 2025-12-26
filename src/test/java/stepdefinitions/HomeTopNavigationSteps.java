package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseDriver;

public class HomeTopNavigationSteps {

    WebDriver driver;
    HomePage homePage;

    @Then("User should see {string} menu item on top navigation")
    public void userShouldSeeMenuItemOnTopNavigation() {
        driver = BaseDriver.getDriver();
        homePage = new HomePage(driver);
    }

    @When("User clicks on {string} menu item")
    public void userClicksOnMenuItem() {

    }

    @Then("{string} should perform correct action")
    public void menuShouldPerformCorrectAction() {

    }
}
