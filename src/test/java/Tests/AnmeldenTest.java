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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
public class AnmeldenTest {
	
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
	 *Description : Login is scenario for check Logging functionality of Smava.de web application
	 * 1)  Open Smava.de
	 * 2)  click on ANMELDEN link
	 * 3)  Enter Username and Password
	 * 4)  Click on Submit button 
	 * 5)  Verify Label and error message due to invalid entry
	 *Created by : Yagnesh Bhatt
	 *Created on : 6th May 2018
	 *Modified on : 6th May 2018
	 *Parameters :  No Parameters
	 *********************************************************/
	
	@Test
	public void Login() throws InterruptedException {
		System.out.println("************************Test Case Name : Login Started***************************");
		driver.navigate().to(appURL);
		System.out.println(appURL + " Open in browser" );
		ResultPagesCheck.VerifyPageTitle(driver, "Page_title_MainPage");
		System.out.println("Verified Page title" );
		driver.manage().timeouts().implicitlyWait(waitTime*10, TimeUnit.SECONDS);	
		driver.manage().window().maximize();
		ResultPagesCheck.checkPageIsReady("SMAVA Home page",driver);
		System.out.println("Verified Page load" );
		LoanSelectionLandingPagesObjects.link_commonFunction(driver, "LNK_TEXT_Login").click();
		System.out.println("Clicked on Linked" );
		LoanSelectionLandingPagesObjects.TXT_Comman(driver, "TXT_username").clear();
		LoanSelectionLandingPagesObjects.TXT_Comman(driver, "TXT_username").sendKeys(Keys.chord(PR.readProperty("username")));
		String seenText = LoanSelectionLandingPagesObjects.TXT_Comman(driver, "TXT_username").getAttribute("value");
	    assertThat(seenText, equalTo(PR.readProperty("username")));
		LoanSelectionLandingPagesObjects.TXT_Comman(driver, "TXT_password").sendKeys(Keys.chord(PR.readProperty("password")));
		seenText = LoanSelectionLandingPagesObjects.TXT_Comman(driver, "TXT_password").getAttribute("value");
	    assertThat(seenText, equalTo(PR.readProperty("password")));
		System.out.println("Entered Login details" );
		LoanSelectionLandingPagesObjects.Login_Submit_BTN(driver, "BTN_Login");
		ResultPagesCheck.verifyLabelText(driver, "LBL_Header", "Bitte loggen Sie sich ein");
		ResultPagesCheck.verifyLabelText(driver, "LBL_Error_msg", "Ihre Angaben zum Einloggen sind ungültig. Bitte versuchen Sie es erneut. Bitte beachten Sie, dass Ihr Zugang bei 3 Fehlversuchen von uns vorläufig gesperrt wird.");

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
		System.out.println("************************Test Case Name : Login End***************************");
	}


}
