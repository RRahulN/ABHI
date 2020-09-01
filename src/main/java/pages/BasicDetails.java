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

public class BasicDetails extends GenericMethods{
	
	//Application number 1
	@FindBy(xpath="//input[@id='Application Number entry 1']")
	private WebElement applicationnumber1;
	
	//Confirm Application Number
	@FindBy(xpath="//input[@id='Application Number entry 2']")
	private WebElement applicationnumber2;
	
	//Go Green
	@FindBy(xpath="//select[@id='Go Green']")
	private WebElement gogreen;
	
	//Intermediary code
	@FindBy(xpath="//input[@id='Producer Code']")
	private WebElement intermediarycodeField;
	
	//Intermediary search
	@FindBy(xpath="//a[@id='openLookUp Producer Code']/i[1]")
	private WebElement intermediarycodesearch;
	
	//Intermediary Name
	@FindBy(xpath="//input[@id='Intermediary Name']")
	private WebElement intermediaryname;
	
	//Save 
	@FindBy(xpath="//button[@id='Save']")
	private WebElement saveBTN;
	
	//Ok
	@FindBy(xpath="//button[contains(text(),'OK')]")
	private WebElement okBTN3;
	
	//Continue basic details
	@FindBy(xpath="//button[@id='btncontinue']")
	private WebElement continueBTN1;
	
	//Continue policy relations
	@FindBy(xpath="//button[contains(text(),'Continue')]")
	private WebElement continueBTN2;
	
	
	WebDriverWait wait;
	public BasicDetails(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 30);	
		}
		
	
	//Basic Details Method	
	public void fillbasicdetails(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup,CustomAssert customAssert) throws Exception
	{
		
		
		String sheetName = ConfigReader.getInstance().getValue(PropertyConfigs.TestSheet);
        Properties dataRow = ExcelRead.readRowDataInProperties(workbook, sheetName, testCaseName,stepGroup);
	   Reporter.log("<B>Traverse To CommonPage</B>");
	   
		
		String unique = getuniqueApplicationNo();
	    //Application number 1
		switchtoframe(driver, "containerFrame"); 
		Thread.sleep(WaitTime.low);
		clearAndSenKeys(applicationnumber1,unique,"Application Number Entry 1" );
		
		
		//Confirm Application Number
		Thread.sleep(WaitTime.low);
		clearAndSenKeys(applicationnumber2,unique,"Confirm Application Number" );
		Thread.sleep(WaitTime.low);
		
		//Go green
		Thread.sleep(WaitTime.medium);
		selectFromDropdownByVisibleText(gogreen,dataRow.getProperty("Go Green"),"Go Green");
		Thread.sleep(WaitTime.low);
		
		
		//Intermediary code
		//wait.until(ExpectedConditions.elementToBeClickable(intermediarycodeField));
		String parentWindow = driver.getWindowHandle();
		clearAndSenKeys(intermediarycodeField,dataRow.getProperty("IntermediaryCode"),"InterMediaryCode ");
		Thread.sleep(WaitTime.low);
		click(intermediarycodesearch, "Search");
		switchToWindow(driver);
		Thread.sleep(4000);
		//wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'"+dataRow.getProperty("IntermediaryCode")+"')]"))));
		driver.findElement(By.xpath("//a[contains(text(),'"+dataRow.getProperty("IntermediaryCode")+"')]")).click();
		Thread.sleep(2000);
		driver.switchTo().window(parentWindow);
 		switchtodefaultframe(driver);
 		switchtoframe(driver, "display"); 
 		switchtoframe(driver, "containerFrame");
		
		
		//Intermediary Name
//		Thread.sleep(WaitTime.low);
//		clearAndSenKeys(intermediaryname, "New Party", "Intermediary Name" );
//		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.PAGE_DOWN);
		
		
		//Save Button
		Thread.sleep(3000);
		click(saveBTN,"Save");
		Thread.sleep(3000);
		click(okBTN3, "Ok ");
		Thread.sleep(3000);
		
		
		//Continue
		click(continueBTN1, "Continue");
		Thread.sleep(WaitTime.medium);
		 
		
		//Policy Relations
		//Continue Button
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.PAGE_DOWN);
		Thread.sleep(WaitTime.low);
		click(continueBTN2, "Continue");
		Thread.sleep(WaitTime.medium);
		//switchtodefaultframe(driver);
		
}
	public void BasicDetailsMehtod(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup,CustomAssert customAssert) throws Exception
	{
		fillbasicdetails(driver, testCaseName, workbook, conn, stepGroup, customAssert);
}
	
}
