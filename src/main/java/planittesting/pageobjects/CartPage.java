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
	
	@FindBy(css = "tbody tr:nth-child(1) td:nth-child(2)")
    WebElement firstCartItemPrice;
	
	@FindBy(css = "tbody tr:nth-child(1) td:nth-child(3) input")
    private WebElement firstCartItemQuantity;

    @FindBy(css = "tbody tr:nth-child(1) td:nth-child(4)")
    private WebElement firstCartItemSubTotal;
    
    @FindBy(css = "tbody tr:nth-child(2) td:nth-child(2)")
    private WebElement secondCartItemPrice;

    @FindBy(css = "tbody tr:nth-child(2) td:nth-child(3) input")
    private WebElement secondCartItemQuantity;

    @FindBy(css = "tbody tr:nth-child(2) td:nth-child(4)")
    private WebElement secondCartItemSubTotal;
    
    @FindBy(css = "tbody tr:nth-child(3) td:nth-child(2)")
    private WebElement thirdCartItemPrice;
    
    @FindBy(css = "tbody tr:nth-child(3) td:nth-child(3) input")
    private WebElement thirdCartItemQuantity;

    @FindBy(css = "tbody tr:nth-child(3) td:nth-child(4)")
    private WebElement thirdCartItemSubTotal;

    @FindBy(className = "total")
    private WebElement cartItemTotal;
	
	By productsBy = By.cssSelector(".cart-item");
	By titleOfProduct = By.cssSelector("td");
	By priceOfProduct = By.cssSelector(".cart-item:first-child .ng-binding:nth-child(2)");
	By addToCart = By.cssSelector(".btn-success");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return cartProducts;
	}
	
	public Boolean verifyProductAddedToCartByName(String productName) {
		Boolean match = cartProducts.stream().anyMatch(product-> product.findElement(titleOfProduct).getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public int getFirstCartItemQuantity() {
        return Integer.parseInt(firstCartItemQuantity.getAttribute("value"));
    }

    public int getSecondCartItemQuantity() {
        return Integer.parseInt(secondCartItemQuantity.getAttribute("value"));
    }
    
    public int getThirdCartItemQuantity() {
        return Integer.parseInt(thirdCartItemQuantity.getAttribute("value"));
    }
    
    public float getFirstItemPrice() {
        return Float.parseFloat(firstCartItemPrice.getText().replace("$", ""));
    }

    public float getSecondItemPrice() {
        return Float.parseFloat(secondCartItemPrice.getText().replace("$", ""));
    }

    public float getThirdItemPrice() {
        return Float.parseFloat(thirdCartItemPrice.getText().replace("$", ""));
    }
    
    public float getFirstItemSubTotal() {
        return Float.parseFloat(firstCartItemSubTotal.getText().replace("$", ""));
    }

    public float getSecondItemSubTotal() {
        return Float.parseFloat(secondCartItemSubTotal.getText().replace("$", ""));
    }

    public float getThirdItemSubTotal() {
        return Float.parseFloat(thirdCartItemSubTotal.getText().replace("$", ""));
    }

    public float getCartItemTotal() {
        return Float.parseFloat(cartItemTotal.getText().split(" ")[1].replace("$", ""));
    }
}
