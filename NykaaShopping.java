package nykaaShopping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class NykaaShopping {

	public static void main(String[] args) throws InterruptedException {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		
		//1) Go to https://www.nykaa.com/
		
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//2) Mouseover on Brands and Mouseover on Popular
		
		WebElement element=driver.findElementByXPath("//a[text()='brands']");
		System.out.println("Brands");
		Actions builder=new Actions(driver);
		builder.moveToElement(element).build().perform();
		WebElement element1=driver.findElementByXPath("//a[text()='Popular']");
		System.out.println("Popular");
		Actions builder1=new Actions(driver);
		builder1.moveToElement(element1).build().perform();
		
		//3) Click L'Oreal Paris
		
		driver.findElementByXPath("//*[@id=\"brandCont_Popular\"]/ul/li[5]/a/img").click();
		Thread.sleep(3000);
		
		//4) Go to the newly opened window and check the title contains L'Oreal Paris
		
		Set<String> winset = driver.getWindowHandles();
		List<String> winlis = new ArrayList <String>(winset);
		driver.switchTo().window(winlis.get(1)).getTitle();
		System.out.println(driver.getTitle());
		
		if(driver.getTitle().contains("L'Oreal Paris")){
			System.out.println("Title contains L'Oreal Paris");
		}
			else {
				System.out.println("Title doesnot contains L'Oreal Paris");
			}
		
		//5) Click sort By and select customer top rated
		
		driver.findElementByXPath("//span[@class='pull-left']").click();
		driver.findElementByXPath("//span[text()='customer top rated']").click();
		Thread.sleep(3000);
		
		//6) Click Category and click Shampoo
		
		driver.findElementByClassName("expand-icon").click();
		driver.findElementByXPath("//label[@for='chk_Shampoo_undefined']").click();
		driver.findElementByXPath("(//label[@for='chk_Shampoo_undefined'])[2]").click();
		
		//7) check whether the Filter is applied with Shampoo
		
		String Shampoo = driver.findElementByXPath("//ul[@class='pull-left applied-filter-lists']").getText();
		if(Shampoo.contains("Shampoo")) {
			System.out.println("The Filter is applied with Shampoo");
		}
		
		//8) Click on L'Oreal Paris Colour Protect Shampoo
		
		driver.findElementByXPath("//h2[contains(@title,'Paris Colour Protect Shampoo')]").click();
		
		//9) GO to the new window and select size as 175ml
		
		Set<String> winset1 = driver.getWindowHandles();
		List<String> winlis1 = new ArrayList <String>(winset1);
		driver.switchTo().window(winlis1.get(2)).getTitle();
		System.out.println(driver.getTitle());
		driver.findElementByXPath("//span[text()='175ml']").click();
		
		//10) Print the MRP of the product
		
		String str = driver.findElementByXPath("(//span[@class='post-card__content-price-offer'][1])").getText();
		String text = str.replaceAll("\\D","");
		int MRP = Integer.parseInt(text);
		System.out.println("The MRP of the Product is :"+MRP);
			
		//11) Click on ADD to BAG
			
		driver.findElementByXPath("//button[@class='combo-add-to-btn prdt-des-btn js--toggle-sbag nk-btn nk-btn-rnd atc-simple m-content__product-list__cart-btn  ']").click();
			
		//12) Go to Shopping Bag 
		
		driver.findElementByClassName("AddBagIcon").click();
		
		//13) Print the Grand Total amount
		
		String str1 = driver.findElementByXPath("(//div[@class='value medium-strong'])").getText();
		String text1 = str1.replaceAll("\\D","");
		int GrandTotal = Integer.parseInt(text1);
		System.out.println("The Grand Total Amount is :"+GrandTotal);
		
		//14) Click Proceed
		
		driver.findElementByXPath("//button[@class='btn full fill no-radius proceed ']").click();
		
		//15) Click on Continue as Guest
		
		driver.findElementByXPath("//button[@class='btn full big']").click();
		
		//16) Print the warning message (delay in shipment)
		
		
		String str2 = driver.findElementByXPath("//div[@class='message']").getText();
		System.out.println("Warning Message(delay in shipment) : "+str2);
				
		//17) Close all windows

		driver.quit();	
			
			
	}
}
	

