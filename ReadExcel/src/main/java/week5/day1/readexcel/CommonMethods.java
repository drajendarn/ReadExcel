package week5.day1.readexcel;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;


public class CommonMethods {
	WebDriver driver;
	public String filepath; // declaring the global variable to mention the dataprovider filepath

	//Parameterization
	@Parameters({ "url", "browser","username","password" })
	@BeforeMethod

	public void preCondition(String url, String browser,String username,String password) {
		switch (browser) {
		case "chrome":
			driver = new ChromeDriver();
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;

		default:
			driver = new ChromeDriver();
			break;
		}

		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
	}
	

	@AfterMethod
	public void postCondition() {
		driver.quit();
	}
	
	@DataProvider(name="fetchData")

	public String[][] setupData() throws IOException {
		
		
		return Datalibrary.excelIntegration(filepath);
	}
}
