package Week6.marathon3.testng;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class CommonSpecificMethodsMarathon3 {
	
	ChromeOptions options = new ChromeOptions();
	public static ChromeDriver driver ;
	public String filepath;
	
	@Parameters({"url","username","password"})
	@BeforeMethod
	public void precondition(String url,String username,String password)
	{
		options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get(url);
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("Login")).click();
	}
	
	@AfterMethod
	public void postcondition()
	{
		driver.quit();
	}
	@DataProvider(name="fetchData")
	public String[][] readDataFromExcel() throws IOException
	{
		return DatalibrarySalesForce.excelIntegration(filepath);
	}
	

}
