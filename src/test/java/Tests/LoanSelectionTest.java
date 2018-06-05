package Tests;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Pages.LoanSelectionLandingPagesObjects;
import Pages.ResultPagesCheck;
import UtilExtra.propertyReader;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class LoanSelectionTest {
	
	
	public propertyReader PR = new propertyReader();
	int waitTime = Integer.parseInt(PR.readProperty("implicitWaitInSeconds")); 
	String appURL = PR.readProperty("url");
	String BrowserName;	
	private  WebDriver driver;
	
	/********************************************************
	 *Description : Setup is initial process of program. Browser initialization where define browsers based on properties value 
	 *Created by : Yagnesh Bhatt
	 *Created on : 6th May 2018
	 *Modified on : 6th May 2018
	 *Parameters :  No Parameters
	 *********************************************************/

	
	@BeforeClass
	public void testSetUp() {
		driver= null;
		BrowserName = PR.readProperty("browser");
		
		if  (BrowserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty(PR.readProperty("chromewebdriverString"),PR.readProperty("linuxChromeDriver")+"/chromedriver.exe");
			driver = new ChromeDriver();
			
		}
		else
		{
			driver = new FirefoxDriver();
		}
		
	}

	/********************************************************
	 *Description : LoanSelection is First Scenario. in this test case, below are the steps
	 * 1)  Open Smava.de
	 * 2)  click on Kredit link
	 * 3)  Enter Loan amount in online kredit page & click on Zum button
	 * 4)  Enter Duration and Use field entry
	 * 5)  Click on Weiter
	 * 6)  Verify PageLoad of next landing page
	 *Created by : Yagnesh Bhatt
	 *Created on : 6th May 2018
	 *Modified on : 6th May 2018
	 *Parameters :  No Parameters
	 *********************************************************/
	@Test
	public void LoanSelection() throws InterruptedException 
	{
		System.out.println("************************Test Case Name : Loan Selection Started***************************");
		driver.navigate().to(appURL);
		driver.manage().window().maximize();
		System.out.println(appURL + " Open in browser" );
		ResultPagesCheck.VerifyPageTitle(driver, "Page_title_MainPage");
		System.out.println("Verified Page title" );
		driver.manage().timeouts().implicitlyWait(waitTime*10, TimeUnit.SECONDS);
		ResultPagesCheck.checkPageIsReady("SMAVA Home page",driver);
		System.out.println("Verified Page load" );
		//**************Kredit link click
		LoanSelectionLandingPagesObjects.link_commonFunction(driver,"link_hyperlink_Creadit_Text").click();
		System.out.println("Click on Kredit Link" );
		ResultPagesCheck.checkPageIsReady("Kredit Online page",driver);
		System.out.println("Verified Page load" );
		//*********************Amount need to be entered
		LoanSelectionLandingPagesObjects.Text_Amount(driver).clear();
		LoanSelectionLandingPagesObjects.Text_Amount(driver).sendKeys(Keys.chord(PR.readProperty("AmountLoanSelection")));
		LoanSelectionLandingPagesObjects.Text_Amount(driver).sendKeys(Keys.ENTER);
		String seenText = LoanSelectionLandingPagesObjects.Text_Amount(driver).getAttribute("value");
	    assertThat(seenText, equalTo(PR.readProperty("AmountLoanSelection")));
		System.out.println("Entered Amount" );
		//******************Click next for Main loan page
		LoanSelectionLandingPagesObjects.link_commonFunction(driver,"link_zum").click();
		ResultPagesCheck.checkPageIsReady("Kredit page",driver);
		System.out.println("Verified Page load" );
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
		//***************Enter month
		LoanSelectionLandingPagesObjects.Select_dropdown(driver, "Loan_duration_property", PR.readProperty("MonthLoanSelection"));
		System.out.println("Select Months" );
		//***************Enter use of loan
		LoanSelectionLandingPagesObjects.Select_dropdown(driver, "Loan_category_property", PR.readProperty("UseLonaSelection"));
		System.out.println("Select Category" );
		//Click on Weiter
		LoanSelectionLandingPagesObjects.BTN_CommonFunction(driver, "weiter_btn_KreditPage").click();
		System.out.println("Clicked on Weiter");
		ResultPagesCheck.checkPageIsReady("Personal data entry page",driver);
		System.out.println("Verified Page load" );
		driver.manage().timeouts().implicitlyWait(waitTime*100, TimeUnit.SECONDS);
}
	
	/********************************************************
	 *Description : In Tear Down function, Close selenium webdriver.
	 *Created by : Yagnesh Bhatt
	 *Created on : 6th May 2018
	 *Modified on : 6th May 2018
	 *Parameters :  No Parameters
	 *********************************************************/
	@AfterClass
	public void tearDown() {
		
		driver.quit();
		System.out.println("************************Test Case Name : Loan Selection End***************************");
	}
	
		
}

	
