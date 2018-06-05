package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import UtilExtra.propertyReader;


public class LoanSelectionLandingPagesObjects {
	
public static  propertyReader PRPOM = new propertyReader();	
static WebElement element;

/********************************************************
	 *Description : "link_commonFunction" function for all link which will return link as weblelement object 
	 *Created by : Yagnesh Bhatt
	 *Created on : 6th May 2018
	 *Modified on : 6th May 2018
	 *Parameters :  No Parameters
	 *********************************************************/
		
	public static WebElement link_commonFunction(WebDriver d, String LinkPropertyName)
	{
		element = d.findElement(By.linkText(PRPOM.readProperty(LinkPropertyName)));
		
		return element;
	}
	
	/********************************************************
   	 *Description : "Text_Amount" function for return element as amount textbox 
   	 *Created by : Yagnesh Bhatt
   	 *Created on : 6th May 2018
   	 *Modified on : 6th May 2018
   	 *Parameters :  No Parameters
   	 *********************************************************/
	
	public static WebElement Text_Amount(WebDriver d)
	{
		element = d.findElement(By.xpath(PRPOM.readProperty("TXT_Amount_Xpath")));
		return element;
	}
	
	/********************************************************
   	 *Description : "Select_dropdown" function for selecting dropdown value 
   	 *Created by : Yagnesh Bhatt
   	 *Created on : 6th May 2018
   	 *Modified on : 6th May 2018
   	 *Parameters :  No Parameters
   	 *********************************************************/
	public static void Select_dropdown(WebDriver d, String propertyfield, String Value)
	{
		element = d.findElement(By.id(PRPOM.readProperty(propertyfield)));
		List<WebElement> options = element.findElements(By.tagName("option"));
		for (WebElement option : options) {			
		if(Value.equals(option.getText().trim())){
		 option.click();
		}
		}
	}
	
	/********************************************************
   	 *Description : "BTN_CommonFunction" function for return button as webelement 
   	 *Created by : Yagnesh Bhatt
   	 *Created on : 6th May 2018
   	 *Modified on : 6th May 2018
   	 *Parameters :  No Parameters
   	 *********************************************************/
	public static WebElement BTN_CommonFunction(WebDriver d, String PrpFld)
	{
		element = d.findElement(By.id(PRPOM.readProperty(PrpFld)));
		return element;
	}
	/********************************************************
   	 *Description : "Login_Submit_BTN" function for click on submit button 
   	 *Created by : Yagnesh Bhatt
   	 *Created on : 6th May 2018
   	 *Modified on : 6th May 2018
   	 *Parameters :  No Parameters
   	 *********************************************************/
	public static void Login_Submit_BTN(WebDriver d, String prpFld)
	{
		
		List<WebElement> MultiBTN = d.findElements(By.xpath(PRPOM.readProperty(prpFld)));
		for (WebElement option : MultiBTN) {			
		if(option.isEnabled())
		{
			option.click();
			break;
		}
		}
	}
	/********************************************************
   	 *Description : "TXT_Comman" function for return Textbox object as Webelement 
   	 *Created by : Yagnesh Bhatt
   	 *Created on : 6th May 2018
   	 *Modified on : 6th May 2018
   	 *Parameters :  No Parameters
   	 *********************************************************/

	
	public static WebElement TXT_Comman(WebDriver d, String  prpFld)
	{
		element = d.findElement(By.id(PRPOM.readProperty(prpFld)));
		return element;
		
	}

}
