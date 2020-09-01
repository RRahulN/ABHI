package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.codoid.products.fillo.Connection;

import constants.PropertyConfigs;
import util.ConfigReader;
import util.ExcelRead;
import util.GenericMethods;
import util.WaitTime;

public class GSTNOnlineLoginPage  extends GenericMethods {
	
	@FindBy(xpath="//input[@id='username']")
	private WebElement userName;
	
	@FindBy(xpath="//input[@id='user_pass']")
	private WebElement PassWord;
	
	@FindBy(xpath="//input[@id='captcha']")
	private WebElement Captha;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement Submit;
	
	@FindBy(xpath="//input[@id='fo-user']")
	private List<WebElement> OTP;
	
	WebDriverWait wait;
	public GSTNOnlineLoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 30);	
	}
	
	public void openNewBrowserTab(WebDriver driver) {
		//openNewbrowserTab(driver);
	}
	
	public void loginToOnlinePortal(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
		//String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,"t");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.open('"+ConfigReader.getInstance().getValue(PropertyConfigs.GSTNOnlineLogin_Url)+"');");
		//Switch to tab
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_OnlineLoginPage", testCaseName,stepGroup);
		Reporter.log("<B>Login To Online Application</B>");
		System.out.println(driver.getTitle()+"------"+driver.getCurrentUrl());
		switch (dataRow.getProperty("GSTIN")) {
		case "26BMVPB7411GGZL":
			clearAndSenKeys(userName, dataRow.getProperty("Username"), "Username");
			Thread.sleep(WaitTime.low);		
			clearAndSenKeys(PassWord, dataRow.getProperty("Password"), "Password");
			Thread.sleep(WaitTime.low);		
			clearAndSenKeys(Captha, dataRow.getProperty("Captcha"), "Captcha");
			break;
        case "26BMVPB7411GGZ":
			
			break;	

		default:
			break;
		}
		
		//Click on Proceed
		click(Submit, "Submit");
		if(OTP.size()>0) {
			clearAndSenKeys(OTP.get(0), dataRow.getProperty("OTP"), "OTP");
			click(Submit, "Submit");
		}
		else
		{
			System.out.println("OTP is disabled");
		}
	}

}
