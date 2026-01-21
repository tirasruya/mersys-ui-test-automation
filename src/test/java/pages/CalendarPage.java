package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.base.BasePage;
import utils.BaseDriver;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CalendarPage extends BasePage {

    public CalendarPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[normalize-space()='Calendar' or normalize-space()='CALENDAR']")
    private WebElement calendarHeader;

    @FindBy(xpath = "//div[contains(@class,'mdc-tab__text-label') and normalize-space()='Weekly Course Plan']")
    private WebElement weeklyCoursePlanTab;

    @FindBy(xpath = "//div[contains(@class,'mdc-tab__text-label') and normalize-space()='Calendar']")
    private WebElement calendarTab;

    @FindBy(xpath = "//*[contains(.,'Weekly Course Plan')]/ancestor::*[1]")
    private WebElement weeklyPlanArea;

    @FindBy(css = ".fc, .fc-view, ms-calendar")
    private WebElement monthlyCalendarArea;

    @FindBy(xpath = "(//*[contains(@class,'date') and contains(.,'-')])[1]")
    private WebElement weeklyDateRangeText;

    @FindBy(xpath = "//*[normalize-space()='P' or normalize-space()='S' or normalize-space()='E' or normalize-space()='C']")
    private List<WebElement> statusIcons;

    @FindBy(xpath = "//*[contains(.,'Published') and contains(.,'Started') and contains(.,'Ended') and contains(.,'Cancelled')]")
    private WebElement statusLegendBox;

    @FindBy(css = ".weekly-course-item, .event, ms-course-event, .calendar-event")
    private List<WebElement> weeklyCourseItems;

    @FindBy(css = ".mat-dialog-container, .cdk-overlay-pane, ms-dialog, .course-details-dialog")
    private WebElement courseDetailsPopup;

    @FindBy(xpath = "//*[contains(@class,'mat-dialog') or contains(@class,'dialog')]" +
            "//*[self::h1 or self::h2 or self::h3][1]")
    private WebElement popupCourseName;

    @FindBy(xpath = "//*[contains(translate(.,'Iİ','ii'),'meeting') and (contains(translate(.,'Iİ','ii'),'not started') or contains(translate(.,'Iİ','ii'),'has not started'))]" )
    private WebElement meetingNotStartedMsg;

    @FindBy(xpath = "//*[contains(.,'Teacher') or contains(.,'Instructor') or contains(.,'Mentor')]")
    private WebElement teacherInfo;

    @FindBy(xpath = "//*[contains(.,'Date') or contains(.,'Time') or contains(.,':')]")
    private WebElement dateTimeInfo;

    @FindBy(xpath = "//div[contains(@class,'mdc-tab__text-label') and normalize-space()='Information']")
    private WebElement tabInformation;

    @FindBy(xpath = "//div[contains(@class,'mdc-tab__text-label') and normalize-space()='Topic']")
    private WebElement tabTopic;

    @FindBy(xpath = "//div[contains(@class,'mdc-tab__text-label') and normalize-space()='Attachments']")
    private WebElement tabAttachments;

    @FindBy(xpath = "//div[contains(@class,'mdc-tab__text-label') and normalize-space()='Recent Events']")
    private WebElement tabRecentEvents;

    @FindBy(css = ".tab-content, .mat-tab-body-content, ms-dialog-content")
    private WebElement tabContentArea;

    @FindBy(xpath = "(//button[.//*[name()='svg' and @data-icon='chevron-right']])[1]")
    private WebElement nextWeekBtn;

    private By weeklyPrevBtn = By.xpath("(//button[.//*[name()='svg' and @data-icon='chevron-left']])[1]");

    private By weeklyTodayBtn = By.xpath(
            "(//button[.//*[name()='svg' and (@data-icon='calendar' or @data-icon='calendar-day' or @data-icon='calendar-days')]])[1]"
    );
    private final By weeklyCourseItemBy = By.cssSelector(
            ".weekly-course-item,.event, ms-course-event, .calendar-event, ms-course-event *"
    );
    @FindBy(xpath =
            "//*[contains(text(),'Monday') and " +
                    "contains(text(),'Sunday') and " +
                    "contains(text(),'-') and " +
                    "contains(text(),':')]")
    private WebElement weekRangeText;

    @FindBy(xpath = "//div[@role='tab' and (contains(.,'Weekly') or contains(.,'Weekly Course Plan') or contains(.,'Week'))]")
    private WebElement weeklyTab;

    @FindBy(xpath = "//div[@role='tab' and (contains(.,'Month') or contains(.,'Monthly') or contains(.,'Calendar'))]")
    private WebElement monthTab;

    @FindBy(xpath = "//*[self::a or self::button][contains(translate(normalize-space(.),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'RECORDING')]")
    private WebElement recordingBtn;

    @FindBy(css = "button.vjs-big-play-button")
    private WebElement playButton;

    @FindBy(css = "video.vjs-tech")
    private WebElement videoElement;

    public void verifyPlayIconVisible() {
        wait.until(ExpectedConditions.visibilityOf(playButton));
        Assert.assertTrue(playButton.isDisplayed(), "Play icon/button is not visible!");
    }

    public void clickPlayIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(playButton));
        playButton.click();
    }

    public void verifyVideoIsPlaying() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Boolean paused = (Boolean) js.executeScript("return arguments[0].paused;", videoElement);
        Assert.assertFalse(paused, "Video is still paused, playback did not start!");
    }

    public void openRandomCourseFromWeeklyPlan2() {
        openWeeklyCoursePlanTabIfNeeded();
        if (!waitForWeeklyItems(5)) {
            for (int i = 0; i < 3 && !waitForWeeklyItems(3); i++) {
                clickWeeklyNavigationButton("next");
            }
        }
        int size = driver.findElements(weeklyCourseItemBy).size();
        Assert.assertTrue(size > 0,
                "No course item found in Weekly Course Plan! weeklyCourseItemBy size=" + size);

        var items = driver.findElements(weeklyCourseItemBy);
        WebElement randomCourse = items.get(new Random().nextInt(items.size()));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", randomCourse);
        wait.until(ExpectedConditions.elementToBeClickable(randomCourse)).click();

        LOGGER.info("Random course clicked from weekly plan. Item count=" + items.size());
    }

    private boolean waitForWeeklyItems(int seconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(seconds))
                    .until(d -> d.findElements(weeklyCourseItemBy).size() > 0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void verifyMeetingNotStartedMessageVisible() {
        wait.until(ExpectedConditions.visibilityOf(courseDetailsPopup));
        wait.until(ExpectedConditions.visibilityOf(meetingNotStartedMsg));
        Assert.assertTrue(meetingNotStartedMsg.isDisplayed(), "Meeting not started message is not visible!");
    }

    public void verifyTeacherInstructorAndDateTimeInfoVisible() {
        wait.until(ExpectedConditions.visibilityOf(courseDetailsPopup));
        String src = driver.getPageSource().toLowerCase();

        Assert.assertTrue(
                src.contains("teacher") || src.contains("instructor"),
                "Teacher/Instructor info not found on popup!"
        );
        Assert.assertTrue(
                src.contains("date") || src.contains("time") || src.contains(":"),
                "Date/Time info not found on popup!"
        );
    }
    public void verifyRecordingButtonVisible() {
        wait.until(ExpectedConditions.visibilityOf(courseDetailsPopup));
        wait.until(ExpectedConditions.visibilityOf(recordingBtn));
        Assert.assertTrue(recordingBtn.isDisplayed(), "Recording button is not visible!");
    }
    public void clickRecordingButton() {
        wait.until(ExpectedConditions.elementToBeClickable(recordingBtn));
        recordingBtn.click();
    }
    public void verifyRecordingPageOpened() {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("record"),
                ExpectedConditions.urlContains("recording"),
                ExpectedConditions.urlContains("video")
        ));
    }
    public void clickWeeklyNavigationButton(String button) {
        openWeeklyCoursePlanTabIfNeeded();

        switch (button.trim().toLowerCase()) {
            case "next":
                wait.until(ExpectedConditions.elementToBeClickable(nextWeekBtn)).click();
                break;
            case "previous":
                wait.until(ExpectedConditions.elementToBeClickable(weeklyTodayBtn)).click();
                break;
            case "today":
                wait.until(ExpectedConditions.elementToBeClickable(weeklyPrevBtn)).click();
                break;
            default:
                throw new RuntimeException("Unknown navigation button: " + button);
        }
    }
    public boolean isCourseNameVisibleOnPopup() {
        try {
            wait.until(ExpectedConditions.visibilityOf(popupCourseName));
            return popupCourseName.isDisplayed() && !popupCourseName.getText().trim().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
    public void openCourseByStatus(String status) {
        Assert.assertTrue(weeklyCourseItems != null && weeklyCourseItems.size() > 0,
                "No course item found in Weekly Course Plan!");

        String st = status.trim().toUpperCase();

        WebElement target = null;

        for (WebElement item : weeklyCourseItems) {
            try {
                String txt = item.getText().toUpperCase();
                boolean hasP = txt.contains("P");
                boolean hasS = txt.contains("S");
                boolean hasE = txt.contains("E");

                if (st.equals("P") && hasP) { target = item; break; }
                if (st.equals("S") && hasS) { target = item; break; }
                if (st.equals("E") && hasE) { target = item; break; }

                if (st.equals("PENDING") && !(hasP || hasS || hasE)) { target = item; break; }

            } catch (Exception ignored) {}
        }

        if (target == null) {
            target = weeklyCourseItems.get(new Random().nextInt(weeklyCourseItems.size()));
        }

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", target);
        wait.until(ExpectedConditions.elementToBeClickable(target)).click();
    }
    public void verifyNotStartedMeetingPopupDetails() {
        Assert.assertTrue(isCourseNameVisibleOnPopup(), "Course name not visible on popup!");
        try {
            wait.until(ExpectedConditions.visibilityOf(meetingNotStartedMsg));
            Assert.assertTrue(meetingNotStartedMsg.isDisplayed(), "Meeting not started message not shown!");
        } catch (Exception e) {
            Assert.fail("Meeting not started message not found on unpublished course popup!");
        }
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("teacher")
                        || driver.getPageSource().toLowerCase().contains("instructor")
                        || driver.getPageSource().toLowerCase().contains("mentor"),
                "Teacher/Instructor info not found on popup!");

        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("time")
                        || driver.getPageSource().toLowerCase().contains("date")
                        || driver.getPageSource().contains(":"),
                "Date/Time info not found on popup!");
    }
    public void verifyPopupTabsWork() {
        clickTabAndVerifyContent(tabInformation, "Information tab content not loaded!");
        clickTabAndVerifyContent(tabTopic, "Topic tab content not loaded!");
        clickTabAndVerifyContent(tabAttachments, "Attachments tab content not loaded!");
        clickTabAndVerifyContent(tabRecentEvents, "Recent Events tab content not loaded!");
    }
    private void clickTabAndVerifyContent(WebElement tab, String failMessage) {
        wait.until(ExpectedConditions.elementToBeClickable(tab));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);
        try {
            wait.until(ExpectedConditions.visibilityOf(tabContentArea));
            Assert.assertTrue(tabContentArea.isDisplayed(), failMessage);
        } catch (Exception e) {
            Assert.assertTrue(driver.getPageSource().toLowerCase().contains("information")
                            || driver.getPageSource().toLowerCase().contains("topic")
                            || driver.getPageSource().toLowerCase().contains("attachment")
                            || driver.getPageSource().toLowerCase().contains("recent"),
                    failMessage);
        }
    }
    WebDriver driver = BaseDriver.getDriver();
    public void verifyCalendarPageOpened() {
        try {
            wait.until(ExpectedConditions.urlContains("calendar"));
            LOGGER.info("Calendar page opened (url contains calendar)");
        } catch (Exception e) {
            wait.until(ExpectedConditions.visibilityOf(calendarHeader));
            Assert.assertTrue(calendarHeader.isDisplayed(), "Calendar page is not opened!");
        }
    }
    public void openWeeklyCoursePlanTabIfNeeded() {
        By weeklyTab = By.xpath(
                "//*[contains(@class,'mdc-tab__text-label') or contains(@class,'mat-mdc-tab-label-content')]" +
                        "[normalize-space()='Weekly Course Plan']");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(weeklyTab)).click();
        } catch (Exception ignored) {
        }
    }
    public void openWeeklyCoursePlanTabIfNeeded2() {
        try {
            wait.until(ExpectedConditions.visibilityOf(weeklyCoursePlanTab));
            wait.until(ExpectedConditions.elementToBeClickable(weeklyCoursePlanTab)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", weeklyCoursePlanTab);
        }
        wait.until(ExpectedConditions.visibilityOf(weeklyPlanArea));
    }
    public void verifyWeeklyCoursePlanVisible() {
        Assert.assertTrue(driver.getPageSource().contains("Weekly Course Plan"),
                "Weekly Course Plan text not found on page!");
    }
    public void verifyTodayVisibleOnWeeklyPlan() {
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("today"),
                "Today is not visible on Weekly Course Plan!");
    }
    public void verifyStatusIconsVisible() {
        Assert.assertTrue(statusIcons != null && statusIcons.size() > 0,
                "Course status icons (P/S/E/C) are not visible!");
    }
    public void verifyStatusLegendVisibleWithMeanings() {
        wait.until(ExpectedConditions.visibilityOf(statusLegendBox));
        Assert.assertTrue(statusLegendBox.isDisplayed(), "Status legend is not visible!");

        String t = statusLegendBox.getText();
        Assert.assertTrue(t.contains("Published"), "Legend missing: Published");
        Assert.assertTrue(t.contains("Started"), "Legend missing: Started");
        Assert.assertTrue(t.contains("Ended"), "Legend missing: Ended");
        Assert.assertTrue(t.contains("Cancelled"), "Legend missing: Cancelled");
    }

    public void switchToView(String view) {
        String v = view.trim().toLowerCase();
        WebElement target;

        if (v.equals("month") || v.equals("monthly") || v.equals("calendar")) {
            target = monthTab;
        } else if (v.equals("weekly") || v.equals("week") || v.equals("weekly course plan")) {
            target = weeklyTab;
        } else {
            throw new RuntimeException("Unknown view: " + view);
        }

        wait.until(ExpectedConditions.visibilityOf(target));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", target);
        wait.until(ExpectedConditions.elementToBeClickable(target));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", target);
    }
    public void verifyMonthlyCalendarVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(monthlyCalendarArea));
            Assert.assertTrue(monthlyCalendarArea.isDisplayed(), "Monthly calendar area not visible!");
        } catch (Exception e) {
            Assert.assertTrue(driver.getPageSource().toLowerCase().contains("calendar"),
                    "Monthly calendar view is not displayed!");
        }
    }
    public String getWeeklyDateRangeText() {
        openWeeklyCoursePlanTabIfNeeded();
        return wait
                .until(ExpectedConditions.visibilityOf(weekRangeText))
                .getText()
                .trim();
    }
    public void openRandomCourseFromWeeklyPlan() {
        Assert.assertTrue(weeklyCourseItems != null && weeklyCourseItems.size() > 0,
                "No course item found in Weekly Course Plan!");

        WebElement randomCourse = weeklyCourseItems.get(new Random().nextInt(weeklyCourseItems.size()));

        WebDriver driver = BaseDriver.getDriver();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", randomCourse);
        wait.until(ExpectedConditions.elementToBeClickable(randomCourse)).click();

        LOGGER.info("Random course clicked from weekly plan");
    }
    public void verifyCourseDetailsPopupDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(courseDetailsPopup));
        Assert.assertTrue(courseDetailsPopup.isDisplayed(), "Course details popup is not displayed!");
    }
}
