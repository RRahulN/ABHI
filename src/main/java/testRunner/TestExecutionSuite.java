package testRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestNGListener;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import constants.PropertyConfigs;
import core.FrameworkServices;
import core.TestScriptStepGenerator;
import core.TestSuiteGenerator;
import stepDefination.StepKeyword;
import util.ConfigReader;
import util.CustomAssert;
import util.ExcelDatabase;
import util.SetUpWebdriver;

public class TestExecutionSuite{


	public XSSFWorkbook workbook = null;
	public Connection conn = null;
	public Connection connOfTestData = null;
	public String GSTNID = "";
	public String PlaceOfSupply="";
	public String ScenarioID = "";
	public String ReturnType="";
	public String executionStatus = "";
	String filePath="";
	String MigrationFilePath="";
	String SheetName = "";
	private static HashMap<String, String> scenarioStatus = new HashMap<>();
	
	
	@Parameters({ "ScenarioID", "Module", "Description", "ScriptReference", "BrowserName","ScenarioUID", "TestScenario_RepositoryFileIndex" ,"TestData_RepositoryFile" })
	@Test(testName = "ScenarioID")
	public void executionSuite(String testScenario_Id, String Module, String Description,String scriptReference, String BrowserName, String ScenarioUID,String TestScenario_RepositoryFileIndex,String TestData_RepositoryFile)throws Exception{
		// Update GSTIN in MasterData sheet
		String Browser=ConfigReader.getInstance().getValue(PropertyConfigs.Browser);
		String CONFIG_PATH = ConfigReader.getInstance().getValue(PropertyConfigs.TestDataFolder) + File.separator;
		FrameworkServices frameworkServices=new FrameworkServices();
		for(TestSuiteGenerator testSuiteGenerator:frameworkServices.getTestSuiteForExecution())
		{
		filePath=CONFIG_PATH.concat(testSuiteGenerator.getTestData_RepositoryFile().toString());
	    }
		Fillo fl=new Fillo();
		conn=fl.getConnection(filePath);
		FileInputStream fileInputMasterStream = new FileInputStream(new File(CONFIG_PATH+"0001_MasterTestSuite.xlsx"));
		FrameworkServices.masterWorkbook = new XSSFWorkbook(fileInputMasterStream);
		WebDriver driver = null;
		
		Fillo fillo = new Fillo();
		connOfTestData = fillo.getConnection(filePath);
		MigrationFilePath = CONFIG_PATH.concat(TestScenario_RepositoryFileIndex);
		updateMigrationDataToMasterData(connOfTestData, MigrationFilePath, testScenario_Id, ScenarioUID);
		
		filePath=CONFIG_PATH.concat(TestData_RepositoryFile.toString());
		
		
		FileInputStream fileInputStream = new FileInputStream(new File(filePath));
		workbook = new XSSFWorkbook(fileInputStream);
		try {
			
			driver=SetUpWebdriver.setupWebDriver(Browser);
			
			CustomAssert customAssert = new CustomAssert(driver);
			CustomAssert.executionFlag = true;
			StepKeyword keyword = new StepKeyword(driver);
			for (TestScriptStepGenerator testScriptStepGenerator : FrameworkServices.getScriptStepFromScriptName(scriptReference)) {
				// TODO Amiya 05-10-2019
				Reporter.log("<B><I><Font color=\"BLUE\"><U> Step   ==>"+ testScriptStepGenerator.getStepKeyword() + " </U> ===></Font></I></B>");
				keyword.executeTestStep(driver, testScenario_Id, testScriptStepGenerator.getStepKeyword(),testScriptStepGenerator.getStepGroup(),GSTNID,ReturnType, workbook,conn,customAssert);
			}

			if (CustomAssert.executionFlag) {
				Reporter.log("Test Scenario has been passed");
				scenarioStatus.put(testScenario_Id,"PASSED");
				// TO DO Amiya 01-11-2018
				// Update sheet Execution Status to passed if testcase excuted successfully.
				/*if (FrameworkServices.getConfigProperties().getProperty("ExecutionMode").equalsIgnoreCase("Migration")) {
					String MGFilePath = FrameworkServices.getConfigProperties().getProperty("TestDataFolder").concat(TestScenario_RepositoryFileIndex);
					Fillo fillo1 = new Fillo();
					Connection connOfTestData1 = fillo1.getConnection(filePath);
					updateExecutionStatus(connOfTestData1, MGFilePath, testScenario_Id, MigrationScenarioID, "Passed");
				}*/

			} 
			
			
			else {
				/*if (FrameworkServices.getConfigProperties().getProperty("ExecutionMode").equalsIgnoreCase("Migration")) {
					String MGFilePath = FrameworkServices.getConfigProperties().getProperty("TestDataFolder").concat(TestScenario_RepositoryFileIndex);
					Fillo fillo1 = new Fillo();
					Connection connOfTestData1 = fillo1.getConnection(filePath);
					updateExecutionStatus(connOfTestData1, MGFilePath, testScenario_Id, MigrationScenarioID, "Failed");
				}*/
				scenarioStatus.put(testScenario_Id,"FAILED");
				throw new AssertionError();
			}
		} catch (Exception e) {

			// TO DO Amiya 06/11/2018
			// Update sheet Execution Status to passed if testcase excuted successfully.
			/*if (FrameworkServices.getConfigProperties().getProperty("ExecutionMode").equalsIgnoreCase("Migration")) {
				String MGFilePath = FrameworkServices.getConfigProperties().getProperty("TestDataFolder").concat(TestScenario_RepositoryFileIndex);
				Fillo fillo1 = new Fillo();
				Connection connOfTestData1 = fillo1.getConnection(filePath);
				updateExecutionStatus(connOfTestData1, MGFilePath, testScenario_Id, MigrationScenarioID, "Failed");
			}*/

			SetUpWebdriver.captureScreenShot(driver, TestEngine.excutionFolder+ConfigReader.getInstance().getValue(PropertyConfigs.screenShotFolder),testScenario_Id);
			e.printStackTrace();
			Reporter.log(e.toString());
			if(e.getMessage().equals(ConfigReader.getInstance().getValue("CustomExceptionMessage")) && CustomAssert.executionFlag)
			{	
				scenarioStatus.put(testScenario_Id,"FAILED");
				Assert.assertEquals(true, true);
				
			}
			else
			{
			scenarioStatus.put(testScenario_Id,"FAILED");
			Reporter.log(e.getCause().getMessage());
			Assert.assertEquals(true,false);
			
			}
		} finally {
			/*if(driver.toString()==null)
			{
				System.out.println("Browser is closed");
			}
			else
			{
				//Logout from Application
				System.out.println("Go to Home Page");
			}*/
			
			driver.close();
			driver.quit();
			workbook.close();
			connOfTestData.close();
			conn.close(); 
		}
	}

	@AfterClass 
	public void tearDown() throws IOException {
		ExcelDatabase.updateBorders(MigrationFilePath);
		ExcelDatabase.updateBorders(filePath);
		
	}
	
	@AfterSuite
	public void afterSuite() throws Exception {
		String ScanerioType=ConfigReader.getInstance().getValue(PropertyConfigs.ScanerioType);
		 XSSFWorkbook workbook = new XSSFWorkbook();
	    	XSSFSheet sheet = workbook.createSheet("Result Summery");
	    	int rowNumber = 0;
	    	XSSFRow rowheadHeader = sheet.createRow((short)rowNumber++);
	    	rowheadHeader.createCell(0).setCellValue("Scenario");
	    	rowheadHeader.createCell(1).setCellValue("Status");
	        for (Map.Entry<String,String> entry : scenarioStatus.entrySet()) {
	        	XSSFRow rowhead = sheet.createRow((short)rowNumber++);
	        	rowhead.createCell(0).setCellValue(entry.getKey());
	        	rowhead.createCell(1).setCellValue(entry.getValue());
	        	 System.out.println("Key = " + entry.getKey() + 
	                     ", Value = " + entry.getValue()); 
	        }
	        FileOutputStream fileOut = new FileOutputStream(TestEngine.excutionFolder+"\\LatestSheet.xlsx");
	        workbook.write(fileOut);
	        fileOut.close();
	        workbook.close();
	        String cssOut = ".invocation-failed,  .test-failed  { background-color: #E53030; }\n" + 
					".invocation-percent, .test-percent { background-color: #006600; }\n" + 
					".invocation-passed,  .test-passed  { background-color: #0D5D12; }\n" + 
					".invocation-skipped, .test-skipped { background-color: #A5A129; }\n" + 
					"\n" + 
					".main-page {\n" + 
					"  font-size: x-large;\n" + 
					"}\n" + 
					"\n" + 
					"body{background-color: #5A5353;color: white;}\n" + 
					"table{border-color: black;}\n" + 
					"";
	        FileWriter fw=new FileWriter(TestEngine.excutionFolder+"\\"+ScanerioType+"\\testng.css");    
	           fw.write(cssOut);    
	           fw.close();
	if(!scenarioStatus.isEmpty()) {
		File pass = new File(TestEngine.excutionFolder + "\\"+ScanerioType+"\\PASS");
		File fail = new File(TestEngine.excutionFolder + "\\"+ScanerioType+"\\FAIL");
		File assertion = new File(TestEngine.excutionFolder + "\\"+ScanerioType+"\\ASSERT");
		
		if(!pass.exists()) {
			pass.mkdir();
			
		}
		if(!fail.exists()) {
			fail.mkdir();
		}
		
		Set<String> keys = scenarioStatus.keySet();
		for (String key : keys) {
			System.out.println("=====================================================>" + key);
			String status = scenarioStatus.get(key);

			File htmlFileToMove = new File(TestEngine.excutionFolder + "\\"+ScanerioType+"\\" + key + ".html");
			File xmlFileToMove = new File(TestEngine.excutionFolder + "\\"+ScanerioType+"\\" + key + ".xml");

			if(status.equalsIgnoreCase("PASSED")) {
				htmlFileToMove.renameTo(new File(TestEngine.excutionFolder +"\\"+ScanerioType+"\\PASS\\" + key + ".html"));
				xmlFileToMove.renameTo(new File(TestEngine.excutionFolder +"\\"+ScanerioType+"\\PASS\\" + key + ".xml"));
			}else if(status.equalsIgnoreCase("FAILED")) {
				htmlFileToMove.renameTo(new File(TestEngine.excutionFolder +"\\"+ScanerioType+"\\FAIL\\" + key + ".html"));
				xmlFileToMove.renameTo(new File(TestEngine.excutionFolder + "\\"+ScanerioType+"\\FAIL\\" + key + ".xml"));
			}
			else {
				htmlFileToMove.renameTo(new File(TestEngine.excutionFolder +"\\"+ScanerioType+"\\ASSERT_or_SKIPPED\\" + key + ".html"));
				xmlFileToMove.renameTo(new File(TestEngine.excutionFolder + "\\"+ScanerioType+"\\ASSERT_or_SKIPPED\\" + key + ".xml"));
		}
		}
		
		
	}
	}


	public void updateExecutionStatus(Connection connForTestData, String migrationTestData, String scenarioId,String migrationId, String Status) throws FilloException, IOException {

		try {

			/*Fillo fillo = new Fillo();
			ExcelDatabase excelDatabase = new ExcelDatabase();
			Connection connOfMigrationTestData = fillo.getConnection(migrationTestData);
			String fetchMigrationPropasal = "select * from MigrationTestData where MigrationScenarioID='"+migrationId+"'";
			Recordset recordsetMigration = connOfMigrationTestData.executeQuery(fetchMigrationPropasal);
			String updateMigrationIntoTestData = "Update MigrationTestData Set Status='"+ Status+"' Where MigrationScenarioID='"+migrationId+"' and ProposalNumber='"+ ProposalNumbers+"'";
			excelDatabase.sheetsname.add("MigrationTestData");
			connOfMigrationTestData.executeUpdate(updateMigrationIntoTestData);
			recordsetMigration.close();
			connOfMigrationTestData.close();*/

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
	//TODO Amiya Modified 09/10/2019
	@SuppressWarnings("static-access")
	public void updateMigrationDataToMasterData(Connection connForTestData, String migrationTestData, String scenarioId,String scenarioUid) throws FilloException {
		try{
			Fillo fillo = new Fillo();
			Connection connOfMigrationTestData = fillo.getConnection(migrationTestData);
			//String ProposalNumber = "";
			
			String fetchMigrationPropasal = "select * from TestScenarios where ScenarioUID='"+scenarioUid+"'";
			Recordset recordsetMigration = connOfMigrationTestData.executeQuery(fetchMigrationPropasal);
			while (recordsetMigration.next()) {
				GSTNID = recordsetMigration.getField("GSTNid");
				PlaceOfSupply=recordsetMigration.getField("PlaceOfSupply");
				ReturnType=recordsetMigration.getField("ReturnType");
			}
			String updateMigrationIntoTestData = "Update MASTERTESTDATA set GSTNID='"+GSTNID+"', PlaceOfSupply='"+PlaceOfSupply+"',ReturnType='"+ReturnType+"' where TCID='"+scenarioId +"'";
			connForTestData.executeUpdate(updateMigrationIntoTestData);
			connOfMigrationTestData.close();
			recordsetMigration.close();
			//TODO Amiya added 27/12/2018
			ExcelDatabase excelDatabase = new ExcelDatabase();
			excelDatabase.sheetsname.add("MASTERTESTDATA");
		}catch(Exception e){
			System.out.println(e);
			Reporter.log(e.getMessage());
		}
	}
	
}
