package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CalendarPage;
import pages.HomePage;
import utils.BaseDriver;

public class CalendarFeature_CourseDetailsSteps {
    CalendarPage calendarPage = new CalendarPage(BaseDriver.getDriver());

    @When("User opens a course by status {string}")
    public void userOpensACourseByStatus(String status) {
        calendarPage.openWeeklyCoursePlanTabIfNeeded();
        calendarPage.openCourseByStatus(status); // P / S / E / PENDING
    }

    @And("Course name should be visible on course details popup")
    public void courseNameShouldBeVisibleOnCourseDetailsPopup() {
        Assert.assertTrue(
                calendarPage.isCourseNameVisibleOnPopup(),
                "Course name is not visible or empty on popup!");
    }

    @And("Meeting not started message should be visible on popup")
    public void meetingNotStartedMessageShouldBeVisibleOnPopup() {
        calendarPage.verifyMeetingNotStartedMessageVisible();
    }

    @And("Teacher or Instructor info should be visible on popup")
    public void teacherOrInstructorInfoShouldBeVisibleOnPopup() {
        calendarPage.verifyTeacherInstructorAndDateTimeInfoVisible();
    }

    @And("Date and Time info should be visible on popup")
    public void dateAndTimeInfoShouldBeVisibleOnPopup() {
        calendarPage.verifyTeacherInstructorAndDateTimeInfoVisible();
    }

    @And("Popup tabs should work properly")
    public void popupTabsShouldWorkProperly() {
        calendarPage.verifyPopupTabsWork();
    }
}