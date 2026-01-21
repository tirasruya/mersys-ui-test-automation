package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.CalendarPage;
import pages.HomePage;
import utils.BaseDriver;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.CalendarPage;
import pages.HomePage;

public class CalendarFeature_NavigationSteps {
    WebDriver driver;
    HomePage homePage;
    CalendarPage calendarPage;
    String oldDateRange;
    @When("User navigates to Calendar page from Top Nav")
    public void userNavigatesToCalendarPageFromTopNav() {
        driver = BaseDriver.getDriver();
        homePage = new HomePage(driver);
        calendarPage = new CalendarPage(driver);
        homePage.clickCalendarFromTopNav();
    }
    @Then("Calendar page should be displayed")
    public void calendarPageShouldBeDisplayed() {
        calendarPage.verifyCalendarPageOpened();
    }
    @And("Weekly Course Plan for today should be visible")
    public void weeklyCoursePlanForTodayShouldBeVisible() {
        calendarPage.openWeeklyCoursePlanTabIfNeeded();
        calendarPage.verifyWeeklyCoursePlanVisible();
        calendarPage.verifyTodayVisibleOnWeeklyPlan();
    }
    @And("Course status icons should be visible on the plan")
    public void courseStatusIconsShouldBeVisibleOnThePlan() {
        calendarPage.verifyStatusIconsVisible();
    }
    @And("Course status legend should be visible with meanings")
    public void courseStatusLegendShouldBeVisibleWithMeanings() {
        calendarPage.verifyStatusLegendVisibleWithMeanings();
    }
    @And("User switches to {string} view on Calendar page")
    public void userSwitchesToViewOnCalendarPage(String view) {
        calendarPage.switchToView(view);
    }
    @Then("Calendar monthly view should be displayed")
    public void calendarMonthlyViewShouldBeDisplayed() {
        calendarPage.verifyMonthlyCalendarVisible();
    }
    @And("User clicks {string} navigation button on Weekly Course Plan")
    public void userClicksNavigationButtonOnWeeklyCoursePlan(String button) {
        calendarPage.openWeeklyCoursePlanTabIfNeeded();
        oldDateRange = calendarPage.getWeeklyDateRangeText();
        calendarPage.clickWeeklyNavigationButton(button);
    }
    @Then("Weekly Course Plan date range should change")
    public void weeklyCoursePlanDateRangeShouldChange() {
        String newRange = calendarPage.getWeeklyDateRangeText();
        Assert.assertNotEquals(newRange, oldDateRange, "Weekly date range did not change!");
    }
    @And("User opens a random course from Weekly Course Plan")
    public void userOpensARandomCourseFromWeeklyCoursePlan() {
        calendarPage.openWeeklyCoursePlanTabIfNeeded();
        calendarPage.openRandomCourseFromWeeklyPlan();
    }
    @Then("Course details popup should be displayed")
    public void courseDetailsPopupShouldBeDisplayed() {
        calendarPage.verifyCourseDetailsPopupDisplayed();
    }
}
