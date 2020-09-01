package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import util.ConfigReader;


public class FrameworkServices {
	
	
	public static XSSFWorkbook masterWorkbook;
	
	public FrameworkServices() throws IOException {
		
	}
	
	public List<TestSuiteGenerator> getTestSuiteForExecution() throws Exception{
		String CONFIG_PATH = ConfigReader.getInstance().getValue("TestDataFolder") + File.separator;
		FileInputStream fileInputStream=new FileInputStream(new File(CONFIG_PATH + "0001_MasterTestSuite.xlsx"));
		masterWorkbook=new XSSFWorkbook(fileInputStream);
		XSSFSheet masterSuiteSheet=masterWorkbook.getSheet("MasterTestSuite");
		int numberOfRows=masterSuiteSheet.getLastRowNum();
		int ReferenceIndexForTestSuite=Integer.parseInt(ConfigReader.getInstance().getValue("ReferenceIndex"));
		int LOBNameIndex=Integer.parseInt(ConfigReader.getInstance().getValue("LOBNameIndex"));
		int DesriptionIndex=Integer.parseInt(ConfigReader.getInstance().getValue("DesriptionIndex"));
		int TestData_RepositoryFileIndex=Integer.parseInt(ConfigReader.getInstance().getValue("TestData_RepositoryFileIndex"));
		int BrowserNameIndex=Integer.parseInt(ConfigReader.getInstance().getValue("BrowserNameIndex"));
		int ExecuteFlagIndex=Integer.parseInt(ConfigReader.getInstance().getValue("ExecuteFlagIndex"));
		int TestScenario_RepositoryFileIndex=Integer.parseInt(ConfigReader.getInstance().getValue("TestScenario_RepositoryFileIndex"));
	


		List<TestSuiteGenerator> testSuiteGeneratorList=new ArrayList<>();
		for(int i=1;i<=numberOfRows;i++) {
			Row rowData=masterSuiteSheet.getRow(i);
			if(rowData.getCell(ExecuteFlagIndex).getStringCellValue().equalsIgnoreCase("yes")) {
				TestSuiteGenerator testSuiteGenerator=new TestSuiteGenerator();
				testSuiteGenerator.setReference(rowData.getCell(ReferenceIndexForTestSuite).getStringCellValue());
				testSuiteGenerator.setLOBName(rowData.getCell(LOBNameIndex).getStringCellValue());
				testSuiteGenerator.setDesription(rowData.getCell(DesriptionIndex).getStringCellValue());
				testSuiteGenerator.setTestData_RepositoryFile(rowData.getCell(TestData_RepositoryFileIndex).getStringCellValue());
				testSuiteGenerator.setBrowserName(rowData.getCell(BrowserNameIndex).getStringCellValue());
				testSuiteGenerator.setExecuteFlag(rowData.getCell(ExecuteFlagIndex).getStringCellValue());
				testSuiteGenerator.setTestScenario_RepositoryFile(rowData.getCell(TestScenario_RepositoryFileIndex).getStringCellValue());
				testSuiteGeneratorList.add(testSuiteGenerator);
			}
		}
		return testSuiteGeneratorList;
	}

	public static List<TestScriptStepGenerator> getScriptStepFromScriptName( String scriptName) throws Exception {
		String CONFIG_PATH = ConfigReader.getInstance().getValue("TestDataFolder") + File.separator;
		FileInputStream fileInputStream=new FileInputStream(new File(CONFIG_PATH + "0001_MasterTestSuite.xlsx"));
		masterWorkbook=new XSSFWorkbook(fileInputStream);
		XSSFSheet masterTestScriptStepsheet=masterWorkbook.getSheet("MasterTestScriptStep");
		int numberOfRows=masterTestScriptStepsheet.getLastRowNum();
		int ReferenceIndexForTestStep=Integer.parseInt(ConfigReader.getInstance().getValue("ReferenceIndexForTestStep"));
		int automationScriptName_MasterTestScriptStep_Index=Integer.parseInt(ConfigReader.getInstance().getValue("AutomationScriptName_MasterTestScriptStep_Index"));
		int stepKeywordIndex=Integer.parseInt(ConfigReader.getInstance().getValue("StepKeywordIndex"));
		int stepGroupIndex=Integer.parseInt(ConfigReader.getInstance().getValue("StepGroupIndex"));
		int skipStepIndex=Integer.parseInt(ConfigReader.getInstance().getValue("SkipStepIndex"));
		List<TestScriptStepGenerator> testScriptStepGenerators=new ArrayList<>();
		for(int i=1;i<=numberOfRows;i++) {
			Row rowData=masterTestScriptStepsheet.getRow(i);
			if(rowData.getCell(automationScriptName_MasterTestScriptStep_Index).getStringCellValue().equalsIgnoreCase(scriptName)&&rowData.getCell(skipStepIndex).getStringCellValue().equalsIgnoreCase("No")) {
				TestScriptStepGenerator testScriptStepGenerator=new TestScriptStepGenerator();
				testScriptStepGenerator.setReference(rowData.getCell(ReferenceIndexForTestStep).getStringCellValue());
				testScriptStepGenerator.setAutomationScriptName(rowData.getCell(automationScriptName_MasterTestScriptStep_Index).getStringCellValue());
				testScriptStepGenerator.setStepKeyword(rowData.getCell(stepKeywordIndex).getStringCellValue());
				testScriptStepGenerator.setStepGroup(rowData.getCell(stepGroupIndex).getStringCellValue());
				testScriptStepGenerator.setSkipStep(rowData.getCell(skipStepIndex).getStringCellValue());
				testScriptStepGenerators.add(testScriptStepGenerator);
			}
		}
		return testScriptStepGenerators;
	}


	/*public static List<MigrationTestDataGenerator> getMigrationSuiteGenerator(TestSuiteGenerator testSuiteGenerator) throws FileNotFoundException, IOException{
		FileInputStream fileInputStream=new FileInputStream(new File(getConfigProperties().getProperty("TestDataFolder")+testSuiteGenerator.getTestScenario_RepositoryFile()));
		masterWorkbook=new XSSFWorkbook(fileInputStream);
		XSSFSheet migrationTestData=masterWorkbook.getSheet("MigrationTestData");
		int numberOfRows=migrationTestData.getLastRowNum();
		int SrNo=Integer.parseInt(masterConfigProp.getProperty("SrNo"));
		int ScenarioID=Integer.parseInt(masterConfigProp.getProperty("ScenarioID"));
		int Module=Integer.parseInt(masterConfigProp.getProperty("Module"));
		int Desription=Integer.parseInt(masterConfigProp.getProperty("Desription"));
		int Count=Integer.parseInt(masterConfigProp.getProperty("Count"));
		int MigrationScenarioID=Integer.parseInt(masterConfigProp.getProperty("MigrationScenarioID"));
		int Product=Integer.parseInt(masterConfigProp.getProperty("Product"));
		int ExecuteFlag=Integer.parseInt(masterConfigProp.getProperty("ExecuteFlag"));
		int ProposalNumber=Integer.parseInt(masterConfigProp.getProperty("ProposalNumber"));
		int ClientId1=Integer.parseInt(masterConfigProp.getProperty("ClientId1"));
		int ClientId2=Integer.parseInt(masterConfigProp.getProperty("ClientId2"));
		int Status=Integer.parseInt(masterConfigProp.getProperty("Status"));
		int ScriptName=Integer.parseInt(masterConfigProp.getProperty("ScriptName"));
		List<MigrationTestDataGenerator> migrationTestDataList=new ArrayList<>();

		for(int i=1;i<=numberOfRows;i++) {
			Row rowData=migrationTestData.getRow(i);
			if(rowData.getCell(ExecuteFlag).getStringCellValue().equalsIgnoreCase("yes")) {
				MigrationTestDataGenerator migrationTestDataGenerator=new MigrationTestDataGenerator();
				migrationTestDataGenerator.setSrNo((rowData.getCell(SrNo).getStringCellValue()));
				migrationTestDataGenerator.setScenarioID((rowData.getCell(ScenarioID).getStringCellValue()));
				migrationTestDataGenerator.setModule((rowData.getCell(Module).getStringCellValue()));
				migrationTestDataGenerator.setDesription((rowData.getCell(Desription).getStringCellValue()));
				migrationTestDataGenerator.setCount((rowData.getCell(Count).getStringCellValue()));
				migrationTestDataGenerator.setMigrationScenarioID((rowData.getCell(MigrationScenarioID).getStringCellValue()));
				migrationTestDataGenerator.setProduct((rowData.getCell(Product).getStringCellValue()));
				migrationTestDataGenerator.setExecuteFlag((rowData.getCell(ExecuteFlag).getStringCellValue()));
				migrationTestDataGenerator.setProposalNumber((rowData.getCell(ProposalNumber).getStringCellValue()));
				migrationTestDataGenerator.setClientId1((rowData.getCell(ClientId1).getStringCellValue()));
				migrationTestDataGenerator.setClientId2((rowData.getCell(ClientId2).getStringCellValue()));
				migrationTestDataGenerator.setStatus((rowData.getCell(Status).getStringCellValue()));
				migrationTestDataGenerator.setScriptName((rowData.getCell(ScriptName).getStringCellValue()));
				migrationTestDataList.add(migrationTestDataGenerator);
			}
		}
		return migrationTestDataList;
	}*/
	
	public static List<TestScenarioDataGenerator> getScenarioSuiteGenerator() throws Exception{
		String CONFIG_PATH = ConfigReader.getInstance().getValue("TestDataFolder") + File.separator;
		String MigrationPath="";
		FrameworkServices frameworkServices=new FrameworkServices();
		for(TestSuiteGenerator testSuiteGenerator:frameworkServices.getTestSuiteForExecution()) {
			MigrationPath=testSuiteGenerator.getTestScenario_RepositoryFile();
		}
		FileInputStream fileInputStream=new FileInputStream(new File(CONFIG_PATH.concat(MigrationPath)));
		masterWorkbook=new XSSFWorkbook(fileInputStream);
		XSSFSheet migrationTestData=masterWorkbook.getSheet("TestScenarios");
		int numberOfRows=migrationTestData.getLastRowNum();
		int SrNo=Integer.parseInt(ConfigReader.getInstance().getValue("SrNo"));
		int ScenarioID=Integer.parseInt(ConfigReader.getInstance().getValue("ScenarioID"));
		int Module=Integer.parseInt(ConfigReader.getInstance().getValue("Module"));
		int Desription=Integer.parseInt(ConfigReader.getInstance().getValue("Desription"));
		int Count=Integer.parseInt(ConfigReader.getInstance().getValue("Count"));
		int ScenarioUID=Integer.parseInt(ConfigReader.getInstance().getValue("ScenarioUID"));
		//int State=Integer.parseInt(ConfigReader.getInstance().getValue("TestType"));
		int State=Integer.parseInt(ConfigReader.getInstance().getValue("State"));
		int ExecuteFlag=Integer.parseInt(ConfigReader.getInstance().getValue("ExecuteFlag"));
		int GSTNid=Integer.parseInt(ConfigReader.getInstance().getValue("GSTNid"));
		int Status=Integer.parseInt(ConfigReader.getInstance().getValue("Status"));
		int ScriptName=Integer.parseInt(ConfigReader.getInstance().getValue("ScriptName"));
		List<TestScenarioDataGenerator> migrationTestDataList=new ArrayList<>();

		for(int i=1;i<=numberOfRows;i++) {
			Row rowData=migrationTestData.getRow(i);
			if(rowData.getCell(ExecuteFlag).getStringCellValue().equalsIgnoreCase("yes")) {
				TestScenarioDataGenerator migrationTestDataGenerator=new TestScenarioDataGenerator();
				migrationTestDataGenerator.setSrNo((rowData.getCell(SrNo).getStringCellValue()));
				migrationTestDataGenerator.setScenarioID((rowData.getCell(ScenarioID).getStringCellValue()));
				migrationTestDataGenerator.setModule((rowData.getCell(Module).getStringCellValue()));
				migrationTestDataGenerator.setDesription((rowData.getCell(Desription).getStringCellValue()));
				migrationTestDataGenerator.setCount((rowData.getCell(Count).getStringCellValue()));
				migrationTestDataGenerator.setScenarioUID((rowData.getCell(ScenarioUID).getStringCellValue()));
				migrationTestDataGenerator.setProduct((rowData.getCell(State).getStringCellValue()));
				migrationTestDataGenerator.setExecuteFlag((rowData.getCell(ExecuteFlag).getStringCellValue()));
				migrationTestDataGenerator.setGSTNID((rowData.getCell(GSTNid).getStringCellValue()));
				migrationTestDataGenerator.setStatus((rowData.getCell(Status).getStringCellValue()));
				migrationTestDataGenerator.setScriptName((rowData.getCell(ScriptName).getStringCellValue()));
				migrationTestDataList.add(migrationTestDataGenerator);
			}
		}
		return migrationTestDataList;
	}
	

}
