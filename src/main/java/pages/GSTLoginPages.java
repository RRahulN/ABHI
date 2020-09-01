package pages;

import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.codoid.products.fillo.Connection;

import util.CustomAssert;
import util.ExcelRead;
import util.WaitTime;

public class GSTLoginPages extends GSTNDashBoard {
	
	@FindBy(xpath="//input[@id='pUserName']")
	private WebElement username;
	
	@FindBy(xpath="//input[@id='textfield2']")
	private WebElement password;
	
	@FindBy(xpath="//input[@id='textfield4']")
	private WebElement role;
	
	@FindBy(xpath="//strong[contains(text(),'LOGIN')]")
	private WebElement loginBTN;
	
	
	
	
	@FindBy(xpath="//label[contains(text(),'GSTIN')]/parent::div/following-sibling::div/p-autocomplete/span/input")
	private WebElement GSTIN;
	
	@FindBy(xpath="//select[@id='finyear']")
	private WebElement FinancialYear;
	
	@FindBy(xpath="//select[@id='taxper']")
	private WebElement TaxPayerPeriod;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement Submit;
	
    WebDriverWait wait;
	public GSTLoginPages(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 30);	
	}
	
	public void fillAndSubmitNewGSTLogin(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup,CustomAssert customAssert) throws Exception
	{
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_LoginPage", testCaseName,stepGroup);
		Reporter.log("<B>Login To Application</B>");
		
		switchtoframe(driver, "display");
		
		clearAndSenKeys(username,dataRow.getProperty("UserName"), "UserName");
		Thread.sleep(WaitTime.low);
		
		clearAndSenKeys(password,dataRow.getProperty("Password"), "Password");
		Thread.sleep(WaitTime.low);
		
		clearAndSenKeys(role, dataRow.getProperty("Role"), "Role");
		Thread.sleep(WaitTime.low);
		
		click(loginBTN, "Login Button");
		switchtodefaultframe(driver);
		
//		clearAndSenKeys(GSTIN, dataRow.getProperty("GSTIN"), "GSTIN");
//		Thread.sleep(WaitTime.low);		
//		selectFromDropdownByVisibleText(FinancialYear, dataRow.getProperty("FinancialYear"), "Financial Year");
//		wait.until(ExpectedConditions.visibilityOf(TaxPayerPeriod));
//		selectFromDropdownByVisibleText(TaxPayerPeriod, dataRow.getProperty("TaxPeriod"), "TaxPeriod");
//		//Click on Proceed
//		Thread.sleep(WaitTime.low);
//		click(Submit, "Submit");
		
	}

}
