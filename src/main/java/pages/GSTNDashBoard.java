package pages;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.App;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.testng.Reporter;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Recordset;

import core.FrameworkServices;
import core.TestSuiteGenerator;
import util.ExcelRead;
import util.GenericMethods;
import util.SetUpWebdriver;
import util.SikuliScript;
import util.WaitTime;


public class GSTNDashBoard extends GenericMethods {

	WebDriverWait wait;

	@FindBy(xpath="//a[text()='Prepare Offline']")
	private WebElement PrepareOffline;
	
	@FindBy(xpath="//select[@id='selTable']")
	private WebElement SelectReturnType;
	
	@FindBy(xpath="//label[@for='anx1files']")
	private WebElement OpenDownloadedJSONFileAnx1;
	
	Screen screen=new Screen();
	public GSTNDashBoard(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 30);	// TODO Auto-generated constructor stub
	}
	
	
	public void navigateToPrapareOffline(WebDriver driver) throws Exception
	{
		wait.until(ExpectedConditions.visibilityOf(PrepareOffline));
		Reporter.log("<B>Prapare Offline</B>");
		if(isDisplayed(PrepareOffline))
		{
			click(PrepareOffline, "Prepare Offline");
		}
		else
		{
			System.out.println();
		}
	}
	
	public void SuppliesToUnregisteredPersons_Consumers_B2C(WebDriver driver) throws Exception
	{
		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		Reporter.log("<B>3A- Supplies to unregistered persons/consumers (B2C)</B>");
		if(isDisplayed(SelectReturnType))
		{
			selectFromDropdownByVisibleText(SelectReturnType, "3A- Supplies to unregistered persons/consumers (B2C)", "Return type Drop Down");
		}
		else
		{
			System.out.println("<B>3A- Supplies to unregistered persons/consumers (B2C) not available</B>");
		}
	
	}
	
	public void SuppliesToRegisteredPersonsB2B(WebDriver driver) throws Exception
	{
		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		Reporter.log("<B>3B- Supplies to registered persons (B2B)</B>");
		if(isDisplayed(SelectReturnType))
		{
			String LOB="";
	  		FrameworkServices frameworkServices=new FrameworkServices();
	  		for(TestSuiteGenerator testSuiteGenerator:frameworkServices.getTestSuiteForExecution()) {
	  			LOB=testSuiteGenerator.getLOBName();
	  		}
	  		if(LOB.equalsIgnoreCase("GSTNewReturnOffline"))
	  		{
	  			selectFromDropdownByVisibleText(SelectReturnType, "3B- Supplies to registered persons (B2B)", "Return type Drop Down");
	  		}
	  		else if(LOB.equalsIgnoreCase("GSTNewReturnOnline")) {      
	  			selectFromDropdownByValue(SelectReturnType, "/auth/b2b", "Return type Drop Down");
	  			Thread.sleep(WaitTime.high);
	  		}
			
		}
		else
		{
			System.out.println("<B>3B- Supplies to registered persons (B2B) not available</B>");
		}
	}
	
	public void SuppliesToRegisteredPersons3C3D(WebDriver driver) throws Exception
	{
		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		Reporter.log("<B>3B- Supplies to registered persons (B2B)</B>");
		if(isDisplayed(SelectReturnType))
		{
			selectFromDropdownByVisibleText(SelectReturnType, "3C & 3D- Exports with/without payment of tax (EXP)", "Return type Drop Down");
		}
		else
		{
			System.out.println("<B>3C & 3D- Exports with/without payment of tax (EXP)</B>");
		}
	}
	
	public void SuppliesToSEZWithWithoutPaymentOfTax3E3F(WebDriver driver) throws Exception
	{
		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		Reporter.log("<B>3B- Supplies to registered persons (B2B)</B>");
		if(isDisplayed(SelectReturnType))
		{
			selectFromDropdownByVisibleText(SelectReturnType, "3E & 3F- Supplies to SEZ with/without payment of tax (SEZ)", "Return type Drop Down");
		}
		else
		{
			System.out.println("<B>3E & 3F- Supplies to SEZ with/without payment of tax (SEZ)</B>");
		}
	}
	public void DeemedExports_3G(WebDriver driver) throws Exception
	{
		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		Reporter.log("<B>3G- Deemed exports (DE)</B>");
		if(isDisplayed(SelectReturnType))
		{
			selectFromDropdownByVisibleText(SelectReturnType, "3G- Deemed exports (DE)", "Return type Drop Down");
		}
		else
		{
			System.out.println("<B>3G- Deemed exports (DE)</B>");
		}
	}
	public void Select3HInwardSuppliesAttractingReverseCharge(WebDriver driver) throws Exception
	{
		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		Reporter.log("<B>3H- Inward supplies attracting reverse charge (RCM)</B>");
		if(isDisplayed(SelectReturnType))
		{
			selectFromDropdownByVisibleText(SelectReturnType, "3H- Inward supplies attracting reverse charge (RCM)", "Return type Drop Down");
		}
		else
		{
			System.out.println("<B>3H- Inward supplies attracting reverse charge (RCM)</B>");
		}
	}
	public void ImportOfServices3I(WebDriver driver) throws Exception
	{
		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		Reporter.log("<B>3I- Import of services (IMPS)</B>");
		if(isDisplayed(SelectReturnType))
		{
			selectFromDropdownByVisibleText(SelectReturnType, "3I- Import of services (IMPS)", "Return type Drop Down");
		}
		else
		{
			System.out.println("<B>3I- Import of services (IMPS)</B>");
		}
	}
	public void ImportOfGoods3J(WebDriver driver) throws Exception
	{
		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		Reporter.log("<B>3J- Import of goods (IMPG)</B>");
		if(isDisplayed(SelectReturnType))
		{
			selectFromDropdownByVisibleText(SelectReturnType, "3J- Import of goods (IMPG)", "Return type Drop Down");
		}
		else
		{
			System.out.println("<B>3J- Import of goods (IMPG)</B>");
		}
	}
	public void ImportOfGoodsFromSEZUnitsDevelopers3K(WebDriver driver) throws Exception
	{
		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		Reporter.log("<B>3K- Import of goods from SEZ units/developers (IMPG SEZ)</B>");
		if(isDisplayed(SelectReturnType))
		{
			selectFromDropdownByVisibleText(SelectReturnType, "3K- Import of goods from SEZ units/developers (IMPG SEZ)", "Return type Drop Down");
		}
		else
		{
			System.out.println("<B>3K- Import of goods from SEZ units/developers (IMPG SEZ)</B>");
		}
	}
	public void MissingDocuments3L(WebDriver driver) throws Exception
	{
		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		Reporter.log("<B>3L - Missing documents(provisional credit availed)</B>");
		if(isDisplayed(SelectReturnType))
		{
			selectFromDropdownByVisibleText(SelectReturnType, "3L - Missing documents(provisional credit availed)", "Return type Drop Down");
		}
		else
		{
			System.out.println("<B>3L - Missing documents(provisional credit availed)</B>");
		}
	}
	public void SuppliesMadeThroughECommerceOperators4(WebDriver driver) throws Exception
	{
		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		Reporter.log("<B>4 - Supplies made through e - commerce operators</B>");
		if(isDisplayed(SelectReturnType))
		{
			selectFromDropdownByVisibleText(SelectReturnType, "4 - Supplies made through e - commerce operators", "Return type Drop Down");
		}
		else
		{
			System.out.println("<B>4 - Supplies made through e - commerce operators</B>");
		}
	}
	public void openDownloadedJSONFile(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception
	{
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "generateJSONToUpload", testCaseName,stepGroup);
		wait.until(ExpectedConditions.visibilityOf(OpenDownloadedJSONFileAnx1));
		Reporter.log("<B>4 - Supplies made through e - commerce operators</B>");
		if(dataRow.getProperty("Annexure").equals("GST ANX-1"))
		{
			App.focus("chrome");
			screen.wait(System.getProperty("user.dir") + "\\sikuliImage\\OpenDownloadJSONFileAnx-1.PNG",30).highlight(5);
			Region region=screen.find(System.getProperty("user.dir") + "\\sikuliImage\\OpenDownloadJSONFileAnx-1.PNG");
			region.below(-40).click();
		
		}

		
		String filePath=dataRow.getProperty("DownloadedJSONFileName");
		wait.until(ExpectedConditions.visibilityOf(PrepareOffline));
		SikuliScript sikuliScript=new SikuliScript();
		sikuliScript.sikuliSelectFileToUpload(screen, "", filePath);
		
		screen.wait(System.getProperty("user.dir") + "\\sikuliImage\\OK.PNG",30).highlight(5);
		screen.click(System.getProperty("user.dir") + "\\sikuliImage\\OK.PNG");
		
	}
	
	public void selectReturnType(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception
	{
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "MarkForDelete", testCaseName,stepGroup);
		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");
		
	}

	//TODO to be modified
	/*public void navigateToFundSwitchMaker(WebDriver driver) throws InterruptedException {
		Reporter.log("<B>Home Page</B>");
		if(!isDisplayed(moneyManagement)) {
			click(policyFinance, "Policy Finance");
			Thread.sleep(WaitTime.veryLow);
			click(moneyManagement, "Money Management ");
			Thread.sleep(WaitTime.veryLow);
			click(fundTransfterP2PRequest, "Fund Transfter % to % Request ");
			Thread.sleep(WaitTime.veryLow);
		}
		else {
			if(!isDisplayed(fundTransfterP2PRequest)) {
				Thread.sleep(WaitTime.veryLow);
				click(moneyManagement, "Money Management ");
				Thread.sleep(WaitTime.veryLow);
				click(fundTransfterP2PRequest, "Fund Transfter % to % Request ");
				Thread.sleep(WaitTime.veryLow);
			}else {
				click(fundTransfterP2PRequest, "Fund Transfter % to % Request ");
				Thread.sleep(WaitTime.veryLow);
			}
		}
	}
	//TODO Anil modified 05/09/2018
	public void navigateToAddressList(WebDriver driver) throws InterruptedException {
		Reporter.log("<B>Home Page</B>");
		if(!isDisplayed(clientSetUP)) {
			click(client, "Client");
			Thread.sleep(WaitTime.veryLow);
			click(clientSetUP, "Client SetUP ");
			Thread.sleep(WaitTime.veryLow);
			click(addressList, "Address List ");
			Thread.sleep(WaitTime.veryLow);
		}
		else {
			if(!isDisplayed(addressList)) {
				click(clientSetUP, "Client SetUP ");
				Thread.sleep(WaitTime.veryLow);
				click(addressList, "Address List ");
				Thread.sleep(WaitTime.veryLow);
			}else {
				click(addressList, "Address List ");
				Thread.sleep(WaitTime.veryLow);
			}
		}

	}*/
}