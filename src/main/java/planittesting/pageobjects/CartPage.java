package planittesting.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import planittesting.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cart-item")
	List<WebElement> cartProducts;

    @FindBy(className = "total")
    private WebElement cartItemTotal;
	
	By productsBy = By.cssSelector(".cart-item");
	By titleOfProduct = By.cssSelector("td");
	By priceOfProduct = By.cssSelector("td:nth-child(2)");
	By CartItemQuantity = By.cssSelector("td:nth-child(3)");
	By CartItemSubTotal = By.cssSelector("td:nth-child(4)");
	By addToCart = By.cssSelector(".btn-success");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return cartProducts;
	}
	
	public Boolean verifyProductAddedToCartByName(String productName) {
		Boolean match = cartProducts.stream().anyMatch(product-> product.findElement(titleOfProduct).getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(titleOfProduct).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public float getProductPriceByName(String productName) {
		WebElement prod = getProductByName(productName);
		return Float.parseFloat(prod.findElement(priceOfProduct).getText().replace("$", ""));
	}
	
	public int getCartItemQtyByName(String productName) {
		WebElement prod = getProductByName(productName);
		return Integer.parseInt(prod.findElement(CartItemQuantity).getText());
	}
	
	public float getCartItemSubTotalByName(String productName) {
		WebElement prod = getProductByName(productName);
		return Float.parseFloat(prod.findElement(CartItemSubTotal).getText().replace("$", ""));
	}

    public float getCartItemTotal() {
        return Float.parseFloat(cartItemTotal.getText().split(" ")[1].replace("$", ""));
    }
}
