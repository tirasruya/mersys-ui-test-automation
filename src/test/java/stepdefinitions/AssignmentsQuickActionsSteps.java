package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.AssignmentsPage;
import utils.BaseDriver;

public class AssignmentsQuickActionsSteps {
    WebDriver driver;
    AssignmentsPage assignmentsPage;

    @When("User selects a random assignment from the list")
    public void userSelectsRandomTask() {
        driver = BaseDriver.getDriver();
        assignmentsPage = new AssignmentsPage(driver);

    }

    @Then("Assignment action icons should be displayed correctly")
    public void assignmentActionIconsShouldBeDisplayedCorrectly() {
        assignmentsPage.verifyAssignmentActionIcons();
    }

    @When("User clicks on a random assignment row")
    public void userClicksAssignmentRow() {
        driver = BaseDriver.getDriver();
        assignmentsPage = new AssignmentsPage(driver);

        assignmentsPage.openRandomTask();
    }

    @Then("Assignment details page should be displayed")
    public void assignmentDetailsPageShouldBeDisplayed() {
        Assert.assertTrue(
                assignmentsPage.isAssignmentDetailsPageOpened(),
                "Assignment details page was not opened"
        );
    }
}
