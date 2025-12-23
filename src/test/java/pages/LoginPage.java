package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.base.BasePage;

public class LoginPage extends BasePage {

    @FindBy(css = "div.mat-mdc-form-field-infix input[formcontrolname='username']")
    private WebElement usernameInput;

    @FindBy(css = "div.mat-mdc-form-field-infix input[formcontrolname='password']")
    private WebElement passwordInput;

    @FindBy(css = "form[name='loginForm'] button[aria-label='LOGIN']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class='mat-mdc-form-field-error-wrapper']//*[contains(text(),'E-mail')]")
    private WebElement usernameError;

    @FindBy(xpath = "//div[@class='mat-mdc-form-field-error-wrapper']//*[contains(text(),'Password')]")
    private WebElement passwordError;

    @FindBy(css = ".login-error")
    private WebElement loginError;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        sendKeysToElement(usernameInput, username);
    }

    public void enterPassword(String password) {
        sendKeysToElement(passwordInput, password);
    }

    public void clickLoginButton() {
        clickElement(loginButton);
    }

    public void verifyErrorMessage(String errorType, String expectedError) {
        String actualError;

        switch (errorType.toLowerCase()) {
            case "login":
                actualError = loginError.getText();
                break;
            case "username":
                actualError = usernameError.getText();
                break;
            case "password":
                actualError = passwordError.getText();
                break;
            default:
                throw new RuntimeException("Unknown error type: " + errorType);
        }

        Assert.assertEquals(actualError, expectedError, "Error message does not match!");
    }
}
