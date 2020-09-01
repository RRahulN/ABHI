package pages;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.Reporter;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;

import util.ExcelDatabase;
import util.ExcelRead;
import util.GenericMethods;
import util.SetUpWebdriver;
import util.SikuliScript;

public class GSTNAOSCommonPage extends GenericMethods {

		@FindBy(xpath="//div[text()='IMPORT EXCEL/CSV FILES']/..")
		private WebElement ImportExcelCSVFile;
		
		@FindBy(xpath="//label[text()='IMPORT EXCEL']")
		private WebElement ImportExcelButton;
		
		@FindBy(xpath="//label[text()='IMPORT EXCEL']")
		List<WebElement> ImportExcelbutton;
		
		@FindBy(xpath="//button[@id='btn-save']")
		private WebElement generateJSONFile;
		
		@FindBy(xpath="//li[@class='gsHdInfo']/a[1]")
		private WebElement GSTIN;
		
		@FindBy(xpath="//li[@class='gsHdInfo']/a[3]")
		private WebElement FinancialYear;
		
		@FindBy(xpath="//li[@class='gsHdInfo']/a[4]")
		private WebElement TaxPeriod;
		
		@FindBy(xpath="(//span)[2]")
		private WebElement SuccessfulMsg;
		
		@FindBy(xpath="//select[@id='selTable']")
		private WebElement selTable;
		
		@FindBy(xpath="//label[text()='IMPORT CSV']")
		private WebElement importCSV;
		
		By filePath=By.className("Breadcrumb Parent");
		By fileName=By.name("File name:");
		By Open=By.name("Open");
		
		ExcelDatabase database = new ExcelDatabase();
		WebDriverWait wait;
		Screen screen=new Screen();

		WiniumDriver winiumDriver=null;
		public GSTNAOSCommonPage(WebDriver driver) throws Exception {
			super(driver);
			PageFactory.initElements(driver, this);
			wait = new WebDriverWait(driver, 30);
			/*Runtime.getRuntime().exec("taskkill /F /IM Winium.Desktop.Driver.exe");
	        Thread.sleep(3000);
			WiniumDriverService service = new WiniumDriverService.Builder().usingDriverExecutable(new File(setupWiniumDriver("Winium.Desktop.Driver"))).usingPort(9999)
	                .withVerbose(true)
	                .withSilent(false)
	                .buildDesktopService();
	                service.start();
	                DesktopOptions options = new DesktopOptions(); // Initiate Winium Desktop Options
	                //options.setApplicationPath("C:\\GST Offline Tool\\offline.bat");
	                winiumDriver = new WiniumDriver(new URL("http://localhost:9999"),options);*/
		}

		public void generateJSONFile(WebDriver driver, String testCaseName, String GSTNID, XSSFWorkbook workbook,Connection conn, String stepGroup) throws InterruptedException, IOException, FilloException {
			Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "generateJSONToUpload", testCaseName,stepGroup);
			File file=new File(SetUpWebdriver.getSaveDownloadedFile());
			 // Verify downloads dir is empty, if not remove all files.
		    deleteFileInDirectory(file); 
		    click(generateJSONFile, "GENERATE JSON FILE TO UPLOAD");
		    Thread.sleep(3000);    
		    Reporter.log("<B>"+SuccessfulMsg.getText().trim()+"</B>");
		    Assert.assertEquals(SuccessfulMsg.getText().trim(), dataRow.getProperty("Message").trim());
		    Thread.sleep(3000);
			Reporter.log("Save file in the directory <B>"+file+"</B> and File name is <B>"+getDownloadedFileName(SetUpWebdriver.getSaveDownloadedFile())+"</B>");
			database.updateQueryForData(conn, "generateJSONToUpload", "FileName", testCaseName, stepGroup, getDownloadedFileName(SetUpWebdriver.getSaveDownloadedFile()));
			database.updateQueryForData(conn, "generateJSONToUpload", "GSTIN", testCaseName, stepGroup, GSTIN.getText().trim());
			Reporter.log("GSTIN is <B>"+GSTIN.getText().trim()+"</B>");
			database.updateQueryForData(conn, "generateJSONToUpload", "FinancialYear", testCaseName, stepGroup, FinancialYear.getText().trim());
			Reporter.log("Financial Year is <B>"+FinancialYear.getText().trim()+"</B>");
			database.updateQueryForData(conn, "generateJSONToUpload", "TaxPeriod", testCaseName, stepGroup, TaxPeriod.getText().trim());
			Reporter.log("Tax Period is <B>"+TaxPeriod.getText().trim()+"</B>");
		}
		public void importExcelCSV(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {

			String filePATH=null;
			SikuliScript sikuliScript=new SikuliScript();
			Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_ImportFile", testCaseName,stepGroup);
			click(ImportExcelCSVFile, "IMPORT EXCEL/CSV FILES");
			wait.until(ExpectedConditions.visibilityOf(ImportExcelButton));
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
			if(dataRow.getProperty("FileType").equalsIgnoreCase("Excel")) {
				//click(ImportExcelButton, "IMPORT EXCEL");
				//winiumDriver.findElement(filePath);
				Reporter.log("Import Excel file to upload");
				String fileTypes=dataRow.getProperty("FileType");				
				sikuliScript.sikuliSelectFileToUpload(screen, fileTypes,filePATH);
			}
			else if (dataRow.getProperty("FileType").equalsIgnoreCase("CSV")) {
				Reporter.log("Import CSV file to upload");
				String fileTypes=dataRow.getProperty("FileType");	
				selectFromDropdownByValue(selTable, dataRow.getProperty("ReturnType"), "Select table DropDown");
				sikuliScript.sikuliSelectFileToUpload(screen,fileTypes,filePATH);
			}
			    
		}
		
		public static String setupWiniumDriver(String driverExeName) {
	        return System.getProperty("user.dir") + File.separator + "drivers" + File.separator + driverExeName + ".exe";
	    }

	}

