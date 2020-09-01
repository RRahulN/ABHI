package com.aqm;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.server.DriverFactory;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import constants.PropertyConfigs;
import core.FrameworkServices;
import core.TestScenarioDataGenerator;
import util.ConfigReader;
import util.ExcelDatabase;

public class FrameworkServiceCheck {

	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
			List<TestScenarioDataGenerator> GSTNID=FrameworkServices.getScenarioSuiteGenerator();
			for(TestScenarioDataGenerator testScenarioDataGenerator2:GSTNID) {
			System.out.println(testScenarioDataGenerator2.getScenarioUID());
		Fillo fillo = new Fillo();
		Connection connOfMigrationTestData = fillo.getConnection("D:\\TCSTestData\\TCS_Return\\TestDataSuite\\0002_TestDataSuite.xlsx");
	    ExcelDatabase excelDatabase=new ExcelDatabase();
	    
		//Recordset billingmode=excelDatabase.selectQueryForHSNData(connOfMigrationTestData, "GSTN_HSN_Table", "TaxableValue", "TS_GSTNAddReturnB2B_001", "26BMVPB7411GGZL");
	    Recordset billingmode=excelDatabase.selectQueryForHSNTable(connOfMigrationTestData, "GSTN_B2C_ReturnPage", "TS_GSTNAddReturnB2C_001", "PlaceOfSupply","Maharashtra (27)");
		while(billingmode.next())
		{
			System.out.println(billingmode.getCount());
			System.out.println(billingmode.getField("TaxRate"));
		}
        
		
		
	     DesiredCapabilities capabilities1 =new  DesiredCapabilities();
         capabilities1.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
         capabilities1.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());
         capabilities1.setJavascriptEnabled(true);
         System.setProperty("webdriver.chrome.driver", "D:\\GSTNAutomationFramework\\GSTN\\drivers\\chromedriver.exe");
         
         WebDriver driver = new ChromeDriver(capabilities1);
         driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
         System.out.println(driver.getCurrentUrl());
         
			} 
	
	}
	
	 private static ChromeOptions getChromeOptions() throws Exception {
	    	String fileDownloadPath=ConfigReader.getInstance().getValue(PropertyConfigs.DownloadPath);
	        Map<String, Object> prefsMap = new HashMap<String, Object>();
	        prefsMap.put("profile.default_content_settings.popups", 0);
	        prefsMap.put("download.default_directory", fileDownloadPath);
	        prefsMap.put("plugins.plugins_disabled", new String[] {
	            "Chrome PDF Viewer"
	        });
	        prefsMap.put("plugins.always_open_pdf_externally", true);
	        
	        ChromeOptions options = new ChromeOptions();
	        options.setExperimentalOption("useAutomationExtension", false);
	        options.setBinary("C:\\GSTNewReturnsOffline\\winstaller\\ZZ.bat");
	        options.addArguments("test-type"); 
	        options.addArguments("--disable-extensions");
	        options.addArguments("--no-sandbox");
	        options.addArguments("--allow-insecure-localhost");
	        options.addArguments("--start-maximized");
	        options.addArguments("--disable-infobars");
	        options.addArguments("chrome.switches","--disable-extensions");
	        options.addArguments("--disable-dev-shm-usage");
	        options.addArguments("--headless");
	        
	        return options;
	    }
}
