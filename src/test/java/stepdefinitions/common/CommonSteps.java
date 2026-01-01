package stepdefinitions.common;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseDriver;

public class CommonSteps {

    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @Given("User is logged in as a student")
    public void userIsLoggedInAsStudent() {

        driver = BaseDriver.getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        loginPage.loginAsStudent();
        homePage.verifyHomePage("Welcome");
    }

    @And("User is on the Assignments page")
    public void userNavigatesToAssignmentsPage() {
        homePage.clickMenuItem("Assignments");
        homePage.verifyMenuAction("Assignments");
    }
}