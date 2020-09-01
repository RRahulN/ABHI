package core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;


public class TestSuiteEngine {
	@SuppressWarnings("static-access")
	public List<XmlSuite> executeTestSuiteGenerator(FrameworkServices frameworkServices,List<XmlSuite> suiteList) throws Exception {

		for(TestSuiteGenerator testSuiteGenerator:frameworkServices.getTestSuiteForExecution()) {
			XmlSuite suite=new XmlSuite();
			suite.setName(testSuiteGenerator.getLOBName());
			suite.setThreadCount(2);
			suite.setParallel("tests");
			Map<String, String> testSuiteParams=new HashMap<>();
			testSuiteParams.put("BrowserName", testSuiteGenerator.getBrowserName());
			testSuiteParams.put("LoBName",testSuiteGenerator.getLOBName());
			testSuiteParams.put("TestScenario_RepositoryFileIndex", testSuiteGenerator.getTestScenario_RepositoryFile());
			testSuiteParams.put("TestData_RepositoryFile", testSuiteGenerator.getTestData_RepositoryFile());
			suite.setParameters(testSuiteParams);
			//for(MigrationTestDataGenerator migrationScenarioGenerator:frameworkServices.getMigrationSuiteGenerator(testSuiteGenerator)) {
			for(TestScenarioDataGenerator testScenarioDataGenerator:frameworkServices.getScenarioSuiteGenerator()) {
					XmlTest test=new XmlTest(suite);
					test.setName(testScenarioDataGenerator.getScenarioUID());
					test.setVerbose(2);
					Map<String, String> testCaseParams=new HashMap<>();
					testCaseParams.put("ScenarioID", testScenarioDataGenerator.getScenarioID());
					testCaseParams.put("Module", testScenarioDataGenerator.getModule());
					testCaseParams.put("Description", testScenarioDataGenerator.getDesription());
					testCaseParams.put("Product",testScenarioDataGenerator.getProduct());
					testCaseParams.put("ScriptReference", testScenarioDataGenerator.getScriptName());
					//testCaseParams.put("TestType", testScenarioDataGenerator.getProduct());
					//testCaseParams.put("Policy Type", testScenarioDataGenerator.getScriptName());
					//testCaseParams.put("BrowserName", "Chrome");
					testCaseParams.put("ScenarioUID",testScenarioDataGenerator.getScenarioUID());
					test.setParameters(testCaseParams);
					List<XmlClass> classes=new ArrayList<>();
					classes.add(new XmlClass("testRunner.TestExecutionSuite"));
					test.setClasses(classes);
				}
			
			suiteList.add(suite);
		}
		return suiteList;
	}
}




