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
import util.ConfigReader;
import util.CustomAssert;
import util.ExcelRead;
import util.GenericMethods;
import util.WaitTime;

public class PaymentsDatailsPage extends GenericMethods {

	
	@FindBy(xpath="//a[@id='6']//i[contains(@class,'fa-credit-card')]")	
	private WebElement paymentscreen;
	
	@FindBy(xpath="//select[@id='Payment Mode']")
	private WebElement paymentMode;
	
	@FindBy(xpath="//select[@id='Payment Frequency']")
	private WebElement paymentFrequency;
		
	@FindBy(xpath="//select[@id='Auto-Debit Flag']")
	private WebElement autoDebitflag;
	
	
	@FindBy(xpath="//input[@id='Name of Account Holder']")
	private WebElement NameOfHolder;
	
	
	@FindBy(xpath="//input[@id='Account Number']")
	private WebElement accountNo;
	
	@FindBy(xpath="//input[@id='Bank IFSC Code']")
	private WebElement Ifsccode;
	
	
	@FindBy(xpath="//select[@id='Account Type']")
	private WebElement accountType;
	
	@FindBy(xpath="//input[@name='Bank City']")
	private WebElement bankCity;
	
	@FindBy(xpath="//button[@id='SaveButton']")
	private WebElement saveBTN;
	
	@FindBy(xpath="//button[contains(text(),'OK')]")
	private WebElement OkBtn;
	
	@FindBy(xpath="//button[contains(text(),'Continue')]")
	private WebElement continueBTN;

	
	
	 WebDriverWait wait;
		public PaymentsDatailsPage(WebDriver driver) {
			super(driver);
			PageFactory.initElements(driver, this);
			wait=new WebDriverWait(driver, 30);	
		}
		
		
	public void fillpayment(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup,CustomAssert customAssert) throws Exception
	{	
		
//		String sheetName = ConfigReader.getInstance().getValue(PropertyConfigs.TestSheet);
//		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, sheetName, testCaseName,stepGroup);
//		Reporter.log("<B>Traverse To CommonPage</B>");
		
		
		//Payment mode
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.PAGE_DOWN);
		/* Thread.sleep(WaitTime.medium);
		selectFromDropdownByVisibleText(paymentMode,"Cash","Payment Mode");
		Thread.sleep(WaitTime.low);
		
		//Payment Frequency
		Thread.sleep(WaitTime.medium);
		selectFromDropdownByVisibleText(paymentFrequency,"Monthly","Payent Frequency");
		Thread.sleep(WaitTime.low);
		
		Thread.sleep(WaitTime.medium);
		selectFromDropdownByVisibleText(autoDebitflag,"No","Auto Debit Flag");
		Thread.sleep(WaitTime.low);
		
		Thread.sleep(WaitTime.medium);
		clearAndSenKeys(NameOfHolder,"Gatha","Name Of Holder");
		Thread.sleep(WaitTime.low);
		
		Thread.sleep(WaitTime.medium);
		clearAndSenKeys(accountNo,"0000123456","Account No");
		Thread.sleep(WaitTime.low);
		
		Thread.sleep(WaitTime.medium);
		clearAndSenKeys(Ifsccode,"SBIN000025","IFSC Code");
		Thread.sleep(WaitTime.low);
		
		Thread.sleep(WaitTime.medium);
		selectFromDropdownByVisibleText(accountType,"Savings Account","Auto Debit Flag");
		Thread.sleep(WaitTime.low);
		
		Thread.sleep(WaitTime.medium);
		clearAndSenKeys(bankCity,"Mumbai","Bank City");
		Thread.sleep(WaitTime.low);
		
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.PAGE_DOWN);
		Thread.sleep(WaitTime.medium);
		click(saveBTN,"Save");
		Thread.sleep(WaitTime.medium);
		
		Thread.sleep(WaitTime.medium);
		click(OkBtn,"Ok Button");
		Thread.sleep(WaitTime.low); */
		
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.PAGE_DOWN);
		click(continueBTN,"Continue Btn");
		Thread.sleep(WaitTime.low);
			
		
	}
	
	public void FillPaymentDetails(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup,CustomAssert customAssert) throws Exception
	{	
		fillpayment(driver, testCaseName, workbook, conn, stepGroup, customAssert);
		
	}
	
	
	
}
