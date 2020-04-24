package hpstore;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class HpStore {

	public static void main(String[] args) throws InterruptedException {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		
		
		//1) Go to https://store.hp.com/in-en/
		
		driver.get("https://store.hp.com/in-en/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		try {
			driver.findElementByXPath("//span[contains(@class,'close-icon')]").click();
		} catch (Exception e) {
			System.out.println("Sign-Up Pop_Up not Available");
		}
		
		try {
			driver.findElementByXPath("//button[contains(@class,'banner-close-button')]").click();
		} catch (Exception e) {
			System.out.println("Cookie Option not Available");
		}
		
		//2) Mouse over on Laptops menu and click on Pavilion
		
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("//a[@id='ui-id-2']//span[text()='Laptops']")).build().perform();
		System.out.println("Laptops");
		driver.findElementByXPath("//a[@id='ui-id-43']//span[text()='Pavilion']").click();
		System.out.println("Pavilion");
		
		//3) Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7
		
		driver.findElementByXPath("(//span[text()='Processor'])[2]").click();
		System.out.println("Processor");
		driver.findElementByXPath("(//span[text()='Intel Core i7'])/parent::a").click();
		System.out.println("Intel Core i7");
		Thread.sleep(2000);
				
		//4) Hard Drive Capacity -->More than 1TB
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,550)");
		Thread.sleep(2000);
		driver.findElementByXPath("(//span[text()='More than 1 TB'])/parent::a").click();
		System.out.println("Hard Drive Capacity: More than 1TB");
		Thread.sleep(2000);
		
		//5) Select Sort By: Price: Low to High
		
		WebElement sort = driver.findElementById("sorter"); 
		Select asc = new Select(sort); 
		asc.selectByValue("price_asc");
		System.out.println("sortBy: Price: Low to High");
		Thread.sleep(3000);
		
		//6) Print the First resulting Product Name and Price
		
		String str = driver.findElementByXPath("(//a[@class='product-item-link'])[1]").getText();	
		System.out.println("Product Name : " +str);
		String str1 = driver.findElementByXPath("(//span[@class='price-wrapper '])[1]").getText();
		String text = str1.replaceAll("\\D","");
		int total = Integer.parseInt(text);
		System.out.println("Price : " +total);
		Thread.sleep(3000);
		
		//7) Click on Add to Cart
		
		driver.findElementByXPath("(//button[@class='action tocart primary'])[1]").click();
		System.out.println("Added to cart");
		Thread.sleep(3000);
		
		//8) Click on Shopping Cart icon --> Click on View and Edit Cart
		
		driver.findElementByXPath("//a[@class='action showcart']").click();
		driver.findElementByXPath("//a[@class='action primary viewcart']").click();
		Thread.sleep(3000); 
		
		//9) Check the Shipping Option --> Check availability at Pincode
		
		driver.findElementByName("pincode").sendKeys("600071"); 
		//Thread.sleep(2000); 
		driver.findElementByXPath("//button[text()='check']").click(); 
		System.out.println("Available to pincode : 600071");
		
		//10) Verify the order Total against the product price
		
		String eletotalamt = driver.findElementByXPath("//Strong[text()='Order Total']/ancestor::tr//span[@class='price']").getText();
		String num = eletotalamt.replaceAll("\\D", ""); 
		int orderTotal = Integer.parseInt(num); 
		Thread.sleep(3000);
		
		//11) Proceed to Checkout if Order Total and Product Price matches
		
		if(total == orderTotal) {
			System.out.println("Order Total and Total product price is matching");
			driver.findElementByXPath("(//span[text()='Proceed to Checkout'])[1]").click();
		} 
		else {
			System.out.println("Order Total and Total Product Price not matching");
		} 
		Thread.sleep(3000); 
		
		//12) Click on Place Order
		
				driver.findElementByXPath("//div[@class='place-order-primary']//span").click();
				System.out.println("Order Placed");
				
		//13) Capture the Error message and Print
				
				driver.findElementByXPath("//div[@class='message notice']/span").getText();
				
		//14) Close Browser
				
				driver.close();
			
	}

}
