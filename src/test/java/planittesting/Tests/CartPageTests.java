package planittesting.Tests;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import planittesting.AbstractComponents.AbstractComponent;
import planittesting.BaseTest.BaseTest;
import planittesting.pageobjects.CartPage;
import planittesting.pageobjects.HomePage;
import planittesting.pageobjects.ShopPage;

public class CartPageTests extends BaseTest {
	
	@DataProvider(name = "testData")
    public Object[][] dataSet() {
        return new Object[][]{
                {3, 5, 3}
        };
    }

	@Test(dataProvider = "testData")
	public void CartPageTests(int firstProductQuantity, int secondProductQuantity, int thirdProductQuantity) throws IOException
	{	
		ShopPage ShopPage = HomePage.clickStartShoppingButton();
		
		for(int a=0; a<firstProductQuantity; a++) {
			ShopPage.addProductToCart("Stuffed Frog");
		}
		for(int b=0; b<secondProductQuantity; b++) {
			ShopPage.addProductToCart("Fluffy Bunny");
		}
		for(int c=0; c<thirdProductQuantity; c++) {
			ShopPage.addProductToCart("Valentine Bear");
	    }
		float StuffedFrogProductPrice = ShopPage.getProductPrice("Stuffed Frog");
		float FluffyBunnyProductPrice = ShopPage.getProductPrice("Fluffy Bunny");
		float ValentineBearProductPrice = ShopPage.getProductPrice("Valentine Bear");
		
		CartPage CartPage = HomePage.clickCartFromHeader();

		List<WebElement> cartproducts = CartPage.getProductList();
		CartPage.verifyProductAddedToCartByName("Stuffed Frog");
		CartPage.verifyProductAddedToCartByName("Fluffy Bunny");
		CartPage.verifyProductAddedToCartByName("Valentine Bear");
		
		Assert.assertEquals(CartPage.getFirstItemPrice(), StuffedFrogProductPrice);
		Assert.assertEquals(CartPage.getSecondItemPrice(), FluffyBunnyProductPrice);
		Assert.assertEquals(CartPage.getThirdItemPrice(), ValentineBearProductPrice);
        
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        float firstItemSubtotal = Float.parseFloat(df.format(CartPage.getFirstItemPrice() * firstProductQuantity));
        float secondItemSubtotal = Float.parseFloat(df.format(CartPage.getSecondItemPrice() * secondProductQuantity));
        float thirdItemSubtotal = Float.parseFloat(df.format(CartPage.getThirdItemPrice() * thirdProductQuantity));
        
        Assert.assertEquals(CartPage.getFirstItemSubTotal(), firstItemSubtotal);
		Assert.assertEquals(CartPage.getSecondItemSubTotal(), secondItemSubtotal);
		Assert.assertEquals(CartPage.getThirdItemSubTotal(), thirdItemSubtotal);
		
		float totalOfThreeProducts = (firstItemSubtotal + secondItemSubtotal + thirdItemSubtotal);
		Assert.assertEquals(CartPage.getCartItemTotal(), totalOfThreeProducts);
		
	}
}
