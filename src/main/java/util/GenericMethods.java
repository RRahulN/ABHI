package util;


import java.io.File;
import java.io.IOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.github.javafaker.Faker;

import constants.PropertyConfigs;
import core.DateGenerator;
import testRunner.TestEngine;



public class GenericMethods {

	WebDriver driver;
	WebDriverWait wait;
	
	static String QuoteNo;
		
	public static String getQuoteNo() {
		return QuoteNo;
	}
	
	public static void setQuoteNo(String quoteNo) {
		QuoteNo = quoteNo;
	}
	
	
	
	
	
	
	public GenericMethods() {

	}
	public GenericMethods(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 700);
	}
	
	
	public String getuniqueApplicationNo() {
        String SALTCHARS = "0123456789";
      StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
       while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
       return saltStr;

   }

	public static boolean executionFlag=true;
	public  int counter=1;
	
//	public void verifyAssert(int expected,int actual, String message) throws Exception {
//		try {
//			Assert.assertEquals(expected, actual);
//			Reporter.log("<B><Font color=\"Yellow\">"+counter+".   "+message+"       => PASSED</Font></B>");
//			Reporter.log("<B>Expected =  "+expected+"</Font></B>");
//			Reporter.log("<B>Actual =  "+actual+"</Font></B>");
//		}catch(AssertionError assertionError){
//			executionFlag=false;
//			Reporter.log("<B><Font color=\"Yellow\">"+counter+".   "+message+"       => FAILED</Font></B>");
//			Reporter.log("<B>Expected =  "+expected+"</Font></B>");
//			Reporter.log("<B>Actual =  "+actual+"</Font></B>");
//			SetUpWebdriver.captureScreenShot(driver, TestEngine.excutionFolder+ConfigReader.getInstance().getValue(PropertyConfigs.screenShotFolder), new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date()) );
//		}finally {
//			counter++;
//		}
//	}
	
	
	
	public static String getRandomString()
	{
		Faker faker = new Faker();
		String firstName = faker.name().firstName();
        return firstName;

    }
	

	//Anmol 11-06-2020 WaitClick
	public void click(WebElement webElement, String webElementName) throws InterruptedException {
		highlighter(webElement);
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", webElement);
		//webElement.click();
		Reporter.log("Clicked on <B> "+ webElementName +"</B> ");
	}

	

//	public void click(WebElement webElement, String webElementName) throws InterruptedException {
//		JavascriptExecutor executor = (JavascriptExecutor) driver;
//		executor.executeScript("arguments[0].click();", webElement);
//		//webElement.click();
//		Reporter.log("Clicked on <B>"+ webElementName +"</B> ");
//	}

	public void actionClick(WebDriver driver,WebElement webElement, String webElementName) throws InterruptedException {

		highlighter(webElement);
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
		Actions act=new Actions(driver);
		act.moveToElement(webElement).perform();
		webElement.sendKeys(Keys.ENTER);
		Reporter.log("Clicked on <B>"+ webElementName +"</B> ");

	}


	//Anmolclear and send keys Without js
	public void clearAndSenKeysStale(WebElement webElement, String data,String fieldName) {
		//wait.until(ExpectedConditions.elementToBeClickable(webElement));
		//webElement.clear();
		webElement.sendKeys(data);
		Reporter.log("<B>"+ data +"</B> is entered in  "+fieldName+" text field");
	}
	
	public void selectFromDropdownByVisibleTextStale(WebElement webElement, String visibleText,String fieldname) {
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
		Select selectVisibleText = new Select(webElement);
		selectVisibleText.selectByVisibleText(visibleText);
		Reporter.log("<B>"+ visibleText +"</B> is selected from "+ fieldname);
	}
	
	
	
	public void clearAndSenKeys(WebElement webElement, String data,String fieldName) {
		highlighter(webElement);
		webElement.clear();
		webElement.sendKeys(data);
		Reporter.log("<B>"+ data +"</B> is entered in  "+fieldName+" text field");
	}
	
	
	public static void clrscr() throws InterruptedException{
      //Clears Screen in java

		try
		   {
		      Runtime r = Runtime.getRuntime ();
		      Process p = r.exec ("mode.com con cols=80 lines=25");
		      try
		      {
		         p.waitFor();
		      }
		      catch (InterruptedException ie) {}
		    }
		    catch (IOException ioe)
		    {
		       ioe.printStackTrace();
		    }

	}
	
	
	
	public void clearAndSenKeysAutoComplete(WebElement webElement,By by, String data,String fieldName) throws InterruptedException {
		highlighter(webElement);
		webElement.clear();
		webElement.sendKeys(data);
		Thread.sleep(2000);
		List<WebElement> optionsToSelect =webElement.findElements(by);
		for(WebElement option : optionsToSelect){
	        if(option.getText().equals(data)) {
	            option.click();
	            break;
	        }
	    }
		Reporter.log("<B>"+ data +"</B> is entered in  "+fieldName+" text field");
	}
	
	
	public void selectFromDropdownByVisibleText(WebElement webElement, String visibleText,String fieldname) {

		//wait.until(ExpectedConditions.elementToBeClickable(webElement));
		highlighter(webElement);
		Select selectVisibleText = new Select(webElement);
		selectVisibleText.selectByVisibleText(visibleText);
		Reporter.log("<B>"+ visibleText +"</B> is selected from "+ fieldname);
	}

	public void selectFromDropdownById(WebElement webElement, int index, String fieldname) {
		highlighter(webElement);
		Select selectindex = new Select(webElement);
		selectindex.selectByIndex(index);
		Reporter.log("index <B>"+ index +"</B> is selected from "+ fieldname);
	}

	public void selectFromDropdownByValue(WebElement webElement, String value, String fieldname) {
		highlighter(webElement);
		Select selectvalue = new Select(webElement);
		selectvalue.selectByValue(value);
		Reporter.log("<B>"+ value +"</B> is selected from "+ fieldname);
	}

	public void selectCheckBox(WebElement webElement, String checkBoxName) {
		highlighter(webElement);
		if(!webElement.isSelected()) {
			webElement.click();
			Reporter.log("<B>"+ checkBoxName +"</B> is checked");
		}
	}
	public void uncheckCheckbox(WebElement webElement, String checkBoxName) {
		highlighter(webElement);
		if(webElement.isSelected()) {
			webElement.click();
			Reporter.log("<B>"+ checkBoxName +"</B> is Unchecked");
		}
		else {
			
			Reporter.log("<B>"+ checkBoxName +"</B> is Already Checked");
		}
		
	}

	public void switchToWindow(WebDriver driver) {
		String parentWindow = driver.getWindowHandle();
		Set<String> multiWindows = driver.getWindowHandles();
		for(String winHandles : multiWindows) {
			if(!winHandles.equalsIgnoreCase(parentWindow)){
				driver.switchTo().window(winHandles);
				driver.manage().window().maximize();
				Reporter.log("Switched to <B>"+driver.getTitle()+"</B> window");
				//System.out.println(driver.getCurrentUrl());
			}
			
		}
		
	}

	

	public void selectRadioButton(WebElement webElement, String radioButtonName) {
		highlighter(webElement);
		if(!webElement.isSelected()) {
			webElement.click();
			Reporter.log("<B>"+radioButtonName+"</B> is selected");
		}
	}

	public void highlighter(WebElement webElement) {
		((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'",webElement);
	}


	// Added by Amiya on 23-08-2018 for splitting proposal into prefix and suffix
	public String ProposalNumberPrefix(String proposalNumber) {
		String prefix=proposalNumber;
		if(proposalNumber.length()==10) {
			prefix=proposalNumber.substring(0, proposalNumber.length()-1);
		}
		return prefix;
	}

	public String ProposalNumberSuffix(String proposalNumber) {
		String suffix="";
		if(proposalNumber.length()==10) {
			suffix=proposalNumber.substring(proposalNumber.length()-1);
		}
		return suffix;
	}

	public void clickOnShowView(WebDriver driver) throws InterruptedException {
		if(driver.findElement(By.id("menuButton")).getText().equalsIgnoreCase("Show Menu")) {
			Reporter.log("<B>Landing Page</B>");
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			highlighter(driver.findElement(By.id("menuButton")));
			//((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'",driver.findElement(By.id("menuButton")) );
			executor.executeScript("arguments[0].click();", driver.findElement(By.id("menuButton")));
			//driver.findElement(By.id("menuButton")).click();
			Reporter.log("Clicked on <B>Show Menu</B> link");	
			Thread.sleep(WaitTime.low);
		}
	}

	// TODO Amiya added 28/08/2018
	public int numberOfDays(String dateBefore, String dateAfter) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = simpleDateFormat.parse(dateBefore);
		Date date2 = simpleDateFormat.parse(dateAfter);
		int daysBetween = (int) ((date2.getTime() - date1.getTime())/(1000*60*60*24));
		return daysBetween;
	}
	/*// TODO Amiya added 28/08/2018
	public void setSessionDate(XSSFWorkbook workbook, String userName, String date_in_dd_MMM_yy) throws InterruptedException, SQLException, Exception {
		UPDATE TUSES@INDIGO_INGENIUM
			SET USER_SESN_PRCES_DT='02-NOV-17' WHERE USER_ID='BHUSHAN';
			COMMIT;

		//TODO updated by Amiya 21-09-2018		
       
		DatabaseConnectionUtils databaseConnectionUtils=new DatabaseConnectionUtils();
		Connection databaseconnection=databaseConnectionUtils.connection_With_Database(workbook, "DatabaseLogin", "Ingenium_UAT");
		Statement statement = databaseconnection.createStatement();
	   try
	      {
		statement.executeUpdate("UPDATE TUSES SET USER_SESN_PRCES_DT='"+date_in_dd_MMM_yy+"' WHERE USER_ID='"+userName.toUpperCase()+"'");
	      }
        catch (Exception e) {
			if(date_in_dd_MMM_yy.contains("-")) {
				date_in_dd_MMM_yy.replaceAll("-", "/");
			}
			SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat format2=new SimpleDateFormat("dd/MMM/yyyy");
			Date date=format1.parse(date_in_dd_MMM_yy);	
			statement.executeUpdate("UPDATE TUSES SET USER_SESN_PRCES_DT='"+format2.format(date)+"' WHERE USER_ID='"+userName.toUpperCase()+"'");
		}
       finally {
    	databaseconnection.commit();
   		databaseconnection.close();
   		Reporter.log("Session date <B>"+ date_in_dd_MMM_yy + "</B> set for user <B>"+userName+"</B>");
	}
	}
*/
	//TODO Amiya Added 30/08/2018
	public boolean isDisplayed(WebElement element) {
		boolean flag= true;
		try {
			if(element.isDisplayed()) {
				flag = true;
			}		
		}catch (NoSuchElementException e) {
			// TODO: handle exception
			flag = false;
		}
		return flag;
	}
	//TODO Amiya Added 30/08/2018
	public void freezeMenu(WebDriver driver) throws InterruptedException {
		//highlighter(driver.findElement(By.id("pinMenuButton")));
		click(driver.findElement(By.id("pinMenuButton")), "Pin Menu Icon");
		Thread.sleep(WaitTime.veryLow);
	}

	//TODO Amiya Added 30/08/2018
	public String fetchTextFromApplication(WebElement element, String fieldName) {

		highlighter(element);
		String data = element.getText().trim();
		Reporter.log(fieldName+": <B>"+data+" </B> fetched from "+fieldName);
		return data;
	}
	
	//TODO Amiya Added 30/08/2018
		public String fetchTextFromAngularApplication(WebElement element, String fieldName) {

			highlighter(element);
			String data = element.getAttribute("value").trim();
			Reporter.log(fieldName+": <B>"+data+" </B> fetched from "+fieldName);
			return data;
		}
		
		public String fetchTextFromAngularApplicationUsingJquery(String element,int i, String fieldName) {
	
			String data = (String) ((JavascriptExecutor)driver).executeScript("return $('input["+element+"]:eq("+i+")').val();");
			Reporter.log(fieldName+": <B>"+data+" </B> fetched from "+fieldName);
			return data.trim();
		}

	//TODO Amiya 31-08-2018
	public boolean errorCapture(List<WebElement> element, String msg) {
		Boolean flag=false;
		   outer:   if(element.size()!=0) {
			if(element.get(0).getText().equalsIgnoreCase(msg)) {
				highlighter(element.get(0));
				Reporter.log("<B><Font Color=\"Orange\">Message: "+element.get(0).getText());
				flag=true;
				
			}
			break outer;
		      }
		Reporter.log("</Font></B>");
		return flag;
	}

	/*//TODO Amiya 04-09-2018
	public void verifyErrorMessage (WebDriver driver,String testScenarioID, XSSFWorkbook workbook, com.codoid.products.fillo.Connection conn,String stepGroup,CustomAssert customAssert,String Sheetname) throws Exception {
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, Sheetname, testScenarioID,stepGroup);
		if(!dataRow.getProperty("ErrorMessage").equalsIgnoreCase("")) {
			customAssert.verifyAssert(true, errorCapture(driver, dataRow.getProperty("ErrorMessage")), "Verify Error Message");
		}
	}*/
	//TODO Amiya added 24/09/2018
	
	public void navigateToIngeniumURL(WebDriver driver) throws Exception {
		if(ConfigReader.getInstance().getValue("ExecutionMode").equalsIgnoreCase("Migration")) {
			driver.get(ConfigReader.getInstance().getValue("IngeniumMigrationURL"));
			Thread.sleep(WaitTime.low);
		}else if(ConfigReader.getInstance().getValue("ExecutionMode").equalsIgnoreCase("Regression")) {
			driver.get(ConfigReader.getInstance().getValue("IngeniumURL"));
			Thread.sleep(WaitTime.low);
		}
		Reporter.log("Navigated to <B>"+ driver.getTitle() +"</B>");
	}

	//TODO Amiya 25-09-2018
	public void switchtoframe(WebDriver driver,Integer index) {
		driver.switchTo().frame(index);
		Reporter.log("Switch to frame()");
	}

	//TODO Amiya 25-09-2018
	public void switchtoframe(WebDriver driver,String name) {
		driver.switchTo().frame(name);
	}

	//TODO Amiya 25-09-2018
	public void switchtodefaultframe(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	//TODO Amiya 28-09-2018
	public void switchToWindow(WebDriver driver, String windowname) throws InterruptedException {
		Set<String> windowhandles=driver.getWindowHandles();
		for(String winodow:windowhandles) {
			driver.switchTo().window(winodow);
		}
		//	driver.switchTo().window(windowname);
		Reporter.log("Switch to window("+driver.getTitle()+")");
		Thread.sleep(WaitTime.low);
		driver.switchTo().defaultContent();
		System.out.println(driver.getTitle());
	}

	//TODO Amiya 04/10/2018
	public void errorMessageCapture(WebDriver driver) {

		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='messageArea']/div/table/tbody/tr"))));
		List<WebElement> ErrorList=driver.findElements(By.xpath("//*[@id='messageArea']/div/table/tbody/tr"));
		if(ErrorList.size()>0) {
			WebElement ErrorMessage;
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='messageArea']/div/table/tbody/tr"))));
			Reporter.log("<B><Font Color=\"Orange\">Message: </B>");
			for(int a=1;a<=ErrorList.size();a++) {	
				//highlighter(driver.findElement(By.xpath("//*[@id='messageArea']/div/table/tbody/tr["+(a)+"]")));
				ErrorMessage=driver.findElement(By.xpath("//*[@id='messageArea']/div/table/tbody/tr["+(a)+"]"));		
				highlighter(ErrorMessage);
				Reporter.log(ErrorMessage.getText());
			}
			Reporter.log("</Font>");
		}
		/*else
		{
			System.out.println("Message not displayed");
		}*/
	}
	//TODO Amiya added 04/10/2018
	public void clickWithoutJavaScript(WebElement webElement, String webElementName) throws InterruptedException {
		highlighter(webElement);
		webElement.click();
		Reporter.log("Clicked on <B>"+ webElementName +"</B> ");
	}
	
	/*public void javascriptClick(WebElement webElement, String webElementName) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		webElement element=(webElement)executor.executeScript();
		
	}
*/
	public void click_driver(WebElement webElement, String webElementName) throws InterruptedException {
		//JavascriptExecutor executor = (JavascriptExecutor) driver;
		//		highlighter(webElement);
		//executor.executeScript("arguments[0].click();", webElement);
		Thread.sleep(WaitTime.veryLow);
		highlighter(webElement);
		webElement.click();
		Reporter.log("Clicked on <B>"+ webElementName +"</B> ");
	}

	//TODO Amiya 25-10-2018
	public void switchtoframe(WebElement iFrame, String Framename) {
		driver.switchTo().frame(iFrame);
		Reporter.log("switchtoframe("+Framename+")");
	}

	//TODO Amiya Added 05/11/2018
	public String fetchTextFromEditBox(WebElement element, String fieldName) {
		highlighter(element);
		String data = element.getAttribute("value").trim();
		Reporter.log(fieldName+": <B>"+data+" </B> fetched from "+fieldName);
		return data;
	}

	//TODO Amiya added 13/11/2018
	public String ageConvertMinorToMajor(String dateOfBirth, int numberOfYear) throws ParseException {	
		//Date DOB = new SimpleDateFormat("dd/MM/yyyy").parse(dateOfBirth);
		//String date = new SimpleDateFormat("dd/MM/yyyy").format(dateOfBirth);
		String[] dateStr=dateOfBirth.split("/"); 
		int year=Integer.parseInt(dateStr[2]);
		year = year + numberOfYear;
		dateStr[2]=String.valueOf(year);
		String modifiedDateOfBirth=dateStr[0]+"/"+dateStr[1]+"/"+dateStr[2];
		return modifiedDateOfBirth;
	}

	//TODO Amiya added 13/11/2018
	public String policyAnniversaryDate(String dateOfCommencement, String convertedClientDOB) throws ParseException {		
		String[] dateOfCommencementStr=dateOfCommencement.split("/"); 
		String[] convertedClientDOBStr=convertedClientDOB.split("/"); 
		dateOfCommencementStr[2] = convertedClientDOBStr[2];
		String policyAnniversaryDate=dateOfCommencementStr[0]+"/"+dateOfCommencementStr[1]+"/"+convertedClientDOBStr[2];
		int numberOfDays = numberOfDays(convertedClientDOB, policyAnniversaryDate);
		if(numberOfDays<0) {
			int convertedClientDOBYear=Integer.parseInt(convertedClientDOBStr[2]);
			convertedClientDOBYear = convertedClientDOBYear + 1;
			dateOfCommencementStr[2] = String.valueOf(convertedClientDOBYear);
			policyAnniversaryDate=dateOfCommencementStr[0]+"/"+dateOfCommencementStr[1]+"/"+dateOfCommencementStr[2];
		}
		return policyAnniversaryDate;
	}

	//TODO Amiya added 15/11/2018
	public int getAgeInYears(String d1 , String d2) throws ParseException {
		Date first=new SimpleDateFormat("dd/MM/yyyy").parse(d1);  
		Date last=new SimpleDateFormat("dd/MM/yyyy").parse(d2);
		//DateFormat date=new SimpleDateFormat("dd/MM/yyyy");
		Calendar a = getMyCalendar(first);
		Calendar b = getMyCalendar(last);
		int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
		if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) || 
				(a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
			diff--;
		}
		return diff;
	}
	//TODO Amiya added 15/11/2018
	public Calendar getMyCalendar(Date date) {
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(date);
		return cal;
	}

	//TODO Amiya 21-11-2018
	public String addmonthstodate(String date, Integer monthstobeadded) {		
		if(date.contains("/")) {
			date=date.replaceAll("/", "-");
		}

		String[] dateStr=date.split("-"); 
		if(dateStr[2].length()==2) {
			String l2=dateStr[2];
			dateStr[2]=dateStr[0];
			dateStr[0]=l2;
			date=dateStr[0]+"-"+dateStr[1]+"-"+dateStr[2];
		}
		int m1=Integer.parseInt(dateStr[1]);
		int year=Integer.parseInt(dateStr[2]);
		int m2=m1+monthstobeadded;
		int m6=m2;
		while(m2>12) {
			m2=m2-12;
			year=year+1;
		}
		if(m2<10) {
			dateStr[1]="0"+String.valueOf(m2);
		}
		else {
			dateStr[1]=String.valueOf(m2);
		}
		dateStr[2]=String.valueOf(year);

		if(dateStr[0].equalsIgnoreCase("31") || ((dateStr[0].equalsIgnoreCase("30") || dateStr[0].equalsIgnoreCase("29"))&& dateStr[1].equalsIgnoreCase("02"))) {
			switch (dateStr[1]) {
			case "04":
				dateStr[0]="30";
				break;
			case "06":
				dateStr[0]="30";
				break;
			case "09":
				dateStr[0]="30";
				break;
			case "11":
				dateStr[0]="30";
				break;

			case "02":
				if((year%4)==0)
				{
					dateStr[0]="29";
				}
				else {
					dateStr[0]="28";
				}
				break;

			default:
				break;
			}	
		}
		date=dateStr[0]+"-"+dateStr[1]+"-"+dateStr[2];
		return date;
	}

	//TODO Amiya 29-11-2018
	public String subtractmonthstodate(String date, Integer monthstobedeleted) {		
		if(date.contains("/")) {
			date=date.replaceAll("/", "-");
		}
		String[] dateStr=date.split("-"); 

		int m1=Integer.parseInt(dateStr[1]);
		int year=Integer.parseInt(dateStr[2]);
		int m2=m1-monthstobedeleted;
		while(m2<1) {
			m2=m2+12;
			year=year-1;
		}
		if(m2<10) {
			dateStr[1]="0"+String.valueOf(m2);
		}
		else {
			dateStr[1]=String.valueOf(m2);
		}
		dateStr[2]=String.valueOf(year);

		if(dateStr[0].equalsIgnoreCase("31") || dateStr[0].equalsIgnoreCase("31")) {
			switch (dateStr[1]) {
			case "04":
				dateStr[0]="30";
				break;
			case "06":
				dateStr[0]="30";
				break;
			case "09":
				dateStr[0]="30";
				break;
			case "11":
				dateStr[0]="30";
				break;

			case "02":
				if((year%4)==0)
				{
					dateStr[0]="29";
				}
				else {
					dateStr[0]="28";
				}
				break;

			default:
				break;
			}	
		}
		date=dateStr[0]+"-"+dateStr[1]+"-"+dateStr[2];
		return date;
	}
	//TODO Amiya added 11/12/2018
	public String randomNumberGenerator() {
		String randomNumber = "";
		Random random = new Random();
		int num = 100000 + (int) (random.nextFloat() * 89990000);
		randomNumber = randomNumber + String.valueOf(num);
		return randomNumber;
	}
	//TODO Amiya added 11/12/2018
	public void selectCheckBoxWithJavaScript(WebElement webElement, String checkBoxName) {
		highlighter(webElement);
		if(!webElement.isSelected()) {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", webElement);
			Reporter.log("<B>"+ checkBoxName +"</B> is checked");
		}
	}
	//TODO Amiya added 11/12/2018
	public void quitBrowser(WebDriver driver) throws InterruptedException {
		driver.quit();
		Thread.sleep(WaitTime.veryLow);
	}
	//TODO Amiya added 14/01/2019
	public void exectionTime(long startTime_milisec, String batchName) throws InterruptedException {
		long endTime_milisec = System.currentTimeMillis();	
		NumberFormat numberFormat = new DecimalFormat("#0.00");
		Reporter.log("Time taken to execute <B><I><Font color=\"Blue\"><U>"+batchName+"</B></I></U> is "+ numberFormat.format((endTime_milisec - startTime_milisec) / 1000d) + " seconds </Font>");
	}

	//TODO Amiya added 28/01/2019
	public void datafromtprtx(XSSFWorkbook workbook, String ProposalNumber,String date,String username) throws SQLException {

		DatabaseConnectionUtils databaseConnectionUtils=new DatabaseConnectionUtils();
		java.sql.Connection dbconnection=databaseConnectionUtils.connection_With_Database(workbook, "DatabaseLogin", "Ingenium_UAT");
		Statement ingeniumStatement = dbconnection.createStatement();

		String query="select DOC_ID from tprtx where pol_id='"+ProposalNumber+"' and TRNXT_PRCES_DT='"+date+"' and user_id='"+username.toUpperCase()+"'";
		try {
			ResultSet rs=ingeniumStatement.executeQuery(query);
			if(rs.next()) {
				do {
					Reporter .log("Document id created for <B>"+ProposalNumber+"</B> is <B>"+rs.getString("DOC_ID")+"</B>");
				}	while(rs.next());
			}
		}
		catch (Exception e) {
			Reporter.log(e.toString());
		}
		finally {
			dbconnection.close();
			Reporter.log("Connection closed");
		}
	}

	//TODO Amiya Added 29/01/2019
	public String getDayByPassingDate(String dateInStr) throws ParseException {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateInStr);
		DateFormat dateFormat = new SimpleDateFormat("EEE");
		String dayOfWeek = dateFormat.format(date);
		return dayOfWeek;
	}
	//TODO Amiya Added 19/03/2019
	
	public String getNAVDate(String dateInStr) throws ParseException, InterruptedException {


		//TODO Amiya 04-04-2019
		if(dateInStr.contains("-"))
		{
			dateInStr=dateInStr.replaceAll("-", "/");
		}
		String dateSplit[] = dateInStr.split("/");
		//TODO Amiya 04-04-2019
		if(dateSplit[2].length()==2) {
			dateSplit[2]="20"+dateSplit[2];
			dateInStr=dateSplit[0]+"/"+dateSplit[1]+"/"+dateSplit[2];
		}
		
		int day = Integer.parseInt(dateSplit[0]);
		int month = Integer.parseInt(dateSplit[1]);
		DateGenerator dateGenerator = new DateGenerator();
		if((day==15&&month==8)||(day==26&&month==1)||(day==2&&month==10)||(day==25&&month==12)) {
			dateInStr = dateGenerator.dateGenerator("future:1", dateInStr);
		}
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateInStr);
		DateFormat dateFormat = new SimpleDateFormat("EEE");
		String dayOfWeek = dateFormat.format(date);
		if(dayOfWeek.equalsIgnoreCase("Sat")) {
			dateInStr = dateGenerator.dateGenerator("future:2", dateInStr);
		}else if(dayOfWeek.equalsIgnoreCase("Sun")) {
			dateInStr = dateGenerator.dateGenerator("future:1", dateInStr);
		}
		return dateInStr;
	}

	//TODO Amiya 24-04-2019
	public String ddmmyyTOddmmyyyy(String date) {
		if(date.contains("-")) {
			date.replaceAll("-", "/");
		}
		
		String[] datesplit=date.split("/");
		if(datesplit[2].length()==2)
			datesplit[2]="20"+datesplit[2];
		
		date=datesplit[0]+"/"+datesplit[1]+"/"+datesplit[2];
		return date;
	}
	
	//TODO Amiya Added 27/06/2019
		public String fetchTextFromApplication(WebElement element) {

			highlighter(element);
			String data = element.getText().trim();
			return data;
		}
		
		public WebElement fluentWait(final By locator,String name){
			WebElement webElement=driver.findElement((locator));
			highlighter(webElement);
	        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	                .withTimeout(Duration.ofSeconds(100))
	                .pollingEvery(Duration.ofMillis(600))
	                .ignoring(NoSuchElementException.class);

	        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
	            public WebElement apply(WebDriver driver) {
	                        return driver.findElement(locator);
	                }
	                }
	                 );
	        Reporter.log("<B>"+ name +"</B>");
	                 return  foo;
	       }
		
		//TODO Amiya Added 25/06/2019		
		public String getCurrency(Double str) {
			return String.format("%,.2f", str);	
		}
		//TODO Amiya Added 25/06/2019		
		public String getIndCurrency(Float str) {
		return String.format("%,.2f", str);	
		}
		//TODO Amiya Added 25/06/2019	
		public String getIndianCurrency(String str) {
			StringBuilder stringBuilder = new StringBuilder();
		    char amountArray[] = str.toCharArray();
		    int a = 0, b = 0;
		    for (int i = amountArray.length - 1; i >= 0; i--) {
		        if (a < 3) {
		            stringBuilder.append(amountArray[i]);
		            a++;
		        } else if (b < 2) {
		            if (b == 0) {
		                stringBuilder.append(",");
		                stringBuilder.append(amountArray[i]);
		                b++;
		            } else {
		                stringBuilder.append(amountArray[i]);
		                b = 0;
		            }
		        }
		    }
		   return stringBuilder.reverse().toString().concat(".00");
		}
		
		
		//
		public String getMessage(List<WebElement> message,String MessageType){
			try {
			String text="";
			if(message.size()!=0) {
			    text=fetchTextFromApplication(message.get(0), MessageType);
				Reporter.log("<B> "+text +"</B>");
			}
			return text;
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
			
		}
		
	public String getRoundOfDecimal(int i,Double double1) {
		return String.format("%."+i+"f", double1);
	}
	
	public File deleteFileInDirectory(File file) throws IOException {
		if (file != null) {		
            if (file.exists()) {
            	FileUtils.cleanDirectory(file);
            }
    }
		return file;
		
	}
	
	public String getDownloadedFileName(String fileDirectory) {
		String fileName=null;
		File folder = new File(fileDirectory);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    System.out.println("File " + listOfFiles[i].getName());
		    fileName=listOfFiles[i].getName();
		  } else if (listOfFiles[i].isDirectory()) {
			  fileName=listOfFiles[i].getName();
		    System.out.println("Directory " + listOfFiles[i].getName());
		  }
		}
		return fileName;
		
	}
	
	public void openNewbrowserTab(WebDriver driver) {
		/*Actions actions=new Actions(driver);
		actions.sendKeys(Keys.CONTROL+"t").build().perform();*/
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.open()");
		Reporter.log("<B>  Open new Browser Tab</B>");	
	}
	
	public void mouseHover(WebDriver driver,WebElement element,String name) {
		Actions actions=new Actions(driver);
		actions.moveToElement(element).build().perform();
		Reporter.log("<B>  Hover over "+name+" </B>");
		highlighter(element);
	}
	public void scrollIntoViewJavascript(WebElement element) {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	public Select dropDown(WebElement element) {
		try {
		Select select=new Select(element);
		highlighter(element);
		return select;
		}
		catch (Exception e) {
			return null;// TODO: handle exception
		}	
	}
	public void verifyAlertMessage(List<WebElement> element,List<WebElement> element1,ExcelDatabase database,Connection conn,String testCaseName,String stepGroup,String sheetName) {
		if (element.size() != 0) {
			highlighter(element.get(0));
			String message = fetchTextFromApplication(element.get(0), "Update Message");
			try {
				database.updateQueryForData(conn, sheetName, "Message", testCaseName, stepGroup,message);
			} catch (FilloException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Reporter.log("<B> " + message + "</B>");
		}
		else {
			highlighter(element1.get(0));
			String message = fetchTextFromApplication(element1.get(0), "Failed Message");
			try {
				database.updateQueryForData(conn, sheetName, "Message", testCaseName, stepGroup,message);
			} catch (FilloException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Reporter.log("<B> " + message + "</B>");
			CustomAssert.executionFlag = false;
		}
	}
	
	public String getStringPrefix(String str) {
		String replaceSpecialCharecter=str.replaceAll(",", "");
		String splt[]=replaceSpecialCharecter.split("\\.");
		return splt[0];
	}
}