package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

import static utils.BaseDriver.getDriver;

public class SubmissionPage extends BasePage {

    @FindBy(css = "mat-toolbar-row > .dialog-title")
    private WebElement submissionPopup;

    @FindBy(css = "iframe.tox-edit-area__iframe")
    public WebElement textEditorIframe;

    @FindBy(css = "body.mce-content-body")
    public WebElement textEditorBody;

    @FindBy(xpath = "//ms-button[@icon='paperclip']")
    private WebElement attachmentButton;

    @FindBy(xpath = "//ms-button[@icon='save']/button")
    private WebElement saveAsDraftButton;

    @FindBy(xpath = "//ms-confirm-button[@icon='file-import']/button")
    private WebElement sendButton;

    @FindBy(xpath = "//app-simple-dialog//button[.//span[normalize-space(text())='Yes']]")
    private WebElement confirmYesButton;

    @FindBy(xpath = "//div[contains(text(),'Success')]")
    private WebElement successMessage;

    public SubmissionPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSubmissionPopupDisplayed() {
        return isDisplayed(submissionPopup);
    }

    public boolean isTextEditorVisible() {

        return isDisplayed(textEditorIframe);
    }

    public boolean isAttachFileVisible() {
        return isDisplayed(attachmentButton);
    }

    public boolean isSaveAsDraftEnabled() {
        return saveAsDraftButton.isEnabled();
    }

    public boolean isSendButtonEnabled() {
        return sendButton.isEnabled();
    }

    public void enterText(String text) {
        WebDriver driver = getDriver();

        wait.until(ExpectedConditions.visibilityOf(textEditorIframe));
        driver.switchTo().frame(textEditorIframe);

        wait.until(ExpectedConditions.visibilityOf(textEditorBody));

        textEditorBody.clear();
        sendKeysToElement(textEditorBody, text);

        driver.switchTo().defaultContent();
    }

    public void clickSaveAsDraft() {
        clickElement(saveAsDraftButton);
    }

    public boolean isDraftSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }

    public void clickSendButton() {
        clickElement(sendButton);
    }

    public void confirmSubmission() {
        clickElement(confirmYesButton);
    }

    public boolean isFinalSuccessMessageDisplayed() {
        By toastLocator = By.xpath(
                "//*[contains(text(),'Successfully submitted to review')]"
        );

        WebElement toast =
                wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));

        String toastText = toast.getText();
        LOGGER.info("Toast message: {}", toastText);

        return toastText.contains("Successfully submitted");
    }
}
