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
                {2, 5, 3}
        };
    }

	@Test(dataProvider = "testData")
	public void CartPageTests(int stuffedFrogQuantity, int fluffyBunnyQuantity, int valentineBearQuantity) throws IOException
	{	
		ShopPage ShopPage = HomePage.clickStartShoppingButton();
		
		for(int a=0; a<stuffedFrogQuantity; a++) {
			ShopPage.addProductToCart("Stuffed Frog");
		}
		for(int b=0; b<fluffyBunnyQuantity; b++) {
			ShopPage.addProductToCart("Fluffy Bunny");
		}
		for(int c=0; c<valentineBearQuantity; c++) {
			ShopPage.addProductToCart("Valentine Bear");
	    }
		float StuffedFrogProductPrice = ShopPage.getProductPrice("Stuffed Frog");
		float FluffyBunnyProductPrice = ShopPage.getProductPrice("Fluffy Bunny");
		float ValentineBearProductPrice = ShopPage.getProductPrice("Valentine Bear");
		
		CartPage CartPage = HomePage.clickCartFromHeader();

		CartPage.verifyProductAddedToCartByName("Stuffed Frog");
		CartPage.verifyProductAddedToCartByName("Fluffy Bunny");
		CartPage.verifyProductAddedToCartByName("Valentine Bear");
		
		Assert.assertEquals(CartPage.getProductPriceByName("Stuffed Frog"), StuffedFrogProductPrice);
		Assert.assertEquals(CartPage.getProductPriceByName("Fluffy Bunny"), FluffyBunnyProductPrice);
		Assert.assertEquals(CartPage.getProductPriceByName("Valentine Bear"), ValentineBearProductPrice);
		
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        float StuffedFrogItemSubtotal = Float.parseFloat(df.format(CartPage.getProductPriceByName("Stuffed Frog") * stuffedFrogQuantity));
        float FluffyBunnyItemSubtotal = Float.parseFloat(df.format(CartPage.getProductPriceByName("Fluffy Bunny") * fluffyBunnyQuantity));
        float ValentineBearItemSubtotal = Float.parseFloat(df.format(CartPage.getProductPriceByName("Valentine Bear") * valentineBearQuantity));
        
        Assert.assertEquals(CartPage.getCartItemSubTotalByName("Stuffed Frog"), StuffedFrogItemSubtotal);
		Assert.assertEquals(CartPage.getCartItemSubTotalByName("Fluffy Bunny"), FluffyBunnyItemSubtotal);
		Assert.assertEquals(CartPage.getCartItemSubTotalByName("Valentine Bear"), ValentineBearItemSubtotal);
		
		float totalOfThreeProducts = (StuffedFrogItemSubtotal + FluffyBunnyItemSubtotal + ValentineBearItemSubtotal);
		Assert.assertEquals(CartPage.getCartItemTotal(), totalOfThreeProducts);
		
	}
}
