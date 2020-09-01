package pages;

import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.SendKeys;
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

public class CollectionScreen extends GenericMethods {

	
		//Get Total
		@FindBy(xpath="//div[@id='btn']//a[@class='mat-custom-btn']")
		private WebElement gettotal;
	
		
		//To be collected
		@FindBy(xpath="//input[@id='ptbCollected']")
		private WebElement tobeCollected;
	
		
		//Instrument amount 
		@FindBy(xpath="//input[@name='pInstrumentAmt']")
		private WebElement instrumentamount;
	
		
		//Source of Collection
		@FindBy(xpath="//select[@id='pSourceOfCollection']")
		private WebElement sourceofCollection;
	
		
		 //Collection Mode
	     @FindBy(xpath="//select[@id='CollectionMode']")
	     private WebElement mode;
	
    
		//Txn amount 
			@FindBy(xpath="//input[@id='CollectionAmount']")
			private WebElement taxamount;
	
			
		//Sub Code
		    @FindBy(xpath="//input[@id='SubCode']")
			private WebElement subcode;
	
		    
		//Sub Code Search
		    @FindBy(xpath="//input[@id='SubCodeLookup']")
		   private WebElement subcodesearch;
		
		    
		    //Subcode Value
		   @FindBy(xpath="(//a[@name='firstFocus'])[2]")
		   private WebElement subcodevalue;
		    
		//Narration
		   @FindBy(xpath="//input[@id='Narration']")
		   private WebElement narration;

		    
		//Save Button
		   @FindBy(xpath="//span[@id='SaveSpan']")
		   private WebElement saveBTN;
	  
		   
		   //Post Button
		   @FindBy(xpath="//span[contains(text(),'Post')]")
		   private WebElement postBTN;
		   
		    
		//Continue Button Collection
		   @FindBy(xpath="//span[contains(text(),'Continue')]")
		   private WebElement continueBTN;
		    
		    
		//Continue Button Agent Details
		   @FindBy(xpath="//button[contains(text(),'Continue')]")
		   private WebElement continuebutton;
		
		   
	    //Show Requirement 1 on Requirements page
		   @FindBy(xpath="(//img[@title='Show Requirement'])[1]")
		   private WebElement showrequirement1;
		   
		   //Show Requirement 2 on Requirements page
		   @FindBy(xpath="(//img[@title='Show Requirement'])[2]")
		   private WebElement showrequirement2;
		   
		//Requirement status 1
		   @FindBy(xpath="//select[@class='form-control ng-pristine ng-untouched ng-valid ng-scope ng-not-empty'][1]")
		   private WebElement requirementstatus;
		   
		   
		//Requirement status 2
		   @FindBy(xpath="(//select[@class='form-control ng-pristine ng-valid ng-scope ng-not-empty ng-touched'])[2]")
		   private WebElement requirementstatus1;  
		   
		   
		 //Mandatory Status 1
		   @FindBy(xpath="(//select[@class='form-control ng-pristine ng-valid ng-scope ng-not-empty ng-touched'])[1]")
		   private WebElement mandatorystatus1; 
		   
		  
		 //Mandatory Status 2
		   @FindBy(xpath="(//select[@class='form-control ng-pristine ng-valid ng-scope ng-not-empty ng-touched'])[1]")
		   private WebElement mandatorystatus2;
		   
		   //Optional Status 1
		   @FindBy(xpath="(//select[@class='form-control ng-pristine ng-valid ng-scope ng-not-empty ng-touched'])[1]")
		   private WebElement optionalstatus1; 
		   
		  
		 //Optional Status 2
		   @FindBy(xpath="(//select[@class='form-control ng-pristine ng-valid ng-scope ng-not-empty ng-touched'])[1]")
		   private WebElement optionalstatus2;
		   
		 //Upload Requirement 1
		   @FindBy(xpath="(//img[@title='Upload Requirement'])[1]")
		   private WebElement uploadrequirement1;
		   
		   //Upload Requirement 2
		   @FindBy(xpath="(//img[@title='Upload Requirement'])[2]")
		   private WebElement uploadrequirement2;
	
		 //Upload Requirement 1
		   @FindBy(xpath="(//img[@title='Upload Requirement'])[3]")
		   private WebElement uploadrequirement3;
		   
		   //Upload Requirement 2
		   @FindBy(xpath="(//img[@title='Upload Requirement'])[4]")
		   private WebElement uploadrequirement4;
		   
		   //Choose File 1
		   @FindBy(xpath="//input[@type='file']")
		   private WebElement choosefile1; 
		   
		   
		   //Upload File
		   @FindBy(xpath="//button[@title='Upload']")
		   private WebElement upload;
		   
		  //Save
		   @FindBy(xpath="//button[@id='SaveRequire']")
		   private WebElement saverequire; 
		   
		  //Continue Button Requirement page
		   @FindBy(xpath="//button[contains(text(),'Continue')]")
		   private WebElement continueButton;
		    
		 
        
	    
	WebDriverWait wait;
	public CollectionScreen(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 30);	
		}
	
	
	public void fillcollectionscreen(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup,CustomAssert customAssert) throws Exception
	{
		String sheetName = ConfigReader.getInstance().getValue(PropertyConfigs.TestSheet);
        Properties dataRow = ExcelRead.readRowDataInProperties(workbook, sheetName, testCaseName,stepGroup);
	   Reporter.log("<B>Traverse To CommonPage</B>");
	   
	   //Get Total
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.PAGE_DOWN);
		Thread.sleep(WaitTime.low);
	    click(gettotal,"Get Total");
	     
	    
      //To be Collected & Instrument amount
//	   System.out.println("get Text "+tobeCollected.getText());
//	   System.out.println("get Attribute "+ tobeCollected.getAttribute("value")); 
	   String Collectamount=tobeCollected.getAttribute("value");
	   Thread.sleep(WaitTime.low);
	   driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.PAGE_UP);
	   instrumentamount.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
	   Thread.sleep(WaitTime.low);
	   driver.findElement(By.xpath("//input[@name='pInstrumentAmt']")).sendKeys(Collectamount);
	   Thread.sleep(WaitTime.low);
    
	   
	//Source of Collection
	    Thread.sleep(WaitTime.low);
		selectFromDropdownByVisibleText(sourceofCollection,dataRow.getProperty("Source of Collection"),"Source of Collection");
		Thread.sleep(WaitTime.medium);
		
		
	//Collection Mode
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.PAGE_DOWN);
        Thread.sleep(WaitTime.low);
		selectFromDropdownByVisibleText(mode,dataRow.getProperty("Mode"),"Collection Mode");
		Thread.sleep(WaitTime.medium);
	
		
    //Txn Amount	
         clearAndSenKeys(taxamount,Collectamount,"Txn Amount");
         Thread.sleep(2000);
		
         
     //Sub Code
         //wait.until(ExpectedConditions.elementToBeClickable(subcode));
         
//         click(subcode, "Sub Code");
//         clearAndSenKeys(subcode, "ABHI-HO-2502020032-3072310000-BANK" ,"SubCode");
//         Thread.sleep(2000);
         
         
     //Sub Code Search
        String parentWindow = driver.getWindowHandle();
        wait.until(ExpectedConditions.elementToBeClickable(subcode));
 		Thread.sleep(WaitTime.low);
 		click(subcodesearch, " search ");
 		switchToWindow(driver);
 		Thread.sleep(4000);
 		click(subcodevalue,"SubCode Value");
 		Thread.sleep(2000);
 		driver.switchTo().window(parentWindow);
 		switchtodefaultframe(driver);
 		switchtoframe(driver, "display"); 
 		switchtoframe(driver, "containerFrame");  
 		
 		
         
       //Narration
        Thread.sleep(2000);
        clearAndSenKeys(narration,dataRow.getProperty("Narration"),"Narration");
        Thread.sleep(2000);
        
        //Save button
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='Narration']")).sendKeys(Keys.PAGE_DOWN);
        click(saveBTN, "Save Button");
        Thread.sleep(2000);
       // String parentWindow = driver.getWindowHandle();
        
        
        //Post Button
        Thread.sleep(4000);
        driver.findElement(By.xpath("//input[@id='Narration']")).sendKeys(Keys.PAGE_DOWN);
        click(postBTN,"Post Button");
        Thread.sleep(2000);
    
        
        //Continue Button Collection
 	    driver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN);
        click(continueBTN, "Continue Button");
        Thread.sleep(2000);
        
        
        //Continue Button Agent Details
        Thread.sleep(3000);
        click(continuebutton,"Continue Button");
        Thread.sleep(2000);
        
        
        //Requirement Page 
        
        // For Proposer
        
        // Show Requirements 1
        Thread.sleep(3000);
        click(showrequirement1,"Show Requirement");
        Thread.sleep(2000);
        
        
        // Select Requirement Status 1
        Thread.sleep(WaitTime.low);
		selectFromDropdownByVisibleText(requirementstatus,dataRow.getProperty("Requirement Status"),"Requirement Status");
		Thread.sleep(WaitTime.medium);
		
		
		// Mandatory Status 1
        Thread.sleep(WaitTime.low);
		selectFromDropdownByVisibleText(mandatorystatus1,dataRow.getProperty("Mandatory Status"),"Requirement Status");
		Thread.sleep(WaitTime.medium);
        
		
		//Upload Requirement 1
				Thread.sleep(3000);
		        click(uploadrequirement1,"Upload Document of Bank");
		        switchToWindow(driver);
		        Thread.sleep(2000);
		       
		        //Choose File
		        Thread.sleep(3000);
		        click(choosefile1,"Choose File");
		        choosefile1.sendKeys("D:\\code.txt");
		        Thread.sleep(2000);
		        
		        //Upload
		        Thread.sleep(3000);
		        click(upload,"File Uploaded");
		        Thread.sleep(2000);
		        
		       Thread.sleep(3000);
		        click(saverequire,"Save");
		        Thread.sleep(2000);
		        
		        
		        
		// Select Requirement Status 2
        Thread.sleep(WaitTime.low);
		selectFromDropdownByVisibleText(requirementstatus1,dataRow.getProperty("Requirement Status"),"Requirement Status");
		Thread.sleep(WaitTime.medium);
		
		  
		// Mandatory Status 2
        Thread.sleep(WaitTime.low);
		selectFromDropdownByVisibleText(mandatorystatus2,dataRow.getProperty("Mandatory Status"),"Requirement Status");
		Thread.sleep(WaitTime.medium);
		
		
		//Upload Requirement 2
		Thread.sleep(3000);
        click(uploadrequirement2,"Upload Document of Proposer");
        switchToWindow(driver);
        Thread.sleep(2000);
       
        //Choose File
        Thread.sleep(3000);
        click(choosefile1,"Choose File");
        choosefile1.sendKeys("D:\\code.txt");
        Thread.sleep(2000);
        
        //Upload
        Thread.sleep(3000);
        click(upload,"File Uploaded");
        Thread.sleep(2000);
        
       Thread.sleep(3000);
        click(saverequire,"Save");
        Thread.sleep(2000);
        
        


   //For Nominee

      // Show Requirements 2
      Thread.sleep(3000);
      click(showrequirement2,"Show Requirement");
      Thread.sleep(2000);
      
      
      // Select Requirement Status 1
      Thread.sleep(WaitTime.low);
	selectFromDropdownByVisibleText(requirementstatus,dataRow.getProperty("Requirement Status"),"Requirement Status");
	Thread.sleep(WaitTime.medium);
	
	
	// Mandatory Status 1
      Thread.sleep(WaitTime.low);
	selectFromDropdownByVisibleText(optionalstatus1,dataRow.getProperty("Optional Status"),"Requirement Status");
	Thread.sleep(WaitTime.medium);
      
	
	//Upload Requirement 3
		Thread.sleep(3000);
	      click(uploadrequirement3,"Upload Document of Bank");
	      switchToWindow(driver);
	      Thread.sleep(2000);
	     
	      //Choose File
	      Thread.sleep(3000);
	      click(choosefile1,"Choose File");
	      choosefile1.sendKeys("D:\\code.txt");
	      Thread.sleep(2000);
	      
	      //Upload
	      Thread.sleep(3000);
	      click(upload,"File Uploaded");
	      Thread.sleep(2000);
	      
	     Thread.sleep(3000);
	      click(saverequire,"Save");
	      Thread.sleep(2000);
	
	
	      
	// Select Requirement Status 2
      Thread.sleep(WaitTime.low);
	selectFromDropdownByVisibleText(requirementstatus1,dataRow.getProperty("Requirement Status"),"Requirement Status");
	Thread.sleep(WaitTime.medium);
	
	  
	// Mandatory Status 2
      Thread.sleep(WaitTime.low);
	selectFromDropdownByVisibleText(optionalstatus2,dataRow.getProperty("Optional Status"),"Requirement Status");
	Thread.sleep(WaitTime.medium);
	
	
	//Upload Requirement 4
	Thread.sleep(3000);
      click(uploadrequirement4,"Upload Document of Bank");
      switchToWindow(driver);
      Thread.sleep(2000);
     
      //Choose File
      Thread.sleep(3000);
      click(choosefile1,"Choose File");
      choosefile1.sendKeys("D:\\code.txt");
      Thread.sleep(2000);
      
      //Upload
      Thread.sleep(3000);
      click(upload,"File Uploaded");
      Thread.sleep(2000);
      
     Thread.sleep(3000);
      click(saverequire,"Save");
      Thread.sleep(2000);
       
        //end
        
        
        //Continue Button
        Thread.sleep(3000);
        click(continueButton,"Continue Button");
        Thread.sleep(2000);
       
}
	
	public void FillCollection(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup,CustomAssert customAssert) throws Exception
	{
		fillcollectionscreen(driver, testCaseName, workbook, conn, stepGroup, customAssert);
}
	
}