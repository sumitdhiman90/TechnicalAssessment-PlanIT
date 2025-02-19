package planittesting.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import planittesting.BaseTest.BaseTest;
import planittesting.pageobjects.ContactPage;

public class ContactPageTests extends BaseTest {
	
	public String forename = "TestForename";
    public String email = "testemail@xyz.com";
    public String feedbackMessage = "Test Message";

    @Test
	public void mandatoryFields() throws InterruptedException {
		
        ContactPage contactPage = HomePage.clickContactFromHeader();
        
        contactPage.clickSubmitButton();
        
        Assert.assertTrue(contactPage.getForeNameErrorMessage().equalsIgnoreCase("Forename is required"));
        Assert.assertTrue(contactPage.getEmailErrorMessage().equalsIgnoreCase("Email is required"));
        Assert.assertTrue(contactPage.getMessageError().equalsIgnoreCase("Message is required"));

        contactPage.enterForeName(forename);
        contactPage.enterEmail(email);
        contactPage.enterMessage(feedbackMessage);
        
        Assert.assertFalse(contactPage.foreNameWebElementPresence(), "Forename mandatory error message is still present");
        Assert.assertFalse(contactPage.emailWebElementPresence(), "Email mandatory error message is still present");
        Assert.assertFalse(contactPage.messageWebElementPresence(), "Message mandatory error message is still present");
    }
    
    @Test(dataProvider="getData")
	public void submitFeedback(HashMap<String,String> input) throws InterruptedException {
		
        ContactPage contactPage = HomePage.clickContactFromHeader();
        
        contactPage.enterForeName(input.get("forename"));
        contactPage.enterEmail(input.get("email"));
        contactPage.enterMessage(input.get("feedbackMessage"));
        contactPage.clickSubmitButton();
        
        Assert.assertEquals(contactPage.getSuccessMessage(), "Thanks "+input.get("forename")+", we appreciate your feedback.");
    }
    
    @DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//planittesting//testData//ContactPageTests.json");
		return new Object[][]  {{data.get(0)}, {data.get(1)}, {data.get(2)}, {data.get(3)}, {data.get(4)}};
	}
}
