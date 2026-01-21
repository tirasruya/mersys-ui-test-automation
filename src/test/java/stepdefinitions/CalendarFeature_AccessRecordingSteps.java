package stepdefinitions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.CalendarPage;
import pages.HomePage;
import utils.BaseDriver;

public class CalendarFeature_AccessRecordingSteps {

    CalendarPage calendarPage = new CalendarPage(BaseDriver.getDriver());

    @And("Recording button should be visible on popup")
    public void recordingButtonShouldBeVisibleOnPopup() {
        calendarPage.verifyRecordingButtonVisible();
    }

    @When("User clicks Recording button")
    public void userClicksRecordingButton() {
        calendarPage.clickRecordingButton();
    }

    @Then("Recording page should be opened")
    public void recordingPageShouldBeOpened() {
        calendarPage.verifyRecordingPageOpened();
    }

    @And("Play icon should be visible on video")
    public void playIconShouldBeVisibleOnVideo() {
        calendarPage.verifyPlayIconVisible();
    }

    @When("User clicks Play icon")
    public void userClicksPlayIcon() {
        calendarPage.clickPlayIcon();
    }

    @Then("Video should start playing")
    public void videoShouldStartPlaying() {
        calendarPage.verifyVideoIsPlaying();
    }

}
