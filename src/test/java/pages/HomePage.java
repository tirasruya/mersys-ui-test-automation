package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.base.BasePage;

public class HomePage extends BasePage {

    @FindBy(css = ".mat-drawer-content > div > div > span:nth-child(1)")
    private WebElement welcomeText;

    @FindBy(css = "mat-toolbar img:first-of-type")
    private WebElement companyLogo;

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

}