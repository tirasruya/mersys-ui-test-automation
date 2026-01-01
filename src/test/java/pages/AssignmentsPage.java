package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.base.BasePage;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import static utils.BaseDriver.getDriver;

public class AssignmentsPage extends BasePage {

    @FindBy(xpath = "//ms-layout-menu-button[@page='ASSIGNMENT']")
    private WebElement assignmentsLogo;

    @FindBy(css = ".cdk-overlay-pane")
    private WebElement assignmentsCountOverlay;

    @FindBy(css = "button[aria-label='Discussion']")
    private WebElement discussionButton;

    private WebElement selectedTask;
    private WebElement selectedTaskRow;

    WebDriver driver = getDriver();
    Actions actions = new Actions(driver);

    public AssignmentsPage(WebDriver driver) {
        super(driver);
    }

    public void verifyAssignmentsLogoDisplayed() {
        LOGGER.info("Verifying Assignments logo is displayed");
        Assert.assertTrue(isDisplayed(assignmentsLogo), "Assignments logo is not displayed");
    }

    public void hoverOverAssignmentsLogo() {
        LOGGER.info("Hovering over Assignments logo");
        actions.moveToElement(assignmentsLogo).perform();
    }

    public void verifyAssignmentsCountOnHover() {
        LOGGER.info("Verifying assignments count on hover");

        actions.moveToElement(assignmentsLogo).perform();
        LOGGER.info("Hovered over Assignments link");

        Assert.assertTrue(isDisplayed(assignmentsCountOverlay), "Assignments count overlay is not displayed on hover");

        String countText = assignmentsCountOverlay.getText().trim();
        LOGGER.info("Assignments count text from overlay: [{}]", countText);

        Assert.assertFalse(countText.isEmpty(), "Assignments count text is empty");

        Assert.assertTrue(countText.matches("\\d+"), "Assignments count is not numeric: " + countText);

        LOGGER.info("Assignments count verified successfully: {}", countText);
    }

    public void clickAssignmentsLogo() {
        LOGGER.info("Clicking Assignments logo");
        clickElement(assignmentsLogo);
    }

    public void verifyAssignedTasksDisplayed() {

        By assignedTasks = By.xpath("//*[@id='container-3']//*[contains(text(),'B8')]");
        List<WebElement> tasks = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(assignedTasks));

        Assert.assertFalse(tasks.isEmpty(), "Assigned tasks list is empty");

        LOGGER.info("Total assigned tasks: {}", tasks.size());

        for (WebElement task : tasks) {
            String text = task.getText().trim();
            LOGGER.debug("Task content: {}", text);
            Assert.assertTrue(text.contains("B8"), "Non-B8 task found: " + text);
        }
    }

    public void openRandomTask() {
        By assignedTasks =
                By.xpath("//*[@id='container-3']//*[contains(text(),'B8')]");

        List<WebElement> tasks =
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(assignedTasks));

        Assert.assertFalse(tasks.isEmpty(), "No assigned tasks found");

        int randomIndex = new Random().nextInt(tasks.size());

        WebElement selectedTask = tasks.get(randomIndex);
        String taskName = selectedTask.getText();

        LOGGER.info("Random task selected: index={}, name='{}'",
                randomIndex, taskName);

        selectedTask.click();
    }

    public boolean isActionIconDisplayed(String iconName) {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//ms-icon-button[@icon='" + iconName + "']")
                    )
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void verifyAssignmentActionIcons() {

        Assert.assertTrue(
                isActionIconDisplayed("info"),
                "Info icon is not displayed on assignments list"
        );

        Assert.assertTrue(
                isActionIconDisplayed("star"),
                "Star icon is not displayed on assignments list"
        );

        LOGGER.info("Assignment action icons are displayed");
    }

    public boolean isAssignmentDetailsPageOpened() {
        wait.until(ExpectedConditions.urlContains("my-assignments/info"));
        LOGGER.info("Assignments details page opened");
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("my-assignments/info");
    }

    public boolean isDiscussionButtonDisplayed() {
        try {
            return discussionButton.isDisplayed();
        } catch (Exception e) {
            LOGGER.error("Discussion button is NOT displayed");
            return false;
        }
    }
}