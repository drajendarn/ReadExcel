package Week6.marathon3.testng;
import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

/*To set timeOut in DOM - setTimeout(function(){debugger},5000) */

public class SalesForceCreateOpportunity extends CommonSpecificMethodsMarathon3 {

	
	@BeforeTest
	public void getFilepath()
	{
		filepath="SalesForceCreateOpportunityData";
	}
	
	@Test(dataProvider="fetchData")
    public void createOpportunity(String oppName,String amount) {
        
		
       //select toggle
        driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
        //View All
        driver.findElement(By.xpath("//button[text()='View All']")).click();
        //Click Sales
        driver.findElement(By.xpath("//p[text()='Sales']/ancestor::a")).click();
       // View Oppourtunities
        driver.findElement(By.xpath("//a[text()='View Opportunities']")).click();
        driver.findElement(By.xpath("//a[@title='New']")).click();
        
        //Oppourtunity name
        driver.findElement(By.xpath("//input[@name='Name']")).sendKeys(oppName);//oppname
        
        //Amount
        WebElement amountField = driver.findElement(By.xpath("//input[@name='Amount']"));
        
   
        amountField.sendKeys(amount);//amount
        
        //closedate
        //driver.findElement(By.xpath("//input[@name='CloseDate']")).sendKeys("28/03/2025");
        driver.findElement(By.xpath("//input[@name='CloseDate']")).click();
       
        driver.findElement(By.xpath("//button[@name='today']")).click();
        //stage
        JavascriptExecutor js=(JavascriptExecutor) driver;
        WebElement stage = driver.findElement(By.xpath("//label[text()='Stage']//following::div"));
		js.executeScript("arguments[0].scrollIntoView(true);", stage);
		stage.click();
        //driver.executeScript("arguments[0].click()",ownershipDD);
        WebElement stageOption = driver.findElement(By.xpath("//span[text()='Needs Analysis']//parent::span[@class='slds-media__body']"));
        //js.executeScript("arguments[0].scrollIntoView(true);", stageOption);
        stageOption.click();
        driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
        String toastMessage = driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']")).getText();
        System.out.println(toastMessage);
        Assert.assertTrue(toastMessage.contains("Opportunity \"Salesforce Automation by Test\" was created."),"Verify the Account name");
    }

}
