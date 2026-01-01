package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.AssignmentsPage;
import utils.BaseDriver;

public class AssignmentsDiscussionSteps {

    WebDriver driver;
    AssignmentsPage assignmentsPage;

    @When("User opens a homework")
    public void userOpensAHomework() {
        driver = BaseDriver.getDriver();
        assignmentsPage = new AssignmentsPage(driver);

        assignmentsPage.openRandomTask();
    }

    @Then("Discussion button should be visible")
    public void discussionButtonShouldBeVisible() {
        Assert.assertTrue(
                assignmentsPage.isDiscussionButtonDisplayed(),
                "Discussion button is not displayed on homework page"
        );
    }
}