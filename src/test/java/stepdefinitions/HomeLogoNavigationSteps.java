package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseDriver;

public class HomeLogoNavigationSteps {

    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @Given("User is logged in as a student")
    public void userIsLoggedInAsStudent() {
        driver = BaseDriver.getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        loginPage.enterUsername("Student_10");
        loginPage.enterPassword("S12345");
        loginPage.clickLoginButton();

        homePage.verifyHomePage("Student_10");
    }

    @When("User sees the company logo on the top left corner")
    public void userSeesCompanyLogo() {
        Assert.assertTrue(homePage.isLogoDisplayed(), "Company logo is not visible");
    }

    @And("User clicks on the company logo")
    public void userClicksOnCompanyLogo() {
        homePage.clickLogo();
    }

    @Then("User should be redirected to techno.study")
    public void userShouldBeRedirectedTo() {
        homePage.verifyRedirectedToTechnoStudy();
        Assert.assertEquals(driver.getCurrentUrl(),"https://techno.study/","User was not redirected to the correct URL");
    }
}