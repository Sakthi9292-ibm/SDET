package sdet_Selenium_Project;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.SendKeysAction;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;

public class LMS_Test {
	
	WebDriver driver ;
	
	
	
	@BeforeMethod
	public void logintolms() {
		
		System.setProperty("webdriver.chrome.driver","src/main/java/chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://alchemy.hguy.co/lms");
		
	}
	
	@AfterMethod
	public void close() throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File Source = ts.getScreenshotAs(OutputType.FILE);

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String dy = dateFormat.format(date);

		String Filename = "./Screenshots/Screen" + dy + ".png";

		FileUtils.copyFile(Source, new File(Filename));

		driver.close();

	}
	
	
	@Test(priority= 9)
	public void VerfiywebsiteTitle() {
		
		String title = driver.getTitle();
		Assert.assertEquals(title, "Alchemy LMS – An LMS Application");		
		
	}
	
	
	@Test(priority= 8)
	public void Verifywebsiteheading() {
		
		String heading = driver.findElement(By.className("uagb-ifb-title")).getText();
		
		Assert.assertEquals(heading, "Learn from Industry Experts");
				
	
		
		
		
	}
	
	@Test(priority= 7)
	public void verifyfirsttitle() {
		
		String firsttitle=driver.findElement(By.xpath("(//*[@class='uagb-ifb-title'])[2]")).getText();
		
		Assert.assertEquals(firsttitle, "Actionable Training");
		
			
		
	}
	
	
	@Test(priority= 6)
	public void verify_Second_popular() {
		
		String secondpopular = driver.findElement(By.xpath("(//*[@class='entry-title'])[2]")).getText();
		
		Assert.assertEquals(secondpopular, "Email Marketing Strategies");
		
		
		
	}
	
	@Test(priority= 5)
	public void Navigate_to_Other_Page() {
		
		driver.findElement(By.xpath("//*[@id='menu-item-1507']")).click();
		
		assertEquals(driver.getTitle(), "My Account – Alchemy LMS");
		
		
			
		
	}
	
	
	@Test(dependsOnMethods = { "Navigate_to_Other_Page" },priority= 4)
	public void Logintoaccount() throws InterruptedException {

		driver.findElement(By.xpath("//*[@id='menu-item-1507']")).click();

		assertEquals(driver.getTitle(), "My Account – Alchemy LMS");
		
		//*[@id="menu-item-1507"]/a

		driver.findElement(By.xpath("//*[@id='uagb-column-e9d225cb-cee9-4e02-a12d-073d5f051e91']/div[2]/div[2]/a")).click();

		//*[@id="uagb-column-e9d225cb-cee9-4e02-a12d-073d5f051e91"]/div[2]/div[2]/a
		Thread.sleep(5000);
		
		driver.findElement(By.id("user_login")).sendKeys("root");
		driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
		
		driver.findElement(By.id("wp-submit")).click();

		String getusername = driver.findElement(By.xpath("//*[@id='wp-admin-bar-my-account']/a/span")).getText();

		assertEquals(getusername, "root");

		//driver.close();
	}
	
	@Test (priority= 3)
	public void countcourses() {
		
		driver.findElement(By.xpath("//*[@id='menu-item-1508']/a")).click();
		
		List<WebElement> courses = driver.findElements(By.xpath("//*[@class='entry-title']"));
		
		assertEquals(courses.size(), 3);	
		
	}
	
	@Test(priority=2)
	public void contact_admin() {
		
		driver.findElement(By.xpath("//*[@id='menu-item-1506']/a")).click();
		
		driver.findElement(By.id("wpforms-8-field_0")).sendKeys("Sakthi");
		
		driver.findElement(By.id("wpforms-8-field_1")).sendKeys("email@gmail.com");
		
		driver.findElement(By.id("wpforms-8-field_2")).sendKeys("Hi");
		
		driver.findElement(By.id("wpforms-submit-8")).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String message = driver.findElement(By.xpath("//*[@id='wpforms-confirmation-8']/p")).getText();
		
		System.out.println("Message after sending the message --> "+message);
		
		
	}	
	
	@Test(priority = 1)
	public void complete_lesson() throws InterruptedException {

		driver.findElement(By.xpath("//*[@id='menu-item-1507']")).click();

		assertEquals(driver.getTitle(), "My Account – Alchemy LMS");

		// *[@id="menu-item-1507"]/a

		driver.findElement(By.xpath("//*[@id='uagb-column-e9d225cb-cee9-4e02-a12d-073d5f051e91']/div[2]/div[2]/a"))
				.click();

		// *[@id="uagb-column-e9d225cb-cee9-4e02-a12d-073d5f051e91"]/div[2]/div[2]/a
		Thread.sleep(5000);

		driver.findElement(By.id("user_login")).sendKeys("root");
		driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");

		driver.findElement(By.id("wp-submit")).click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		driver.findElement(By.xpath("//*[@id='menu-item-1508']/a")).click();

		driver.findElement(By.xpath("//*[@id='post-69']/div[2]/p[2]/a")).click();

		assertEquals(driver.getTitle(), "Social Media Marketing – Alchemy LMS");

	}
	

}
