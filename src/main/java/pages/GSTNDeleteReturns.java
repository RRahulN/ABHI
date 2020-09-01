package pages;

import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Recordset;

import util.CustomException;
import util.ExcelDatabase;
import util.ExcelRead;
import util.WaitTime;
import util.WebTable;

public class GSTNDeleteReturns extends WebTable {

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible fade show ng-star-inserted']")
	private List<WebElement> FailedMessage;

	@FindBy(xpath = "//span[contains(text(),'No Document(s) available in Table')]")
	private List<WebElement> SuccessMessage;

	@FindBy(xpath = "//button[@title='Click here to remove data from tool']")
	private WebElement RemoveAllData;

	@FindBy(xpath = "//p[text()='Are you sure you want to delete all the details? ']/..//div/button[text()='Yes']")
	private WebElement Yes;

	@FindBy(xpath = "//select[@id='selTable']")
	private WebElement SelectReturnType;

	@FindBy(xpath = "(//input[@class='ng-untouched ng-pristine ng-valid'])[3]")
	private WebElement Search;

	@FindBy(xpath = "//span[contains(text(),'Document(s) deleted successfully')]")
	private WebElement deleteMessage;

	@FindBy(xpath = "//button[text()='REMOVE']")
	private WebElement Remove;

	@FindBy(xpath = "(//button[text()='Yes'])[1]")
	private WebElement Yes1;
	
	@FindAll({
	@FindBy(xpath = "//div[@class='modal-content']/following::div[@class='btn btn-primary']/button[2]"),
	@FindBy(xpath = "//div[@class='modal-content']//following::div[@class='modal-footer ng-star-inserted']//button[2]"),
	@FindBy(xpath = "(//div[@class='modal-content']//following::div[@class='modal-footer ng-star-inserted']//button[2])[1]"),
	@FindBy(xpath = "(//div[@class='modal-content']//following::div[@class='modal-footer ng-star-inserted']//button[2])[2]")
	})
	private WebElement Yes2;
	
	@FindBy(xpath = "(//div[@class='modal-content']//following::div[@class='modal-footer ng-star-inserted']//button[2])[2]")
	private WebElement Yes3;

	@FindBy(xpath = "//span[contains(text(),'Document(s) deleted successfully')]")
	private List<WebElement> SuccessMessage1;

	@FindBy(xpath = "//button[@title=' Click here to remove the item from Offline tool']")
	private List<WebElement> RemoveButtn;
	
	@FindBy(xpath = "//button[@title='Remove Item']")
	private List<WebElement> RemoveButtnB2B;
	
	@FindBy(xpath = "//button[@title=' Click here to remove the item from Offline tool']")
	private List<WebElement> RemoveButtn3C3D;
	
	@FindBy(xpath = "//button[contains(@title,'Click here to remove the item from Offline tool')]")
	private List<WebElement> RemoveButtn3E3F;

	@FindBy(xpath = "//table[@class='table table-bordered']/tbody")
	private WebElement ItemTable;

	@FindBy(xpath = "//button[@title=' Click here to remove the item from Offline tool']")
	private List<WebElement> RemoveItems;

	@FindBy(xpath = "//table[@class='customTable table table-bordered ']/tbody")
	private WebElement DocumentTable;
	
	@FindBy(xpath="//button[text()='SAVE DOCUMENT']")
	private WebElement SaveDocument;
	
	@FindBy(xpath = "//span[contains(text(),'Document updated successfully')]")
	private List<WebElement> updateMessage;
	
	@FindBy(xpath = "//span[contains(text(),'Document(s) deleted successfully')]")
	private List<WebElement> DeleteMessageB2B;
	
	//@FindBy(xpath="(//select[@class='form-control ng-untouched ng-pristine ng-valid'])[2]")
	@FindBy(xpath="//span[contains(text(),' Records Per Page : ')]/..//select")
	private WebElement pagignationDropDown;
	
	@FindBy(xpath="//li[@class='pagination-next ng-star-inserted']/a")
	private List<WebElement> NextPage;
	
	@FindBy(xpath = "//table[@class='DocTable table table-bordered']/tbody")
	private WebElement B2BDocumentTable;
	
	@FindBy(xpath = "(//input[@class='ng-untouched ng-pristine ng-valid'])[1]")
	private WebElement Search3C3D;
	
	@FindBy(xpath = "//table[@class='ExpDocTable table table-bordered ']/tbody")
	private WebElement DocumentTable3C3D;
	
	@FindBy(xpath = "//table[@class='DocTable table table-bordered ']/tbody")
	private WebElement DocumentTable3E3F;
	
	@FindBy(xpath = "//table[@class='DocTable table table-bordered']/tbody")
	private WebElement DocumentTable3H;
	
	@FindBy(xpath="//span[contains(text(),'Search : ')]/../div[1]//input")
	private WebElement SearchRecords;
	
	@FindBy(xpath = "//table[@class='DocTable table table-bordered ']/tbody")
	private WebElement DocumentTable3I;
	
	
	
	By rowElementDoc=By.xpath(".//tr/td[4]");
	By colElementDoc=By.xpath(".//parent::tr/td[5]");
	By EditButtonDoc=By.xpath(".//parent::tr/descendant::div/button[1]");
	By rowElementDocB2B=By.xpath(".//tr/td[11]");
	By colElementDocB2B=By.xpath(".//parent::tr/td[12]");
	By EditButtonDocB2B=By.xpath(".//parent::tr/descendant::div/button[1]");
	By CheckBox=By.xpath(".//parent::tr/descendant::input");
	By colElementDoc3E3F=By.xpath(".//parent::tr/td[13]");
	By taxableValue3H=By.xpath(".//parent::tr/td[6]");
	By integratedTax3H=By.xpath(".//parent::tr/td[7]");
	By taxableValue3I=By.xpath(".//parent::tr/td[4]");
	By integratedTax3I=By.xpath(".//parent::tr/td[5]");
	By taxableValue3J=By.xpath(".//parent::tr/td[9]");
	By integratedTax3J=By.xpath(".//parent::tr/td[10]");
	By MarkForDelete=By.xpath(".//parent::tr/descendant::div/button[3]");
	
	By colElementItem=By.xpath("//input[@id='irate']");
	By colElementItem1=By.xpath(".//ancestor::tr//input[@title='Please enter Integrated Tax Value']");
	By RemoveButtonItem=By.xpath(".//ancestor::tr//button[@title=' Click here to remove the item from Offline tool']");
	By RemoveButtonItemB2B=By.xpath(".//ancestor::tr//button[@title='Remove Item']");
	By taxableValue=By.xpath("//input[@title='Enter taxable value']");
	By HSNCodeB2B=By.xpath("//input[@placeholder='Enter HSN Code/Description']");
	By TaxableValueB2B=By.xpath(".//ancestor::tr//input[@title='Enter taxable value']");
	By CentralTaxB2B=By.xpath(".//ancestor::tr//input[@title='Please enter Central Tax Value']");
	By RemoveButtonItem3C3D=By.xpath(".//ancestor::tr//button[@title=' Click here to remove the item from Offline tool']");
	By RemoveButtonItem3E3F=By.xpath(".//ancestor::tr//button[contains(@title,'Click here to remove the item from Offline tool')]");
	By removeDocumentLevelMessage=By.xpath("//div/span/i/..");

	

	ExcelDatabase database = new ExcelDatabase();
	WebDriverWait wait;
	WebDriver driver;

	public GSTNDeleteReturns(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}

	public void deleteAllDocuments(WebDriver driver, String testCaseName, String GSTNID, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {

		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteAllDocument", testCaseName,
				stepGroup);

		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		Reporter.log("<B>Select '" + dataRow.getProperty("ReturnType") + "' from drop down</B>");
		if (isDisplayed(SelectReturnType)) {
			selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),
					"Return type Drop Down");
		}

		Reporter.log("<B>Remove all records of GSTN='" + GSTNID + "'</B>");
		if (GSTNID.equalsIgnoreCase(dataRow.getProperty("GSTIN"))) {
			click(RemoveAllData, "REMOVE DATA IN SELECTED TABLE");
			wait.until(ExpectedConditions.visibilityOf(Yes));
			click(Yes, "Yes");
			Thread.sleep(WaitTime.low);
		}
		if (SuccessMessage.size() != 0) {
			String message = fetchTextFromApplication(SuccessMessage.get(0), "Success Message");
			database.updateQueryForData(conn, "GSTN_DeleteAllDocument", "Message", testCaseName, stepGroup, message);
			Reporter.log("<B> " + message + "</B>");
		}

	}

	public void deleteSelectedDocument(WebDriver driver, String testCaseName, String GSTNID, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedDocument", testCaseName,stepGroup);
		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		Reporter.log("<B>Select '" + dataRow.getProperty("ReturnType") + "' from drop down</B>");
		if (isDisplayed(SelectReturnType)) {
			selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");
		}

		Reporter.log("<B>Remove Selected records of GSTN='" + GSTNID + "'</B>");

		Thread.sleep(WaitTime.medium);
		Recordset tableData = database.selectQueryForHSNData(conn, "GSTN_DeleteSelectedDocument", "TCID", testCaseName,GSTNID);
		while (tableData.next()) {
			if (tableData.getField("TCID").equals(testCaseName) && tableData.getField("GSTIN").equals(GSTNID)) {

				String state = tableData.getField("PlaceOfSupply");

				String[] split = state.split(" ");
				String getState = split[0].trim();

				clearAndSenKeys(Search, getState, "Search");

				Double totalTaxableValue = Double.parseDouble(tableData.getField("TotalTaxableValue").replaceAll(",", ""));
				String TotalTaxableValue=getCurrency(totalTaxableValue);
				String TotalIntegratedTax = tableData.getField("TotalIntegratedTax");

				WebElement checkBox = driver.findElement(By.xpath("//table/tbody/tr/td[4][text()='" + TotalTaxableValue+ "']/parent::tr/td[5][text()='" + TotalIntegratedTax + "']/parent::tr/descendant::input"));
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", checkBox);
				if (!checkBox.isSelected()) {
					Reporter.log("Remove Document where Taxable value is <B> '" + TotalTaxableValue + "'</B> and Integrated Tax is <B> '" + TotalIntegratedTax + "'</B>");
					highlighter(checkBox);
					selectCheckBox(checkBox, "Checkbox");
					Reporter.log("<B>CheckBox</B> is checked");
					click(Remove, "Remove");
				}
				wait.until(ExpectedConditions.visibilityOf(Yes1));
				click(Yes1, "Yes");
				Thread.sleep(WaitTime.low);
			}

		}
		if (deleteMessage.isDisplayed()) {
			highlighter(deleteMessage);
			String message = fetchTextFromApplication(deleteMessage, "Success Message");
			database.updateQueryForData(conn, "GSTN_DeleteAllDocument", "Message", testCaseName, stepGroup, message);
			Reporter.log("<B> " + message + "</B>");
		}
	}

	public void deleteSelectedItem(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);

		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		Reporter.log("<B>Select '" + dataRow.getProperty("ReturnType") + "' from drop down</B>");
		if (isDisplayed(SelectReturnType)) {
			selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");
		}

		Reporter.log("<B>Remove Selected records of GSTN='" + GSTNID + "'</B>");

		Thread.sleep(WaitTime.medium);
		Recordset tableData = database.selectQueryForHSNData(conn, "GSTN_DeleteSelectedItems", "TCID", testCaseName,GSTNID);
		while (tableData.next()) {
			if (tableData.getField("TCID").equals(testCaseName) && tableData.getField("GSTIN").equals(GSTNID)) {

				String state = tableData.getField("PlaceOfSupply");

				String[] split = state.split(" ");
				String getState = split[0].trim();

				clearAndSenKeys(Search, getState, "Search");

				Double TotalTaxableValue = Double.parseDouble(tableData.getField("TaxableValue").replaceAll(",", ""));
				String Currencyformat=getCurrency(TotalTaxableValue);
		
				String TaxableRate = tableData.getField("TaxRate");
				
				WebElement editButton=selectFromTable(DocumentTable,rowElementDoc,colElementDoc,Currencyformat,TaxableRate,EditButtonDoc);
				if (editButton.isEnabled()) {
					highlighter(editButton);
					Reporter.log("Edit Document where Taxable value is <B> '" + Currencyformat + "'</B> and TaxRate Tax is <B> '" + TaxableRate + "'</B>");
					click(editButton, "EDIT");
				}
			}
			if (RemoveButtn.size() != 0) {
				Recordset removeItem = database.selectQueryForHSNData(conn, "GSTN_Remove_Item", "TCID", testCaseName,GSTNID);

				while (removeItem.next()) {
					if (removeItem.getField("TCID").equals(testCaseName) && removeItem.getField("GSTIN").equals(GSTNID)&& removeItem.getField("Remove").equalsIgnoreCase("Yes")&& removeItem.getField("ReturnType").equalsIgnoreCase(ReturnType)) {
						Double TotalTaxableValue = Double.parseDouble(removeItem.getField("TaxableValue").replaceAll(",", ""));
						String Currencyformat=getCurrency(TotalTaxableValue);
						WebElement removeItems=selectFromItems(ItemTable, colElementItem, colElementItem1, Currencyformat, removeItem.getField("IntegratedTax"), RemoveButtonItem);
						Reporter.log("Remove item where Taxable value is <B> '" + Currencyformat + "'</B> and Integrated Tax is <B> '" + removeItem.getField("IntegratedTax") + "'</B>");
						click(removeItems, "Remove");
					}
					if (SuccessMessage.size() != 0) {
						highlighter(SuccessMessage.get(0));
						String message = fetchTextFromApplication(SuccessMessage.get(0), "Success Message");
						database.updateQueryForData(conn, "GSTN_DeleteAllDocument", "Message", testCaseName, stepGroup,message);
						Reporter.log("<B> " + message + "</B>");
					}
				}
			}
			click(SaveDocument, "Save Document");
			if (updateMessage.size() != 0) {
				highlighter(updateMessage.get(0));
				String message = fetchTextFromApplication(updateMessage.get(0), "Update Message");
				database.updateQueryForData(conn, "GSTN_Remove_Item", "Message", testCaseName, stepGroup,message);
				Reporter.log("<B> " + message + "</B>");
			}
		}
	}

	/*public WebElement TaxableValue(String value, String value2) {
		List<WebElement> listElement = this.driver.findElements(By.xpath("//input[@id='irate']"));
		for (WebElement webElement : listElement) {
			if (webElement.getAttribute("value").equalsIgnoreCase(value.concat(".00")))
				return IntegratedTax(webElement, value2);
		}
		return null;
	}

	public WebElement IntegratedTax(WebElement webElement, String value) {
		List<WebElement> listElement = webElement.findElements(By.xpath(".//ancestor::tr//input[@title='Please enter Integrated Tax Value']"));
		for (WebElement Element : listElement) {
			if (Element.getAttribute("value").equalsIgnoreCase(value))
				return Element;
		}
		return null;
	}*/
	
	public void deleteSelectedItemB2B(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);

		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		Reporter.log("<B>Select '" + dataRow.getProperty("ReturnType") + "' from drop down</B>");
		if (isDisplayed(SelectReturnType)) {
			selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");
		}

		Reporter.log("<B>Remove Selected items of GSTN='" + GSTNID + "'</B>");

		Thread.sleep(WaitTime.medium);
		Recordset tableData = database.selectQueryForHSNData(conn, "GSTN_DeleteSelectedItems", "TCID", testCaseName,GSTNID);
		Outer:while (tableData.next()) {
			if (tableData.getField("TCID").equals(testCaseName) && tableData.getField("GSTIN").equals(GSTNID)) {

				
				String RecipientGSTINUIN = dataRow.getProperty("RecipientGSTIN/UIN");
				clearAndSenKeys(Search, RecipientGSTINUIN, "Search");

				Double TotalTaxableValue = Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",",""));
				String Currencyformat=getCurrency(TotalTaxableValue);
				//Defect 14906 where table summary doesn't match with Document grid Hence reducing value by 0.1
				String TaxableRate = dataRow.getProperty("TaxRate");
				//Double TotalIntegratedtax=Double.parseDouble(TaxableRate)-0.01;
				Double TotalIntegratedtax=Double.parseDouble(TaxableRate);
				String TotalIntegratedTax = String.format("%.2f", TotalIntegratedtax);
				
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", pagignationDropDown);
				wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
				Select Pagignation=new Select(pagignationDropDown);
				highlighter(pagignationDropDown);
				List<WebElement> editButton=selectFromTables(B2BDocumentTable,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDocB2B);
				if (editButton!=null) {
					highlighter(editButton.get(0));
					click(editButton.get(0), "EDIT");
					Reporter.log("Edit document with Taxable value (₹) <B> '" + Currencyformat + "'</B> and Integrated tax <B> '" + TotalIntegratedTax + "'</B> from document grid");
				}
				else
				{
					for(int i=0;i<Pagignation.getOptions().size();i++) {
			        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", pagignationDropDown);
			        	//System.out.println(Pagignation.getOptions().get(i).getText());
			        	Pagignation.selectByVisibleText(Pagignation.getOptions().get(i).getText());
			        	if (selectFromTables(B2BDocumentTable,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDocB2B)!=null) {
			        		WebElement EditButton=selectFromTables(B2BDocumentTable,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDocB2B).get(0);
			        		highlighter(EditButton);
							click(EditButton, "EDIT");
							Reporter.log("Edit document with Taxable value (₹) <B> '" + Currencyformat + "'</B> and Integrated tax <B> '" + TotalIntegratedTax + "'</B> from document grid");
						break Outer;}
			        	else {
			        		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.tagName("a")));			        				        	
			        		while(NextPage.size()!=0) {
			        	    String title = NextPage.get(0).getAttribute("aria-label");
			        	    if (title.equals("Next page")) {
			        	    	NextPage.get(0).click();
			        	        if (selectFromTables(B2BDocumentTable,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDocB2B)!=null) {
			        	        	WebElement EditButton=selectFromTables(B2BDocumentTable,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDocB2B).get(0);
					        		highlighter(EditButton);
									click(EditButton, "EDIT");
									Reporter.log("Edit document with Taxable value (₹) <B> '" + Currencyformat + "'</B> and Integrated tax <B> '" + TotalIntegratedTax + "'</B> from document grid");
								break Outer;}

								}
							}
						}
					}
				}
			}
		}
		if (RemoveButtnB2B.size() != 0) {
				Recordset removeItem = database.selectQueryForHSNData(conn, "GSTN_Remove_Item", "TCID", testCaseName,GSTNID);

				while (removeItem.next()) {
					if (removeItem.getField("TCID").equals(testCaseName) && removeItem.getField("GSTIN").equals(GSTNID)&& removeItem.getField("Remove").equalsIgnoreCase("Yes")&& removeItem.getField("ReturnType").equalsIgnoreCase(ReturnType)) {
						String HSNcode= removeItem.getField("HSNCode");
						Double TotalTaxableValue = Double.parseDouble(removeItem.getField("TaxableValue"));
						String Currencyformat=getCurrency(TotalTaxableValue);
						WebElement removeItems=selectFromItems(ItemTable, HSNCodeB2B,TaxableValueB2B, HSNcode, Currencyformat, RemoveButtonItemB2B);
						highlighter(removeItems);
						click(removeItems, "Remove");
						Reporter.log("Remove item with Taxable value (₹) <B> '" + Currencyformat + "'</B> and Integrated tax <B> '" + removeItem.getField("IntegratedTax") + "'</B> from item grid");
					}
					/*if (SuccessMessage.size() != 0) {
						highlighter(SuccessMessage.get(0));
						String message = fetchTextFromApplication(SuccessMessage.get(0), "Success Message");
						database.updateQueryForData(conn, "GSTN_DeleteAllDocument", "Message", testCaseName, stepGroup,message);
						Reporter.log("<B> " + message + "</B>");
					}*/
				}
			}
			click(SaveDocument, "Save Document");
			if (updateMessage.size() != 0) {
				highlighter(updateMessage.get(0));
				String message = fetchTextFromApplication(updateMessage.get(0), "Update Message");
				database.updateQueryForData(conn, "GSTN_Remove_Item", "Message", testCaseName, stepGroup,message);
				Reporter.log("<B> " + message + "</B>");
			}
		}

	public void deleteSelectedItem3C3D(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);

		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		Reporter.log("<B>Select '" + dataRow.getProperty("ReturnType") + "' from drop down</B>");
		if (isDisplayed(SelectReturnType)) {
			selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");
		}

		Reporter.log("<B>Remove Selected items of GSTN='" + GSTNID + "'</B>");

		Thread.sleep(WaitTime.medium);
		Recordset tableData = database.selectQueryForHSNData(conn, "GSTN_DeleteSelectedItems", "TCID", testCaseName,GSTNID);
		Outer:while (tableData.next()) {
			if (tableData.getField("TCID").equals(testCaseName) && tableData.getField("GSTIN").equals(GSTNID)) {

				
				String RecipientGSTINUIN = dataRow.getProperty("TaxableValue").split("\\.")[0];
			    clearAndSenKeys(Search3C3D, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");

				Double TotalTaxableValue = Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",",""));
				String Currencyformat=getCurrency(TotalTaxableValue);
				//Defect 14906 where table summary doesn't match with Document grid Hence reducing value by 0.1
				String TaxableRate = dataRow.getProperty("TaxRate");
				//Double TotalIntegratedtax=Double.parseDouble(TaxableRate)-0.01;
				Double TotalIntegratedtax=Double.parseDouble(TaxableRate);
				String TotalIntegratedTax = String.format("%.2f", TotalIntegratedtax);
				
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", pagignationDropDown);
				wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
				Select Pagignation=new Select(pagignationDropDown);
				highlighter(pagignationDropDown);
				List<WebElement> editButton=selectFromTables(DocumentTable3C3D,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDocB2B);
				if (editButton!=null) {
					highlighter(editButton.get(0));
					click(editButton.get(0), "EDIT");
					Reporter.log("Edit document with Taxable value (₹) <B> '" + Currencyformat + "'</B> and Integrated tax <B> '" + TotalIntegratedTax + "'</B> from document grid");
				}
				else
				{
					for(int i=0;i<Pagignation.getOptions().size();i++) {
			        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", pagignationDropDown);
			        	//System.out.println(Pagignation.getOptions().get(i).getText());
			        	Pagignation.selectByVisibleText(Pagignation.getOptions().get(i).getText());
			        	if (selectFromTables(DocumentTable3C3D,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDocB2B)!=null) {
			        		WebElement EditButton=selectFromTables(DocumentTable3C3D,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDocB2B).get(0);
			        		highlighter(EditButton);
							click(EditButton, "EDIT");
							Reporter.log("Edit document with Taxable value (₹) <B> '" + Currencyformat + "'</B> and Integrated tax <B> '" + TotalIntegratedTax + "'</B> from document grid");
						break Outer;}
			        	else {
			        		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.tagName("a")));			        				        	
			        		while(NextPage.size()!=0) {
			        	    String title = NextPage.get(0).getAttribute("aria-label");
			        	    if (title.equals("Next page")) {
			        	    	NextPage.get(0).click();
			        	        if (selectFromTables(DocumentTable3C3D,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDocB2B)!=null) {
			        	        	WebElement EditButton=selectFromTables(DocumentTable3C3D,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDocB2B).get(0);
					        		highlighter(EditButton);
									click(EditButton, "EDIT");
									Reporter.log("Edit document with Taxable value (₹) <B> '" + Currencyformat + "'</B> and Integrated tax <B> '" + TotalIntegratedTax + "'</B> from document grid");
								break Outer;}

								}
							}
						}
					}
				}
			}
		}
		if (RemoveButtn3C3D.size() != 0) {
				Recordset removeItem = database.selectQueryForHSNData(conn, "GSTN_Remove_Item", "TCID", testCaseName,GSTNID);

				while (removeItem.next()) {
					if (removeItem.getField("TCID").equals(testCaseName) && removeItem.getField("GSTIN").equals(GSTNID)&& removeItem.getField("Remove").equalsIgnoreCase("Yes")&& removeItem.getField("ReturnType").equalsIgnoreCase(ReturnType)) {
						String HSNcode= removeItem.getField("HSNCode");
						Double TotalTaxableValue = Double.parseDouble(removeItem.getField("TaxableValue"));
						String Currencyformat=getCurrency(TotalTaxableValue);
						WebElement removeItems=selectFromItems(ItemTable, HSNCodeB2B,TaxableValueB2B, HSNcode, Currencyformat, RemoveButtonItem3C3D);
						highlighter(removeItems);
						click(removeItems, "Remove");
						Reporter.log("Remove item with HSNCode (₹) <B> '" + HSNcode + "'</B> and Taxable Value <B> '" + Currencyformat + "'</B> from item grid");
					}
					
				}
			}
			click(SaveDocument, "Save Document");
			if (updateMessage.size() != 0) {
				highlighter(updateMessage.get(0));
				String message = fetchTextFromApplication(updateMessage.get(0), "Update Message");
				database.updateQueryForData(conn, "GSTN_Remove_Item", "Message", testCaseName, stepGroup,message);
				Reporter.log("<B> " + message + "</B>");
			}
		}
	public void deleteSelectedItem3E3F(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
		
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);

		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");

			if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {
				
				String RecipientGSTINUIN = dataRow.getProperty("TaxableValue").split("\\.")[0];
			    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
			  }

			String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",", "")));
			String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TaxRate")));
			scrollIntoViewJavascript(pagignationDropDown);
			wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
			Select Pagignation=dropDown(pagignationDropDown);
			List<WebElement> editButtons=selectFromTables(DocumentTable3E3F,colElementDocB2B,colElementDoc3E3F,TotalTaxableValue,TotalIntegratedtax,EditButtonDoc);
			clickOnEditDocument(editButtons, pagignationDropDown, Pagignation);
			deleteEntriesFromItemGrid(driver,removeDocumentLevelMessage,conn, testCaseName, GSTNID, ReturnType, RemoveButtn3E3F, ItemTable,HSNCodeB2B,TaxableValueB2B,RemoveButtonItem3E3F);
			click(SaveDocument, "Save Document");
			verifyAlertMessage(updateMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_Remove_Item");
		}
	
public void deleteSelectedItem3G(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
		
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);

		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");

			if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {
				
				String RecipientGSTINUIN = dataRow.getProperty("TaxableValue").split("\\.")[0];
			    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
			  }

			String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",", "")));
			String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TaxRate")));
			scrollIntoViewJavascript(pagignationDropDown);
			wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
			Select Pagignation=dropDown(pagignationDropDown);
			List<WebElement> editButtons=selectFromTables(DocumentTable3E3F,rowElementDocB2B,colElementDocB2B,TotalTaxableValue,TotalIntegratedtax,EditButtonDoc);
			clickOnEditDocument(editButtons, pagignationDropDown, Pagignation);
			deleteEntriesFromItemGrid(driver,removeDocumentLevelMessage,conn, testCaseName, GSTNID, ReturnType, RemoveButtn3E3F, ItemTable,HSNCodeB2B,TaxableValueB2B,RemoveButtonItem3E3F);
			click(SaveDocument, "Save Document");
			verifyAlertMessage(updateMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_Remove_Item");
		}
public void deleteSelectedItem3H(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
	
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);

	wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
	selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");

		if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {
			
			String RecipientGSTINUIN = dataRow.getProperty("TaxableValue").split("\\.")[0];
		    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
		  }

		String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",", "")));
		String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TaxRate")));
		scrollIntoViewJavascript(pagignationDropDown);
		wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
		Select Pagignation=dropDown(pagignationDropDown);
		List<WebElement> editButtons=selectFromTables(DocumentTable3H,taxableValue3H,integratedTax3H,TotalTaxableValue,TotalIntegratedtax,EditButtonDoc);
		clickOnEditDocument(editButtons, pagignationDropDown, Pagignation);
		deleteEntriesFromItemGrid(driver,removeDocumentLevelMessage,conn, testCaseName, GSTNID, ReturnType, RemoveButtn3E3F, ItemTable,HSNCodeB2B,TaxableValueB2B,RemoveButtonItem3E3F);
		click(SaveDocument, "Save Document");
		verifyAlertMessage(updateMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_Remove_Item");
	}
public void deleteSelectedItem3I(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
	
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);

	wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
	selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");

		if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {
			
			String RecipientGSTINUIN = dataRow.getProperty("TaxableValue").split("\\.")[0];
		    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
		  }

		String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",", "")));
		String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TaxRate")));
		scrollIntoViewJavascript(pagignationDropDown);
		wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
		Select Pagignation=dropDown(pagignationDropDown);
		List<WebElement> editButtons=selectFromTables(DocumentTable3I,taxableValue3I,integratedTax3I,TotalTaxableValue,TotalIntegratedtax,EditButtonDoc);
		clickOnEditDocument(editButtons, pagignationDropDown, Pagignation);
		deleteEntriesFromItemGrid(driver,removeDocumentLevelMessage,conn, testCaseName, GSTNID, ReturnType, RemoveButtn3E3F, ItemTable,HSNCodeB2B,TaxableValueB2B,RemoveButtonItem3E3F);
		click(SaveDocument, "Save Document");
		verifyAlertMessage(updateMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_Remove_Item");
	}
public void deleteSelectedItem3J(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {	
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
	wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
	selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");

		if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {
			
			String RecipientGSTINUIN = dataRow.getProperty("TaxableValue").split("\\.")[0];
		    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
		  }

		String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",", "")));
		String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TaxRate")));
		scrollIntoViewJavascript(pagignationDropDown);
		wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
		Select Pagignation=dropDown(pagignationDropDown);
		List<WebElement> editButtons=selectFromTables(DocumentTable3I,taxableValue3J,integratedTax3J,TotalTaxableValue,TotalIntegratedtax,EditButtonDoc);
		clickOnEditDocument(editButtons, pagignationDropDown, Pagignation);
		deleteEntriesFromItemGrid(driver,removeDocumentLevelMessage,conn, testCaseName, GSTNID, ReturnType, RemoveButtn3E3F, ItemTable,HSNCodeB2B,TaxableValueB2B,RemoveButtonItem3E3F);
		click(SaveDocument, "Save Document");
		verifyAlertMessage(updateMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_Remove_Item");
	}
public void deleteSelectedItem3K(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {	
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
	wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
	selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");

		if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {
			
			String RecipientGSTINUIN = dataRow.getProperty("TaxableValue").split("\\.")[0];
		    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
		  }

		String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",", "")));
		String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TaxRate")));
		scrollIntoViewJavascript(pagignationDropDown);
		wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
		Select Pagignation=dropDown(pagignationDropDown);
		List<WebElement> editButtons=selectFromTables(DocumentTable3I,rowElementDocB2B,colElementDocB2B,TotalTaxableValue,TotalIntegratedtax,EditButtonDoc);
		clickOnEditDocument(editButtons, pagignationDropDown, Pagignation);
		deleteEntriesFromItemGrid(driver,removeDocumentLevelMessage,conn, testCaseName, GSTNID, ReturnType, RemoveButtn3E3F, ItemTable,HSNCodeB2B,TaxableValueB2B,RemoveButtonItem3E3F);
		click(SaveDocument, "Save Document");
		verifyAlertMessage(updateMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_Remove_Item");
	}
public void deleteSelectedItem3L(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {	
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
	wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
	selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");

		if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {			
			String RecipientGSTINUIN = dataRow.getProperty("TaxableValue").split("\\.")[0];
		    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
		  }

		String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",", "")));
		String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TaxRate")));
		scrollIntoViewJavascript(pagignationDropDown);
		wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
		Select Pagignation=dropDown(pagignationDropDown);
		List<WebElement> editButtons=selectFromTables(DocumentTable3I,colElementDocB2B,colElementDoc3E3F,TotalTaxableValue,TotalIntegratedtax,EditButtonDoc);
		clickOnEditDocument(editButtons, pagignationDropDown, Pagignation);
		deleteEntriesFromItemGrid(driver,removeDocumentLevelMessage,conn, testCaseName, GSTNID, ReturnType, RemoveButtn3E3F, ItemTable,HSNCodeB2B,TaxableValueB2B,RemoveButtonItem3E3F);
		click(SaveDocument, "Save Document");
		verifyAlertMessage(updateMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_Remove_Item");
	}
	public void deleteSelectedDocumentB2B(WebDriver driver, String testCaseName, String GSTNID, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedDocument", testCaseName,stepGroup);

		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		Reporter.log("<B>Select '" + dataRow.getProperty("ReturnType") + "' from drop down</B>");
		if (isDisplayed(SelectReturnType)) {
			selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");
		}

		Reporter.log("<B>Remove Selected records of GSTN='" + GSTNID + "'</B>");

		Thread.sleep(WaitTime.medium);
		Recordset tableData = database.selectQueryForHSNData(conn, "GSTN_DeleteSelectedDocument", "TCID", testCaseName,GSTNID);
		while (tableData.next()) {
			if (tableData.getField("TCID").equals(testCaseName) && tableData.getField("GSTIN").equals(GSTNID)) {

				String RecipientGSTINUIN = tableData.getField("RecipientGSTIN/UIN");
				/*String[] split = state.split(" ");
				String getState = split[0].trim();*/
				clearAndSenKeys(Search, RecipientGSTINUIN, "Search");

				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", pagignationDropDown);
				wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
				Select Pagignation=new Select(pagignationDropDown);
				highlighter(pagignationDropDown);
		        
		       
				//Defect 14906 where table summary doesn't match with Document grid Hence reducing value by 0.1
				String TaxableRate = dataRow.getProperty("TotalIntegratedTax");
				/*Double TotalIntegratedtax=Double.parseDouble(TaxableRate)-0.01;
				String TotalIntegratedTax = String.format("%.2f", TotalIntegratedtax);*/
				Double TotalTaxableValue = Double.parseDouble(dataRow.getProperty("TotalTaxableValue").replaceAll(",",""));
				String Currencyformat=getCurrency(TotalTaxableValue);

				//WebElement checkBox = driver.findElement(By.xpath("//table[@class='DocTable table table-bordered']/tbody/tr/td[11][text()=' " + Currencyformat + " ']/parent::tr/td[12][text()='" + TaxableRate + "']/parent::tr/descendant::input"));
			
				Outer:if (driver.findElements(By.xpath("//table[@class='DocTable table table-bordered']/tbody/tr/td[11][text()=' " + Currencyformat + " ']/parent::tr/td[12][text()='" + TaxableRate + "']/parent::tr/descendant::input")).size() != 0) {
					WebElement checkBox = driver.findElement(By.xpath("//table[@class='DocTable table table-bordered']/tbody/tr/td[11][text()=' " + Currencyformat + " ']/parent::tr/td[12][text()='" + TaxableRate + "']/parent::tr/descendant::input"));
					highlighter(checkBox);
					selectCheckBox(checkBox, "Checkbox");
		        	Reporter.log("Remove dcument where Taxable Value is <B> " + Currencyformat + "</B> and Integrated tax is <B> " + TaxableRate + "</B>");		
					click(Remove, "Remove");
				}
				else
				{
					for(int i=0;i<Pagignation.getOptions().size();i++) {
			        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", pagignationDropDown);
			        	//System.out.println(Pagignation.getOptions().get(i).getText());
			        	Pagignation.selectByVisibleText(Pagignation.getOptions().get(i).getText());
			        	if (driver.findElements(By.xpath("//table[@class='DocTable table table-bordered']/tbody/tr/td[11][text()=' " + Currencyformat + " ']/parent::tr/td[12][text()='" + TaxableRate + "']/parent::tr/descendant::input")).size() != 0) {
			        		WebElement checkBox = driver.findElement(By.xpath("//table[@class='DocTable table table-bordered']/tbody/tr/td[11][text()=' " + Currencyformat + " ']/parent::tr/td[12][text()='" + TaxableRate + "']/parent::tr/descendant::input"));
			        	selectCheckBox(checkBox, "Checkbox");
			        	Reporter.log("Remove dcument where Taxable Value is <B> " + Currencyformat + "</B> and Integrated tax is <B> " + TaxableRate + "</B>");		
						click(Remove, "Remove");
						break Outer;}
			        	else {
			        		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.tagName("a")));			        	
			        	//for (int j = 0; j < NextPage.size(); j++) {
			        		while(NextPage.size()!=0) {
			        	    String title = NextPage.get(0).getAttribute("aria-label");
			        	    if (title.equals("Next page")) {
			        	    	NextPage.get(0).click();
			        	        if (driver.findElements(By.xpath("//table[@class='DocTable table table-bordered']/tbody/tr/td[11][text()=' " + Currencyformat + " ']/parent::tr/td[12][text()='" + TaxableRate + "']/parent::tr/descendant::input")).size() != 0) {
					        		WebElement checkBox = driver.findElement(By.xpath("//table[@class='DocTable table table-bordered']/tbody/tr/td[11][text()=' " + Currencyformat + " ']/parent::tr/td[12][text()='" + TaxableRate + "']/parent::tr/descendant::input"));
					        	selectCheckBox(checkBox, "Checkbox");
					        	Reporter.log("Remove dcument where Taxable Value is <B> " + Currencyformat + "</B> and Integrated tax is <B> " + TaxableRate + "</B>");		
								click(Remove, "Remove");
								break Outer;}
			        	        //break;
			        	    }
			        	}
			        	}
					}
				}
				wait.until(ExpectedConditions.visibilityOf(Yes1));
				click(Yes1, "Yes");
		        
				//break Outer;}
				Thread.sleep(WaitTime.low);
				if (DeleteMessageB2B.size() != 0) {
					highlighter(DeleteMessageB2B.get(0));
					String message = fetchTextFromApplication(DeleteMessageB2B.get(0), "Delete Message");
					database.updateQueryForData(conn, "GSTN_DeleteSelectedDocument", "Message", testCaseName, stepGroup, message);
					Reporter.log("<B> " + message + "</B>");
				}
			}
		 
		}
		
	}
	public void deleteSelectedDocument3C3D(WebDriver driver, String testCaseName, String GSTNID, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedDocument", testCaseName,stepGroup);
		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		Reporter.log("<B>Select '" + dataRow.getProperty("ReturnType") + "' from drop down</B>");
		if (isDisplayed(SelectReturnType)) {
			selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");
		}
		Reporter.log("<B>Remove Selected records of GSTN='" + GSTNID + "'</B>");
		Thread.sleep(WaitTime.medium);
		Recordset tableData = database.selectQueryForHSNData(conn, "GSTN_DeleteSelectedDocument", "TCID", testCaseName,GSTNID);
		while (tableData.next()) {
			if (tableData.getField("TCID").equals(testCaseName) && tableData.getField("GSTIN").equals(GSTNID)) {
				String TotalTaxablevalue = tableData.getField("TotalTaxableValue").split("\\.")[0];
			    clearAndSenKeys(Search3C3D, TotalTaxablevalue.replaceAll("[^a-zA-Z0-9]", ""), "Search");
			    String TaxableRate = tableData.getField("TotalIntegratedTax");
				Double TotalTaxableValue = Double.parseDouble(tableData.getField("TotalTaxableValue").replaceAll(",",""));
				String Currencyformat=getCurrency(TotalTaxableValue);  
				WebElement checkBox= selectTableFromDocumentGrid(driver, testCaseName, GSTNID, workbook, conn, stepGroup, Currencyformat, TaxableRate, DocumentTable3C3D,rowElementDocB2B,colElementDocB2B);
					        	selectCheckBox(checkBox, "Checkbox");
					        	Reporter.log("Remove dcument where Taxable Value is <B> " + Currencyformat + "</B> and Integrated tax is <B> " + TaxableRate + "</B>");		
								click(Remove, "Remove");
								}
		         }
				wait.until(ExpectedConditions.visibilityOf(Yes2));
				click(Yes2, "Yes");
				Thread.sleep(WaitTime.low);
				if (DeleteMessageB2B.size() != 0) {
					highlighter(DeleteMessageB2B.get(0));
					String message = fetchTextFromApplication(DeleteMessageB2B.get(0), "Delete Message");
					database.updateQueryForData(conn, "GSTN_DeleteSelectedDocument", "Message", testCaseName, stepGroup, message);
					Reporter.log("<B> " + message + "</B>");
				}
		}
	public void deleteSelectedDocument3E3F(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {	
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedDocument", testCaseName,stepGroup);
	wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
	selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");

		if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {			
			String RecipientGSTINUIN = dataRow.getProperty("TotalTaxableValue").split("\\.")[0];
		    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
		  }

		String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TotalTaxableValue").replaceAll(",", "")));
		String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TotalIntegratedTax")));
		deleteDocumentsFromItemGrid(driver, testCaseName, GSTNID, workbook, conn, stepGroup, TotalTaxableValue, TotalIntegratedtax, DocumentTable3I, colElementDocB2B, colElementDoc3E3F);
		wait.until(ExpectedConditions.visibilityOf(Yes2));
		click(Yes2, "Yes");
		verifyAlertMessage(updateMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_DeleteSelectedDocument");
		}
	public void deleteSelectedDocument3G(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {	
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedDocument", testCaseName,stepGroup);
		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");

			if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {			
				String RecipientGSTINUIN = dataRow.getProperty("TotalTaxableValue").split("\\.")[0];
			    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
			  }

			String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TotalTaxableValue").replaceAll(",", "")));
			String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TotalIntegratedTax")));
			deleteDocumentsFromItemGrid(driver, testCaseName, GSTNID, workbook, conn, stepGroup, TotalTaxableValue, TotalIntegratedtax, DocumentTable3E3F,rowElementDocB2B,colElementDocB2B);
			wait.until(ExpectedConditions.visibilityOf(Yes2));
			click(Yes2, "Yes");
			verifyAlertMessage(DeleteMessageB2B, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_DeleteSelectedDocument");
			}
	public void deleteSelectedDocument3H(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {	
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedDocument", testCaseName,stepGroup);
		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");

			if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {			
				String RecipientGSTINUIN = dataRow.getProperty("TotalTaxableValue").split("\\.")[0];
			    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
			  }

			String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TotalTaxableValue").replaceAll(",", "")));
			String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TotalIntegratedTax")));
			deleteDocumentsFromItemGrid(driver, testCaseName, GSTNID, workbook, conn, stepGroup, TotalTaxableValue, TotalIntegratedtax, DocumentTable3H,taxableValue3H,integratedTax3H);
			wait.until(ExpectedConditions.visibilityOf(Yes2));
			click(Yes2, "Yes");
			verifyAlertMessage(DeleteMessageB2B, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_DeleteSelectedDocument");
			}
	public void deleteSelectedDocument3I(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {	
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedDocument", testCaseName,stepGroup);
		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");

			if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {			
				String RecipientGSTINUIN = dataRow.getProperty("TotalTaxableValue").split("\\.")[0];
			    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
			  }

			String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TotalTaxableValue").replaceAll(",", "")));
			String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TotalIntegratedTax")));
			deleteDocumentsFromItemGrid(driver, testCaseName, GSTNID, workbook, conn, stepGroup, TotalTaxableValue, TotalIntegratedtax, DocumentTable3I,taxableValue3I,integratedTax3I);
			wait.until(ExpectedConditions.visibilityOf(Yes3));
			click(Yes3, "Yes");
			verifyAlertMessage(DeleteMessageB2B, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_DeleteSelectedDocument");
			}
	public void deleteSelectedDocument3J(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {	
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedDocument", testCaseName,stepGroup);
		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");

			if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {			
				String RecipientGSTINUIN = dataRow.getProperty("TotalTaxableValue").split("\\.")[0];
			    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
			  }

			String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TotalTaxableValue").replaceAll(",", "")));
			String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TotalIntegratedTax")));
			deleteDocumentsFromItemGrid(driver, testCaseName, GSTNID, workbook, conn, stepGroup, TotalTaxableValue, TotalIntegratedtax, DocumentTable3I,taxableValue3J,integratedTax3J);
			wait.until(ExpectedConditions.visibilityOf(Yes3));
			click(Yes3, "Yes");
			verifyAlertMessage(DeleteMessageB2B, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_DeleteSelectedDocument");
			}
	public void deleteSelectedDocument3K(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {	
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedDocument", testCaseName,stepGroup);
		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");

			if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {			
				String RecipientGSTINUIN = dataRow.getProperty("TotalTaxableValue").split("\\.")[0];
			    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
			  }

			String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TotalTaxableValue").replaceAll(",", "")));
			String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TotalIntegratedTax")));
			deleteDocumentsFromItemGrid(driver, testCaseName, GSTNID, workbook, conn, stepGroup, TotalTaxableValue, TotalIntegratedtax, DocumentTable3I,rowElementDocB2B,colElementDocB2B);
			wait.until(ExpectedConditions.visibilityOf(Yes3));
			click(Yes3, "Yes");
			verifyAlertMessage(DeleteMessageB2B, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_DeleteSelectedDocument");
			}
	public void deleteSelectedDocument3L(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {	
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedDocument", testCaseName,stepGroup);
		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");

			if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {			
				String RecipientGSTINUIN = dataRow.getProperty("TotalTaxableValue").split("\\.")[0];
			    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
			  }

			String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TotalTaxableValue").replaceAll(",", "")));
			String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TotalIntegratedTax")));
			deleteDocumentsFromItemGrid(driver, testCaseName, GSTNID, workbook, conn, stepGroup, TotalTaxableValue, TotalIntegratedtax, DocumentTable3I,colElementDocB2B,colElementDoc3E3F);
			wait.until(ExpectedConditions.visibilityOf(Yes2));
			click(Yes2, "Yes");
			verifyAlertMessage(DeleteMessageB2B, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_DeleteSelectedDocument");
			}
	public void deleteSelectedDocument4(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
		
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedDocument", testCaseName,stepGroup);
		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");

			if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {			
				String RecipientGSTINUIN = dataRow.getProperty("ValueOfSuppliesMade").split("\\.")[0];
			    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
			  }

			String ValueOfSuppliesMade = getCurrency(Double.parseDouble(dataRow.getProperty("ValueOfSuppliesMade").replaceAll(",", "")));
			String ValueOfSuppliesReturned=String.format("%.2f", Double.parseDouble(dataRow.getProperty("ValueOfSuppliesReturned")));
			deleteDocumentsFromItemGrid(driver, testCaseName, GSTNID, workbook, conn, stepGroup, ValueOfSuppliesMade, ValueOfSuppliesReturned, DocumentTable3I, integratedTax3I, taxableValue3H);
			wait.until(ExpectedConditions.visibilityOf(Yes3));
			click(Yes3, "Yes");
			verifyAlertMessage(DeleteMessageB2B, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_DeleteSelectedDocument");
		}
	public void markForDelete(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
		Recordset markDelete = database.selectQueryForHSNTable(conn, "MarkForDelete", testCaseName, "GroupName","none");
		Select Pagignation=dropDown(pagignationDropDown);
		scrollIntoViewJavascript(pagignationDropDown);
		
		while(markDelete.next()) {
			if (markDelete.getField("TCID").equals(testCaseName) && markDelete.getField("MarkForDelete").equalsIgnoreCase("Yes")) {
				String TotalTaxableValue = markDelete.getField("TotalTaxableValue");
				String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(markDelete.getField("TotalIntegratedTax")));
		switch (markDelete.getField("ReturnType")){
		case "3A- Supplies to unregistered persons/consumers (B2C)":
			String TotalTaxableV = markDelete.getField("TotalTaxableValue");
			clearAndSenKeys(SearchRecords, getStringPrefix(TotalTaxableV), "Search");
			List<WebElement> editButtons3A=selectFromTables(DocumentTable,rowElementDoc,colElementDoc,TotalTaxableValue,TotalIntegratedtax,MarkForDelete);
			clickOnEditDocument(editButtons3A,pagignationDropDown, Pagignation);
			break;
		case "3B- Supplies to registered persons (B2B)":
			String DocumentNo3B = markDelete.getField("DocumentNo.");
			clearAndSenKeys(SearchRecords, DocumentNo3B, "Search");
			List<WebElement> editButtons3B=selectFromTables(B2BDocumentTable,rowElementDocB2B,colElementDocB2B,TotalTaxableValue,TotalIntegratedtax,MarkForDelete);
			clickOnEditDocument(editButtons3B,pagignationDropDown, Pagignation);
			break;
		case "3C & 3D- Exports with/without payment of tax (EXP)":
			List<WebElement> editButtons3C3D=selectFromTables(DocumentTable3C3D,rowElementDocB2B,colElementDocB2B,TotalTaxableValue,TotalIntegratedtax,MarkForDelete);
			clickOnEditDocument(editButtons3C3D,pagignationDropDown, Pagignation);
			break;
		case "3E & 3F- Supplies to SEZ with/without payment of tax (SEZ)":
			List<WebElement> editButtons3E3F=selectFromTables(DocumentTable3E3F,colElementDocB2B,colElementDoc3E3F,TotalTaxableValue,TotalIntegratedtax,MarkForDelete);
			clickOnEditDocument(editButtons3E3F,pagignationDropDown, Pagignation);
			break;
		case "3G- Deemed exports (DE)":
			List<WebElement> editButtons3G=selectFromTables(DocumentTable3E3F,rowElementDocB2B,colElementDocB2B,TotalTaxableValue,TotalIntegratedtax,MarkForDelete);
			clickOnEditDocument(editButtons3G,pagignationDropDown, Pagignation);
			break;
		case "3H- Inward supplies attracting reverse charge (RCM)":
			List<WebElement> editButtons3H=selectFromTables(DocumentTable3H,taxableValue3H,integratedTax3H,TotalTaxableValue,TotalIntegratedtax,MarkForDelete);
			clickOnEditDocument(editButtons3H,pagignationDropDown, Pagignation);
			break;
		case "3I- Import of services (IMPS)":
			List<WebElement> editButtons3I=selectFromTables(DocumentTable3I,taxableValue3I,integratedTax3I,TotalTaxableValue,TotalIntegratedtax,MarkForDelete);
			clickOnEditDocument(editButtons3I,pagignationDropDown, Pagignation);
			break;
		case "3J- Import of goods (IMPG)":
			List<WebElement> editButtons3J=selectFromTables(DocumentTable3I,taxableValue3J,integratedTax3J,TotalTaxableValue,TotalIntegratedtax,MarkForDelete);
			clickOnEditDocument(editButtons3J,pagignationDropDown, Pagignation);
			break;
		case "3K- Import of goods from SEZ units/developers (IMPG SEZ)":
			List<WebElement> editButtons3K=selectFromTables(DocumentTable3I,rowElementDocB2B,colElementDocB2B,TotalTaxableValue,TotalIntegratedtax,MarkForDelete);
			clickOnEditDocument(editButtons3K,pagignationDropDown, Pagignation);
			break;
		case "3L - Missing documents(provisional credit availed)":
			List<WebElement> editButtons3L=selectFromTables(DocumentTable3I,colElementDocB2B,colElementDoc3E3F,TotalTaxableValue,TotalIntegratedtax,MarkForDelete);
			clickOnEditDocument(editButtons3L,pagignationDropDown, Pagignation);
			break;
		case "4 - Supplies made through e - commerce operators":
			List<WebElement> editButtons4=selectFromTables(DocumentTable3I, integratedTax3I, taxableValue3H,TotalTaxableValue,TotalIntegratedtax,MarkForDelete);
			clickOnEditDocument(editButtons4,pagignationDropDown, Pagignation);
			break;	
		default:
			 break; 
				}
			}
		}
	}
	public boolean isElementPresentCheckUsingJavaScriptExecutor(WebDriver driver,WebElement element) {
        JavascriptExecutor jse=(JavascriptExecutor) driver;
        try {
            Object obj = jse.executeScript("return typeof(arguments[0]) != 'undefined' && arguments[0] != null;",element);
            if (obj.toString().contains("true")) {
                System.out.println("isElementPresentCheckUsingJavaScriptExecutor: SUCCESS");
                return true;
            } else {
                System.out.println("isElementPresentCheckUsingJavaScriptExecutor: FAIL");
            }

        } catch (NoSuchElementException e) {
            System.out.println("isElementPresentCheckUsingJavaScriptExecutor: FAIL");
        }
        return false;
    }
	
	public boolean isElementPresent(WebDriver driver,By by){
        try{
            driver.findElement(by);
            return true;
        }
        catch(NoSuchElementException e){
            return false;
        }
    }
	
	public WebElement selectTableFromDocumentGrid(WebDriver driver, String testCaseName, String GSTNID, XSSFWorkbook workbook,Connection conn, String stepGroup,String TaxableValueText,String IntegratedTaxText,WebElement Table,By totaltaxableValue,By totalIntegratedTax) throws Exception {
		try {
			WebElement editButton = null;
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pagignationDropDown);
			wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
			Select Pagignation = new Select(pagignationDropDown);
			highlighter(pagignationDropDown);
			editButton = selectFromTables(Table, totaltaxableValue, totalIntegratedTax, TaxableValueText, IntegratedTaxText,CheckBox).get(0);
			Outer: if (editButton != null) {
				highlighter(editButton);
			} else {
				for (int i = 0; i < Pagignation.getOptions().size(); i++) {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",pagignationDropDown);
					// System.out.println(Pagignation.getOptions().get(i).getText());
					Pagignation.selectByVisibleText(Pagignation.getOptions().get(i).getText());
					if (selectFromTables(Table, totaltaxableValue, totalIntegratedTax, TaxableValueText, IntegratedTaxText,CheckBox) != null) {
						editButton = selectFromTables(Table, totaltaxableValue, totalIntegratedTax, TaxableValueText,IntegratedTaxText, CheckBox).get(0);
						highlighter(editButton);
						break Outer;
					} else {
						new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.tagName("a")));
						while (NextPage.size() != 0) {
							String title = NextPage.get(0).getAttribute("aria-label");
							if (title.equals("Next page")) {
								NextPage.get(0).click();
								if (selectFromTables(Table, totaltaxableValue, totalIntegratedTax, TaxableValueText,IntegratedTaxText, CheckBox) != null) {
									editButton = selectFromTables(Table, totaltaxableValue, totalIntegratedTax,TaxableValueText, IntegratedTaxText, CheckBox).get(0);
									highlighter(editButton);
									
									break Outer;
								}

							}
						}
					}
				}
			}
			Reporter.log("Delete document with Taxable value (₹) <B> '" + TaxableValueText+ "'</B> and Integrated tax <B> '" + IntegratedTaxText+ "'</B> from document grid");
			return editButton;
		} catch (Exception e) {
			return null;
		}
	}
	/*public void selectTableToDelete(WebDriver driver, String testCaseName, String GSTNID, XSSFWorkbook workbook,Connection conn, String stepGroup,String Currencyformat,String TotalIntegratedTax,WebElement Table) throws Exception {
		
		try {
		WebElement element=null;
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", pagignationDropDown);
		wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
		Select Pagignation=new Select(pagignationDropDown);
		highlighter(pagignationDropDown);
        
       
		//Defect 14906 where table summary doesn't match with Document grid Hence reducing value by 0.1
		Outer:if (driver.findElements(By.xpath("//table[@class='DocTable table table-bordered']/tbody/tr/td[11][text()=' " + Currencyformat + " ']/parent::tr/td[12][text()='" + TotalIntegratedTax + "']/parent::tr/descendant::input")).size() != 0) {
			WebElement checkBox = driver.findElement(By.xpath("//table[@class='DocTable table table-bordered']/tbody/tr/td[11][text()=' " + Currencyformat + " ']/parent::tr/td[12][text()='" + TotalIntegratedTax + "']/parent::tr/descendant::input"));
			highlighter(checkBox);
			selectCheckBox(checkBox, "Checkbox");
        	Reporter.log("Remove dcument where Taxable Value is <B> " + Currencyformat + "</B> and Integrated tax is <B> " + TotalIntegratedTax + "</B>");		
			click(Remove, "Remove");
		}
		else
		{
			for(int i=0;i<Pagignation.getOptions().size();i++) {
	        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", pagignationDropDown);
	        	//System.out.println(Pagignation.getOptions().get(i).getText());
	        	Pagignation.selectByVisibleText(Pagignation.getOptions().get(i).getText());
	        	if (driver.findElements(By.xpath("//table[@class='DocTable table table-bordered']/tbody/tr/td[11][text()=' " + Currencyformat + " ']/parent::tr/td[12][text()='" + TotalIntegratedTax + "']/parent::tr/descendant::input")).size() != 0) {
	        	WebElement checkBox = driver.findElement(By.xpath("//table[@class='DocTable table table-bordered']/tbody/tr/td[11][text()=' " + Currencyformat + " ']/parent::tr/td[12][text()='" + TotalIntegratedTax + "']/parent::tr/descendant::input"));
	        	selectCheckBox(checkBox, "Checkbox");
	        	Reporter.log("Remove document where Taxable Value is <B> " + Currencyformat + "</B> and Integrated tax is <B> " + TotalIntegratedTax + "</B>");		
				click(Remove, "Remove");
				break Outer;}
	        	else {
	        		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.tagName("a")));			        	
	        	//for (int j = 0; j < NextPage.size(); j++) {
	        		while(NextPage.size()!=0) {
	        	    String title = NextPage.get(0).getAttribute("aria-label");
	        	    if (title.equals("Next page")) {
	        	    	NextPage.get(0).click();
	        	        if (driver.findElements(By.xpath("//table[@class='DocTable table table-bordered']/tbody/tr/td[11][text()=' " + Currencyformat + " ']/parent::tr/td[12][text()='" + TotalIntegratedTax + "']/parent::tr/descendant::input")).size() != 0) {
			        		WebElement checkBox = driver.findElement(By.xpath("//table[@class='DocTable table table-bordered']/tbody/tr/td[11][text()=' " + Currencyformat + " ']/parent::tr/td[12][text()='" + TotalIntegratedTax + "']/parent::tr/descendant::input"));
			        	selectCheckBox(checkBox, "Checkbox");
			        	Reporter.log("Remove dcument where Taxable Value is <B> " + Currencyformat + "</B> and Integrated tax is <B> " + TotalIntegratedTax + "</B>");		
						click(Remove, "Remove");
						break Outer;}
	        	        //break;
						}
					}
				}
			}
		}
			}
		catch (Exception e) {
			e.getCause();
		}
	}*/
	
	public void clickOnEditDocument(List<WebElement> element,WebElement selectDropDown,Select select) {
		try {
		Outer:if (element!=null) {
			highlighter(element.get(0));
			click(element.get(0), "EDIT");
		}
		else
		{
			for(int i=0;i<select.getOptions().size();i++) {
				scrollIntoViewJavascript(selectDropDown);
	        	select.selectByVisibleText(select.getOptions().get(i).getText());
	        	if (element!=null) {
	        		highlighter(element.get(0));
					click(element.get(0), "EDIT");
				break Outer;}
	        	else {
	        		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.tagName("a")));			        				        	
	        		while(NextPage.size()!=0) {
	        	    String title = NextPage.get(0).getAttribute("aria-label");
	        	    if (title.equals("Next page")) {
	        	    	NextPage.get(0).click();
	        	        if (element!=null) {
			        		highlighter(element.get(0));
							click(element.get(0), "EDIT");
						break Outer;}

	        	    }
	        	}
	        	}
			}
		}
		
	}catch (Exception e) {
		e.getMessage();
	}
	}
	public void deleteEntriesFromItemGrid(WebDriver driver,By recordExist,Connection conn,String testCaseName, String GSTNID,String ReturnType,List<WebElement> RemoveButton,WebElement ItemGrid,By HSNCode,By TotalTaxableValue,By RemoveButtonIcon ) {
		try {
			if (RemoveButton.size() != 0) {
				Recordset removeItem = database.selectQueryForHSNData(conn, "GSTN_Remove_Item", "TCID", testCaseName,GSTNID);

				while (removeItem.next()) {
					if (removeItem.getField("TCID").equals(testCaseName) && removeItem.getField("GSTIN").equals(GSTNID)&& removeItem.getField("Remove").equalsIgnoreCase("Yes")&& removeItem.getField("ReturnType").equalsIgnoreCase(ReturnType)) {
						String HSNcode= removeItem.getField("HSNCode");
						Double TotalTaxableValueAmount = Double.parseDouble(removeItem.getField("TaxableValue"));
						String Currencyformat=getCurrency(TotalTaxableValueAmount);
						WebElement removeItems=selectFromItems(ItemGrid, HSNCode,TotalTaxableValue, HSNcode, Currencyformat, RemoveButtonIcon);
						highlighter(removeItems);
						click(removeItems, "Remove");
						if(isElementPresent(driver, recordExist)) {
							Reporter.log("<B>Alert :- '"+driver.findElement(recordExist).getText() +"'</B>");
						}
						Reporter.log("Remove item with HSNCode (₹) <B> '" + HSNcode + "'</B> and Taxable Value <B> '" + Currencyformat + "'</B> from item grid");
					}
					else{
						throw new CustomException("No Records are selected to delete");
					}
					
				}
		
			}
		}catch (Exception e) {
			e.getMessage();
		}	
	}
	public void deleteDocumentsFromItemGrid(WebDriver driver,String testCaseName, String GSTNID, XSSFWorkbook workbook,Connection conn, String stepGroup,String TaxableValueText,String IntegratedTaxText,WebElement Table,By totaltaxableValue,By totalIntegratedTax ) {
		try {
			Recordset tableData = database.selectQueryForHSNData(conn, "GSTN_DeleteSelectedDocument", "TCID", testCaseName,GSTNID);
			while (tableData.next()) {
				if (tableData.getField("TCID").equals(testCaseName) && tableData.getField("GSTIN").equals(GSTNID)) {
					WebElement checkBox= selectTableFromDocumentGrid(driver, testCaseName, GSTNID, workbook, conn, stepGroup, TaxableValueText, IntegratedTaxText, Table,totaltaxableValue,totalIntegratedTax);
						        	selectCheckBox(checkBox, "Checkbox");
						        	Reporter.log("Remove Document where Taxable Value is <B> " + TaxableValueText + "</B> and Integrated tax is <B> " + IntegratedTaxText + "</B>");		
									click(Remove, "Remove");
				  }
					else{
						throw new CustomException("No Documents are selected to delete");
					}
					
				}
		
			
		}catch (Exception e) {
			e.getMessage();
		}	
	}
	
	

}
