package pages;

import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class PPMCFlow extends GenericMethods{
	
	
	//PPMC Sub-Status
	@FindBy(xpath="//label[@id='Sub-Status']")
	private WebElement referforppmc;
	
	
	//Username
	@FindBy(xpath="//input[@id='pUserName']")
	private WebElement username;
	
	
	//Password
	@FindBy(xpath="//input[@id='textfield2']")
	private WebElement password;
	
	
	//Role
	@FindBy(xpath="//input[@id='textfield4']")
	private WebElement role;
	
	
	//Login
	@FindBy(xpath="//strong[contains(text(),'LOGIN')]")
	private WebElement loginBTN;
	
	
	//Role Click
	@FindBy(xpath="//i[@id='roleIcon']")
	private WebElement roleCLICK;
	
	
	// IT Operations
	@FindBy(xpath="(//p[contains(text(),'IT Operations')])[2]")
	private WebElement ITOperations;
	
	
	//Quote No.
	@FindBy(xpath="//input[@id='Policy No.']")
	private WebElement QuoteNoSearch;
	
	
	//Search
	@FindBy(xpath="//button[@id='Search']")
	private WebElement SearchButton;
	
	
	//PPMC Dashboard
	@FindBy(xpath="//a[contains(text(),'PPMC DashBoard')]")
	private WebElement ppmcdashboard;
	
	
	//Search PPMC
	@FindBy(xpath="//input[@placeholder='Search']")
	private WebElement searchppmc;
	
	
	//Search Icon
	@FindBy(xpath="//img[@title='Search']")
	private WebElement searchicon;
	
	
	//Plus Icon
	@FindBy(xpath="//img[@title='show sub grid']")
	private WebElement plusicon;
	
	
	//Checkbox Icon
	@FindBy(xpath="//input[@name='All']")
	private WebElement checkbox;
	
	
	//Data Entry 1 Completed
	@FindBy(xpath="//label[@id='Sub-Status']")
	private WebElement DataEntryCompleted;
	
	
	//Policy Management
	@FindBy(xpath="(//p[contains(text(),'Policy Management')])[1]")
	private WebElement PolicyManagement;
	
	
	//Member Code
	@FindBy(xpath="//label[@ng-model='clmGridData.strParameterValue']")
	private WebElement memberCode;
	
	
	//Member icon
	@FindBy(xpath="//a[@title='Member Info']/i")
	private WebElement membericon;

	
	//Height Feet
	@FindBy(xpath="//input[@id='Height (Feet)']")
	private WebElement heightfeet;
	
	
	//Weight Kg
	@FindBy(xpath="//input[@id='Weight (in kgs)']")
	private WebElement weightinKG;
	
	//Occupation
	@FindBy(xpath="//select[@id='Occupation']")
	private WebElement occupation;
	
	//Chronic
	@FindBy(xpath="//select[@id='Is Chronic Disease ?']")
	private WebElement Ischronic;
	
	//Opted zone
	@FindBy(xpath="//select[@id='Opted zone']")
	private WebElement Optedzone;
	
	//Save
	@FindBy(xpath="//button[@id='SaveButton']")
	private WebElement SaveButton;
	
	//Ok
	@FindBy(xpath="//button[contains(text(),'OK')]")
	private WebElement Okbutton;
	
	//Data Entry
	@FindBy(xpath="//button[@id='DataEntry1Completed']")
	private WebElement DataEntry1;
	
	//Submit 	
	@FindBy(xpath="//button[@id='sendQCBtn']")
	private WebElement SubmitButton;
	
	//Policy Summary
	@FindBy(xpath="(//a[@name='Policy Summary'])[2]")
	private WebElement policysummary;
	
	//Medical Status
	@FindBy(xpath="//[@ng-model='subCol.value']")
	private WebElement medicalstatus;
	
	//Save
	@FindBy(xpath="//img[@title='Save']")
	private WebElement Save;
	
	//Case Registered
	@FindBy(xpath="(//select[@ng-model='subCol.value'])/option[3]")
	private WebElement caseregistered;
	
	//Appointment Requested
	@FindBy(xpath="(//select[@ng-model='subCol.value'])/option[2]")
	private WebElement AppointmentRequested;
	
	//Appointment Fixed
	@FindBy(xpath="(//select[@ng-model='subCol.value'])/option[2]")
	private WebElement AppointmentFixed;
	
	//Medical Done
	@FindBy(xpath="(//select[@ng-model='subCol.value'])/option[2]")
	private WebElement MedicalDone;
	
	//Medical Done
	@FindBy(xpath="(//select[@ng-model='subCol.value'])/option[2]")
	private WebElement ReportUploaded;
	
	//Digitization Pending
	@FindBy(xpath="(//select[@ng-model='subCol.value'])/option[2]")
	private WebElement DigitizationPending;
		
	//Digitization Done
	@FindBy(xpath="(//select[@ng-model='subCol.value'])/option[2]")
	private WebElement DigitizationDone;

	//Sub-Status Refer to UWR
	@FindBy(xpath="//label[contains(text(),'Refer to UWR')]")
	private WebElement refertoUWR;
	
	
	
	
	WebDriverWait wait;
	public PPMCFlow(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 30);	
		}
	
	
	
	//PPMCFlow Method
	public void PPMCFlowDetails(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup,CustomAssert customAssert) throws Exception
	{
		
		
		String sheetName = ConfigReader.getInstance().getValue(PropertyConfigs.TestSheet);
        Properties dataRow = ExcelRead.readRowDataInProperties(workbook, sheetName, testCaseName,stepGroup);
	   Reporter.log("<B>Traverse To CommonPage</B>");

	   
	   
	   //Switch to COPS
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
		
		
		Thread.sleep(WaitTime.low);
		switchtodefaultframe(driver);
		switchtoframe(driver,"display");
		Thread.sleep(WaitTime.low);
		click(membericon,"Member Icon");
		Thread.sleep(WaitTime.medium);
		switchtoframe(driver,"containerFrame");
		Thread.sleep(WaitTime.low);
       click(memberCode, "Member Code");
       switchtoframe(driver,"memberiframe0");
       
       
       
//		Thread.sleep(WaitTime.medium);
//	    clearAndSenKeys(mobileNumber,"9890122325","Mobile No");
//		Thread.sleep(WaitTime.low);
		
		Thread.sleep(WaitTime.medium);
		clearAndSenKeys(heightfeet,dataRow.getProperty("HeightFeet"),"Height Feet");
		Thread.sleep(WaitTime.low);
		
		Thread.sleep(WaitTime.medium);
		clearAndSenKeys(weightinKG,dataRow.getProperty("WeightInKG"),"Weight In KG");
		Thread.sleep(WaitTime.low);
		weightinKG.sendKeys(Keys.TAB);
		
//		Thread.sleep(WaitTime.medium);
//		clearAndSenKeys(bmi,dataRow.getProperty("BMI"),"bmi");
//		Thread.sleep(WaitTime.low);
		
//		Thread.sleep(WaitTime.medium);
//		selectFromDropdownByVisibleText(nationality,"Indian","Nationality");
//		Thread.sleep(WaitTime.low);
//		
//		Thread.sleep(WaitTime.medium);
//		clearAndSenKeys(countryofResidence,"India","Country of Residence");
//		countryofResidence.sendKeys(Keys.TAB);
//		Thread.sleep(WaitTime.low);
		
		Thread.sleep(WaitTime.medium);
		selectFromDropdownByVisibleText(occupation,dataRow.getProperty("Occupation"),"Occupation");
		Thread.sleep(WaitTime.low);
		
//		Thread.sleep(WaitTime.medium);
//		selectFromDropdownByVisibleText(sumInsured,"200000","Sum Insured");
//		Thread.sleep(WaitTime.low);
		
//		Thread.sleep(WaitTime.medium);
//		selectFromDropdownByVisibleText(roomCategory,"Single Private","Room Category");
//		Thread.sleep(WaitTime.low);
	
//		Thread.sleep(WaitTime.medium);
//		clearAndSenKeys(countryofResidence,"India","Country of Residence");
//		Thread.sleep(WaitTime.low);
		
		Thread.sleep(WaitTime.medium);
		selectFromDropdownByVisibleText(Ischronic,dataRow.getProperty("IsChronic"),"Is Chronic");
		Thread.sleep(WaitTime.low);
		
//		Thread.sleep(WaitTime.medium);
//		selectFromDropdownByVisibleText(hniCustomer,"No","hni Customer");
//		Thread.sleep(WaitTime.low);
//		
//		Thread.sleep(WaitTime.medium);
//		selectFromDropdownByVisibleText(CEOClubAdvisorCustomer,"No","CEO Club Advisor Customerr");
//		Thread.sleep(WaitTime.low);
//		
//		Thread.sleep(WaitTime.medium);
//		selectFromDropdownByVisibleText(priorityCustomer,"No","Priority Customer");
//		Thread.sleep(WaitTime.low);
//		
//		Thread.sleep(WaitTime.medium);
//		selectFromDropdownByVisibleText(sensitiveCustomerr,"No","sensitive Customerr");
//		Thread.sleep(WaitTime.low);
		
//		Thread.sleep(WaitTime.medium);
//		clearAndSenKeys(Pincode,"400050","Pincode");
//		Pincode.sendKeys(Keys.TAB);
//	    Thread.sleep(WaitTime.low);
//		
//		Thread.sleep(WaitTime.medium);
//		clearAndSenKeys(city,"MUMBAI","City");
//	    Thread.sleep(WaitTime.low);
//	
//	    Thread.sleep(WaitTime.medium);
//		clearAndSenKeys(districtName,"MUMBAI","District Name");
//		Thread.sleep(WaitTime.low);
//	
//		Thread.sleep(WaitTime.medium);
//		clearAndSenKeys(state,"MAHARASHTRA","State");
//		Thread.sleep(WaitTime.low);
			
		Thread.sleep(WaitTime.medium);
	    selectFromDropdownByVisibleText(Optedzone,dataRow.getProperty("Zone"),"Zone");
		Thread.sleep(WaitTime.low);
		
		
		//Save & Ok
		((JavascriptExecutor) driver)
	     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		click(SaveButton,"Save");
		Thread.sleep(WaitTime.medium);
		click(Okbutton,"Ok Button");
		Thread.sleep(WaitTime.low);
		
		
		switchtodefaultframe(driver);
		switchtoframe(driver,"display");
		Thread.sleep(WaitTime.medium);
		click(policysummary, "Policy Summary");
		switchtoframe(driver,"containerFrame");
		Thread.sleep(WaitTime.low);
		
		

		//Policy Summary
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN);
		Thread.sleep(WaitTime.low);
		click(DataEntry1,"DataEntry 1 Complteted");
		Thread.sleep(WaitTime.low);
		
		
		//Data Entry1 Completed for all scrutiny cases
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.PAGE_UP);
		String dataentry=DataEntryCompleted.getText();
		Reporter.log("----------");
		Reporter.log("For all Scrutiny cases "+dataentry);
		Reporter.log("---------");
		
		
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN);
		Thread.sleep(WaitTime.low);
		click(SubmitButton,"Submit");
		Thread.sleep(WaitTime.low);

	   
	   
	   
	//	-----PPMC flow
	
	//Refer for PPMC
	driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.PAGE_UP);
	String ppmc=referforppmc.getText();
	Reporter.log("----------");
	Reporter.log("For all Scrutiny cases "+ppmc);
	Reporter.log("---------");
	
	
	//Switch from COPS to BOPS
	switchtodefaultframe(driver);
	switchtoframe(driver, "head");
	
	click(roleCLICK,"Role Click");	
	
	click(driver.findElement(By.xpath("//div[contains(text(),'BOPS')]")),"Selected Role as BOPS");
	
	Thread.sleep(2000);
	
	//IT Operations Tab
	switchtodefaultframe(driver);
	Thread.sleep(2000);
	switchtoframe(driver, "display");
	Thread.sleep(2000);
	click(ITOperations,"IT Operations Tab");
	Thread.sleep(4000);
	
	
	//PPMC Dashboard
	switchtodefaultframe(driver);
	Thread.sleep(2000);
	switchtoframe(driver, "head");
	Thread.sleep(2000);
	click(ppmcdashboard,"PPMC Dashboard");
	Thread.sleep(4000);
	
	
	//Search PPMC
	switchtodefaultframe(driver);
	Thread.sleep(2000);
	switchtoframe(driver, "display");
	Thread.sleep(2000);
	clearAndSenKeys(searchppmc,getQuoteNo(),"Search PPMC");
	Thread.sleep(2000);
	
	
	//Search Icon
	click(searchicon,"Search Icon");
	Thread.sleep(2000);
	
	
	//Plus Icon
	click(plusicon,"Plus Icon");
	Thread.sleep(2000);
	
	//	Check Proposal Details
	click(checkbox,"Select Checkbox");
	Thread.sleep(2000);
	
	
	//Case Referred
	selectFromDropdownByVisibleText(medicalstatus,"CASE REFERRED FOR CALLING","Case Referred for Calling");
    Thread.sleep(2000);
    
	    //Save & OK
		click(Save,"Click Save");
		 Thread.sleep(2000);
		click(Okbutton,"Ok Button");
		Thread.sleep(WaitTime.low);
	
		//Plus Icon
		click(plusicon,"Plus Icon");
		Thread.sleep(2000);
			
		//	Check Proposal Details
		click(checkbox,"Select Checkbox");
		Thread.sleep(2000);
	
	//Case Registered
		selectFromDropdownByVisibleText(medicalstatus,"CASE REGISTERED","Case Registered");
    Thread.sleep(2000);

	    //Save & OK
	  	click(Save,"Click Save");
	  	 Thread.sleep(2000);
	  	click(Okbutton,"Ok Button");
	  	Thread.sleep(WaitTime.low);
	
	  	//Plus Icon
	  	click(plusicon,"Plus Icon");
	  	Thread.sleep(2000);
	  		
	  	//	Check Proposal Details
	  	click(checkbox,"Select Checkbox");
	  	Thread.sleep(2000);
    
	//Appointment Requested
	  	selectFromDropdownByVisibleText(medicalstatus,"APPOINTMENT REQUESTED","Appointment Requested");
    Thread.sleep(2000);
	
	    //Save & OK
	    click(Save,"Click Save");
	 	 Thread.sleep(2000);
	 	click(Okbutton,"Ok Button");
	 	Thread.sleep(WaitTime.low);
	
	 	//Plus Icon
	 	click(plusicon,"Plus Icon");
	 	Thread.sleep(2000);
	 		
	 	//	Check Proposal Details
	 	click(checkbox,"Select Checkbox");
	 	Thread.sleep(2000);
 	
 	//Appointment Fixed
	 	selectFromDropdownByVisibleText(medicalstatus,"APPOINTMENT FIXED","Appointment Fixed");
        Thread.sleep(2000);
    
	    //Save & OK
	    click(Save,"Click Save");
	 	 Thread.sleep(2000);
	 	click(Okbutton,"Ok Button");
	 	Thread.sleep(WaitTime.low);
	
	 	//Plus Icon
	 	click(plusicon,"Plus Icon");
	 	Thread.sleep(2000);
	 		
	 	//	Check Proposal Details
	 	click(checkbox,"Select Checkbox");
	 	Thread.sleep(2000);
  	
  	//Medical Done
	 	selectFromDropdownByVisibleText(medicalstatus,"MEDICAL DONE","Medical Done");
        Thread.sleep(2000);
    
		//Save & OK
		click(Save,"Click Save");
		 Thread.sleep(2000);
		click(Okbutton,"Ok Button");
		Thread.sleep(WaitTime.low);
		
		//Plus Icon
		click(plusicon,"Plus Icon");
		Thread.sleep(2000);
			
		//	Check Proposal Details
		click(checkbox,"Select Checkbox");
		Thread.sleep(2000);
    
    //Report Uploaded
		selectFromDropdownByVisibleText(medicalstatus,"REPORT UPLOADED","Report Uploaded");
 	    Thread.sleep(2000);
 	    
	 	//Save & OK
	 	click(Save,"Click Save");
	 	Thread.sleep(2000);
	 	click(Okbutton,"Ok Button");
	 	Thread.sleep(WaitTime.low);
	
	 	//Plus Icon
	 	click(plusicon,"Plus Icon");
	 	Thread.sleep(2000);
	 	 		
	 	//Check Proposal Details
	 	click(checkbox,"Select Checkbox");
	 	Thread.sleep(2000);
    
  	
	 	
	//Digitization Pending
	 	selectFromDropdownByVisibleText(medicalstatus,"DIGITIZATION PENDING","Digitization Pending");
 	    Thread.sleep(2000);
	 	    
	 	//Save & OK
	 	click(Save,"Click Save");
	 	Thread.sleep(2000);
	 	click(Okbutton,"Ok Button");
	 	Thread.sleep(WaitTime.low);
	
	 	//Plus Icon
	 	click(plusicon,"Plus Icon");
	 	Thread.sleep(2000);
	 	 		
	 	//Check Proposal Details
	 	click(checkbox,"Select Checkbox");
	 	Thread.sleep(2000);
	 	
	 	
 	//Digitization Done
	 	selectFromDropdownByVisibleText(medicalstatus,"DIGITIZATION DONE","Digitization Done");
 	    Thread.sleep(2000);
		 	    
	 	//Save & OK
	 	click(Save,"Click Save");
	 	Thread.sleep(2000);
	 	click(Okbutton,"Ok Button");
	 	Thread.sleep(WaitTime.low);
	
	 	//Plus Icon
	 	click(plusicon,"Plus Icon");
	 	Thread.sleep(2000);
	 	 		
	 	//Check Proposal Details
	 	click(checkbox,"Select Checkbox");
	 	Thread.sleep(2000);
		 	
	 	
	//Check Status Refer to UWR
	 	String UWR=refertoUWR.getText();
		Reporter.log("----------");
		Reporter.log("Sub-Status for this case is "+UWR);
		Reporter.log("---------");
	 	
	
	
	//----PPMC Flow End
	
}

	public void FillPPMCInfo(WebDriver driver, String testCaseName, XSSFWorkbook workbook, Connection conn,String stepGroup, CustomAssert customAssert) throws Exception {
	PPMCFlowDetails(driver, testCaseName, workbook, conn, stepGroup, customAssert);
		
	}
}
