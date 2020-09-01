package com.aqm;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import util.ConfigReader;
import util.FilloUtil;

public class Test extends FilloUtil {
	
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		 
		System.out.println(ConfigReader.getInstance().getValue("BrowserNameIndex"));
		System.out.println(ConfigReader.getInstance().getValue("ExecuteFlagIndex"));
		
		Fillo fillo = new Fillo();
		Connection connOfMigrationTestData = fillo.getConnection("D:\\GSTINTestData\\GST_Return\\TestScenarioSuite\\0003_ScenarioSuite.xlsx");
		String fetchMigrationPropasal = "select * from MigrationTestData where MigrationScenarioID='TS_Assignment_001_26_1'";
		Recordset recordsetMigration = connOfMigrationTestData.executeQuery(fetchMigrationPropasal);
		while (recordsetMigration.next()) {
			String ProposalNumbers = recordsetMigration.getField("GSTNid");
			System.out.println(ProposalNumbers);
			String client1 = recordsetMigration.getField("ClientId1");
			String client2 = recordsetMigration.getField("ClientId2");
			String userDate = recordsetMigration.getField("UserDate");
			String time = recordsetMigration.getField("Time");
			String bonusApplicable = recordsetMigration.getField("BonusApplicable");
			
			
			Connection connOfMigrationTestData1 = fillo.getConnection("D:\\GSTINTestData\\GST_Return\\TestDataSuite\\0002_TestDataSuite.xlsx");
			String updateMigrationIntoTestData = "Update MASTERTESTDATA set GSTNID='37GDJPS0340K1Z7' where TCID='TS_Assignment_001'";
			connOfMigrationTestData1.executeUpdate(updateMigrationIntoTestData);
			connOfMigrationTestData1.close();
			recordsetMigration.close();
			
			FilloUtil filloUtil=new FilloUtil();
			filloUtil.getEntityObject(fillo, connOfMigrationTestData,"D:\\GSTINTestData\\GST_Return\\TestScenarioSuite\\0003_ScenarioSuite.xlsx", "select * from MigrationTestData where MigrationScenarioID='TS_Assignment_001_26_1'");
			System.out.println(filloUtil.getStringValueForField("GSTNid"));
			
						
		}
		
		
		
		
		
         String fileDownloadPath="D:\\Users\\Temp\\Downloads\\chromedriver_win32";
         Map<String, Object> prefsMap = new HashMap<String, Object>();
         prefsMap.put("profile.default_content_settings.popups", 0);
         prefsMap.put("download.default_directory", fileDownloadPath);
         prefsMap.put("plugins.plugins_disabled", new String[] {
             "Chrome PDF Viewer"
         });
         prefsMap.put("plugins.always_open_pdf_externally", true);
         System.setProperty("webdriver.chrome.driver", "D:\\GSTNAutomationFramework\\GSTN\\drivers\\chromedriver.exe");
         ChromeOptions options = new ChromeOptions();
         options.setExperimentalOption("prefs", prefsMap);
         options.addArguments("test-type"); 
         options.addArguments("--disable-extensions");
         DesiredCapabilities capabilities = DesiredCapabilities.chrome();
         capabilities.setCapability(ChromeOptions.CAPABILITY, options);
         capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
         capabilities.setCapability(ChromeOptions.CAPABILITY, options);

         WebDriver driver = new ChromeDriver(capabilities);
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         driver.get("http://www.pdfpdf.com/samples.html");
         Thread.sleep(2000);
         driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[1]/td[3]/p[1]/font/a[2]")).click();
         
         
	}

}
