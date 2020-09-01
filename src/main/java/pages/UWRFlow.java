package pages;

import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.codoid.products.fillo.Connection;

import constants.PropertyConfigs;
import util.ConfigReader;
import util.CustomAssert;
import util.ExcelRead;
import util.GenericMethods;
import util.WaitTime;

public class UWRFlow extends GenericMethods{

	//Role Click
	@FindBy(xpath="//i[@id='roleIcon']")
	private WebElement roleCLICK;
	
	//Policy Management
	@FindBy(xpath="(//p[contains(text(),'Policy Management')])[1]")
	private WebElement PolicyManagement;
	
	//Quote No. Search
	@FindBy(xpath="//input[@id='Policy No.']")
	private WebElement QuoteNoSearch;
	
	//Search Button
	@FindBy(xpath="//button[@id='Search']")
	private WebElement SearchButton;
	
	//Member Level Action
	@FindBy(xpath="//button[@id='btnFWA']")
	private WebElement MemberLevelAction;
	
	
	//Decision
	@FindBy(xpath="//select[@id='Decision_1']")
	private WebElement decision;
	
	//Save
	@FindBy(xpath="//button[@id='btnSave']")
	private WebElement Save;
	
	//Quote Issue Completed
	@FindBy(xpath="//label[@id='Sub-Status']")
	private WebElement QuoteIssueComplete;
	
	//Issue Policy
	@FindBy(xpath="//button[@id='issuePolicyBtn']")
	private WebElement issuepolicy;
	
	//Member Code
	@FindBy(xpath="//label[@ng-model='clmGridData.strParameterValue']")
	private WebElement memberCode;
	
	//Member icon
	@FindBy(xpath="//a[@title='Member Info']/i")
	private WebElement membericon;
	
	//Save Button
	@FindBy(xpath="//button[@id='SaveButton']")
	private WebElement SaveButton;
	
	//Ok Button
	@FindBy(xpath="//button[contains(text(),'OK')]")
	private WebElement Okbutton;
	
	//Close
	@FindBy(xpath="//button[@id='CloseButton']")
	private WebElement closebutton;
	
	//Policy Number
	@FindBy(xpath="//label[@id='Number']")
	private WebElement policynumber;
	
	//Continue
	@FindBy(xpath="//button[contains(text(),'Continue')]")
	private WebElement Continue;
	
	//Follow Up
	@FindBy(xpath="(//a[@name='Follow-up'])[2]")
	private WebElement FollowUp;
	
	
	
	WebDriverWait wait;
	public UWRFlow(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 30);	
	}
	
public void fillUWR(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup,CustomAssert customAssert) throws Exception
{
	String sheetName = ConfigReader.getInstance().getValue(PropertyConfigs.TestSheet);
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, sheetName, testCaseName,stepGroup);
	Reporter.log("<B>Login To Application</B>");
	
	
	//Switch from BOPS to UWR
	switchtodefaultframe(driver);
	switchtoframe(driver, "head");
	
	click(roleCLICK,"Role Click");	
	
	click(driver.findElement(By.xpath("//div[contains(text(),'UWR')]")),"Selected Role as UWR");
	
	Thread.sleep(2000);
	
	switchtodefaultframe(driver);
	switchtoframe(driver, "display");
	click(PolicyManagement,"Policy Management tab");
	
	Thread.sleep(4000);
	clearAndSenKeys(QuoteNoSearch,getQuoteNo(),"Quote No Input");
	Thread.sleep(4000);
	
	click(SearchButton, "search");
	
	driver.findElement(By.xpath("//input[@id='Policy No.']")).sendKeys(Keys.PAGE_DOWN);
	Thread.sleep(2000);
	click(driver.findElement(By.xpath("//a[contains(text(),'"+getQuoteNo()+"')]")),"Quote no");
	
	
	//Policy Summary
	Thread.sleep(WaitTime.low);
	switchtodefaultframe(driver);
	Thread.sleep(2000);
	switchtoframe(driver,"display");
	Thread.sleep(2000);
	switchtoframe(driver,"containerFrame");
	Thread.sleep(2000);
	driver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN);
	Thread.sleep(2000);
	
	String parentWindow = driver.getWindowHandle();
	Thread.sleep(2000);
	click(MemberLevelAction,"Member Level Action");
	switchToWindow(driver);
	Thread.sleep(2000);
	
	//Select Decision
	selectFromDropdownByVisibleText(decision,dataRow.getProperty("Decision_UWR"),"Decision");
	Thread.sleep(2000);

	//Save
	click(Save,"Save");
	driver.switchTo().window(parentWindow);
	Thread.sleep(2000);
	switchtodefaultframe(driver);
	Thread.sleep(2000);
	switchtoframe(driver,"display");
	Thread.sleep(2000);
	switchtoframe(driver,"containerFrame");
	
	//Quote Issue Completed
	String QuoteIssue=QuoteIssueComplete.getText();
	Reporter.log("----------");
	Reporter.log("Sub-Status for this case is "+QuoteIssue);
	Reporter.log("---------");
	
	
	
	//Switch from UWR to COPS
	switchtodefaultframe(driver);
	switchtoframe(driver, "head");
	
	click(roleCLICK,"Role Click");	
	
	click(driver.findElement(By.xpath("//div[contains(text(),'COPS')]")),"Selected Role as COPS");
	
	Thread.sleep(2000);
	
	switchtodefaultframe(driver);
	switchtoframe(driver, "display");
	click(PolicyManagement,"Policy Management tab");
	
	Thread.sleep(4000);
	clearAndSenKeys(QuoteNoSearch,getQuoteNo(),"Quote No Input");
	Thread.sleep(4000);
	
	click(SearchButton, "search");
	
	driver.findElement(By.xpath("//input[@id='Policy No.']")).sendKeys(Keys.PAGE_DOWN);
	Thread.sleep(2000);
	click(driver.findElement(By.xpath("//a[contains(text(),'"+getQuoteNo()+"')]")),"Quote no");
	
	
	//Traverse Member Info
			switchtodefaultframe(driver);
			switchtoframe(driver,"display");
			Thread.sleep(WaitTime.low);
			click(membericon,"Member Icon");
			Thread.sleep(WaitTime.medium);
			switchtoframe(driver,"containerFrame");
			Thread.sleep(WaitTime.low);
	        click(memberCode, "Member Code");
	        switchtoframe(driver,"memberiframe0");
	        
	        //Save ,Ok, Close
	        click(SaveButton,"Save");
			Thread.sleep(WaitTime.medium);
			click(Okbutton,"Ok Button");
			Thread.sleep(WaitTime.low);
			click(closebutton,"Close");
			Thread.sleep(WaitTime.medium);
			switchtodefaultframe(driver);
			switchtoframe(driver,"display");
			switchtoframe(driver,"containerFrame");
			click(Continue,"Continue");
			Thread.sleep(WaitTime.low);
			
			
			//Printing Policy Number
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.PAGE_UP);
			String policyno=policynumber.getText();
			Reporter.log("----------");
			Reporter.log("Policy Number for this case is "+policyno);
			Reporter.log("---------");
			
			
			//Policy Summary
			click(Continue,"Continue");
			Thread.sleep(WaitTime.low);
			
			//Payment Cycle
			Thread.sleep(WaitTime.low);
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN);
			click(Continue,"Continue");
			Thread.sleep(WaitTime.low);
			
			
			//Follow Up
				switchtodefaultframe(driver);
				switchtoframe(driver,"display");
				Thread.sleep(WaitTime.low);
				click(FollowUp,"Follow Up");
				Thread.sleep(WaitTime.low);
				switchtoframe(driver,"containerFrame");
				driver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN);
		//		click(Continue, "Submit btn");
	   //		Thread.sleep(WaitTime.low);
			
				
				//UWR Flow End
			
     }
	
         public void FillUWRInfo(WebDriver driver, String testCaseName, XSSFWorkbook workbook, Connection conn,
		String stepGroup, CustomAssert customAssert) throws Exception {
	       
        	 fillUWR(driver, testCaseName, workbook, conn, stepGroup, customAssert);
        	 
       }   
   }
