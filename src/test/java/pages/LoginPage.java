package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.base.BasePage;

public class LoginPage extends BasePage {

    @FindBy(css = "div.mat-mdc-form-field-infix > input[formcontrolname='username']")
    private WebElement usernameInput;

    @FindBy(css = "div.mat-mdc-form-field-infix > input[formcontrolname='password']")
    private WebElement passwordInput;

    @FindBy(css = "form[name='loginForm'] button[aria-label='LOGIN']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class='mat-mdc-form-field-error-wrapper']//*[contains(text(),'E-mail')]")
    private WebElement usernameError;

    @FindBy(xpath = "//div[@class='mat-mdc-form-field-error-wrapper']//*[contains(text(),'Password')]")
    private WebElement passwordError;

    @FindBy(xpath = "//mat-expansion-panel-header[@role='button']")
    private WebElement loginError;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        LOGGER.info("Entering username");
        sendKeysToElement(usernameInput, username);
    }

    public void enterPassword(String password) {
        LOGGER.info("Entering password");
        sendKeysToElement(passwordInput, password);
    }

    public void clickLoginButton() {
        LOGGER.info("Clicking login button");
        clickElement(loginButton);
    }

    public void loginAsStudent() {
        String username = System.getenv("TEST_USERNAME");
        String password = System.getenv("TEST_PASSWORD");

        Assert.assertNotNull(username, "TEST_USERNAME is not set");
        Assert.assertNotNull(password, "TEST_PASSWORD is not set");

        LOGGER.info("Logged in as user: {}", username);

        enterUsername(username);
        enterPassword(password);
        clickLoginButton();

        Allure.label("logged_in_user", "STUDENT");
    }

    public void verifyErrorMessage(String errorType, String expectedError) {

        LOGGER.info("Verifying error message for type: {}", errorType);

        String actualError;

        switch (errorType.toLowerCase()) {
            case "login":
                wait.until(ExpectedConditions.visibilityOf(loginError));
                Assert.assertTrue(loginError.isDisplayed(), "Login error is not displayed");
                actualError = loginError.getText();
                break;
            case "username":
                wait.until(ExpectedConditions.visibilityOf(usernameError));
                Assert.assertTrue(usernameError.isDisplayed(), "Username error is not displayed");
                actualError = usernameError.getText();
                break;
            case "password":
                wait.until(ExpectedConditions.visibilityOf(passwordError));
                Assert.assertTrue(passwordError.isDisplayed(), "Password error is not displayed");
                actualError = passwordError.getText();
                break;
            default:
                throw new RuntimeException("Unknown error type: " + errorType);
        }
        Assert.assertEquals(actualError, expectedError, "Error message does not match!");
    }
}