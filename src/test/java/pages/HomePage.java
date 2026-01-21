package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.base.BasePage;

import java.util.Objects;

import static utils.BaseDriver.getDriver;

public class HomePage extends BasePage {

    @FindBy(css = ".mat-drawer-content > div > div > span:nth-child(1)")
    private WebElement welcomeText;

    @FindBy(css = "mat-toolbar img:first-of-type")
    private WebElement companyLogo;

    @FindBy(xpath = "//ms-layout-menu-button[@page='COURSES']")
    private WebElement coursesLogo;

    @FindBy(xpath = "//ms-layout-menu-button[@page='CALENDAR']")
    private WebElement calendarLogo;

    @FindBy(xpath = "//ms-layout-menu-button[@page='ATTENDANCE']")
    private WebElement attendanceLogo;

    @FindBy(xpath = "//ms-layout-menu-button[@page='ASSIGNMENT']")
    private WebElement assignmentsLogo;

    @FindBy(xpath = "//ms-layout-menu-button[@page='GRADING']")
    private WebElement gradingLogo;

    @FindBy(xpath = "(//button//span[@class='mdc-button__label'])[6]")
    private WebElement hamburgerMenuLogo;

    @FindBy(css = ".cdk-overlay-pane .mat-mdc-menu-panel")
    private WebElement hamburgerMenuPanel;

    @FindBy(xpath = "//user-announcement-bell/button")
    private WebElement announcementsLogo;

    @FindBy(xpath = "//mat-toolbar-row//span[normalize-space()='Announcements']")
    private WebElement announcementsPopup;

    @FindBy(xpath = "//user-message-bell/button")
    private WebElement messagesLogo;

    @FindBy(xpath = "//mat-toolbar-row//span[normalize-space()='Messages']")
    private WebElement messagesPopup;

    @FindBy(css = "div > div:nth-child(2) > button > span.mdc-button__label")
    private WebElement profileSettingsLogo;

    @FindBy(css = ".cdk-overlay-pane .mat-mdc-menu-panel")
    private WebElement profileSettingsMenu;

    @FindBy(xpath = "//div[contains(@class,'mat-mdc-menu-panel')]//button[.//span[normalize-space()='Finance']]\n ")
    private WebElement hamburgerFinanceItem;

    @FindBy(xpath = "//div[contains(@class,'mat-mdc-menu-panel')]//button[.//span[normalize-space()='My Finance']]")
    private WebElement hamburgerMyFinanceItem;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void verifyHomePage(String expectedText) {

        LOGGER.info("Verifying Home Page is opened");
        Assert.assertTrue(isDisplayed(welcomeText), "Welcome text is not displayed on Home Page");
        String actualText = welcomeText.getText().trim();
        LOGGER.debug("Actual welcome text: {}", actualText);
        Assert.assertTrue(actualText.contains(expectedText), "Welcome text does not contain expected text. Actual: " + actualText);
    }

    public boolean isLogoDisplayed() {
        LOGGER.info("Checking if company logo is displayed");
        return isDisplayed(companyLogo);
    }

    public void clickLogo() {
        clickElement(companyLogo);
        LOGGER.info("Clicked on the company logo");
    }

    public void verifyRedirectedToTechnoStudy() {
        switchToNewTab();
        wait.until(ExpectedConditions.urlToBe("https://technostudy.com.tr/"));
        LOGGER.info("Verifying redirection to techno.study");
    }

    public void verifyMenuVisibleAndClickable(String menu) {

        LOGGER.info("Verifying visibility and clickability of menu: {}", menu);

        WebElement element;

        switch (menu.toLowerCase()) {
            case "logo":
                element = companyLogo;
                break;
            case "courses":
                element = coursesLogo;
                break;
            case "calendar":
                element = calendarLogo;
                break;
            case "attendance":
                element = attendanceLogo;
                break;
            case "assignments":
                element = assignmentsLogo;
                break;
            case "grading":
                element = gradingLogo;
                break;
            case "announcements":
                element = announcementsLogo;
                break;
            case "messages":
                element = messagesLogo;
                break;
            case "profilesettings":
                element = profileSettingsLogo;
                break;
            case "hamburger":
                element = hamburgerMenuLogo;
                break;
            default:
                LOGGER.error("Unknown menu item for visibility check: {}", menu);
                throw new RuntimeException("Unknown menu item: " + menu);
        }

        wait.until(ExpectedConditions.visibilityOf(element));
        Assert.assertTrue(isDisplayed(element), menu + " is not visible");

        wait.until(ExpectedConditions.elementToBeClickable(element));
        Assert.assertTrue(element.isEnabled(), menu + " is not clickable");

        LOGGER.info("{} menu is visible and clickable", menu);
    }

    public void clickMenuItem(String menu) {

        LOGGER.info("Attempting to click menu item: {}", menu);

        switch (menu.toLowerCase()) {
            case "logo":
                clickElement(companyLogo);
                LOGGER.debug("Clicked Logo");
                break;

            case "courses":
                clickElement(coursesLogo);
                LOGGER.debug("Clicked Courses");
                break;

            case "calendar":
                clickElement(calendarLogo);
                LOGGER.debug("Clicked Calendar");
                break;

            case "attendance":
                clickElement(attendanceLogo);
                LOGGER.debug("Clicked Attendance");
                break;

            case "assignments":
                clickElement(assignmentsLogo);
                LOGGER.debug("Clicked Assignments");
                break;

            case "grading":
                clickElement(gradingLogo);
                LOGGER.debug("Clicked Grading");
                break;

            case "announcements":
                clickElement(announcementsLogo);
                LOGGER.debug("Clicked Announcements");
                break;

            case "messages":
                clickElement(messagesLogo);
                LOGGER.debug("Clicked Messages");
                break;

            case "profilesettings":
                clickElement(profileSettingsLogo);
                LOGGER.debug("Clicked Profile Settings");
                break;

            case "hamburger":
                clickElement(hamburgerMenuLogo);
                LOGGER.debug("Clicked Hamburger Menu");
                break;

            default:
                LOGGER.error("Invalid menu item provided: {}", menu);
                throw new RuntimeException("Unknown menu item: " + menu);
        }
    }

    public void verifyMenuAction(String menu) {

        WebDriver driver = getDriver();

        LOGGER.info("Verifying action for menu item: {}", menu);

        switch (menu.toLowerCase()) {

            case "logo":
                verifyRedirectedToTechnoStudy();
                LOGGER.info("Logo redirection verified");
                break;

            case "courses":
                wait.until(ExpectedConditions.urlContains("courses"));
                Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("courses"));
                LOGGER.info("Courses page opened");
                break;

            case "calendar":
                wait.until(ExpectedConditions.urlContains("calendar"));
                Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("calendar"));
                LOGGER.info("Calendar page opened");
                break;

            case "attendance":
                wait.until(ExpectedConditions.urlContains("attendance"));
                Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("attendance"));
                LOGGER.info("Attendance page opened");
                break;

            case "assignments":
                wait.until(ExpectedConditions.urlContains("assignment"));
                Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("assignment"));
                LOGGER.info("Assignments page opened");
                break;

            case "grading":
                wait.until(ExpectedConditions.urlContains("grading"));
                Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("grading"));
                LOGGER.info("Grading page opened");
                break;

            case "hamburger":
                Assert.assertTrue(isDisplayed(hamburgerMenuPanel));
                LOGGER.info("Hamburger menu panel opened");
                break;

            case "announcements":
                Assert.assertTrue(isDisplayed(announcementsPopup));
                LOGGER.info("Announcements popup opened");
                break;

            case "messages":
                Assert.assertTrue(isDisplayed(messagesPopup));
                LOGGER.info("Messages popup opened");
                break;

            case "profilesettings":
                Assert.assertTrue(isDisplayed(profileSettingsMenu));
                LOGGER.info("Profile settings dropdown opened");
                break;

            default:
                LOGGER.error("Undefined menu behavior for: {}", menu);
                throw new RuntimeException("Unknown menu action: " + menu);
        }
    }

    public void clickHamburgerMenuItem(String item) {
        LOGGER.info("Clicking item on Hamburger menu: {}", item);

        switch (item.toLowerCase()) {
            case "finance":

                Assert.assertTrue(isDisplayed(hamburgerMenuPanel), "Hamburger menu panel is not opened!");
                wait.until(ExpectedConditions.elementToBeClickable(hamburgerFinanceItem));
                clickElement(hamburgerFinanceItem);
                LOGGER.debug("Clicked Finance on Hamburger menu");
                break;

            default:
                LOGGER.error("Invalid hamburger menu item provided: {}", item);
                throw new RuntimeException("Unknown hamburger menu item: " + item);
        }
    }

    public void openHamburgerMenu() {
        LOGGER.info("Opening Hamburger menu");
        clickElement(hamburgerMenuLogo);
        wait.until(ExpectedConditions.visibilityOf(hamburgerMenuPanel));
    }

    public boolean isHamburgerMenuOpened() {
        LOGGER.info("Checking if Hamburger menu is opened");
        try {
            return isDisplayed(hamburgerMenuPanel);
        } catch (Exception e) {
            return false;
        }
    }

    public void clickFinanceFromHamburger() {
        LOGGER.info("Clicking Finance from Hamburger menu");
        Assert.assertTrue(isHamburgerMenuOpened(), "Hamburger menu is not opened!");

        wait.until(ExpectedConditions.elementToBeClickable(hamburgerFinanceItem));
        clickElement(hamburgerFinanceItem);

        LOGGER.debug("Clicked Finance menu item");
    }

    public void clickMyFinanceFromHamburger() {
        wait.until(ExpectedConditions.elementToBeClickable(hamburgerMyFinanceItem));
        clickElement(hamburgerMyFinanceItem);
    }

    public void clickCalendarFromTopNav() {
        wait.until(ExpectedConditions.elementToBeClickable(calendarLogo));
        clickElement(calendarLogo);
    }


}