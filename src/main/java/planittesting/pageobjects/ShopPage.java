package planittesting.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import planittesting.AbstractComponents.AbstractComponent;

public class ShopPage extends AbstractComponent {
	
	WebDriver driver;
	
	public ShopPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".product")
	List<WebElement> products;
	
	By productsBy = By.cssSelector(".product");
	By titleOfProduct = By.cssSelector("h4");
	By productPrice = By.cssSelector(".product-price");
	By addToCart = By.cssSelector(".btn-success");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(titleOfProduct).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
	}
	
	public float getProductPrice(String productName) {
		WebElement prod = getProductByName(productName);
		return Float.parseFloat(prod.findElement(productPrice).getText().replace("$", ""));
	}
}
