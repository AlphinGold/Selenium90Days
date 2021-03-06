package myntraShopping;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class MyntraShopping {

	public static void main(String[] args) throws InterruptedException {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");			
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		
		//1) Open https://www.myntra.com/
		
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//2) Mouse over on WOMEN (Actions -> moveToElement
		
		WebElement element1=driver.findElementByXPath("//a[text()='Women']");
		System.out.println("Women");
		Actions builder=new Actions(driver);
		builder.moveToElement(element1).build().perform();
		
		//3) Click Jackets & Coats

		driver.findElementByLinkText("Jackets & Coats").click();
		System.out.println("Jackets & Coats");
		
		/*4) Find the total count of item (top) -> getText -> String

	 	String str = driver.findElementByClassName("title-count").getText();
	 	split, 
	 	String text = str.replaceAll("\\D","") -> String
	 	Integer.parseInt(text) -> int
		 */
		
		String str = driver.findElementByClassName("title-count").getText();
		String text = str.replaceAll("\\D","");
		int Totalcount= Integer.parseInt(text);
		System.out.println("The total count of Jackets & coats : "+Totalcount);
		String str1 = driver.findElementByXPath("(//span[@class='categories-num'])[1]").getText();
		String JacCount = str1.replaceAll("\\D","");
		int JacketCount = Integer.parseInt(JacCount);
		System.out.println("JacketCount :"+JacketCount);
		String str2 = driver.findElementByXPath("(//span[@class='categories-num'])[2]").getText();
		String CoCount = str2.replaceAll("\\D","");
		int CoatCount = Integer.parseInt(CoCount);
		System.out.println("CoatCount :"+CoatCount);
		int JacketandCoatCount = JacketCount + CoatCount;
		System.out.println("Sum od Jacket & Coat :"+JacketandCoatCount);
		
		//5) Validate the sum of categories count matches
		
		if(JacketandCoatCount == Totalcount)
		{
			System.out.println("Count matched with total count and sum of count of Jacket and Coat");
		}
		else
		{
			System.out.println("Count is not matched with total count and sum of count of Jacket and Coat");
		}
		
		//6) Check Coats
		
		driver.findElementByXPath("(//div[@class='common-checkboxIndicator'])[2]").click();
		
		//7) Click + More option under BRAND
		
		driver.findElementByClassName("brand-more").click();
		
		//8) Type MANGO and click checkbox
		
		driver.findElementByClassName("FilterDirectory-searchInput").sendKeys("MANGO");		
		driver.findElementByXPath("//span[@class='FilterDirectory-count']/following-sibling::div").click();
		
		//9) Close the pop-up x
		
		driver.findElementByXPath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();
		Thread.sleep(1000);
		
		/*10) Confirm all the Coats are of brand MANGO
    		findElements (brand) -> List<WebElement> 
    		foreach -> getText of each brand 
    		compare > if(condition)
		 */
		
		driver.findElement(By.className("results-base"));
		List<WebElement>brandList = driver.findElements(By.tagName("h3"));
		for(WebElement eleBrand : brandList) {
			String name = eleBrand.getText();
			if(name.equalsIgnoreCase("MANGO")){
				System.out.println("The brand of this coat is MANGO");
			}
		}		
		Thread.sleep(1000);
		
		//11) Sort by Better Discount
		
		WebElement element2= driver.findElementByClassName("sort-sortBy");
		System.out.println("sortBy");
		Actions builder1=new Actions(driver);
		builder1.moveToElement(element2).build().perform();
		driver.findElementByXPath("//label[text()='Better Discount']").click();
		Thread.sleep(3000);
		
		/*12) Find the price of first displayed item
     		findElements (price) -> List<WebElement> 
     		get(0) -> WebElement -> getText -> String -> int
		 */
		
		List<WebElement> price = driver.findElementsByXPath("(//ul[@class='results-base']//span[@class='product-discountedPrice'])[1]");
		for(WebElement elePrice : price) {
			String price1 = elePrice.getText();
			String Price2 = price1.replaceAll("\\D", "");
			int firstPrice = Integer.parseInt(Price2);
			System.out.println("The price of the 1st displayed item is :"+firstPrice);
		
		//13) Mouse over on size of the first item
			
		Actions action=new Actions(driver);	
		WebElement product = driver.findElement(By.className("product-base"));
		action.moveToElement(product).perform();
		Thread.sleep(2000);
		WebElement size= driver.findElement(By.xpath("//ul[@class='results-base']/li[1]/a/div/h4/span[4]"));
		action.moveToElement(size).perform();
		
		//14) Click on WishList Now
		
		driver.findElementByXPath("//span[@class='product-actionsButton product-wishlist product-prelaunchBtn']").click();
		System.out.println("Title :"+driver.getTitle());
		}
		
		//15) Close Browser
		
		driver.close();

	}
	
}


