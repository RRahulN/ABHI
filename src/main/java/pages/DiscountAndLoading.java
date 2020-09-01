package pages;

import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
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
import testRunner.TestEngine;
import util.ConfigReader;
import util.CustomAssert;
import util.ExcelRead;
import util.GenericMethods;
import util.SetUpWebdriver;
import util.WaitTime;

public class DiscountAndLoading extends GenericMethods{
	
	 
	 //Finalize Button
		@FindBy(xpath="//button[@id='btnFinalize']")
		private WebElement finalizeBTN;
	
	
		//Policy Holder Code
		@FindBy(xpath="(//a[@id='createParty']/i)[1]")
		private WebElement policyholderCD;
		
		
		//Policy Holder Code
			@FindBy(xpath="(//a[@id='createParty']/i)[2]")
			private WebElement policyholderCDsearch;
		
		
		//Title
		@FindBy(xpath="//select[@id='Title']")
		private WebElement title;
		
		
		//First Name
		@FindBy(xpath="//input[@id='First Name']")
		private WebElement firstname;
		
		
		//Gender
		@FindBy(xpath="//select[@id='Gender']")
		private WebElement gender;
		
		
		//Date OF Birth
		@FindBy(xpath="//input[@id='Date of Birth']")
		private WebElement dateofbirth;
		
		
		//Nationality
		@FindBy(xpath="//select[@id='Nationality']")
		private WebElement nationality;
		
		
		//Country of Residence
		@FindBy(xpath="//select[@id='Country of Residence']")
		private WebElement countryofresidence;
		
		
		//HNI Customer
		@FindBy(xpath="//select[@id='HNI Customer']")
		private WebElement HNIcustomer;
		
		
		//CEOclub Advisor
		@FindBy(xpath="//select[@id='CEO Club Advisor Customer']")
		private WebElement CEOclubadvisor;
		
		
		//Priority customer
		@FindBy(xpath="//select[@id='Priority Customer']")
		private WebElement prioritycustomer;
		
		
		//Sensitive Customer
		@FindBy(xpath="//select[@id='Sensitive Customer']")
		private WebElement sensitivecustomer;
		
		
		//GST 
		@FindBy(xpath="//select[@id='GST Registration Type']")
		private WebElement GSTregistrationtype;
		
		
		//Whatsapp Number
		@FindBy(xpath="//input[@id='WhatsApp Number']")
		private WebElement whatsappnumber;
		
		
		//Mailing Radiobutton
		@FindBy(xpath="//input[@value='Y']")
		private WebElement mailing;
		
		
		//Save Button
		@FindBy(xpath="//button[@id='Save']")
		private WebElement saveBTN;
		
		
		//Ok Button
		@FindBy(xpath="//button[contains(text(),'OK')]")
		private WebElement okBTN;
		
		
		//Click Multicolor icon
		@FindBy(xpath="//img[@class='circle_img multi-address ng-scope']")
		private WebElement MulticolorIcon;
		
		
		//Click Permanent Contact
		@FindBy(xpath="//img[@class='circle_img']")
		private WebElement PermanentContact;
		
		
		//Fill Address Line 1
		@FindBy(xpath="//input[@id='Address Pty 1']")
		private WebElement AddressLine1;
		
		
		//Fill Address Line 2
		@FindBy(xpath="//input[@id='Address Pty 2']")
		private WebElement AddressLine2;
		
		
		//Fill Pincode
		@FindBy(xpath="//input[@id='Zip CodeParty']")
		private WebElement Pincode;
		
		
		//Fill City
		@FindBy(xpath="//input[@id='City1']")
		private WebElement City;	
		
		
		//Fill State
		@FindBy(xpath="//input[@id='Emirates/State']")
		private WebElement State;
		
		
		//Fill Country
		@FindBy(xpath="//input[@id='Country1']")
		private WebElement Country;
		
		
		//Fill District
		@FindBy(xpath="//input[@id='District']")
		private WebElement District;

		
		//Fill Landline Number
		@FindBy(xpath="//input[@id='Landline NumberP']")
		private WebElement Landline;
			
			
		//Fill Mobile number
		@FindBy(xpath="//input[@id='Mobile Number']")
		private WebElement Mobile;
			
			
		//Fill Email ID
		@FindBy(xpath="//input[@id='{objQuestion.strParameterName}}']")
		private WebElement Email;
		
		
		//Click Save button
		@FindBy(xpath="//button[@class='mat-custom-btn ng-scope']")
		private WebElement Save1;
		
		
		//Activate client
		@FindBy(xpath="//button[@id='Back']")
		private WebElement activateclient;
		
		
		//Ok Button
		@FindBy(xpath="//button[contains(text(),'OK')]")
		private WebElement okBTN1;
		
		
		//Policy Holder Member
		@FindBy(xpath="//select[@ng-model='isPolHolMember']")
		private WebElement policyholdermember;
		
		
		//Convert Detail Quote
		@FindBy(xpath="//input[@id='btnConverToDQ']")
		private WebElement convertdetailquote;
		
		
		//Ok Button
		@FindBy(xpath="//button[contains(text(),'OK')]")
		private WebElement okBTN2;

	
		//Discount And Loading button
		@FindBy(xpath="//button[contains(text(),'Details')]")
		private WebElement discountandloading;
		
		//Discount And Loading button
		@FindBy(xpath="//button[@id='CloseBtn']")
		private WebElement close;
		
		
	
	    WebDriverWait wait;
	    public DiscountAndLoading(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 30);	
	}

	    
	    public void CheckDiscount(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup,CustomAssert customAssert) throws Exception
		{	
			
			String sheetName = ConfigReader.getInstance().getValue(PropertyConfigs.TestSheet);
			Properties dataRow = ExcelRead.readRowDataInProperties(workbook, sheetName, testCaseName,stepGroup);
			Reporter.log("<B>Traverse To CommonPage</B>");

			
			switchtoframe(driver, "display");    
			click(finalizeBTN, "FinalizeButton");
			String parentWindow = driver.getWindowHandle();
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
			Thread.sleep(2000);
			
			
			//Entering Policy Holder Details
			wait.until(ExpectedConditions.elementToBeClickable(policyholderCD));
			Thread.sleep(WaitTime.low);
			click(policyholderCD, "PolicyHolderCD");
			switchToWindow(driver);
			Thread.sleep(4000);

			
			//Title
			Thread.sleep(WaitTime.medium);
			selectFromDropdownByVisibleText(title,dataRow.getProperty("Title"),"Title");
			Thread.sleep(WaitTime.low);
			
			
			//First Name
			clearAndSenKeys(firstname,getRandomString(),"First Name" );
			Thread.sleep(WaitTime.medium);
			
			//Gender
			selectFromDropdownByVisibleText(gender,dataRow.getProperty("Gender"),"Gender");
			Thread.sleep(WaitTime.low);
			
			
			//Date Of Birth
			Thread.sleep(WaitTime.medium);
			clearAndSenKeys(dateofbirth,dataRow.getProperty("DateOfBirth"), "Date of Birth" );
			Thread.sleep(WaitTime.low);
			dateofbirth.sendKeys(Keys.TAB);
			
			
		    
			//Country of Residence
			Thread.sleep(WaitTime.medium);
			selectFromDropdownByVisibleText(countryofresidence,dataRow.getProperty("Country of Residence"),"Country of Residence");
			//Thread.sleep(WaitTime.low);
			
			//GST
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
			Thread.sleep(WaitTime.medium);
			selectFromDropdownByVisibleText(GSTregistrationtype,dataRow.getProperty("GST Registration Type"),"GST Registration Type");
			Thread.sleep(WaitTime.low);
			
			
			//Whatsapp Number
			Thread.sleep(WaitTime.low);
			clearAndSenKeys(whatsappnumber,dataRow.getProperty("WhatsApp Number"),"WhatsApp Number" );
			
			
			//IstheMailing Radiobutton   Doubt
			Thread.sleep(WaitTime.low);
			click(mailing,"IstheMailing");
			Thread.sleep(3000);
			
			
			//Save Button
			click(saveBTN,"Save");
			Thread.sleep(3000);
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK']")));
			click(okBTN, "OK");
			Thread.sleep(3000);

			
			//click Multicolor icon
			click(MulticolorIcon,"Multicolor Icon");
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.PAGE_DOWN);
			Thread.sleep(2000);
			
			
			//Click Permanent Contact
			click(PermanentContact,"Permanent Contact");
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.PAGE_DOWN);
			
			
			//Fill Address line 1
			Thread.sleep(3000);
			clearAndSenKeys(AddressLine1,dataRow.getProperty("Address Line 1"),"Address Line 1");
			
			
			//Fill Address line 2
			Thread.sleep(3000);
			clearAndSenKeys(AddressLine2,dataRow.getProperty("Address Line 2"),"Address Line 2");
			
			
			//Fill Pincode
			Thread.sleep(3000);
			clearAndSenKeys(Pincode,dataRow.getProperty("PinCode"),"Pincode");
			Pincode.sendKeys(Keys.TAB);
			
			
			//Landline Number
			Thread.sleep(3000);
			clearAndSenKeys(Landline,dataRow.getProperty("Landline Number"),"Landline number");
			
			
			//Mobile Number
			Thread.sleep(3000);
			clearAndSenKeys(Mobile,dataRow.getProperty("Mobile Number"),"Mobile Number");
			
			
			//Fill Email ID
			Thread.sleep(3000);
			clearAndSenKeys(Email,dataRow.getProperty("Email ID"),"Email ID");
			
			
			//Click Save Button
			Thread.sleep(3000);
			click(Save1,"Save Button");
			
			
			//Click OK Button
			Thread.sleep(3000);
			click(okBTN,"OK");
			Thread.sleep(2000);
			
			
			//Activate Client
			click(activateclient,"Activate Client");
			Thread.sleep(3000);

			//Is Policy Holder a Member
			driver.switchTo().window(parentWindow);
			Thread.sleep(2000);
			switchtoframe(driver, "display");  
			Thread.sleep(WaitTime.medium);
			selectFromDropdownByVisibleText(policyholdermember,dataRow.getProperty("PolicyHolderIsMember"),"IsPolicyHolderMember");
			Thread.sleep(WaitTime.low);
			
			
			//Convert Detail Quote
			click(convertdetailquote,"ConvertDetailQuote");
			Thread.sleep(3000);
			click(okBTN2, "OK");
			//switchtoframe(driver, "display");    
			//switchtodefaultframe(driver);
			
			
			switchtoframe(driver, "containerFrame");  
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
			Thread.sleep(3000);
			click(discountandloading,"Discount And Loading");
			switchToWindow(driver);
			Thread.sleep(3000);
			SetUpWebdriver.captureScreenShot(driver, TestEngine.excutionFolder+ConfigReader.getInstance().getValue(PropertyConfigs.screenShotFolder),dataRow.getProperty("TCID"));
			Thread.sleep(2000);
			click(close,"Close Button");
			driver.switchTo().window(parentWindow);
			Thread.sleep(2000);
			switchtoframe(driver, "containerFrame");  
			
}


		public void fillDiscountLoading(WebDriver driver, String testCaseName, XSSFWorkbook workbook, Connection conn,
				String stepGroup, CustomAssert customAssert) throws Exception {
			CheckDiscount(driver, testCaseName, workbook, conn, stepGroup, customAssert);
		}


		
}