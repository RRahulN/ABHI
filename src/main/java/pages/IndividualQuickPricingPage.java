package pages;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.ClickElement;
import org.openqa.selenium.remote.server.handler.MaximizeWindow;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.codoid.products.fillo.Connection;

import constants.PropertyConfigs;
import junit.framework.Assert;
import testRunner.TestEngine;
import util.ConfigReader;
import util.CustomAssert;
import util.ExcelRead;
import util.GenericMethods;
import util.SetUpWebdriver;
import util.WaitTime;



//ANMOL 11-06-2020


public class IndividualQuickPricingPage extends GenericMethods {
	
	
     
	
	
	@FindBy(xpath="//a[contains(text(),'Proceed')]")
	private WebElement proceedBTN;
	
	@FindBy(xpath="//select[@id='Source Code']")
	private WebElement sourcecode;
	
	@FindBy(xpath="//select[@id='Type of Business']")
	private WebElement businesstype;
	
	@FindBy(xpath="//input[@id='Producer Code']")
	private WebElement intermediarycodeField;
	
	@FindBy(xpath="(//div[@class='lobicon ng-scope']/a)[1]")
	private WebElement intermediarysearch;
	
	@FindBy(xpath="//select[@id='Policy Tenure']")
	private WebElement policytenure;
	
	@FindBy(xpath="//select[@id='Premium Frequency']")
	private WebElement premiumFrequency;
	
	@FindBy(xpath="//select[@id='Plan Type']")
	private WebElement covertype;
	
	@FindBy(xpath="//select[@id='Plan']")
	private WebElement plantype;
	
	@FindBy(xpath="//select[@id='Sub Plan Type']")
	private WebElement subplantype;
	
	@FindBy(xpath="//select[@id='Employee Discount']")
	private WebElement employeediscount;

	@FindBy(xpath="//input[@id='Proposer Pin Code']")
	private WebElement pincode;

	@FindBy(xpath="//input[@id='Number of Members']")
	private WebElement membernumbers;
	
	@FindBy(xpath="//input[@id='Proposer Email ID']")
	private WebElement emailid;
	
	@FindBy(xpath="(//input[@id='Member Name']//preceding::select[1])[1]")
	private WebElement SI;
	
	@FindBy(xpath="(//input[@id='Member Name'])[1]")
	private WebElement membername;
	
	@FindBy(xpath="(//input[@id='Member Name']//following::select[1])[1]")
	private WebElement zone;
	
	@FindBy(xpath="(//input[@id='Member Name']//following::select[3])[1]")
	private WebElement deductible;
	
	@FindBy(xpath="(//input[@id='Date of Birth'])[1]")
	private WebElement dob;
	
	@FindBy(xpath="(//input[@id='Date of Birth']//following::select[1])[1]")
	private WebElement gender;
	
	@FindBy(xpath="(//input[@id='Date of Birth']//following::select[2])[1]")
	private WebElement relation;
	
	@FindBy(xpath="(//input[@id='Date of Birth']//following::select[3])[1]")
	private WebElement room;
	
	
	@FindBy(xpath="//input[@id='Age']")
	private WebElement AgeValue;
	
	
	@FindBy(xpath="//button[@id='btnCalcPrem']")
	private WebElement calpremBTN;
	
	
	//PremiumCalculation Gettexts
	@FindBy(xpath="(//b[contains(text(),'Net Premium before Discount')]//following::div/div/b)[1]")
	private WebElement netpremiumbefore;
	
	@FindBy(xpath="(//b[contains(text(),'Net Premium before Discount')]//following::div/div/b)[3]")
	private WebElement Discount;
	
	@FindBy(xpath="(//b[contains(text(),'Net Premium before Discount')]//following::div/div/b)[5]")
	private WebElement netpremiumafter;
	
	@FindBy(xpath="(//b[contains(text(),'Net Premium before Discount')]//following::div/div/b)[7]")
	private WebElement loading;
	
	@FindBy(xpath="(//b[contains(text(),'Net Premium before Discount')]//following::div/div/b)[9]")
	private WebElement netpremiumafterloading;
	
	
	@FindBy(xpath="(//b[contains(text(),'Net Premium before Discount')]//following::div/div/b)[11]")
	private WebElement taxamountElement;
	
	@FindBy(xpath="(//b[contains(text(),'Net Premium before Discount')]//following::div/div/b)[13]")
	private WebElement premiuminclusiveofTAX ;
	
	//OPDE
	@FindBy(xpath="//input[@name='OPDE']")
	private WebElement opdeCheckbox ;
	
	//HCB
	@FindBy(xpath="//input[@name='HSCB']")
	private WebElement hcbCheckbox ;
	
	//Co-Pay Waiver
	@FindBy(xpath="//input[@name='WMCP']")
	private WebElement wmpcpCheckbox ;
	
	//maternity express
	@FindBy(xpath="//input[@name='MTEX']")
	private WebElement mtexCheckbox ;
	
	@FindBy(xpath="//select[@name='Applicable Sum Insured']")
	private WebElement opdeDropDown;
	
	
	@FindBy(xpath="(//b[contains(text(),'HSCB - Hospital Cash Benefit')]//following::select[1])[1]")
	private WebElement hcbDropDown;
	
	//save btn
	@FindBy(xpath="//button[@id='btnSave']")
	private WebElement saveBTN;
	
	//Ok Save
	@FindBy(xpath="//button[contains(text(),'OK')]")
	private WebElement saveokBTN;
	
	
	//reference  Number
	@FindBy(xpath="//div[contains(text(),'Quotation Saved Successfully')]")
	private WebElement refno;
	
	//reference Number2
	@FindBy(xpath="//label[contains(text(),'Reference Number')]//following::label[1]")
	private WebElement refno2;
	
	
    WebDriverWait wait;
	public IndividualQuickPricingPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 30);	
	}

	public void fillAddQuote(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup,CustomAssert customAssert) throws Exception
	{	
		
		String sheetName = ConfigReader.getInstance().getValue(PropertyConfigs.TestSheet);
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, sheetName, testCaseName,stepGroup);
		Reporter.log("<B>Traverse To CommonPage</B>");

		switchtoframe(driver, "display");    
		//String winHandleBefore = driver.getWindowHandle();
		click(proceedBTN, "ProceedButton");
		String parentWindow = driver.getWindowHandle();
		
		//EnteringQuoteDetails
		wait.until(ExpectedConditions.elementToBeClickable(intermediarycodeField));
		clearAndSenKeys(intermediarycodeField,dataRow.getProperty("IntermediaryCode"),"InterMediaryCode ");
		Thread.sleep(WaitTime.low);
		click(intermediarysearch, " search ");
		switchToWindow(driver);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'"+dataRow.getProperty("IntermediaryCode")+"')]")).click();
		driver.switchTo().window(parentWindow);
		System.out.println(parentWindow);
		Thread.sleep(2000);
		

		switchtoframe(driver, "display");  
		Thread.sleep(WaitTime.medium);
		selectFromDropdownByVisibleText(policytenure, dataRow.getProperty("Policy Tenure"),"Policy Tenure");
		Thread.sleep(WaitTime.low);
		
		selectFromDropdownByVisibleText(premiumFrequency, dataRow.getProperty("Premium Frequency"),"Premium Frequency");
		Thread.sleep(WaitTime.low);
		
		selectFromDropdownByVisibleText(covertype, dataRow.getProperty("Cover Type"),"Cover Type");
		Thread.sleep(WaitTime.medium);
		
		
		wait.until(ExpectedConditions.elementToBeClickable(plantype));
		Thread.sleep(4000);
		selectFromDropdownByVisibleText(plantype, dataRow.getProperty("Plan"),"Plan Type");
		Thread.sleep(WaitTime.low);
		
		Thread.sleep(WaitTime.medium);
		Thread.sleep(2000);
		selectFromDropdownByVisibleText(subplantype, dataRow.getProperty("SubPlan"),"SubPlan Type");
		Thread.sleep(WaitTime.low);
		
		Thread.sleep(WaitTime.medium);
		selectFromDropdownByVisibleText(employeediscount, dataRow.getProperty("EmployeeDiscount"),"EmployeeDiscount");
		Thread.sleep(WaitTime.medium);

		clearAndSenKeys(pincode,dataRow.getProperty("PinCode"),  "PinCode ");
		Thread.sleep(WaitTime.low);
		
		clearAndSenKeys(membernumbers,dataRow.getProperty("NoOfMembers"),  "No Of Members ");
		membernumbers.sendKeys(Keys.TAB);
		Thread.sleep(WaitTime.low);
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
		
		//Member Details
		Thread.sleep(WaitTime.medium);
		selectFromDropdownByVisibleText(SI, dataRow.getProperty("SumInsured"),"Sum Insured");
		Thread.sleep(WaitTime.low);
		
		Thread.sleep(WaitTime.low);
		clearAndSenKeys(membername, getRandomString(),"Member Name");
		Thread.sleep(WaitTime.low);
		
		Thread.sleep(WaitTime.low);
		selectFromDropdownByVisibleText(zone, dataRow.getProperty("Zone"),"Zone ");
		Thread.sleep(WaitTime.low);
	
		Thread.sleep(WaitTime.low);
		selectFromDropdownByVisibleText(deductible, dataRow.getProperty("Deductible")," Dedcutible ");
		Thread.sleep(WaitTime.low);
		
		
		//Age & DOB //Date Calculation
		 DateFormat dfor = new SimpleDateFormat("dd/MM/yyyy");
		 Date obj = new Date();
		 String acurrdate=dfor.format(obj);
		 
		 
		if (dataRow.getProperty("Age").equalsIgnoreCase("<1"))
		{
			 clearAndSenKeys(AgeValue,"0","Age");
			 Thread.sleep(3000);
			 String CollectAge=AgeValue.getAttribute("value");
			 String[] arrofstr=acurrdate.split("/",3);
	         String date3=arrofstr[2];
		     int calactual= Integer.parseInt(date3);
		     int ageCal=Integer.parseInt(CollectAge);
		     int year=calactual-ageCal;
	         String yearStr=String.valueOf(year);
	         String actualdate=acurrdate.replaceAll(date3, yearStr);
	         Thread.sleep(WaitTime.low);
	         clearAndSenKeys(dob,actualdate,"Date of Birth");
	         Thread.sleep(WaitTime.low);
	         dob.sendKeys(Keys.TAB);
	         AgeValue.sendKeys(Keys.TAB);
	         Thread.sleep(1000);
	} 
	         
		else
		{
        	 clearAndSenKeys(AgeValue,dataRow.getProperty("Age"),"Age");
        	 Thread.sleep(3000);
        	 String CollectAge=AgeValue.getAttribute("value");
			 String[] arrofstr=acurrdate.split("/",3);
	         String date3=arrofstr[2];
		     int calactual= Integer.parseInt(date3);
		     int ageCal=Integer.parseInt(CollectAge);
		     int year=calactual-ageCal;
	         String yearStr=String.valueOf(year);
	         String actualdate=acurrdate.replaceAll(date3, yearStr);
	         Thread.sleep(WaitTime.low);
	         clearAndSenKeys(dob,actualdate,"Date of Birth");
	         Thread.sleep(WaitTime.low);
	         dob.sendKeys(Keys.TAB);
	         AgeValue.sendKeys(Keys.TAB);
	         
		}
		
		    
		//Gender
		Thread.sleep(WaitTime.medium);
		selectFromDropdownByVisibleText(gender, dataRow.getProperty("Gender")," Gender ");
		Thread.sleep(WaitTime.low);
		
		Thread.sleep(WaitTime.low);
		selectFromDropdownByVisibleText(relation, dataRow.getProperty("Relationship")," Relationship ");
		Thread.sleep(WaitTime.low);
		
		Thread.sleep(WaitTime.low);
		selectFromDropdownByVisibleText(room, dataRow.getProperty("RoomCategory")," Room Category ");
		Thread.sleep(WaitTime.low);
	
		//click on Chronic
		if (dataRow.getProperty("IsChronic").equalsIgnoreCase("Yes"))
		{
		String Chronic = dataRow.getProperty("Chronic");
		ArrayList Chroniclist= new ArrayList(Arrays.asList(Chronic.split(",")));
		for(int i =0;i<Chroniclist.size();i++)
		{
		WebElement Chronicclick = driver.findElement(By.xpath("(//option[contains(text(),'"+Chroniclist.get(i)+"')])[1]"));

		clickWithoutJavaScript(Chronicclick, " Chronic ");
		Reporter.log(" as "+Chroniclist.get(i));
		}
		}
		
		
		Thread.sleep(WaitTime.low);
		//SetUpWebdriver.captureScreenShot(driver, TestEngine.excutionFolder+ConfigReader.getInstance().getValue(PropertyConfigs.screenShotFolder),dataRow.getProperty("TCID"));
		click(calpremBTN, "Calculate Premium Button");
		Thread.sleep(WaitTime.high);
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
	
		
	
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='btnSave']")));
		Reporter.log("");
		Reporter.log("<B> -------------------------------------------</B>");
		Reporter.log("<B>  OPD not applied </B>");
		Reporter.log("<B> NetPremiumBefore Value:-  </B>"+ netpremiumbefore.getText());
		Reporter.log("<B> Discount:-  </B>"+Discount.getText());
		Reporter.log("<B> NetPremiumAfter Value:-  </B>"+netpremiumafter.getText());
		Reporter.log("<B> Loading Value:-  </B>"+loading.getText());
		Reporter.log("<B> NetPremiumAfter Loading Value:-  </B>"+netpremiumafterloading.getText());
		Reporter.log("<B> Tax Amount Element Value:-  </B>"+taxamountElement.getText());
		Reporter.log("<B> Premium Inclusive of Tax Value Value:-  </B>"+premiuminclusiveofTAX.getText());
		Reporter.log("<B> -------------------------------------------</B>");
		
		
		click(saveBTN," SaveButton ");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
		click(saveokBTN, "Ok ");
		Thread.sleep(3000);
		Reporter.log("<B> Quotation:- </B> "+refno2.getText());
		Reporter.log("                     ");
		Reporter.log("---------------------");
		
		
		
		
		//Assert Quote Details
		String netpremiumbeforeval = netpremiumbefore.getText().toString().replace("₹ ", "").replace(",", "");
		Assert.assertEquals("Expected value",netpremiumbeforeval, dataRow.getProperty("NetPremiumBeforeDiscouunt(BeforeOPD)").replace(",", ""));
		
		
		
		//ELSE-IF Waiver of Mandatory Co-payment 
		Thread.sleep(WaitTime.medium);
		if(dataRow.getProperty("Co-Pay Waiver").equalsIgnoreCase("Yes"))
		{
			Thread.sleep(WaitTime.medium);
			click(wmpcpCheckbox," Hospital Cash Benefit checkBOX");
			Thread.sleep(WaitTime.medium);
			
			click(calpremBTN, "Calculate Premium Button");
			Thread.sleep(WaitTime.medium);
			WebElement saveBTN1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave")));
			
			//click on OK Quote button
			boolean okBTN = driver.findElements(By.xpath("//button[contains(text(),'OK')]")).size() !=0;
			if (okBTN == true) {
				click(saveokBTN,"OK");
			}
			
		}
		

		String premiumbeforeOPD = netpremiumafter.getText().toString().replace("₹ ", "").replace(",", "");
		Assert.assertEquals(premiumbeforeOPD, dataRow.getProperty("NetPremiumAfterDiscount(BeforeOPD)").replace(",", ""));
		
				
		//IF opd 
		Thread.sleep(WaitTime.medium);
		if(dataRow.getProperty("OPDapplicable").equalsIgnoreCase("Yes"))
		{
			Thread.sleep(WaitTime.medium);
			click(opdeCheckbox,"OPDE checkBOX");
			Thread.sleep(WaitTime.low);
			
			//click on OK Quote button
			boolean okBTN = driver.findElements(By.xpath("//button[contains(text(),'OK')]")).size() !=0;
			if (okBTN == true) {
				click(saveokBTN,"OK");
			}
			
			Thread.sleep(WaitTime.medium);
			selectFromDropdownByVisibleText(opdeDropDown, dataRow.getProperty("OPDsi")," OPD Expenses SumInsured ");
			Thread.sleep(WaitTime.medium);
			
			click(calpremBTN, "Calculate Premium Button");
			Thread.sleep(15000);
			WebElement saveBTN1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave")));	
			
			Reporter.log("");
			Reporter.log("<B> -------------------------------------------</B>");
			Reporter.log("<B>After OPD calculated</B>");
			Reporter.log("<B> NetPremiumBefore Value:-  </B>"+netpremiumbefore.getText());
			Reporter.log("<B> Discount:-  </B>"+Discount.getText());
			Reporter.log("<B> NetPremiumAfter Value:-  </B>"+netpremiumafter.getText());
			Reporter.log("<B> Loading Value:-  </B>"+loading.getText());
			Reporter.log("<B> NetPremiumAfter Loading Value:-  </B>"+netpremiumafterloading.getText());
			Reporter.log("<B> Tax Amount Element Value:-  </B>"+taxamountElement.getText());
			Reporter.log("<B> Premium Inclusive of Tax Value Value:-  </B>"+premiuminclusiveofTAX.getText());
			Reporter.log("<B> -------------------------------------------</B>");
				
		}		
		
		
		//ELSE-IF HOSPITAL CASH BENEFIT 
		Thread.sleep(WaitTime.medium);
		if(dataRow.getProperty("HospitalCashBenefit").equalsIgnoreCase("Yes"))
		{
			Thread.sleep(WaitTime.medium);
			click(hcbCheckbox," Hospital Cash Benefit checkBOX");
			Thread.sleep(WaitTime.low);
			
			//click on OK Quote button
			boolean okBTN = driver.findElements(By.xpath("//button[contains(text(),'OK')]")).size() !=0;
			if (okBTN == true) {
				click(saveokBTN,"OK");
			}
			
			Thread.sleep(WaitTime.medium);
			selectFromDropdownByVisibleText(hcbDropDown, dataRow.getProperty("HCBsi")," Hospital Cash Benefit  Expenses");
			Thread.sleep(WaitTime.medium);
			
			click(calpremBTN, "Calculate Premium Button");
			Thread.sleep(15000);
			WebElement saveBTN1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave")));	
			
			Reporter.log("");
			Reporter.log("<B> -------------------------------------------</B>");
			Reporter.log("<B>After Hospital Cash Benefit calculated</B>");
			Reporter.log("<B> NetPremiumBefore Value:-  </B>"+netpremiumbefore.getText());
			Reporter.log("<B> Discount:-  </B>"+Discount.getText());
			Reporter.log("<B> NetPremiumAfter Value:-  </B>"+netpremiumafter.getText());
			Reporter.log("<B> Loading Value:-  </B>"+loading.getText());
			Reporter.log("<B> NetPremiumAfter Loading Value:-  </B>"+netpremiumafterloading.getText());
			Reporter.log("<B> Tax Amount Element Value:-  </B>"+taxamountElement.getText());
			Reporter.log("<B> Premium Inclusive of Tax Value Value:-  </B>"+premiuminclusiveofTAX.getText());
			Reporter.log("<B> -------------------------------------------</B>");
				
		}
		

		
		//ELSE-IF Maternity Expenses
		Thread.sleep(WaitTime.medium);
		if(dataRow.getProperty("MaternityExpense").equalsIgnoreCase("Yes"))
		{
			Thread.sleep(WaitTime.medium);
			click(mtexCheckbox," Maternity Expense checkBOX");
			Thread.sleep(WaitTime.medium);
			
			//click on OK Quote button
			boolean okBTN = driver.findElements(By.xpath("//button[contains(text(),'OK')]")).size() !=0;
			if (okBTN == true) {
				click(saveokBTN,"OK");
			}
			
			
			click(calpremBTN, "Calculate Premium Button");
			Thread.sleep(15000);
			WebElement saveBTN1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave")));
			
			Reporter.log("");
			Reporter.log("<B> -------------------------------------------</B>");
			Reporter.log("<B>After Maternity calculated</B>");
			Reporter.log("<B> NetPremiumBefore Value:-  </B>"+netpremiumbefore.getText());
			Reporter.log("<B> Discount:-  </B>"+Discount.getText());
			Reporter.log("<B> NetPremiumAfter Value:-  </B>"+netpremiumafter.getText());
			Reporter.log("<B> Loading Value:-  </B>"+loading.getText());
			Reporter.log("<B> NetPremiumAfter Loading Value:-  </B>"+netpremiumafterloading.getText());
			Reporter.log("<B> Tax Amount Element Value:-  </B>"+taxamountElement.getText());
			Reporter.log("<B> Premium Inclusive of Tax Value Value:-  </B>"+premiuminclusiveofTAX.getText());
			Reporter.log("<B> -------------------------------------------</B>");
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public void AssertQuote(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup,CustomAssert customAssert) throws Exception
	{
		String sheetName = ConfigReader.getInstance().getValue(PropertyConfigs.TestSheet);
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook,sheetName, testCaseName,stepGroup);
		
		//Values of Premium Calculation	
		String netpremiumbeforeval = netpremiumbefore.getText().toString().replace("₹ ", "").replace(",", "");
		String discountval = Discount.getText().toString().replace("₹ ", "").replace(",", "");
		String netpremiumafterval = netpremiumafter.getText().toString().replace("₹ ", "").replace(",", "");
		String loadingval = loading.getText().toString().replace("₹ ", "").replace(",", "");
		String netpremiumafterloadingval = netpremiumafterloading.getText().toString().replace("₹ ", "").replace(",", "");
		String taxamountElementval = taxamountElement.getText().toString().replace("₹ ", "").replace(",", "");
		String premiuminclusiveofTAXval = premiuminclusiveofTAX.getText().toString().replace("₹ ", "").replace(",", "");
		
		
		
		
		//after OPD assert
		Assert.assertEquals(netpremiumafterval, dataRow.getProperty("NetPremiumAfterDiscount(AfterOPD)").replace(",", ""));
		//Assert.assertEquals(netpremiumafterloadingval, dataRow.getProperty("NetPremiumAfterLoading(AfterOPD)"));
		Assert.assertEquals(taxamountElementval, dataRow.getProperty("GST").replace(",", ""));
		Assert.assertEquals(premiuminclusiveofTAXval, dataRow.getProperty("Total Premium").replace(",", ""));
		
		
		
		
		System.out.println("-------------Execution Complete-----------");
		switchtodefaultframe(driver);
		
		
	}
	
		public void fillQuote(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup,CustomAssert customAssert) throws Exception
		{
			fillAddQuote(driver, testCaseName, workbook, conn, stepGroup, customAssert);
			AssertQuote(driver, testCaseName, workbook, conn, stepGroup, customAssert);
			
			
		}
	
	
	


}
