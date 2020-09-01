package pages;

import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.codoid.products.fillo.Connection;

import constants.PropertyConfigs;
import core.FrameworkServices;
import core.TestSuiteGenerator;
import util.ConfigReader;
import util.ExcelRead;
import util.GenericMethods;
import util.WaitTime;

public class GSTNOnlineNewReturndashBoardPage extends GenericMethods {

	@FindBy(xpath="//label[contains(text(),'GSTIN')]/parent::div/following-sibling::div/p-autocomplete/span/input")
	private WebElement GSTIN;
	
	@FindBy(xpath="//select[@name='finyr']")
	private WebElement FinancialYear;
	
	@FindBy(xpath="//select[@name='returnprd']")
	private WebElement TaxPayerPeriod;
	
	@FindBy(xpath="//select[@name='fType']")
	private WebElement FormReturn;
	
	@FindBy(xpath="//select[@name='p_mode']")
	private WebElement PreparationMode;
	
	@FindBy(xpath="//a[text()='PROCEED']")
	private WebElement PROCEED;
	
	WebDriverWait wait;
	
	public GSTNOnlineNewReturndashBoardPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 30);	// TODO Auto-generated constructor stub
	}
	
	public void fillNewReturnAndProceedToNext(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception{
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_LoginPage", testCaseName,stepGroup);
		Reporter.log("<B>Fill new Ruturn and Proceed</B>");
		selectFromDropdownByVisibleText(FinancialYear, dataRow.getProperty("FinancialYear"), "Financial Year");
		wait.until(ExpectedConditions.visibilityOf(TaxPayerPeriod));
		//String[] TaxPeriod=dataRow.getProperty("TaxPeriod").split("-");
		selectFromDropdownByVisibleText(TaxPayerPeriod, dataRow.getProperty("TaxPeriod"), "TaxPeriod");
		//Click on Proceed
		if(FormReturn.isEnabled()) {
			selectFromDropdownByVisibleText(FormReturn, dataRow.getProperty("ReturnType"), "Form/Return");
		}
		String LOB="";
  		FrameworkServices frameworkServices=new FrameworkServices();
  		for(TestSuiteGenerator testSuiteGenerator:frameworkServices.getTestSuiteForExecution()) {
  			LOB=testSuiteGenerator.getLOBName();
  		}
  		if(LOB.equalsIgnoreCase("GSTNewReturnOffline"))
  		{
  			if(PreparationMode.isEnabled()) {
  				selectFromDropdownByVisibleText(PreparationMode, "Upload/Download JSON", "Preparation Mode");
  			}
  		}
  		else if(LOB.equalsIgnoreCase("GSTNewReturnOnline")) {      
  			selectFromDropdownByVisibleText(PreparationMode, "Online", "Preparation Mode");
  		}
  		else {
  			System.out.println("Nop mode selected");
  		}
		
		Thread.sleep(WaitTime.low);
		click(PROCEED, "PROCEED");
	}
}
