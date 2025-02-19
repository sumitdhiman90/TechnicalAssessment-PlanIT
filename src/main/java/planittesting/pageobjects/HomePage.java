package planittesting.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import planittesting.AbstractComponents.AbstractComponent;

public class HomePage extends AbstractComponent {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id=("nav-home"))
	WebElement HomeHeader;
	
	@FindBy(id=("nav-shop"))
	WebElement ShopHeader;
	
	@FindBy(id=("nav-contact"))
	WebElement ContactHeader;
	
	@FindBy(id=("nav-login"))
	WebElement LoginHeader;
	
	@FindBy(id=("nav-cart"))
	WebElement CartHeader;
	
	@FindBy(css=".btn-success")
	WebElement StartShoppingButton;
	
	public void clickHomeFromHeader() {
		HomeHeader.click();
	}
	
	public ShopPage clickShopFromHeader() {
		ShopHeader.click();
		return new ShopPage(driver);
	}
	
	public ContactPage clickContactFromHeader() {
		ContactHeader.click();
		return new ContactPage(driver);
	}
	
	public void clickLoginFromHeader() {
		LoginHeader.click();
	}
	
	public CartPage clickCartFromHeader() {
		CartHeader.click();
		return new CartPage(driver);
	}
	
	public ShopPage clickStartShoppingButton() {
		StartShoppingButton.click();
		return new ShopPage(driver);
	}
	
	public void goTo() {
		driver.get("http://jupiter.cloud.planittesting.com");
	}
}
