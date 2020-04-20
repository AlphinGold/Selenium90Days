package makemytrip;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class MakeMyTrip {

	public static void main(String[] args) throws InterruptedException {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		
		//1) Go to https://www.makemytrip.com/
		
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//2) Click Hotels
		
		driver.findElementByXPath("//a[@class='makeFlex hrtlCenter column']").click();
		Thread.sleep(1000);
		
		//3) Enter city as Goa, and choose Goa, India
		
		driver.findElement(By.xpath("//input[@class='hsw_inputField font30 lineHeight36 latoBlack']")).click();
		driver.findElementByXPath("(//p[@class='locusLabel appendBottom5'])[6]").click();
		System.out.println("Place: GOA");
		
		//4) Enter Check in date as Next month 15th (May 15) and Check out as start date+5
		
		driver.findElementByXPath("(//div[text()='15'])[2]").click();
		driver.findElementByXPath("(//div[text()='19'])[2]").click();
		System.out.println("Booking Date: 15May - 19May");
		
		//5) Click on ROOMS & GUESTS and click 2 Adults and one Children(age 11). Click Apply Button.

		driver.findElement(By.xpath("//input[@class='hsw_inputField guests font20']")).click();
		driver.findElement(By.xpath("//li[@data-cy='adults-2']")).click();
		System.out.println("Adults : 2");
		driver.findElement(By.xpath("//li[@data-cy='children-1']")).click();
		System.out.println("Children : 1");
		WebElement webElement = driver.findElementByXPath("//select[@data-cy='childAge-0']");
		Select dropDown = new Select(webElement);
		dropDown.selectByIndex(10);
		System.out.println("Child Age : 12");
		driver.findElementByXPath("(//button[@type='button'])[2]").click();
		
		//6) Click Search button
		
		driver.findElementById("hsw_search_button").click();
		
		//7) Select locality as Baga
		
		driver.findElementByXPath("//div[@class='mmBackdrop wholeBlack']").click();
		driver.findElementByXPath("//label[text()='Baga']").click();
		System.out.println("Location : Bage");
		Thread.sleep(3000);
		
		//8) Select 5 start in Star Category under Select Filters 
		 
		driver.findElementByXPath("//ul[@class='filterList']");
		driver.findElementByXPath("//label[text()='5 Star']").click();
		System.out.println("Rating : 5*****");
		
		//9) Click on the first resulting hotel and go to the new window
		
		driver.findElementByXPath("(//img[@alt='hotelImg'])[1]").click();
		Set<String> winset = driver.getWindowHandles();
		List<String> winlis = new ArrayList <String>(winset);
		driver.switchTo().window(winlis.get(1)).getTitle();
		System.out.println(driver.getTitle());
		
		//10) Print the Hotel Name 
		
		String str = driver.findElementById("detpg_hotel_name").getText();		
		System.out.println("Hotel Name : " +str);
		
		//11) Click MORE OPTIONS link and Select 3Months plan and close
		
		driver.findElementByXPath("//span[text()='MORE OPTIONS']").click();
		System.out.println("More Options");
		driver.findElementByXPath("(//span[text()='SELECT'])[1]").click();
		System.out.println("Plan : 3 Months Plan");
		driver.findElementByClassName("close").click();
		
		//12) Click on BOOK THIS NOW
		
		driver.findElementByLinkText("BOOK THIS NOW").click();
		System.out.println("BOOKED");

		//13) Print the Total Payable amount
		
		String amount = driver.findElementById("revpg_total_payable_amt").getText();
		System.out.println("Total Amount : "+amount);
		
		//14) Close the browser
		
		driver.quit();	
		
	
	}

}
