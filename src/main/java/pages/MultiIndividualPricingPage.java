package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import javax.annotation.Nullable;

import org.apache.commons.collections.Predicate;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.codoid.products.fillo.Connection;
//import com.google.common.base.Predicate;

import constants.PropertyConfigs;
import junit.framework.Assert;
import testRunner.TestEngine;
import util.ConfigReader;
import util.CustomAssert;
import util.ExcelRead;
import util.GenericMethods;
import util.SetUpWebdriver;
import util.WaitTime;

public class MultiIndividualPricingPage extends GenericMethods {
	
	


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
	
//	@FindBy(xpath="(//input[@id='Member Name']//preceding::select[1])[1]")
//	private WebElement SI;
	
	@FindBy(xpath="(//input[@id='Member Name'])[1]")
	private WebElement membername;
	
//	@FindBy(xpath="(//input[@id='Member Name']//following::select[3])[1]")
//	private WebElement deductible;
	
	@FindBy(xpath="(//input[@id='Date of Birth'])[1]")
	private WebElement dob;
	
	@FindBy(xpath="(//input[@id='Date of Birth']//following::select[1])[1]")
	private WebElement gender;
	
	@FindBy(xpath="(//input[@id='Date of Birth']//following::select[2])[1]")
	private WebElement relation;
	
//	@FindBy(xpath="(//input[@id='Date of Birth']//following::select[3])[1]")
//	private WebElement room;
	
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
	
	@FindBy(xpath="//input[@name='WMCP']")
	//Co-Pay Waiver
	private WebElement wmpcpCheckbox ;
	
	//maternity express
	@FindBy(xpath="//input[@name='MTEX']")
	private WebElement mtexCheckbox ;
	
	@FindBy(xpath="//select[@id='Applicable Sum Insured']")
	private WebElement opdeDropDown;
	
	
	@FindBy(xpath="")
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
	public MultiIndividualPricingPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 40);	
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
		Thread.sleep(2000);
		//System.out.println(dataRow.getProperty("IntermediaryCode"));
		clearAndSenKeys(intermediarycodeField,dataRow.getProperty("IntermediaryCode"),"InterMediaryCode ");
		Thread.sleep(WaitTime.low);
		click(intermediarysearch, " search ");
		switchToWindow(driver);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'"+dataRow.getProperty("IntermediaryCode")+"')]")).click();
		driver.switchTo().window(parentWindow);
		//System.out.println(parentWindow);
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
		Thread.sleep(WaitTime.medium);
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
		
		
		int MemSize = Integer.parseInt(dataRow.getProperty("NoOfMembers"));
		
		
		//Random String Generator
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder(20);
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		//String name = sb.toString();
		
		String Family = dataRow.getProperty("Relation");
		String Family1 = Family.replace(" ", "");
		ArrayList<String> Relationlist = new ArrayList<String>(Arrays.asList(Family1.split("\\+")));
		
		String SIval = dataRow.getProperty("SumInsured");
		String SI1val = SIval.replace(" ", "");
		ArrayList<String> SumInsuredList = new ArrayList<String>(Arrays.asList(SI1val.split("\\+")));
		
		String zoneval = dataRow.getProperty("Zone");
		//String zoneval1 = zoneval.replace(" ", "");
		ArrayList<String> zonelist = new ArrayList<String>(Arrays.asList(zoneval.split("\\+")));
		
		
		String deductval = dataRow.getProperty("Deductible");
		String deductval1= deductval.replace(" ", "");
		ArrayList<String> deductlist = new ArrayList<String>(Arrays.asList(deductval1.split("\\+")));
		
		String dobval = dataRow.getProperty("DateOfBirth");
		String dobval1= dobval.replace(" ", "");
		ArrayList<String> doblist = new ArrayList<String>(Arrays.asList(dobval1.split("\\+")));
		
		String genderval = dataRow.getProperty("Gender");
		String genderval1= genderval.replace(" ", "");
		ArrayList<String> genderlist = new ArrayList<String>(Arrays.asList(genderval1.split("\\+")));
		
		String roomval = dataRow.getProperty("RoomCategory");
		//String roomval1= roomval.replace(" ", "");
		ArrayList<String> roomlist = new ArrayList<String>(Arrays.asList(roomval.split("\\+")));
		
		String ischronicval = dataRow.getProperty("IsChronic");
		String ischronicval1= ischronicval.replace(" ", "");
		ArrayList<String> ischroniclist = new ArrayList<String>(Arrays.asList(ischronicval1.split("\\+")));
		
		String chronicval = dataRow.getProperty("Chronic");
		//String chronicval1= chronicval;
		ArrayList<String> chroniclist = new ArrayList<String>(Arrays.asList(chronicval.split("\\+")));
		
		
		HashMap<String, String> NamesList = new HashMap<>(); 
		NamesList.put("Self","Tom");
		NamesList.put("Spouse","Emily");
		NamesList.put("Brother","Jacob");
		NamesList.put("Brother-in-law","Olivier");
		NamesList.put("Sister","Isabella");
		NamesList.put("Sister-in-law","Sophie");
		NamesList.put("Son","Archie");
		NamesList.put("Son-in-law","Alexander");
		NamesList.put("Son","Archie");
		NamesList.put("Son","Archie");
		NamesList.put("Son","Archie");
		
		
		NamesList.put("Kid1","Harry");
		NamesList.put("Kid2","Jacl");
		NamesList.put("Kid3","Thomas");
		NamesList.put("Father","Noah");
		NamesList.put("Mother","Female");
		NamesList.put("Father-in-law","George");
		NamesList.put("Mother-in-law","Olivia");
		
		ArrayList<String> Names = new ArrayList<String>();
		Names.add("Self");
		Names.add("Spouse");
		Names.add("Brother");
		Names.add("Brother-in-law");
		Names.add("Sister");
		Names.add("Sister-in-law");
		Names.add("Father");
		Names.add("Father-in-law");
		Names.add("Mother");
		Names.add("Mother-in-law");
		Names.add("Son");
		Names.add("Son-in-law");
		Names.add("Granddaughter");
		Names.add("Grandfather");
		Names.add("Grandmother");
		Names.add("Grandson");
		Names.add("Nephew");
		Names.add("Niece");
		Names.add("Daughter");
		Names.add("Daughter-in-law");
		
		
		
		//Member Details
		for (int x = 0;x<MemSize;x++)
		{
			int y = x+1;
			
			//WebElement MemberName = driver.findElement(By.xpath("(//input[@id='Member Name'])["+y+"]"));
			WebElement MemberName = driver.findElement(By.xpath("(//input[@id='Member Name'])["+y+"]"));
			WebElement dob = driver.findElement(By.xpath("(//input[@id='Date of Birth'])["+y+"]"));
			WebElement gender = driver.findElement(By.xpath("(//input[@id='Date of Birth']//following::select[1])["+y+"]"));
			WebElement relationship = driver.findElement(By.xpath("(//input[@id='Date of Birth']//following::select[2])["+y+"]"));
			WebElement SI = driver.findElement(By.xpath("(//input[@id='Member Name']//preceding::select[1])["+y+"]"));
			WebElement zone = driver.findElement(By.xpath("(//input[@id='Member Name']//following::select[1])["+y+"]"));
			WebElement deduct = driver.findElement(By.xpath("(//input[@id='Member Name']//following::select[3])["+y+"]"));
			WebElement room = driver.findElement(By.xpath("(//input[@id='Date of Birth']//following::select[3])["+y+"]"));
			
		
				
				Thread.sleep(WaitTime.medium);
				
				selectFromDropdownByVisibleText(driver.findElement(By.xpath("(//input[@id='Member Name']//preceding::select[1])["+y+"]")),SumInsuredList.get(x)," SumInsured ");
				Thread.sleep(WaitTime.low);
				
				Thread.sleep(WaitTime.medium);
				clearAndSenKeys(driver.findElement(By.xpath("(//input[@id='Member Name'])["+y+"]")), getRandomString(), "Member ");
				//driver.findElement(By.xpath("(//input[@id='Member Name'])["+y+"]")).sendKeys(name);
				Thread.sleep(WaitTime.low);
				
				
				Thread.sleep(WaitTime.medium);
				selectFromDropdownByVisibleText(driver.findElement(By.xpath("(//input[@id='Member Name']//following::select[1])["+y+"]")),zonelist.get(x)," SumInsured ");
				Thread.sleep(WaitTime.low);
				
				Thread.sleep(WaitTime.medium);
				selectFromDropdownByVisibleTextStale(driver.findElement(By.xpath("(//input[@id='Member Name']//following::select[3])["+y+"]")),deductlist.get(x)," SumInsured ");
				Thread.sleep(WaitTime.low);
			
				
				Thread.sleep(WaitTime.medium);
				clearAndSenKeysStale(driver.findElement(By.xpath("(//input[@id='Date of Birth'])["+y+"]")),doblist.get(x)," Self DOB ");
				Thread.sleep(WaitTime.low);
				driver.findElement(By.xpath("(//input[@id='Date of Birth'])["+y+"]")).sendKeys(Keys.TAB);
				
				
				Thread.sleep(WaitTime.medium);
				selectFromDropdownByVisibleTextStale(driver.findElement(By.xpath("(//input[@id='Date of Birth']//following::select[1])["+y+"]")),genderlist.get(x)," Gender ");
				Thread.sleep(WaitTime.low);

				//Relationship
				{
				Thread.sleep(WaitTime.medium);
				selectFromDropdownByVisibleTextStale(driver.findElement(By.xpath("(//input[@id='Date of Birth']//following::select[2])["+y+"]")),Relationlist.get(x)," Relationship ");
				Thread.sleep(WaitTime.low);
				}
				Thread.sleep(WaitTime.medium);
				selectFromDropdownByVisibleTextStale(driver.findElement(By.xpath("(//input[@id='Date of Birth']//following::select[3])["+y+"]")),roomlist.get(x)," Room ");
				Thread.sleep(WaitTime.low);
				
				//click on Chronic
				if (ischroniclist.get(x).equalsIgnoreCase("Yes"))
				{
				String Chronic = chroniclist.get(x);
				String na = "n/a";
				
				if(Chronic != na) {
				ArrayList Chroniclist= new ArrayList(Arrays.asList(Chronic.split(",")));
				for(int i =0;i<Chroniclist.size();i++)
				{
				WebElement Chronicclick = driver.findElement(By.xpath("(//option[contains(text(),'"+Chroniclist.get(i)+"')])["+y+"]"));

				clickWithoutJavaScript(Chronicclick, " Chronic ");
				Reporter.log(" as "+Chroniclist.get(i));
				}
				}
				}

		}
		
		
		Thread.sleep(WaitTime.low);	
		if(dataRow.getProperty("TestCase").equalsIgnoreCase("QuoteCreation"))
		{
		SetUpWebdriver.captureScreenShot(driver, TestEngine.excutionFolder+ConfigReader.getInstance().getValue(PropertyConfigs.screenShotFolder),dataRow.getProperty("TCID"));
		}
		click(calpremBTN, "Calculate Premium Button");
		Thread.sleep(WaitTime.high);
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='btnSave']")));
		click(saveBTN," SaveButton ");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
		if(dataRow.getProperty("TestCase").equalsIgnoreCase("QuoteCreation"))
		{
		SetUpWebdriver.captureScreenShot(driver, TestEngine.excutionFolder+ConfigReader.getInstance().getValue(PropertyConfigs.screenShotFolder),dataRow.getProperty("TCID"));
		}
		click(saveokBTN, "Ok ");
		Thread.sleep(3000);
		Reporter.log("<B> Quotation:- </B> "+refno2.getText());
		Reporter.log("                     ");
		Reporter.log("---------------------");
		
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
		
		if(dataRow.getProperty("TestCase").equalsIgnoreCase("Calculation"))
		{
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
		Reporter.log("");
		Reporter.log("<B> -------------------------------------------</B>");
		Reporter.log("<B>  No covers Attached </B>");
		Reporter.log("<B> NetPremiumBefore Value:-  </B>"+netpremiumbefore.getText());
		Reporter.log("<B> Discount:-  </B>"+Discount.getText());
		Reporter.log("<B> NetPremiumAfter Value:-  </B>"+netpremiumafter.getText());
		Reporter.log("<B> Loading Value:-  </B>"+loading.getText());
		Reporter.log("<B> NetPremiumAfter Loading Value:-  </B>"+netpremiumafterloading.getText());
		Reporter.log("<B> Tax Amount Element Value:-  </B>"+taxamountElement.getText());
		Reporter.log("<B> Premium Inclusive of Tax Value Value:-  </B>"+premiuminclusiveofTAX.getText());
		Reporter.log("<B> -------------------------------------------</B>");
		}
		
		
		//Assert Quote Details

	String netpremiumbeforeval = netpremiumbefore.getText().toString().replace("₹ ", "").replace(",", "");
	Assert.assertEquals("Expected value",netpremiumbeforeval, dataRow.getProperty("NetPremiumBeforeDiscouunt(BeforeOPD)").replace(",", ""));
		
		
		//CO-Pay Wavier
		String isCoPay = dataRow.getProperty("Co-Pay Waiver");
		String isCoPay1= isCoPay.replace(" ", "");
		ArrayList<String> isCoPaylist= new ArrayList<String>(Arrays.asList(isCoPay1.split("\\+")));
		
		Thread.sleep(WaitTime.medium);
		for (int x = 0;x<isCoPaylist.size();x++)
		{
		if(isCoPaylist.get(x).equalsIgnoreCase("Yes"))
		{
			int y = x+1;
			Thread.sleep(WaitTime.medium);
			click(driver.findElement(By.xpath("(//input[@name='WMCP'])["+y+"]"))," Hospital Cash Benefit checkBOX");
			Thread.sleep(WaitTime.medium);
			
			click(calpremBTN, "Calculate Premium Button");
			Thread.sleep(WaitTime.medium);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
			if (saveokBTN .isEnabled()) {
			click(saveokBTN,"OK");
		}	}
		}
		
		
		
		//IF OPD applicable
		String isOPD = dataRow.getProperty("OPDapplicable");
		String isOPD1= isOPD.replace(" ", "");
		ArrayList<String> isOPDlist= new ArrayList<String>(Arrays.asList(isOPD1.split("\\+")));
		
		String OPDval = dataRow.getProperty("OPDsi");
		String OPDval1= OPDval.replace(" ", "");
		ArrayList<String> OPDvallist= new ArrayList<String>(Arrays.asList(OPDval1.split("\\+")));
		
		Thread.sleep(WaitTime.medium);
		for (int x = 0;x<isOPDlist.size();x++)
		{
		if(isOPDlist.get(x).equalsIgnoreCase("Yes"))
		{
			int y = x+1;
			Thread.sleep(WaitTime.medium);
			click(driver.findElement(By.xpath("(//input[@name='OPDE'])["+y+"]")),"OPDE checkBOX");
			Thread.sleep(WaitTime.medium);
			
			boolean okBTN = driver.findElements(By.xpath("//button[contains(text(),'OK')]")).size() !=0;
			
			if (okBTN == true) {
				click(saveokBTN,"OK");
			}
			
			Thread.sleep(WaitTime.medium);
			
			if (driver.findElement(By.xpath("(//select[@id='Applicable Sum Insured'])[1]")).isEnabled()) {
				selectFromDropdownByVisibleText(driver.findElement(By.xpath("(//select[@id='Applicable Sum Insured'])[1]")), OPDvallist.get(x)," OPD Expenses SumInsured ");

			}
			else
			{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//select[@id='Applicable Sum Insured'])["+y+"]")));	
			selectFromDropdownByVisibleText(driver.findElement(By.xpath("(//select[@id='Applicable Sum Insured'])["+y+"]")), OPDvallist.get(x)," OPD Expenses SumInsured ");
			Thread.sleep(WaitTime.medium);
			}
			click(calpremBTN, "Calculate Premium Button");
			Thread.sleep(15000);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave")));	
			
			Reporter.log("");
			Reporter.log("<B> -------------------------------------------</B>");
			Reporter.log("<B>After OPD calculated "+y+"</B>");
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
		
		
		
		//IF HOSPITAL CSH BENEFIT IS APPLICABLE
		String isHCB = dataRow.getProperty("HospitalCashBenefit");
		String isHCB1= isHCB.replace(" ", "");
		ArrayList<String> isHCBlist= new ArrayList<String>(Arrays.asList(isHCB1.split("\\+")));
		
		String isHCBval = dataRow.getProperty("HCBsi");
		String isHCBval1= isHCBval.replace(" ", "");
		ArrayList<String> isHCBvallist= new ArrayList<String>(Arrays.asList(isHCBval1.split("\\+")));
		
		Thread.sleep(WaitTime.medium);
		for (int x = 0;x<isHCBlist.size();x++)
		{
		if(isHCBlist.get(x).equalsIgnoreCase("Yes"))
		{
			int y = x+1;
			Thread.sleep(WaitTime.medium);
			click(driver.findElement(By.xpath("(//input[@name='HSCB'])["+y+"]"))," Hospital Cash Benefit checkBOX");
			Thread.sleep(WaitTime.low);
			
//			//click on OK Quote button
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
//			click(driver.findElement(By.xpath("//button[contains(text(),'OK')]")), "Ok ");
			
			Thread.sleep(35000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//b[contains(text(),'HSCB - Hospital Cash Benefit')]//following::select[1])["+y+"]")));	
			selectFromDropdownByVisibleText(driver.findElement(By.xpath("(//b[contains(text(),'HSCB - Hospital Cash Benefit')]//following::select[1])["+y+"]")),isHCBvallist.get(x) ," Hospital Cash Benefit  Expenses");
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
		}
		
		
		
		//ELSE-IF MaternityExpense
		String ismaternity = dataRow.getProperty("MaternityExpense");
		String ismaternity1= ismaternity.replace(" ", "");
		ArrayList<String> ismaternitylist= new ArrayList<String>(Arrays.asList(ismaternity1.split("\\+")));
		
		Thread.sleep(WaitTime.medium);
		for(int x = 0;x<ismaternitylist.size();x++)
		{
		if(ismaternitylist.get(x).equalsIgnoreCase("Yes"))
		{
			int y = x+1;
			Thread.sleep(WaitTime.medium);
			click(driver.findElement(By.xpath("(//input[@name='MTEX'])"))," Maternity Expense checkBOX");
			Thread.sleep(WaitTime.medium);
			
			click(calpremBTN,"");
			
			boolean okBTN = driver.findElements(By.xpath("//button[contains(text(),'OK')]")).size() !=0;
			
			if (okBTN == true) {
				click(saveokBTN,"OK");
			}
		}	
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
