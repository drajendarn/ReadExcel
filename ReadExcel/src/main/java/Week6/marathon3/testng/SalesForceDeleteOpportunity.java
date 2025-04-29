package Week6.marathon3.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SalesForceDeleteOpportunity extends CommonSpecificMethodsMarathon3 {
	@Test(dependsOnMethods = {"Week6.marathon3.testng.SalesForceCreateOpportunity.createOpportunity"})
	public void deleteOpportunity() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// select toggle
		//Thread.sleep(2000);
		System.out.println("test delete");
		//driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		WebElement toggleElement = driver.findElement(By.xpath("//div[@class='slds-icon-waffle']"));
		js.executeScript("arguments[0].click();", toggleElement);
		// View All
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		// Click Sales
		//driver.findElement(By.xpath("//p[text()='Sales']/ancestor::a")).click();
		WebElement salesLink = driver.findElement(By.xpath("//p[text()='Sales']/ancestor::a"));
		js.executeScript("arguments[0].scrollIntoView(true);", salesLink);
		js.executeScript("arguments[0].click();", salesLink);
		//salesLink.click();
		// View Oppourtunities
		driver.findElement(By.xpath("//a[text()='View Opportunities']")).click();

		driver.findElement(By.xpath("//input[@placeholder='Search this list...']"))
				.sendKeys("Salesforce Automation by Test" + Keys.ENTER);

		// To get the current cunt

		Thread.sleep(4000);
		String noOfItems = driver.findElement(By.xpath("//span[@aria-label='Recently Viewed']")).getText();
		System.out.println(noOfItems);
		String[] s = noOfItems.split(" ");
		int countBeforeDelete = Integer.parseInt(s[0]);

		// Click on the Dropdown icon and select 'Delete'.
		WebElement result = driver.findElement(By.xpath("//table/tbody/tr[1]/td[8]"));

		
		js.executeScript("arguments[0].scrollIntoView(true);", result);
		result.click();
		driver.findElement(By.xpath("//a[@title='Delete']")).click();

		driver.findElement(By.xpath("//button[@title='Delete']")).click();

		// Refresh the page and search text again
		/*
		 * Thread.sleep(2000); driver.navigate().refresh();
		 * driver.findElement(By.xpath("//input[@placeholder='Search this list...']"))
		 * .sendKeys("Salesforce Automation by Test" + Keys.ENTER);
		 */
		// To get the count after delete
		Thread.sleep(2000);
		String noOfItemsafterDeete = driver.findElement(By.xpath("//span[@aria-label='Recently Viewed']")).getText();
		System.out.println(noOfItemsafterDeete);
		s = noOfItemsafterDeete.split(" ");
		int countAfterDelete = Integer.parseInt(s[0]);

		Assert.assertTrue(countBeforeDelete == (countAfterDelete + 1), "Verify the account has deleted");

	}

}
