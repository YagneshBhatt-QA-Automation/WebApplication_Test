package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import UtilExtra.propertyReader;

public class ResultPagesCheck {
	
	static boolean result=false;
	public static propertyReader PRResult = new propertyReader();
	
	/********************************************************
	 *Description : "VerifyPageTitle" function for verification of any page title  
	 *Created by : Yagnesh Bhatt
	 *Created on : 6th May 2018
	 *Modified on : 6th May 2018
	 *Parameters :  No Parameters
	 *********************************************************/

	
	public static void VerifyPageTitle(WebDriver d, String PropertyField)
	{
		String getTitle = d.getTitle();
		result = getTitle.equalsIgnoreCase(PRResult.readProperty(PropertyField));
		Assert.assertEquals(getTitle, PRResult.readProperty(PropertyField));
		assertThat(getTitle, equalTo(PRResult.readProperty(PropertyField)));
		if (result)
		System.out.println("Page title as expected. Page Title is : "+getTitle);
		else
		System.out.println("Page title as not expected. Page Title is : "+getTitle);	
	}
	
	/********************************************************
	 *Description : "checkPageIsReady" function for check page loaded or in progress.  
	 *Created by : Yagnesh Bhatt
	 *Created on : 6th May 2018
	 *Modified on : 6th May 2018
	 *Parameters :  No Parameters
	 *********************************************************/

	
	public static void checkPageIsReady(String pagename, WebDriver driver) {
		  
		  JavascriptExecutor js = (JavascriptExecutor)driver;
		  //Initially bellow given if condition will check ready state of page.
		  if (js.executeScript("return document.readyState").toString().equals("complete")){ 
		   System.out.println("Page Is loaded. pagename is "+pagename);
		   return; 
		  } 
		  
		  //This loop will rotate for 25 times to check If page Is ready after every 1 second.
		  //You can replace your value with 25 If you wants to Increase or decrease wait time.
		  for (int i=0; i<25; i++){ 
		   try {
		    Thread.sleep(1000);
		    }catch (InterruptedException e) {} 
		   //To check page ready state.
		   if (js.executeScript("return document.readyState").toString().equals("complete")){ 
		    break; 
		   }   
		  }
		 }
	
	/********************************************************
	 *Description : "verifyLabelText" function for check label text  
	 *Created by : Yagnesh Bhatt
	 *Created on : 6th May 2018
	 *Modified on : 6th May 2018
	 *Parameters :  No Parameters
	 *********************************************************/
	public static void verifyLabelText(WebDriver d, String PrpFld,String ExpText)
	{
		
		WebElement HeaderLabel = d.findElement(By.xpath(PRResult.readProperty(PrpFld)));
		String lblResult = HeaderLabel.getText();
		assertThat(lblResult, equalTo(ExpText));		
		if (lblResult.equalsIgnoreCase(ExpText))
		{
			System.out.println("Label exist as expected. Label is"+lblResult);
		}
		else
		{
			System.out.println("Label is not exist as expected. Label is"+lblResult);
			
		}
		
	}
	
	

}
