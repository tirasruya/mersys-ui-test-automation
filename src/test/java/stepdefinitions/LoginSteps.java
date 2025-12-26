package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseDriver;

import java.util.Objects;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @Given("User is on the login page")
    public void userIsOnLoginPage() {
        driver = BaseDriver.getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("mersys"),"User is not on login page");
    }

    @When("User enter valid student credentials")
    public void userLogsInWithValidStudentCredentials() {

        String username = System.getenv("TEST_USERNAME");
        String password = System.getenv("TEST_PASSWORD");

        Assert.assertNotNull(username, "Student username is not set");
        Assert.assertNotNull(password, "Student password is not set");

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("User enters username {string} and password {string}")
    public void userEntersCredentials(String username, String password) {
        if (username != null && !username.isBlank()) {
            loginPage.enterUsername(username);
        }
        if (password != null && !password.isBlank()) {
            loginPage.enterPassword(password);
        }
    }

    @And("User clicks on login button")
    public void userClicksLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("User should be redirected to the homepage")
    public void userShouldBeRedirectedToHomepage() {
        homePage.verifyHomePage("Welcome");
    }

    @Then("User should see error message {string} for {string}")
    public void userShouldSeeErrorMessageFor(String expectedError, String errorType) {
        loginPage.verifyErrorMessage(errorType, expectedError);
    }
}