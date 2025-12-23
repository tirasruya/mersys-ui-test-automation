package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.LoginPage;
import utils.BaseDriver;

import java.util.Objects;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;

    @Given("User is on the login page")
    public void userIsOnLoginPage() {
        driver = BaseDriver.getDriver();
        loginPage = new LoginPage(driver);
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("mersys"));
    }

    @When("User enters username {string} and password {string}")
    public void userEntersCredentials(String username, String password) {
        if (username != null && !username.isEmpty()) {
            loginPage.enterUsername(username);
        }
        if (password != null && !password.isEmpty()) {
            loginPage.enterPassword(password);
        }
    }

    @And("User clicks on login button")
    public void userClicksLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("User should be redirected to the homepage")
    public void userShouldBeRedirectedToHomepage() {
//        Assert.assertTrue(
//                loginPage.isHomePageDisplayed(),
//                "Home page was not displayed after successful login"
//        );
    }

    @Then("User should see error message {string} for {string}")
    public void userShouldSeeErrorMessageFor(String expectedError, String errorType) {
        loginPage.verifyErrorMessage(errorType, expectedError);
    }
}