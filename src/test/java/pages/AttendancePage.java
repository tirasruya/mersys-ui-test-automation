package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class AttendancePage extends BasePage {

    @FindBy(xpath = "//ms-layout-menu-button[@page='ATTENDANCE']")
    public WebElement attendanceMenuItem;

    @FindBy(xpath = "//*[text()=' ATTENDANCE EXCUSES ']")
    public WebElement attendanceExcusesBtn;

    @FindBy(xpath = "//ms-add-button[@tooltip='ATTENDANCE_EXCUSE.TITLE.ADD']//button")
    public WebElement plusIcon;

    @FindBy(xpath = "//mat-select[@formcontrolname='type']")
    public WebElement excuseTypeDropdown;

    @FindBy(xpath = "//span[contains(text(),'Full Day')]")
    public WebElement fullDayOption;

    @FindBy(xpath = "//button[@aria-label='Open calendar']")
    public WebElement calendarInput;

    @FindBy(xpath = "//span[contains(@class, 'mat-calendar-body-today')]")
    public WebElement todayDate;

    @FindBy(xpath = "//textarea[@placeholder='Message']")
    public WebElement descriptionField;

    @FindBy(xpath = "//span[text()='Attach Files...']//ancestor::button")
    public WebElement attachmentBtn;

    @FindBy(xpath = "//ms-button[@caption='GENERAL.BUTTON.SEND']//button")
    public WebElement sendBtn;

    public AttendancePage(WebDriver driver) {
        super(driver);
    }
}
