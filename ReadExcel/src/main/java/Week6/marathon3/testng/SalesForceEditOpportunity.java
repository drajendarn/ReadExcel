
package Week6.marathon3.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Week6.marathon3.testng.SalesForceCreateOpportunity;

import java.time.Duration;

public class SalesForceEditOpportunity extends CommonSpecificMethodsMarathon3 {

	@BeforeTest
	public void getFilepath()
	{
		filepath="SalesForceEditOpportunityData";
	}
	@Test(dataProvider="fetchData",dependsOnMethods = {"Week6.marathon3.testng.SalesForceCreateOpportunity.createOpportunity"})
	//@Test(dataProvider="fetchData")
	public void editOpportunity(String oppName,String des) throws InterruptedException {

		// select toggle
		//Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		// View All
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		// Click Sales
		driver.findElement(By.xpath("//p[text()='Sales']/ancestor::a")).click();
		// View Oppourtunities
		driver.findElement(By.xpath("//a[text()='View Opportunities']")).click();

		driver.findElement(By.xpath("//input[@placeholder='Search this list...']"))
				.sendKeys(oppName + Keys.ENTER);

		Thread.sleep(2000);
		WebElement editopp = driver.findElement(By.xpath("//table/tbody/tr[1]/td[8]"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", editopp);
		editopp.click();
		driver.findElement(By.xpath("//a[@title='Edit']")).click();

		// edit close date
		driver.findElement(By.xpath("//input[@name='CloseDate']")).click();

		driver.findElement(By.xpath("//span[text()='29']")).click();

		// Select 'Stage' as 'Perception Analysis'.

		WebElement stage = driver.findElement(By.xpath("//label[text()='Stage']//following::div"));
		js.executeScript("arguments[0].scrollIntoView(true);", stage);
		stage.click();
		// driver.executeScript("arguments[0].click()",ownershipDD);
		WebElement stageOption = driver
				.findElement(By.xpath("//span[text()='Perception Analysis']//parent::span[@class='slds-media__body']"));
		// js.executeScript("arguments[0].scrollIntoView(true);", stageOption);
		stageOption.click();

		// Select 'Delivery/Installation Status' as 'In Progress'.
		WebElement delivery = driver
				.findElement(By.xpath("//label[text()='Delivery/Installation Status']//following::div"));
		js.executeScript("arguments[0].scrollIntoView(true);", delivery);
		delivery.click();
		WebElement deliveryOption = driver
				.findElement(By.xpath("//span[text()='In progress']//parent::span[@class='slds-media__body']"));
		// js.executeScript("arguments[0].scrollIntoView(true);", stageOption);
		deliveryOption.click();

		// Enter Description as 'SalesForce'.

		driver.findElement(By.xpath("//textarea[@class='slds-textarea']")).sendKeys(des);
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();

		Thread.sleep(1000);
		String stageValue = driver.findElement(By.xpath("//table/tbody/tr[1]/td[5]")).getText();
		System.out.println(stageValue);
		Assert.assertTrue(stageValue.contains("Perception Analysi"), "Stage Value Not matched");

	}

}
