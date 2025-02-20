package planittesting.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import planittesting.AbstractComponents.AbstractComponent;

public class ContactPage extends AbstractComponent {
	
	WebDriver driver;
	
	public ContactPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "forename")
    WebElement foreNameField;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "message")
    WebElement messageField;

    @FindBy(className = "btn-contact")
    WebElement submitButton;

    @FindBy(id = "forename-err")
    WebElement foreNameError;

    @FindBy(id = "email-err")
    WebElement emailError;

    @FindBy(id = "message-err")
    WebElement messageError;
    
    @FindBy(className = "alert-success")
    WebElement successMessage;
    
    @FindBy(className = "modal-header")//(xpath = "//div[@class='popup modal hide ng-scope']")
    WebElement progressBar;
    
    
    public void enterForeName(String forename) {
        foreNameField.sendKeys(forename);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterMessage(String message) {
        messageField.sendKeys(message);
    }


    public void clickSubmitButton() {
    	waitForElementToClickAble(submitButton);
    	submitButton.click();
    }

    public String getForeNameErrorMessage() {
        return foreNameError.getText();
    }

    public boolean foreNameWebElementPresence() {
        try{
            return foreNameError.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public String getEmailErrorMessage() {
        return emailError.getText();
    }

    public boolean emailWebElementPresence() {
        try{
            return emailError.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public String getMessageError() {
        return messageError.getText();
    }

    public boolean messageWebElementPresence() {
        try{
            return messageError.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public String getSuccessMessage() throws InterruptedException {
    	waitForElementToDisappear(progressBar);
    	waitForExpectedElement(successMessage);
    	return successMessage.getText();
    }
	
}
