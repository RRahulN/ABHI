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

public class NSTPindividualFlow extends GenericMethods{
	
	
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
	
	@FindBy(xpath="//select[@id='Manual Underwriting Required?']")
	private WebElement ManualUnderwriting;
	
	
	//Policy Number
	@FindBy(xpath="//label[@id='Number']")
	private WebElement policynumber;
	
	//Questionnaire tab
	@FindBy(xpath="//md-tab-item[@class='md-tab ng-scope ng-isolate-scope md-ink-ripple']//span[@class='ng-scope'][contains(text(),'Questionnaire')]")
	private WebElement Questionnairetab;
	
	//Questions
	@FindBy(xpath="//md-tab-content[@id='tab-content-0']//tr[18]//td[2]/following::input[1]")
	private WebElement PolioQuest;
	
	@FindBy(xpath="//md-tab-content[@id='tab-content-0']//tr[18]//td[2]/following::textarea[1]")
	private WebElement PolioTextBox;
	
	@FindBy(xpath="//td[contains(text(),'Is any of the persons proposed to be insured under')]/following::input[1]")
	private WebElement OtherthanvitaminpillsQuest;
	
	@FindBy(xpath="//td[contains(text(),'Is any of the persons proposed to be insured under')]/following::textarea[1]")
	private WebElement OtherthanvitaminpillsTextbox;
	
	@FindBy(xpath="//md-tab-content[@id='tab-content-0']//tr[20]//td[2]/following::input[1]")
	private WebElement BloodtestsQuest;
	
	@FindBy(xpath="//md-tab-content[@id='tab-content-0']//tr[20]//td[2]/following::textarea[1]")
	private WebElement BloodtestsTextbox;
	
	@FindBy(xpath="//md-tab-content[@id='tab-content-0']//tr[21]//td[2]/following::input[1]")
	private WebElement SurgeryQuest;
	
	@FindBy(xpath="//md-tab-content[@id='tab-content-0']//tr[21]//td[2]/following::textarea[1]")
	private WebElement SurgeryTextbox;
	
	@FindBy(xpath="//td[contains(text(),'Has any of the persons proposed to be insured Suff')]/following::input[1]")
	private WebElement viralfeverQuest;
	
	@FindBy(xpath="//td[contains(text(),'Has any of the persons proposed to be insured Suff')]/following::textarea[1]")
	private WebElement viralfeverTextbox;
	
	@FindBy(xpath="//td[contains(text(),'Any of the insured persons is pregnant? If yes, pl')]/following::input[1]")
	private WebElement dateofdeliveryQuest;
	
	@FindBy(xpath="//td[contains(text(),'Any of the insured persons is pregnant? If yes, pl')]/following::textarea[1]")
	private WebElement dateofdeliveryTextbox;
	
	@FindBy(xpath="//td[contains(text(),'Whether there is diabetes, hypertension or any oth')]/following::input[1]")
	private WebElement earlierpregnancyQuest;
	
	@FindBy(xpath="//td[contains(text(),'Whether there is diabetes, hypertension or any oth')]/following::textarea[1]")
	private WebElement earlierpregnancyTextbox;
	
	@FindBy(xpath="//label[@id='Sub-Status']")
	private WebElement ReferToUWRStatus;
	
	WebDriverWait wait;
	public NSTPindividualFlow(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 30);	
		}
	
	
	
	public void fillNSTPinfo(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup,CustomAssert customAssert) throws Exception
	{
		String sheetName = ConfigReader.getInstance().getValue(PropertyConfigs.TestSheet);
        Properties dataRow = ExcelRead.readRowDataInProperties(workbook, sheetName, testCaseName,stepGroup);
	   Reporter.log("<B>Traverse To CommonPage</B>");
	   
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
	   
       Thread.sleep(WaitTime.medium);
		clearAndSenKeys(heightfeet,dataRow.getProperty("HeightFeet"),"Height Feet");
		Thread.sleep(WaitTime.low);
		
		Thread.sleep(WaitTime.medium);
		clearAndSenKeys(weightinKG,dataRow.getProperty("WeightInKG"),"Weight In KG");
		Thread.sleep(WaitTime.low);
		weightinKG.sendKeys(Keys.TAB);
		
		Thread.sleep(WaitTime.medium);
		selectFromDropdownByVisibleText(occupation,dataRow.getProperty("Occupation"),"Occupation");
		Thread.sleep(WaitTime.low);
		
		Thread.sleep(WaitTime.medium);
		selectFromDropdownByVisibleText(ManualUnderwriting,"Yes","Manual Underwriting");
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
		
		Thread.sleep(WaitTime.medium);
	    selectFromDropdownByVisibleText(Optedzone,dataRow.getProperty("Zone"),"Zone");
		Thread.sleep(WaitTime.low);
		
		//Questionnaires
		
		if(dataRow.getProperty("QuestionnaireConfig")=="Yes") {
			
			
		Thread.sleep(WaitTime.medium);
	    click(Questionnairetab, "Questionnaire tab");
	    Thread.sleep(WaitTime.low);
	    
	    Thread.sleep(WaitTime.medium);
	    click(PolioQuest, "Polio Question RadioButton");
	    Thread.sleep(WaitTime.low);
	    
	    Thread.sleep(WaitTime.medium);
		clearAndSenKeys(PolioTextBox,dataRow.getProperty("PolioTextBox"),"Polio TextBox");
		Thread.sleep(WaitTime.low);
	    
	    Thread.sleep(WaitTime.medium);
	    click(OtherthanvitaminpillsQuest, "Otherthanvitaminpills Question RadioButton");
	    Thread.sleep(WaitTime.low);
	    
	    Thread.sleep(WaitTime.medium);
		clearAndSenKeys(OtherthanvitaminpillsTextbox,dataRow.getProperty("OtherthanvitaminpillsTextbox"),"Other than vitamin pills Textbox");
		Thread.sleep(WaitTime.low);
	    
	    Thread.sleep(WaitTime.medium);
	    click(BloodtestsQuest, "Bloodtests Question RadioButton");
	    Thread.sleep(WaitTime.low);
	    
	    Thread.sleep(WaitTime.medium);
		clearAndSenKeys(BloodtestsTextbox,dataRow.getProperty("BloodtestsTextbox"),"Bloodtests Textbox");
		Thread.sleep(WaitTime.low);
	    
	    Thread.sleep(WaitTime.medium);
	    click(SurgeryQuest, "Surgery Question RadioButton");
	    Thread.sleep(WaitTime.low);
	    
	    Thread.sleep(WaitTime.medium);
		clearAndSenKeys(SurgeryTextbox,dataRow.getProperty("SurgeryTextbox"),"Surgery Textbox");
		Thread.sleep(WaitTime.low);
	    
	    Thread.sleep(WaitTime.medium);
	    click(viralfeverQuest, "viralfever Question RadioButton");
	    Thread.sleep(WaitTime.low);
	    
	    Thread.sleep(WaitTime.medium);
		clearAndSenKeys(viralfeverTextbox,dataRow.getProperty("viralfeverTextbox"),"viralfever Textbox");
		Thread.sleep(WaitTime.low);
	    
	    Thread.sleep(WaitTime.medium);
	    click(dateofdeliveryQuest, "dateofdelivery Question RadioButton");
	    Thread.sleep(WaitTime.low);
	    
	    Thread.sleep(WaitTime.medium);
		clearAndSenKeys(dateofdeliveryTextbox,dataRow.getProperty("dateofdeliveryTextbox"),"date of delivery Textbox");
		Thread.sleep(WaitTime.low);
	    
	    Thread.sleep(WaitTime.medium);
	    click(earlierpregnancyQuest, "earlierpregnancy Question RadioButton");
	    Thread.sleep(WaitTime.low);
	    
	    Thread.sleep(WaitTime.medium);
		clearAndSenKeys(earlierpregnancyTextbox,dataRow.getProperty("earlierpregnancyTextbox"),"earlier pregnancy Textbox");
		Thread.sleep(WaitTime.low);
	    
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
		
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN);
		Thread.sleep(WaitTime.low);
		click(SubmitButton,"Submit");
		Thread.sleep(WaitTime.low);
		
		//fetch Refer to UWR
		
		String SubStatusUWR=ReferToUWRStatus.getText();
		Reporter.log("----------");
		Reporter.log("Status changed to "+SubStatusUWR);
		Reporter.log("---------");
	    
		}
	    
	    
	    
}
}
