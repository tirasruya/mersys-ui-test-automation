package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HomePage;
import utils.BaseDriver;

public class HomeLogoNavigationSteps {

    WebDriver driver;
    HomePage homePage;

    @When("User sees the company logo on the top left corner")
    public void userSeesCompanyLogo() {
        driver = BaseDriver.getDriver();
        homePage = new HomePage(driver);

        Assert.assertTrue(homePage.isLogoDisplayed(), "Company logo is not visible");
    }

    @And("User clicks on the company logo")
    public void userClicksOnCompanyLogo() {
        homePage.clickLogo();
    }

    @Then("User should be redirected to techno.study")
    public void userShouldBeRedirectedTo() {
        homePage.verifyRedirectedToTechnoStudy();
        Assert.assertEquals(driver.getCurrentUrl(),"https://technostudy.com.tr/","User was not redirected to the correct URL");
    }
}