package pages;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;


import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;
import org.testng.Reporter;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;

import util.ExcelDatabase;
import util.ExcelRead;
import util.GenericMethods;
import util.SetUpWebdriver;
import util.WaitTime;
import util.WebTable;

public class offlineUploadAnx1Page extends WebTable{
	
	
	@FindBy(xpath="//input[@id='offline_file']")
	private WebElement ChooseFile;
	
	@FindBy(xpath="//div[@class='alert alert-msg alert-success']")
	private WebElement AlertMessage;
	
	@FindBy(xpath="//table[@class='table tbl inv table-bordered exp ng-table']/tbody")
	private WebElement uploadHistoryTable;
	
	@FindBy(xpath="//i[@class='fa fa-refresh']")
	private WebElement refresh;
	
	@FindBy(xpath="//a[@data-ng-click='generaterr(revdata,rtn_prd)'][text()='Download error report']")
	private WebElement downloadErrorReport;
	
	@FindBy(xpath="//a[text()='Download']")
	private WebElement Download;
	
	@FindBy(xpath="//select[@id='selTable']")
	private WebElement SelectTableToDownloadDetails;
	
	@FindBy(xpath="//button[text()='GENERATE JSON FILE TO DOWNLOAD']")
	private WebElement GENERATEJSONFILETODOWNLOAD;
	
	@FindBy(xpath="//button[text()='BACK']")
	private WebElement BACK;
	
	@FindBy(xpath="//label[@for='anx1files']")
	private WebElement OpenDownloadedJSONFile;

	ExcelDatabase database = new ExcelDatabase();
	Screen screen=new Screen();
    WebDriverWait wait;
	String filePATH=null;

	public offlineUploadAnx1Page(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 30);	// TODO Auto-generated constructor stub
	}
	
	
	public void uploadAnx1Invoice(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws InterruptedException, Exception{
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "generateJSONToUpload", testCaseName,stepGroup);
		File file=new File(SetUpWebdriver.getSaveDownloadedFile());
		wait.until(ExpectedConditions.visibilityOf(ChooseFile));
		String filePath=SetUpWebdriver.getSaveDownloadedFile()+ File.separator +dataRow.getProperty("FileName");
		System.out.println(filePath);
		ChooseFile.sendKeys(filePath);
		Reporter.log("<B>"+AlertMessage.getText()+"</B>");
		Thread.sleep(WaitTime.veryHigh);
		click(refresh, "Refresh Page");
		Thread.sleep(WaitTime.medium);
		
        for(String value:getLatestUploadHistory(uploadHistoryTable)) {
        	System.out.println(value);
        	if(value.equals("Download error report")) {
        		Thread.sleep(WaitTime.low);
        		click(downloadErrorReport, "Download error report");
        		Thread.sleep(WaitTime.low);
        	}
        	else if(value.contains("Processing of JSON file was not successful.")) {
        	    click(Download, "Download");
        	    wait.until(ExpectedConditions.textToBe(By.xpath("//button[text()='GENERATE JSON FILE TO DOWNLOAD']"), "GENERATE JSON FILE TO DOWNLOAD"));
        	    click(GENERATEJSONFILETODOWNLOAD, "GENERATE JSON FILE TO DOWNLOAD");    
        	}
        	else if(value.contains("Processed successfully")) {
        	    click(Download, "Download");
        	    wait.until(ExpectedConditions.textToBe(By.xpath("//button[text()='GENERATE JSON FILE TO DOWNLOAD']"), "GENERATE JSON FILE TO DOWNLOAD"));
        	    selectFromDropdownByVisibleText(SelectTableToDownloadDetails, dataRow.getProperty("Table"), "Select Table to Download Details");
        	    click(GENERATEJSONFILETODOWNLOAD, "GENERATE JSON FILE TO DOWNLOAD");
        	    Thread.sleep(WaitTime.veryLow);
        	    File uploadDirectory = new File(SetUpWebdriver.getSaveDownloadedFile());
    		    File[] downloadedFiles = uploadDirectory.listFiles();
    		  
    		    Arrays.sort(downloadedFiles, new Comparator<File>() {
    		        @Override
    		        public int compare(File fileOne, File fileTwo) {
    		            return Long.valueOf(fileOne.lastModified()).compareTo(fileTwo.lastModified());
    		        }
    		    });

    		    if(downloadedFiles.length>1) {
    		    	int n=downloadedFiles.length;
    		    	filePATH=downloadedFiles[n-1].toString();
    		   
    		    }	
        	    database.updateQueryForData(conn, "generateJSONToUpload", "DownloadedJSONFileName", testCaseName, stepGroup,filePATH );
        	}
        	
        }
        driver.close();
        switchToWindow(driver, "Goods and Services Tax"); 
        Thread.sleep(WaitTime.low);
        click(BACK, "BACK");
	}
	
	public void openDownloadedJSONFile(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws InterruptedException{
		click(BACK, "BACK");
	    wait.until(ExpectedConditions.textToBe(By.xpath("//label[@for='anx1files']"), "Open Downloaded JSON File"));
	    click(OpenDownloadedJSONFile, "BACK");

	}
}
