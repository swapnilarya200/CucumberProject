//Question15 : E-commerce Product Purchase
package stepDefinitions;


import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class LoginSteps {
	WebDriver driver;
	@Given("I am logged into the application")
	public void i_am_logged_into_the_application() throws InterruptedException {
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		Thread.sleep(2000);
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		Thread.sleep(2000);
		driver.findElement(By.id("login-button")).click();
		Thread.sleep(2000);
	
	}
	 @When("I apply the {string} filter")
	 public void i_apply_the_filter(String filterOption) throws InterruptedException {
	     WebElement filterDropdown = driver.findElement(By.className("product_sort_container"));
	     Select select = new Select(filterDropdown);
	     select.selectByVisibleText(filterOption);
	     Thread.sleep(3000);
	}
	 @Then("I should see the products sorted by price in ascending order")
	    public void i_should_see_the_products_sorted_by_price_in_ascending_order() {
	        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));

	        List<Double> prices = priceElements.stream()
	                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
	                .collect(Collectors.toList());

	        List<Double> sortedPrices = prices.stream().sorted().collect(Collectors.toList());

	        Assert.assertEquals(prices, sortedPrices, "Products are NOT sorted correctly!");
	        driver.close();
	    }
	
}
