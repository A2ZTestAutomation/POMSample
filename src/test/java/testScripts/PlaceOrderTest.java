package testScripts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.TestBase;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductListPage;

public class PlaceOrderTest {
	
	WebDriver driver;
	LoginPage loginPage;
	ProductListPage listPage;
	CartPage cartPage;
	CheckoutPage chkoutPage;
	
	public PlaceOrderTest() {
		TestBase.initDriver();
		driver = TestBase.getDriver();
		loginPage = new LoginPage(driver);
		listPage = new ProductListPage(driver);
		cartPage = new CartPage(driver);
		chkoutPage = new CheckoutPage(driver);
		
	}

	@BeforeTest
	public void setup() {
		TestBase.openUrl("https://www.saucedemo.com/");
		loginPage.loginIntoApp("standard_user", "secret_sauce");
	}

  @Test
  public void addItem() {
	  listPage.addToCart();
	  listPage.viewCart();
	  Assert.assertTrue(cartPage.isItemAdded());
	 }
    
  @Test
  public void checkoutItems() {
	  cartPage.checkoutItems();
	  chkoutPage.provideDetails("TestUser", "One", "324343");
	  chkoutPage.checkoutOrder();
	  Assert.assertTrue(chkoutPage.isOrderSuccess());
	 }
  
  @AfterTest
  public void tearDown() {
//	  TestBase.tearDown();
  }
}
