package core;

public class TestSuiteGenerator {
	private String Reference;
	private String LOBName;
	private String Desription;
	private String TestScenario_RepositoryFile;
	private String TestData_RepositoryFile;
	private String TestData_CalculationRepositoryFile;
	private String BrowserName;
	private String ExecuteFlag;
	private String ExecutionStatus;
	private String ExecutedDate;
	
		

	public String getReference() {
		return Reference;
	}
	public void setReference(String reference) {
		Reference = reference;
	}
	public String getLOBName() {
		return LOBName;
	}
	public void setLOBName(String lOBName) {
		LOBName = lOBName;
	}
	public String getDesription() {
		return Desription;
	}
	public void setDesription(String desription) {
		Desription = desription;
	}
	public String getBrowserName() {
		return BrowserName;
	}
	public void setBrowserName(String browserName) {
		BrowserName = browserName;
	}
	public String getExecuteFlag() {
		return ExecuteFlag;
	}
	public void setExecuteFlag(String executeFlag) {
		ExecuteFlag = executeFlag;
	}
	
	public String getExecutedDate() {
		return ExecutedDate;
	}
	public void setExecutedDate(String executedDate) {
		ExecutedDate = executedDate;
	}
	public String getTestData_RepositoryFile() {
		return TestData_RepositoryFile;
	}
	public void setTestData_RepositoryFile(String testData_RepositoryFile) {
		TestData_RepositoryFile = testData_RepositoryFile;
	}
	public String getTestScenario_RepositoryFile() {
		return TestScenario_RepositoryFile;
	}
	public void setTestScenario_RepositoryFile(String testScenario_RepositoryFile) {
		TestScenario_RepositoryFile = testScenario_RepositoryFile;
	}
	
}
