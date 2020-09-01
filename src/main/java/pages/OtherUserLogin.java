package pages;
import java.util.ArrayList;
import java.util.Arrays;
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

public class OtherUserLogin extends GenericMethods {

	
	@FindBy(xpath="//input[@id='pUserName']")
	private WebElement username;
	
	@FindBy(xpath="//input[@id='textfield2']")
	private WebElement password;
	
	@FindBy(xpath="//input[@id='textfield4']")
	private WebElement role;
	
	@FindBy(xpath="//strong[contains(text(),'LOGIN')]")
	private WebElement loginBTN;
	
	@FindBy(xpath="//i[@id='roleIcon']")
	private WebElement roleCLICK;
	
	@FindBy(xpath="(//p[contains(text(),'Policy Management')])[1]")
	private WebElement PolicyManagement;
	
	@FindBy(xpath="//input[@id='Policy No.']")
	private WebElement QuoteNoSearch;
	
	@FindBy(xpath="//button[@id='Search']")
	private WebElement SearchButton;
	
	
	
	@FindBy(xpath="//label[@ng-model='clmGridData.strParameterValue']")
	private WebElement memberCode;

	@FindBy(xpath="//input[@id='Mobile Number']")
	private WebElement mobileNumber;
	
	@FindBy(xpath="//input[@id='Height (Feet)']")
	private WebElement heightfeet;
	
	@FindBy(xpath="//input[@id='Weight (in kgs)']")
	private WebElement weightinKG;
	
	@FindBy(xpath="//input[@id='BMI']")
	private WebElement bmi;
	
	@FindBy(xpath="//select[@id='Nationality']")
	private WebElement nationality;
	
	@FindBy(xpath="//input[@id='countryof residence']")
	private WebElement countryofResidence;
	
	@FindBy(xpath="//select[@id='Occupation']")
	private WebElement occupation;
	
	@FindBy(xpath="//select[@id='Applicable Sum Insured']")
	private WebElement sumInsured;
	
	@FindBy(xpath="//select[@id='Room Category']")
	private WebElement roomCategory;
	
	@FindBy(xpath="//select[@id='Is Chronic Disease ?']")
	private WebElement Ischronic;
	
	@FindBy(xpath="//select[@id='HNI Customer']")
	private WebElement hniCustomer;
	
	@FindBy(xpath="//select[@id='CEO Club Advisor Customer']")
	private WebElement CEOClubAdvisorCustomer;
	
	@FindBy(xpath="//select[@id='Priority Customer']")
	private WebElement priorityCustomer;
	
	@FindBy(xpath="//select[@id='Sensitive Customer']")
	private WebElement sensitiveCustomerr;
	
	@FindBy(xpath="//input[@id='Policy-holder ZIP Code']")
	private WebElement Pincode;
	
	@FindBy(xpath="//input[@id='Policy-holder City']")
	private WebElement city;
	
	@FindBy(xpath="//input[@id='District Name']")
	private WebElement districtName;
	
	@FindBy(xpath="//input[@id='Policy-holder Province']")
	private WebElement state;
	
	@FindBy(xpath="//select[@id='Zone']")
	private WebElement zone;
	
	
	@FindBy(xpath="//select[@id='Opted zone']")
	private WebElement Optedzone;
	
	@FindBy(xpath="(//a[@name='Policy Summary'])[2]")
	private WebElement policysummary;
	
	@FindBy(xpath="//button[@id='Submit']")
	private WebElement subbtn;
	
	@FindBy(xpath="//button[@id='moreInfo']")
	private WebElement subbtn3;
	
	@FindBy(xpath="//button[contains(text(),'Continue')]")
	private WebElement Continue;
	
	@FindBy(xpath="//button[@id='SaveButton']")
	private WebElement SaveButton;
	
	@FindBy(xpath="//button[contains(text(),'OK')]")
	private WebElement Okbutton;
	
	@FindBy(xpath="//button[@id='DataEntry1Completed']")
	private WebElement DataEntry1;
	
	@FindBy(xpath="//button[@id='sendQCBtn']")
	private WebElement SubmitButton;
	
	
	@FindBy(xpath="//button[@id='issuePolicyBtn']")
	private WebElement issuepolicy;
	
	//Member icon
	@FindBy(xpath="//a[@title='Member Info']/i")
	private WebElement membericon;
	
	
	//Policy Number
	@FindBy(xpath="//label[@id='Number']")
	private WebElement policynumber;
	
	
	//Close
	@FindBy(xpath="//button[@id='CloseButton']")
	private WebElement closebutton;
	
	
	
	//Follow Up
		@FindBy(xpath="(//a[@name='Follow-up'])[2]")
		private WebElement FollowUp;
		
		@FindBy(xpath="//button[@id='btncontinue']")
		private WebElement continuebtn;
	
		
		@FindBy(xpath="//label[@id='Sub-Status']")
		private WebElement DataEntryCompleted;
	
		
	
		
		
	//QU2020421200063700000
	
	 WebDriverWait wait;
		public OtherUserLogin(WebDriver driver) {
			super(driver);
			PageFactory.initElements(driver, this);
			wait=new WebDriverWait(driver, 30);	
		}
		
	public void fillAndSubmitNewTCSLogin(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup,CustomAssert customAssert) throws Exception
	{
		String sheetName = ConfigReader.getInstance().getValue(PropertyConfigs.TestSheet);
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, sheetName, testCaseName,stepGroup);
		Reporter.log("<B>Login To Application</B>");
		
		//BOPS to COPS
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
		
		
		//Click on Chronic
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
				
				
//		Thread.sleep(WaitTime.medium);
//		selectFromDropdownByVisibleText(Ischronic,dataRow.getProperty("IsChronic"),"Is Chronic");
//		Thread.sleep(WaitTime.low);
		
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
		
		
		//STP flow
	
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN);
//		Thread.sleep(WaitTime.low);
//		click(issuepolicy,"Issue Policy");
		Thread.sleep(WaitTime.low);
		
		click(Continue,"Continue");
		Thread.sleep(WaitTime.low);
		
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
		
		
	}

	public void fillOtherUserLogin(WebDriver driver, String testCaseName, XSSFWorkbook workbook, Connection conn,
			String stepGroup, CustomAssert customAssert) throws Exception {
		fillAndSubmitNewTCSLogin(driver, testCaseName, workbook, conn, stepGroup, customAssert);
		
	}
	
}
