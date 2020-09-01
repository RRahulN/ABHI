package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Recordset;

//import javafx.scene.control.Tab;
import util.CustomAssert;
import util.ExcelDatabase;
import util.ExcelRead;
import util.Filters;
import util.GenericMethods;
import util.WaitTime;

public class GSTNAddReturns extends GenericMethods {
	
	@FindBy(xpath="//select[@id='selTable']")
	private WebElement SelectReturnType;
	
	@FindAll({
		@FindBy(xpath="(//div[@class='col-sm-12 pull-left head ng-star-inserted'])[2]/input[1]"),
		@FindBy(xpath="//div[@class='col-sm-12 pull-left head ng-star-inserted']/following::input[1]"),
		@FindBy(xpath="//div[@class='col-sm-12 pull-left head']/input[1]")
	})
	private WebElement DifferentialPercentOfTaxRateCheckBox;
	
	@FindAll({
	@FindBy(xpath="(//div[@class='col-sm-12 pull-left head ng-star-inserted'])[2]/input[2]"),
	@FindBy(xpath="//label[text()='Supply covered under sec 7 of IGST Act']/..//input[2]"),
	@FindBy(xpath="//div[@class='col-sm-12 pull-left head']/input[2]")
	})
	private WebElement Supplycoveredundersec7ofIGSTAct;
	
	@FindBy(xpath="//select[@name='sup']")
	private WebElement State;
	
	@FindBy(xpath="//button[@title='Click Here to add Item Details']")
	private WebElement AddItem;
	
	@FindBy(xpath="//table[@class='table table-bordered']")
	private WebElement ItemDetailsTable;
	
	@FindBy(xpath="//input[@id='irate']")
	private List<WebElement> TaxableValue;
	
	@FindBy(xpath="//input[@title='Please enter State/UT Tax Value']")
	private List<WebElement> StateTax;
	
	@FindBy(xpath="//input[@title='Please enter Central Tax Value']")
	private List<WebElement> CentralTaxB2C;
	
	@FindBy(xpath="//select[@id='irate']")
	private List<WebElement> TaxRate;
	
	@FindBy(xpath="//button[@title='Add another item']")
	private WebElement AddIteminTable;
	
	@FindBy(xpath="//button[text()='SAVE DOCUMENT']")
	private WebElement SaveDocument;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible fade show ng-star-inserted']")
	private List<WebElement> FailedMessage;
	
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible fade show ng-star-inserted']")
	private List<WebElement> SuccessMessage;
	
	//B2B 
	@FindBy(xpath="//input[@placeholder='Enter GSTIN/UIN']")
	private WebElement RecipientGSTINUIN;
	
	@FindBy(xpath="//select[@id='docType']")
	private WebElement Documenttype;
	
	@FindBy(xpath="//input[@title=' Please enter Document number']")
	private WebElement DocumentNumber;
	
	@FindBy(xpath="//input[@placeholder='DD/MM/YYYY']")
	private WebElement DocumentDate;
	
	@FindAll({
	@FindBy(xpath="//input[@title='Please enter Document Value']"),
	@FindBy(xpath="//input[@title='Please enter Document value']")
	})
	private WebElement DocumentValue;
	
	@FindBy(xpath="//select[@title='Please select the POS from the drop down']")
	private WebElement PlaceOfSupply;
	
	@FindBy(xpath="//select[@id='1']")
	private List<WebElement> DiffPercentageDropdown;
	
	@FindBy(xpath="//select[@id='2']")
	private List<WebElement> SupplyCoverUnderGSTDropDown;
	
	@FindBy(xpath="//input[@placeholder='Enter HSN Code/Description']")
	private List<WebElement> HSNcode;
	
	@FindBy(xpath="//input[@title='Enter taxable value']")
	private List<WebElement> TaxableValueB2B;
	
	@FindBy(xpath="//input[@title='Please enter Integrated Tax Value']")
	private List<WebElement> IntegratedTax;
	
	@FindBy(xpath="//input[@title='Please enter Cess Value, if any']")
	private List<WebElement> CESS;
	
	@FindBy(xpath="//select[@title='Select the applicable rate of tax from drop down']")
	private List<WebElement> TaxRateB2B;
	
	@FindAll({
	@FindBy(xpath="//button[@title='Add another Item']"),
	@FindBy(xpath="//button[@title='Add Item']"),
	@FindBy(xpath="//button[@title='Add another item']")
	})
	private WebElement AddIteminTableB2B;
	
	@FindBy(xpath="//button[text()='SAVE DOCUMENT']")
	private WebElement SaveDocumentB2B;
	
	@FindBy(xpath="//span[@class='text-danger text-left ng-star-inserted']")
	private List<WebElement> ErrorMessage;
	
	@FindBy(xpath="//button[contains(text(),'TABLE SUMMARY')]")
	private WebElement TableSummary;
	
	@FindBy(xpath="(//table[@class='table table-bordered '])[1]/thead")
	private WebElement Summarytable;
	
	@FindBy(xpath="//i[@class='fa  fa-info-circle']/..")
	private List<WebElement> ErrorMessage1;
	
	@FindBy(xpath="//input[@name='recipient wise summary']")
	private WebElement RecipientWiseSummary;
	
	@FindBy(xpath="//input[@value='Document wise summary']")
	private WebElement DocumentWiseSummary;
	
	@FindBy(xpath="//span[@class='dropdown-btn']")
	private WebElement FilterDropDown;
	
	@FindBy(xpath="//li//input[@aria-label='multiselect-select-all']/..//div")
	private WebElement SelectAllCheckboxes;
	
	@FindBy(xpath="//div[@class='dropdown-list']/ul/li/div")
	private List<WebElement> FilterItems;
	
	@FindAll({
		@FindBy(xpath="//table[@class='DocTable table table-bordered']/thead"),
		@FindBy(xpath="//table[@class='ExpDocTable table table-bordered ']/thead")
		})
	private WebElement TableResponsive;
	
	@FindBy(xpath="//table[@class='DocTable table table-bordered']/thead")
	private WebElement Table;
	
	//@FindBy(xpath="(//th[contains(text(),'Document Type ')])[3]/button[2]")
	@FindAll({
		@FindBy(xpath="(//th[contains(text(),'Document Type ')])[3]/button[2]"),
		@FindBy(xpath="(//th[contains(text(),'Document Type')])[3]/button[2]")
		})
	private WebElement DocumentTypeFilter;
	
	//@FindBy(xpath="(//th[contains(text(),'Document Type ')])[3]/button[2]/following-sibling::select")
	@FindAll({
		@FindBy(xpath="(//th[contains(text(),'Document Type ')])[3]/button[2]/following-sibling::select"),
		@FindBy(xpath="(//th[contains(text(),'Document Type')])[3]/button[2]/following-sibling::select")
		})
	private WebElement DocumentTypeDropDown;
	
	@FindBy(xpath="(//th[contains(text(),'Export Type')])/button[2]")
	private WebElement ExportTypeButton;
	
	@FindBy(xpath="(//th[contains(text(),'Export Type')])/button[2]/following-sibling::select")
	private WebElement ExportTypeDropDown;
	
	@FindBy(xpath="//th[contains(text(),'Differential % of')]/button[2]")
	private WebElement DifferentialPercentage;
	
	@FindBy(xpath="//th[contains(text(),'Differential % of')]/button[2]/following-sibling::select")
	private WebElement DifferentialPercentageDropDown;
	
	@FindBy(xpath="//th[contains(text(),'Supply covered under')]/button[2]")
	private WebElement SupplyCoveredUnder;
	
	@FindBy(xpath="//th[contains(text(),'Supply covered under')]/button[2]/following-sibling::select")
	private WebElement SupplyCoveredUnderDropDown;
	
	@FindBy(xpath="//th[contains(text(),'Supply type')]/button[2]")
	private List<WebElement> SupplyType;
	
	@FindBy(xpath="//th[contains(text(),'Supply type ')]/button[2]/following-sibling::select")
	private WebElement SupplyTypeDropDown;
	
	@FindBy(xpath="//th[contains(text(),'Status')]/button[2]")
	private WebElement Status;
	
	@FindBy(xpath="//th[contains(text(),'Status')]/button[2]/following-sibling::select")
	private WebElement StatusDropDown;
	
	//3C3D
	@FindBy(xpath="//select[contains(@title,'Please select Document')]")
	private WebElement DocumentType;
	
	@FindBy(xpath="(//input[@placeholder='DD/MM/YYYY'])[1]")
	private WebElement DocumentDate3C3D;
	
	@FindBy(xpath="//input[@title='Please enter Document value']")
	private WebElement DocumentValue3C3D;
	
	@FindBy(xpath="//select[@title='Please select export type ']")
	private WebElement ExportType;
	
	@FindAll({
	@FindBy(xpath="//input[@title='Please enter Port code,if any']"),
	@FindBy(xpath="//input[@title='Please enter Port code']"),
	})
	private WebElement PortCode;
	
	@FindBy(xpath="//input[@title='Please enter Shipping value,if any']")
	private WebElement ShippingBillNo;
	
	@FindBy(xpath="(//input[@placeholder='DD/MM/YYYY'])[2]")
	private WebElement ShippingBillDate;
	
	//3E3F
	@FindBy(xpath="//input[@title='Please enter recipient GSTIN']")
	private WebElement RecipientGSTIN;
	
	@FindBy(xpath="//select[@id='rate']")
	private WebElement GSTPayment;
	
	@FindBy(xpath="//td[@class='ng-star-inserted']//select")
	private List<WebElement> ClaimRefund;
	
	@FindBy(xpath="//input[@value=' SEZ supplies without payment of tax ']")
	private WebElement SEZWithoutPaymentOfTax;
	
	//3G
	@FindBy(xpath="//select[@class='form-control ng-untouched ng-pristine ng-valid']/option[text()='Yes']/..")
	private WebElement ClaimRefund3G;
	
	@FindBy(xpath="//input[@title='Please enter Document Number']")
	private WebElement DocumentNumber3G;
	
	@FindBy(xpath="//input[@title='Please enter Document value']")
	private WebElement DocumentValue3G;
	
	//3H
	@FindBy(xpath="//input[@title='Enter GSTIN/PAN']")
	private WebElement SupplierGSTINPAN3H;
	
	@FindBy(xpath="//select[@name='sup']")
	private WebElement PlaceOfSupply3H;
	
	//3J
	@FindBy(xpath="//input[@title='Please enter Bill of Entry number']")
	private WebElement BillOfEntryNo;
	
	@FindBy(xpath="//input[@title='Please enter Bill of Entry value']")
	private WebElement BillOfEntryValue;
	
	//3k
	@FindAll({
	@FindBy(xpath="//select[@title='Please select Document type']"),
	@FindBy(xpath="//select[@title='Please select Document Type']")
	})
	private WebElement DocumentType3K;
	
	@FindBy(xpath="//input[@title=' Please enter Bill of Entry number']")
	private WebElement BillOfEntryNo3K;
	
	@FindBy(xpath="//input[@title='Please enter Document value']")
	private WebElement BillOfEntryValue3K;
	
	//3L
	@FindBy(xpath="//input[@title='Please enter supplier GSTIN']")
	private WebElement SupplierGSTN;
	
	@FindBy(xpath="//input[@name='Supplier wise summary']")
	private WebElement SupplierWiseSummary;
	
	//4
	@FindBy(xpath="//input[@title='Please enter GSTIN']")
	private WebElement GSTINOfECommerceOperator;
	
	@FindBy(xpath="//input[@title='Please enter Trade Name,if any']")
	private WebElement TradeLegalName;
	
	@FindBy(xpath="//input[@title='Please enter Supply value made']")
	private WebElement ValueOfSuppliesMade;
	
	@FindBy(xpath="//input[@title='Please enter Supply value returned']")
	private WebElement ValueOfSuppliesReturned;
	
	
	
	By dropDown=By.xpath("//li[@role='option']/div/div");
	By RecordList=By.xpath("//ancestor::thead/following::tbody/tr/td[1]");
	By TotalTaxableValues=By.xpath("//ancestor::thead/following::tbody/tr/td[2]");
	By TotalTaxAmount=By.xpath("//ancestor::thead/following::tbody/tr/td[3]");
	By MarkedForDeleteItems=By.xpath("//ancestor::thead/following::tbody/tr/td[7]");
	By IntegratedTax1=By.xpath("//ancestor::thead/following::tbody/tr/td[3]");
	By CentralTax=By.xpath("//ancestor::thead/following::tbody/tr/td[4]");
	By Statetax=By.xpath("//ancestor::thead/following::tbody/tr/td[5]");
	By CesS=By.xpath("//ancestor::thead/following::tbody/tr/td[6]");
	By GSTINuinB2B=By.xpath("//ancestor::thead/following::tbody/tr/td[1]");
	By NumberOfRecordsB2B=By.xpath("//ancestor::thead/following::tbody/tr/td[3]");
	By TotalTaxableValuesB2B=By.xpath("//ancestor::thead/following::tbody/tr/td[4]");
	By TotalTaxAmountB2B=By.xpath("//ancestor::thead/following::tbody/tr/td[5]");
	By CentralTaxB2B=By.xpath("//ancestor::thead/following::tbody/tr/td[6]");
	By StatetaxB2B=By.xpath("//ancestor::thead/following::tbody/tr/td[7]");
	By CesSB2B=By.xpath("//ancestor::thead/following::tbody/tr/td[8]");
	By MarkedForDeleteItemsB2B=By.xpath("//ancestor::thead/following::tbody/tr/td[9]");
	By NumberOfRecords3C3D=By.xpath("//ancestor::thead/following::tbody/tr[4]/td[2]");
	By TotalTaxableValues3C3D=By.xpath("//ancestor::thead/following::tbody/tr[4]/td[3]");
	By IntegratedTax3C3D=By.xpath("//ancestor::thead/following::tbody/tr[4]/td[4]");
	By CesS3C3D=By.xpath("//ancestor::thead/following::tbody/tr[4]/td[5]");
	By MarkedForDeleteItems3C3D=By.xpath("//ancestor::thead/following::tbody/tr[4]/td[6]");
	By NoOfRecord3H=By.xpath("//ancestor::thead/following::tbody/tr/td[1]");
	By TotalTaxableValue3H=By.xpath("//ancestor::thead/following::tbody/tr/td[2]");
	By IntigratedTax3H=By.xpath("//ancestor::thead/following::tbody/tr/td[3]");
	
	int TotalTaxableValueAmount=0;
	double TotalIntegratedTaxAmount=0;
	List<String> columns=new ArrayList<String>();
	
	ExcelDatabase database=new ExcelDatabase();
	WebDriverWait wait;
	
	int TotalTaxableValue=0;
	int TotalTaxabeSum=0;
	double TotalIntegratedTaxSum=0;
	double TotalIntegratedTax=0;
	public GSTNAddReturns(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 30);
	}
	
	
	public void addReturnsB2C(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {
		try {
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_B2C_ReturnPage", testCaseName,stepGroup);
		if(dataRow.getProperty("Differential%OfTaxRateChecBox").equalsIgnoreCase("Yes")) {
			selectCheckBox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
		}
		if (dataRow.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equalsIgnoreCase("Yes")) {
			selectCheckBox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
		}
		if(dataRow.getProperty("Differential%OfTaxRateChecBox").equalsIgnoreCase("Yes") && dataRow.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equalsIgnoreCase("Yes")) {
			selectCheckBox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
			selectCheckBox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
		}
		selectFromDropdownByVisibleText(State,dataRow.getProperty("PlaceOfSupply"), "Place of Supply (Name of State/UT)");
		click(AddItem, "Click Here to add Item Details");
		Thread.sleep(WaitTime.medium);
		Recordset tableData=database.selectQueryForHSNTable(conn, "GSTN_B2C_ReturnPage", testCaseName, "PlaceOfSupply",dataRow.getProperty("PlaceOfSupply"));
		int i=0;
		
		while(tableData.next()) {
			clearAndSenKeys(TaxableValue.get(i), tableData.getField("TaxableValue"), "Taxable Value");
			TotalTaxableValue=Integer.parseInt(tableData.getField("TaxableValue"));
			selectFromDropdownByVisibleText(TaxRate.get(i), tableData.getField("TaxRate"), "Tax Rate");
			if(IntegratedTax.size()!=0) {
				Thread.sleep(WaitTime.low);
				String IntegratedTx=fetchTextFromAngularApplication(IntegratedTax.get(i), "Integrated Tax");
				database.updateQueryForHSNData(conn, "GSTN_B2C_ReturnPage", "IntegratedTax", IntegratedTx, testCaseName, tableData.getField("TaxRate"));
				TotalIntegratedTax=Double.parseDouble(IntegratedTx);
			}
			if(StateTax.size()>0) {
				Thread.sleep(WaitTime.low);
				String StateUTTax=fetchTextFromAngularApplication(StateTax.get(i), "State / UT tax");
				database.updateQueryForData(conn, "GSTN_B2C_ReturnPage", "StateUTTax", testCaseName,stepGroup, StateUTTax);
			}
			if(CentralTaxB2C.size()>0) {
				Thread.sleep(WaitTime.low);
				String CentralTax=fetchTextFromAngularApplication(CentralTaxB2C.get(i), "Cetral Tax");
				database.updateQueryForData(conn, "GSTN_B2C_ReturnPage", "CetralTax", testCaseName,stepGroup, CentralTax);
			}	
			i++;
			TotalTaxabeSum=TotalTaxableValue+TotalTaxabeSum;
			TotalIntegratedTaxSum=TotalIntegratedTax+TotalIntegratedTaxSum;
			click(AddIteminTable, "Add another item");		
		}
		
		//Update calculation sheet of Taxable Value and Integrated sum
		//Double TotalTaxableValue1 = Double.valueOf(TotalTaxabeSum);
		String Total_TaxableValue=getIndianCurrency(String.valueOf(TotalTaxabeSum));
		database.updateCalculatedData(conn, "TableSummary", "TotalTaxableValue", Total_TaxableValue, testCaseName);
		database.updateCalculatedData(conn, "TableSummary", "TotalIntegratedTax", String.valueOf(TotalIntegratedTaxSum), testCaseName);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", SaveDocument);	
		Thread.sleep(WaitTime.low);
		click(SaveDocument, "Save Document");
		Thread.sleep(WaitTime.high);
		
		if(FailedMessage.size()!=0) {
			String text=fetchTextFromApplication(FailedMessage.get(0), "Failed Message");
			Reporter.log("<B> "+text +"</B>");
			CustomAssert.executionFlag = false;
		}
		else if(SuccessMessage.size()!=0){
			String text=fetchTextFromApplication(SuccessMessage.get(0), "Success Message");
			Reporter.log("<B> "+text +"</B>");
		}
		}catch (Exception e) {
			e.getCause();
		}
		
	}
	
public void addReturns3E3F(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {
		
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_3E3F_ReturnPage", testCaseName,stepGroup);
		
		if(dataRow.getProperty("Differential%OfTaxRateChecBox").equalsIgnoreCase("Yes")) {
			selectCheckBox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
		}
		else
		{
			Reporter.log("<B>Proceed without selecting Differential % of tax rate </B>");
		}
		
		clearAndSenKeys(RecipientGSTIN, dataRow.getProperty("RecipientGSTIN"), "Recipient GSTIN/UIN");		
		selectFromDropdownByVisibleText(DocumentType, dataRow.getProperty("DocumentType"), "Document Type");
		clearAndSenKeys(DocumentNumber, dataRow.getProperty("DocumentNo"), "Document No");
		clearAndSenKeys(DocumentDate, dataRow.getProperty("DocumentDate"), "Document Date DD/MM/YYYY");
		clearAndSenKeys(DocumentValue3C3D, dataRow.getProperty("DocumentValue"), "Document Value (₹)");
		selectFromDropdownByVisibleText(PlaceOfSupply, dataRow.getProperty("PlaceOfSupply"), "Place of Supply(Name of State/UT)");
		selectFromDropdownByVisibleText(GSTPayment, dataRow.getProperty("GSTPayment"), "GST Payment");
		Thread.sleep(WaitTime.low);
		if(ClaimRefund.size()>0 && dataRow.getProperty("GSTPayment").equals("SEZ Supplies with Payment of Tax")) {
		
		selectFromDropdownByVisibleText(ClaimRefund.get(0), dataRow.getProperty("ClaimRefund"), "Would You Claim Refund?");
		}
		else {
			Reporter.log("GST Payment is <B>SEZ Supplies without Payment of Tax</B>");
		}
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", AddItem);
		
		Thread.sleep(WaitTime.low);
		click(AddItem, "Click Here to add Item Details");
		
	}
	
	public void addReturnsB2B(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_B2B_ReturnPage", testCaseName,stepGroup);
		//selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("SelectTableToAddDetails"), "Select Return Type Drop Down");
		if(dataRow.getProperty("Differential%OfTaxRateChecBox").equalsIgnoreCase("Yes")) {
			selectCheckBox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
		}
		 if (dataRow.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equalsIgnoreCase("Yes")) {
			selectCheckBox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
		}
	     if(dataRow.getProperty("Differential%OfTaxRateChecBox").equalsIgnoreCase("Yes") && dataRow.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equalsIgnoreCase("Yes")) {
			selectCheckBox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
			selectCheckBox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
		}
		
		clearAndSenKeys(RecipientGSTINUIN, dataRow.getProperty("RecipientGSTIN/UIN"), "Recipient GSTIN/UIN");		
		selectFromDropdownByVisibleText(Documenttype, dataRow.getProperty("DocumentType"), "Document Type");		
		clearAndSenKeys(DocumentNumber, dataRow.getProperty("DocumentNo"), "Document No");
		database.updateCalculatedData(conn, "TableSummary", "DocumentNo", dataRow.getProperty("DocumentNo"), testCaseName);		
		clearAndSenKeys(DocumentDate, dataRow.getProperty("DocumentDate"), "Document Date");
		clearAndSenKeys(DocumentValue, dataRow.getProperty("DocumentValue"), "Document Value");
		selectFromDropdownByVisibleText(PlaceOfSupply, dataRow.getProperty("PlaceOfSupply"), "Place Of Supply");
		if(DiffPercentageDropdown.size()>0) {
			Thread.sleep(WaitTime.low);
			String DiffPercentage=fetchTextFromAngularApplication(DiffPercentageDropdown.get(0), "Differential % of tax rate");
			database.updateQueryForData(conn, "GSTN_B2B_ReturnPage", "DifferentialPercentageOfTaxRateDropDown", testCaseName,stepGroup, DiffPercentage);
		}
		if(SupplyCoverUnderGSTDropDown.size()>0) {
			Thread.sleep(WaitTime.low);
			String SupplyCoverUnderGST=fetchTextFromAngularApplication(SupplyCoverUnderGSTDropDown.get(0), "Supply covered under sec 7 of IGST Act");
			database.updateQueryForData(conn, "GSTN_B2B_ReturnPage", "SupplyCoveredUnderSec7OfIGSTActDropDown", testCaseName,stepGroup, SupplyCoverUnderGST);
		}
		if(AddItem.isEnabled()) {
		click(AddItem, "Add Item");
		}
	}
public void addReturns3G(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {
		
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_3G_ReturnPage", testCaseName,stepGroup);
		//selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("SelectTableToAddDetails"), "Select Return Type Drop Down");
		
		if(dataRow.getProperty("Differential%OfTaxRateChecBox").equalsIgnoreCase("Yes")) {
			selectCheckBox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
		}
		 if (dataRow.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equalsIgnoreCase("Yes")) {
			selectCheckBox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
		}
	     if(dataRow.getProperty("Differential%OfTaxRateChecBox").equalsIgnoreCase("Yes") && dataRow.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equalsIgnoreCase("Yes")) {
			selectCheckBox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
			selectCheckBox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
		}
		
		clearAndSenKeys(RecipientGSTIN, dataRow.getProperty("RecipientGSTIN/UIN"), "Recipient GSTIN/UIN");
		
		selectFromDropdownByVisibleText(DocumentType, dataRow.getProperty("DocumentType"), "Document Type");
		
		clearAndSenKeys(DocumentNumber3G, dataRow.getProperty("DocumentNo"), "Document No");
		
		clearAndSenKeys(DocumentDate, dataRow.getProperty("DocumentDate"), "Document Date");
		
		clearAndSenKeys(DocumentValue3G, dataRow.getProperty("DocumentValue"), "Document Value");
		
		selectFromDropdownByVisibleText(PlaceOfSupply, dataRow.getProperty("PlaceOfSupply"), "Place Of Supply");
		
		selectFromDropdownByVisibleText(ClaimRefund3G, dataRow.getProperty("ClaimRefund"), "Would You Claim Refund?");
		
		if(DiffPercentageDropdown.size()>0) {
			//Thread.sleep(WaitTime.low);
			String DiffPercentage=fetchTextFromAngularApplication(DiffPercentageDropdown.get(0), "Differential % of tax rate");
			database.updateQueryForData(conn, "GSTN_3G_ReturnPage", "DifferentialPercentageOfTaxRateDropDown", testCaseName,stepGroup, DiffPercentage);
		}
		
		if(SupplyCoverUnderGSTDropDown.size()>0) {
			//Thread.sleep(WaitTime.low);
			String SupplyCoverUnderGST=fetchTextFromAngularApplication(SupplyCoverUnderGSTDropDown.get(0), "Supply covered under sec 7 of IGST Act");
			database.updateQueryForData(conn, "GSTN_3G_ReturnPage", "SupplyCoveredUnderSec7OfIGSTActDropDown", testCaseName,stepGroup, SupplyCoverUnderGST);
		}
		
		if(AddItem.isEnabled()) {
		
		click(AddItem, "Add Item");
		
		}
	}
public void addReturns3H(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {
	
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_3H_ReturnPage", testCaseName,stepGroup);
	//selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("SelectTableToAddDetails"), "Select Return Type Drop Down");

	if(dataRow.getProperty("Differential%OfTaxRateChecBox").equalsIgnoreCase("Yes")) {
		selectCheckBox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
	}
	 if (dataRow.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equalsIgnoreCase("Yes")) {
		selectCheckBox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
	}
     if(dataRow.getProperty("Differential%OfTaxRateChecBox").equalsIgnoreCase("Yes") && dataRow.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equalsIgnoreCase("Yes")) {
		selectCheckBox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
		selectCheckBox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
	}
	
	clearAndSenKeys(SupplierGSTINPAN3H, dataRow.getProperty("SupplierGSTIN/PAN"), "Supplier GSTIN/PAN");
	
	selectFromDropdownByVisibleText(PlaceOfSupply3H, dataRow.getProperty("PlaceOfSupply"), "Place of Supply (Name of State/UT)");

	if(DiffPercentageDropdown.size()>0) {
		Thread.sleep(WaitTime.low);
		String DiffPercentage=fetchTextFromAngularApplication(DiffPercentageDropdown.get(0), "Differential % of tax rate");
		database.updateQueryForData(conn, "GSTN_3H_ReturnPage", "DifferentialPercentageOfTaxRateDropDown", testCaseName,stepGroup, DiffPercentage);
	}
	
	if(SupplyCoverUnderGSTDropDown.size()>0) {
		Thread.sleep(WaitTime.low);
		String SupplyCoverUnderGST=fetchTextFromAngularApplication(SupplyCoverUnderGSTDropDown.get(0), "Supply covered under sec 7 of IGST Act");
		database.updateQueryForData(conn, "GSTN_3H_ReturnPage", "SupplyCoveredUnderSec7OfIGSTActDropDown", testCaseName,stepGroup, SupplyCoverUnderGST);
	}
	
	if(AddItem.isEnabled()) {
	
	click(AddItem, "Add Item");
	
	}
}
public void addReturns3I(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {
	
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_3I_ReturnPage", testCaseName,stepGroup);
	
	selectFromDropdownByVisibleText(State,dataRow.getProperty("PlaceOfSupply"), "Place of Supply (Name of State/UT)");
	
	if(AddItem.isEnabled()) {
	
	click(AddItem, "Add Item");
	
	}
}
public void addReturns3J(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {
	
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_3J_ReturnPage", testCaseName,stepGroup);
	//selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("SelectTableToAddDetails"), "Select Return Type Drop Down");

	clearAndSenKeys(PortCode, dataRow.getProperty("PortCode"), "Port Code");
	
	clearAndSenKeys(BillOfEntryNo, dataRow.getProperty("BillOfEntryNo"), "Bill of Entry No.");
	
	
	clearAndSenKeys(DocumentDate, dataRow.getProperty("BillOfEntryDate"), "Bill of Entry Date (DD/MM/YYYY)");
	Thread.sleep(WaitTime.medium);
	
	clearAndSenKeys(BillOfEntryValue, dataRow.getProperty("BillOfEntryValue"), "Bill of Entry Value (₹)");
	
	selectFromDropdownByVisibleText(PlaceOfSupply3H, dataRow.getProperty("PlaceOfSupply"), "Place of Supply (Name of State/UT)");
	
	if(AddItem.isEnabled()) {
	
	click(AddItem, "Add Item");
	
	}
}
public void addReturns3K(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {

	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_3K_ReturnPage", testCaseName,stepGroup);
	//selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("SelectTableToAddDetails"), "Select Return Type Drop Down");
	
	clearAndSenKeys(RecipientGSTIN, dataRow.getProperty("SupplierGSTIN"), "Supplier GSTIN");
	
	selectFromDropdownByVisibleText(DocumentType3K, dataRow.getProperty("DocumentType"), "Document Type");
	
	clearAndSenKeys(PortCode, dataRow.getProperty("PortCode"), "Port Code");
	
	clearAndSenKeys(BillOfEntryNo3K, dataRow.getProperty("BillOfEntryNumber"), "Bill of Entry Number");
	
	clearAndSenKeys(DocumentDate, dataRow.getProperty("BillOfEntryDate"), "Bill of Entry Date (DD/MM/YYYY)");
	
	clearAndSenKeys(BillOfEntryValue3K, dataRow.getProperty("BillOfEntryValue"), "Bill of Entry value (₹)");
	
	selectFromDropdownByVisibleText(PlaceOfSupply, dataRow.getProperty("PlaceOfSupply"), "Place Of Supply");
	
	if(AddItem.isEnabled()) {
	
	click(AddItem, "Add Item");
	
	}
}
public void addReturns3L(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {

	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_3L_ReturnPage", testCaseName,stepGroup);
	//selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("SelectTableToAddDetails"), "Select Return Type Drop Down");
	
	if(dataRow.getProperty("Differential%OfTaxRateChecBox").equalsIgnoreCase("Yes")) {
		selectCheckBox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
	}
	
	clearAndSenKeys(SupplierGSTN, dataRow.getProperty("SupplierGSTIN"), "Supplier GSTIN");
	
	selectFromDropdownByVisibleText(DocumentType3K, dataRow.getProperty("DocumentType"), "Document Type");
	
	clearAndSenKeys(DocumentNumber3G, dataRow.getProperty("DocumentNo"), "Document No");
	
	clearAndSenKeys(DocumentDate, dataRow.getProperty("DocumentDate"), "Document Date");
	
	clearAndSenKeys(DocumentValue3G, dataRow.getProperty("DocumentValue"), "Document Value");
	
	selectFromDropdownByVisibleText(PlaceOfSupply, dataRow.getProperty("PlaceOfSupply"), "Place Of Supply");
	
	//selectFromDropdownByVisibleText(ClaimRefund3G, dataRow.getProperty("ClaimRefund"), "Would You Claim Refund?");
	
	if(AddItem.isEnabled()) {
	
	click(AddItem, "Add Item");
	
	}
}
public void addReturns3C3D(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {
		
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_3C3D_ReturnPage", testCaseName,stepGroup);
	
		selectFromDropdownByVisibleText(DocumentType, dataRow.getProperty("Document Type"), "Document Type");
		
		clearAndSenKeys(DocumentNumber, dataRow.getProperty("Document No"), "Document No");
		
		clearAndSenKeys(DocumentDate3C3D, dataRow.getProperty("Document Date"), "Document Date DD/MM/YYYY");
		
		clearAndSenKeys(DocumentValue3C3D, dataRow.getProperty("Document Value"), "Document Value");
		
		selectFromDropdownByVisibleText(ExportType, dataRow.getProperty("Export Type"), "Export Type");
		
		if(dataRow.getProperty("Port Code")=="") {
			Reporter.log("No Port Code is Entered");
		}
		else {
			clearAndSenKeys(PortCode, dataRow.getProperty("Port Code"), "Port Code");
		}
		if(dataRow.getProperty("Shipping bill No")=="") {
			Reporter.log("No Shipping bill No./Bill of Export No. is Entered");
		}
		else {
			clearAndSenKeys(ShippingBillNo, dataRow.getProperty("Shipping bill No"), "Shipping bill No./Bill of Export No.");
		}
		if(dataRow.getProperty("Shipping bill Date")=="") {
			Reporter.log("No Shipping bill Date/Bill of Export Date DD/MM/YYYY is Entered");
		}
		else {
			clearAndSenKeys(ShippingBillDate, dataRow.getProperty("Shipping bill Date"), "Shipping bill Date/Bill of Export Date DD/MM/YYYY");
		}
		
		if(AddItem.isEnabled()) {
		
		click(AddItem, "Add Item");
		
		}
		
		Thread.sleep(WaitTime.medium);
		
		
	}
public void addReturns4(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {

	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_4_ReturnPage", testCaseName,stepGroup);
	
	clearAndSenKeys(GSTINOfECommerceOperator, dataRow.getProperty("GSTINOfEcommerceOperator"), "GSTIN of e-commerce operator");
	
	clearAndSenKeys(TradeLegalName, dataRow.getProperty("TradeLegalName"), "Trade/Legal Name");
	
	clearAndSenKeys(ValueOfSuppliesMade, dataRow.getProperty("ValueOfSuppliesMade"), "Value of supplies made (₹)");
	
	clearAndSenKeys(ValueOfSuppliesReturned, dataRow.getProperty("ValueOfSuppliesReturned"), "Value of supplies returned (₹)");
	
	if(dataRow.getProperty("IntegratedTax")=="") {
		Reporter.log("No Integrated tax is Required");
	}
	else {
		clearAndSenKeys(IntegratedTax.get(0), dataRow.getProperty("IntegratedTax"), "Integrated tax");
	}
	if(dataRow.getProperty("CentralTax")=="") {
		Reporter.log("No Central tax is Required");
	}
	else {
		clearAndSenKeys(CentralTaxB2C.get(0), dataRow.getProperty("CentralTax"), "Central tax");
	}
	if(dataRow.getProperty("StateUTTax")=="") {
		Reporter.log("No State / UT tax is Required");
	}
	else {
		clearAndSenKeys(StateTax.get(0), dataRow.getProperty("StateUTTax"), "State / UT tax");
		StateTax.get(0).sendKeys(Keys.TAB);
	}
	
	if(AddIteminTableB2B.isEnabled()) {
	
	click(AddIteminTableB2B, "Add Item");
	
	}
	click(SaveDocument, "Save Document");
	Thread.sleep(WaitTime.medium);
	
	
}

public void addReturns3BAO(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {

	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_3BAO_ReturnPage", testCaseName,stepGroup);
	
	clearAndSenKeys(GSTINOfECommerceOperator, dataRow.getProperty("GSTINOfEcommerceOperator"), "GSTIN of e-commerce operator");
	
	clearAndSenKeys(TradeLegalName, dataRow.getProperty("TradeLegalName"), "Trade/Legal Name");
	
	clearAndSenKeys(ValueOfSuppliesMade, dataRow.getProperty("ValueOfSuppliesMade"), "Value of supplies made (₹)");
	
	clearAndSenKeys(ValueOfSuppliesReturned, dataRow.getProperty("ValueOfSuppliesReturned"), "Value of supplies returned (₹)");
	
	if(dataRow.getProperty("IntegratedTax")=="") {
		Reporter.log("No Integrated tax is Required");
	}
	else {
		clearAndSenKeys(IntegratedTax.get(0), dataRow.getProperty("IntegratedTax"), "Integrated tax");
	}
	if(dataRow.getProperty("CentralTax")=="") {
		Reporter.log("No Central tax is Required");
	}
	else {
		clearAndSenKeys(CentralTaxB2C.get(0), dataRow.getProperty("CentralTax"), "Central tax");
	}
	if(dataRow.getProperty("StateUTTax")=="") {
		Reporter.log("No State / UT tax is Required");
	}
	else {
		clearAndSenKeys(StateTax.get(0), dataRow.getProperty("StateUTTax"), "State / UT tax");
		StateTax.get(0).sendKeys(Keys.TAB);
	}
	
	if(AddIteminTableB2B.isEnabled()) {
	
	click(AddIteminTableB2B, "Add Item");
	
	}
	click(SaveDocument, "Save Document");
	Thread.sleep(WaitTime.medium);
	
	
}

	public void addItemDetailsToHSNTable(WebDriver driver,String testCaseName,String GSTNID, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {
		Thread.sleep(WaitTime.medium);
		/*List<TestScenarioDataGenerator> testScenarioDataGenerators=FrameworkServices.getScenarioSuiteGenerator();
		for(TestScenarioDataGenerator addTableData:testScenarioDataGenerators ) {
		
		List<String> GSTNIDs=Arrays.asList(addTableData.getGSTNID());
		for(String gstnids:GSTNIDs) {
			
		if(gstnids.toString().equalsIgnoreCase(GSTNID)) {*/

		Recordset data=database.selectQueryForHSNData(conn, "GSTN_Add_Item", "TCID", testCaseName, GSTNID);
		int i=0;
		while(data.next()) {
		
		if(data.getField("TCID").equalsIgnoreCase(testCaseName) && data.getField("GSTIN").equalsIgnoreCase(GSTNID) && data.getField("Add").equalsIgnoreCase("Yes"))	{
			
		
		if(HSNcode.size()!=0) {
			//Thread.sleep(WaitTime.low);
			//clearAndSenKeys(HSNcode.get(i), data.getField("HSNCode"), "HSN Code");
			clearAndSenKeysAutoComplete(HSNcode.get(i),dropDown, data.getField("HSNCode"), "HSN Code");
		}
		if(TaxableValueB2B.size()!=0) {
			//Thread.sleep(WaitTime.low);
			clearAndSenKeys(TaxableValueB2B.get(i), data.getField("TaxableValue"), "Taxable Value");
			TotalTaxableValueAmount=TotalTaxableValueAmount+Integer.parseInt(data.getField("TaxableValue"));
		}
		if(TaxRateB2B.size()!=0) {
			//Thread.sleep(WaitTime.low);
			selectFromDropdownByVisibleText(TaxRateB2B.get(i), data.getField("TaxRate"), "Tax Rate");
		}
		if(IntegratedTax.size()!=0) {
			//Thread.sleep(WaitTime.low);
			String IntegratedTaxs=fetchTextFromAngularApplication(IntegratedTax.get(i), "Integrated Tax");
			database.updateQueryForHSNData(conn, "GSTN_Add_Item", "IntegratedTax", IntegratedTaxs, testCaseName, data.getField("TaxRate"));
			TotalIntegratedTaxAmount=TotalIntegratedTaxAmount+Double.parseDouble(IntegratedTaxs);
		}
		if(CentralTaxB2C.size()!=0) {
			//Thread.sleep(WaitTime.low);
			String CentralTax=fetchTextFromAngularApplication(CentralTaxB2C.get(i), "Central Tax");
			database.updateQueryForHSNData(conn, "GSTN_Add_Item", "CentralTax", CentralTax, testCaseName, data.getField("TaxRate"));
		}
		if(StateTax.size()!=0) {
			//Thread.sleep(WaitTime.low);
			String StateTaxs=fetchTextFromAngularApplication(StateTax.get(i), "State Tax");
			database.updateQueryForHSNData(conn, "GSTN_Add_Item", "StateTax", StateTaxs, testCaseName, data.getField("TaxRate"));
		}
		if (CESS.size()!=0) {
			//Thread.sleep(WaitTime.low);
			clearAndSenKeys(CESS.get(i),data.getField("Cess") , "CESS");
		}
		
		
		click(AddIteminTableB2B, "Add another item");
		i++;
		
		}
		}
		database.updateCalculatedData(conn, "TableSummary", "TotalTaxableValue", getIndianCurrency(String.valueOf(TotalTaxableValueAmount)), testCaseName);
		database.updateCalculatedData(conn, "TableSummary", "TotalIntegratedTax", String.valueOf(TotalIntegratedTaxAmount), testCaseName);
		Thread.sleep(WaitTime.low);
		click(SaveDocument, "Save Document");
		if(FailedMessage.size()!=0) {
			String text=fetchTextFromApplication(FailedMessage.get(0), "Failed Message");
			Reporter.log("<B> "+text +"</B>");
			CustomAssert.executionFlag = false;
		}
		else if(SuccessMessage.size()!=0){
			String text=fetchTextFromApplication(SuccessMessage.get(0), "Success Message");
			Reporter.log("<B> "+text +"</B>");
		}
		
	}
	
public void addItemDetailsToHSNTable3C3D(WebDriver driver,String testCaseName,String GSTNID, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {

		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_3C3D_ReturnPage", testCaseName,stepGroup);
		Recordset data=database.selectQueryForHSNData(conn, "GSTN_Add_Item", "TCID", testCaseName, GSTNID);
		int i=0;
		while(data.next()) {

		if(data.getField("TCID").equalsIgnoreCase(testCaseName) && data.getField("GSTIN").equalsIgnoreCase(GSTNID) && data.getField("Add").equalsIgnoreCase("Yes"))	{
			clearAndSenKeysAutoComplete(HSNcode.get(i),dropDown, data.getField("HSNCode"), "HSN Code");
			clearAndSenKeys(TaxableValueB2B.get(i), data.getField("TaxableValue"), "Taxable Value");
			TotalTaxableValueAmount=TotalTaxableValueAmount+Integer.parseInt(data.getField("TaxableValue"));
			selectFromDropdownByVisibleText(TaxRateB2B.get(i), data.getField("TaxRate"), "Tax Rate");
		if(IntegratedTax.size()!=0 && dataRow.getProperty("Export Type").equals("Export without payment")) {
			Reporter.log("<B> Integrated tax will be NULL when Export Type</B>");
		}
		else {
			String IntegratedTaxs=fetchTextFromAngularApplication(IntegratedTax.get(i), "Integrated Tax");
			database.updateQueryForHSNData(conn, "GSTN_Add_Item", "IntegratedTax", IntegratedTaxs, testCaseName, data.getField("TaxRate"));
			TotalIntegratedTaxAmount=TotalIntegratedTaxAmount+Double.parseDouble(IntegratedTaxs);
		}
		if (CESS.size()!=0 && dataRow.getProperty("Export Type").equals("Export without payment")) {
			Reporter.log("<B> CESS will be NULL when Export Type is Export without payment</B>");
		}
		else {
			Thread.sleep(WaitTime.low);
			clearAndSenKeys(CESS.get(i),data.getField("Cess") , "CESS");
		}

		click(AddIteminTableB2B, "Add another item");
		i++;
		
		}
		}
		database.updateCalculatedData(conn, "TableSummary", "TotalTaxableValue", getIndianCurrency(String.valueOf(TotalTaxableValueAmount)), testCaseName);
		database.updateCalculatedData(conn, "TableSummary", "TotalIntegratedTax", String.valueOf(TotalIntegratedTaxAmount), testCaseName);
		Thread.sleep(WaitTime.low);
		click(SaveDocument, "Save Document");
		if(FailedMessage.size()!=0) {
			String text=fetchTextFromApplication(FailedMessage.get(0), "Failed Message");
			Reporter.log("<B> "+text +"</B>");
			CustomAssert.executionFlag = false;
		}
		else if(SuccessMessage.size()!=0){
			String text=fetchTextFromApplication(SuccessMessage.get(0), "Success Message");
			Reporter.log("<B> "+text +"</B>");
		}
		
	}

public void addItemDetailsToHSNTable3E3F(WebDriver driver,String testCaseName,String GSTNID, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {

	Thread.sleep(WaitTime.medium);
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_3E3F_ReturnPage", testCaseName,stepGroup);
	Recordset data=database.selectQueryForHSNData(conn, "GSTN_Add_Item", "TCID", testCaseName, GSTNID);
	int i=0;
	while(data.next()) {
	
	if(data.getField("TCID").equalsIgnoreCase(testCaseName) && data.getField("GSTIN").equalsIgnoreCase(GSTNID) && data.getField("Add").equalsIgnoreCase("Yes"))	{
		clearAndSenKeysAutoComplete(HSNcode.get(i),dropDown, data.getField("HSNCode"), "HSN Code");
		clearAndSenKeys(TaxableValueB2B.get(i), data.getField("TaxableValue"), "Taxable Value");
		TotalTaxableValueAmount=TotalTaxableValueAmount+Integer.parseInt(data.getField("TaxableValue"));
		selectFromDropdownByVisibleText(TaxRateB2B.get(i), data.getField("TaxRate"), "Tax Rate");
	    boolean IntegratedTaxFieldisDisabled=(boolean) ((JavascriptExecutor)driver).executeScript("return document.querySelector('[title=\"Please enter Integrated Tax Value\"]').disabled");
	    
	if(IntegratedTax.size()!=0 && dataRow.getProperty("GSTPayment").equals("SEZ Supplies without Payment of Tax") && IntegratedTaxFieldisDisabled) {
		Reporter.log("<B> Integrated tax will be NULL when Export Type is SEZ Supplies without Payment of Tax</B>");
	}
	else {
		Thread.sleep(WaitTime.low);
		String IntegratedTaxs=fetchTextFromAngularApplication(IntegratedTax.get(i), "Integrated Tax");
		database.updateQueryForHSNData(conn, "GSTN_Add_Item", "IntegratedTax", IntegratedTaxs, testCaseName, data.getField("TaxRate"));
		TotalIntegratedTaxAmount=TotalIntegratedTaxAmount+Double.parseDouble(IntegratedTaxs);
	}
	boolean CessFieldisDisabled=(boolean) ((JavascriptExecutor)driver).executeScript("return document.querySelector('[title=\"Please enter Cess Value, if any\"]').disabled");
	if (CESS.size()!=0 && dataRow.getProperty("GSTPayment").equals("SEZ Supplies without Payment of Tax") && CessFieldisDisabled) {
		Reporter.log("<B> CESS will be NULL when Export Type is SEZ Supplies without Payment of Tax</B>");
	}
	else {
		Thread.sleep(WaitTime.low);
		clearAndSenKeys(CESS.get(i),data.getField("Cess") , "CESS");
	}
	
	
	click(AddIteminTableB2B, "Add another item");
	i++;
	
	}
	}
	database.updateCalculatedData(conn, "TableSummary", "TotalTaxableValue", getIndianCurrency(String.valueOf(TotalTaxableValueAmount)), testCaseName);
	database.updateCalculatedData(conn, "TableSummary", "TotalIntegratedTax", String.valueOf(TotalIntegratedTaxAmount), testCaseName);
	click(SaveDocument, "Save Document");
	if(FailedMessage.size()!=0) {
		String text=fetchTextFromApplication(FailedMessage.get(0), "Failed Message");
		Reporter.log("<B> "+text +"</B>");
		CustomAssert.executionFlag = false;
	}
	else if(SuccessMessage.size()!=0){
		String text=fetchTextFromApplication(SuccessMessage.get(0), "Success Message");
		Reporter.log("<B> "+text +"</B>");
	}
	
}

	
	public void verifyErrorMessage(WebDriver driver,String testCaseName,String GSTNID, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {
		
		click(AddItem, "Click Here to add Item Details");
		//boolean message=ErrorMessage.get(0).isDisplayed();
		if(ErrorMessage.size()!=0) {
		errorCapture(ErrorMessage, "Error Message"); 
			String mandatetype=fetchTextFromApplication(ErrorMessage.get(0), "Error Message");
			Reporter.log("<B> "+mandatetype +"</B>");
		}
		else if (ErrorMessage1.size()!=0) {
			String mandatetype=fetchTextFromApplication(ErrorMessage1.get(0), "Error Message");
			Reporter.log("<B> "+mandatetype +"</B>");
		}
		else {
			Reporter.log("<B> "+"Error Message not Dsplayed" +"</B>");
		}
		
	}
	
	public void TableSummary(WebDriver driver,String testCaseName,String GSTNID, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", TableSummary);
		click(TableSummary, "TABLE SUMMARY");
		Thread.sleep(WaitTime.low);
		List<WebElement> rows=Summarytable.findElements(By.tagName("tr"));
		for(WebElement row:rows) {
			List<WebElement> cols=row.findElements(By.tagName("th"));
			for(WebElement col:cols) {
				String getCol=col.getText();
				switch (getCol) {
				case "No. of records":
                    String NoOfRecords=fetchTextFromApplication(col.findElement(RecordList), "No. of records");
                    database.updateCalculatedData(conn, "TableSummary", "NoOfRecords", NoOfRecords, testCaseName);
                    System.out.println(NoOfRecords);
                      
					break;
				
				case "Total taxable value (₹)":
                    String TaxableVal=fetchTextFromApplication(col.findElement(TotalTaxableValues), "Total taxable value (₹)");
                    //database.updateCalculatedData(conn, "TableSummary", "TotalTaxableValue", TaxableVal, testCaseName);
                    System.out.println(TaxableVal);

					break;
				case "Total tax amount (₹)":
                    String IntegratedTaxss=fetchTextFromApplication(col.findElement(TotalTaxAmount), "Total tax amount (₹)");
                    //database.updateCalculatedData(conn, "TableSummary", "TotalIntegratedTax", IntegratedTaxss, testCaseName);
                    System.out.println(IntegratedTaxss);
					
					break;
				case "No. of records marked for delete":
                    String MarkedForDel=fetchTextFromApplication(col.findElement(MarkedForDeleteItems), "No. of records marked for delete");
                    database.updateCalculatedData(conn, "TableSummary", "NoOfRecordsMarkedForDelete", MarkedForDel, testCaseName);
                    System.out.println(MarkedForDel);

					break;
		
				case "Central tax":
                    String Centraltax=fetchTextFromApplication(col.findElement(CentralTax), "Central tax");
                    database.updateCalculatedData(conn, "TableSummary", "Centraltax", Centraltax, testCaseName);
                    System.out.println(Centraltax);

					break;
				case "State/UT tax":
                    String StateTax=fetchTextFromApplication(col.findElement(Statetax), "State/UT tax");
                    database.updateCalculatedData(conn, "TableSummary", "StateUttax", StateTax, testCaseName);
                    System.out.println(StateTax);

					break;
				case "Cess":
                    String Cess=fetchTextFromApplication(col.findElement(CesS), "Cess");
                    database.updateCalculatedData(conn, "TableSummary", "Cess", Cess, testCaseName);
                    System.out.println(Cess);

					break;

				default:
					break;
				}
				
			}
			
		}	
		
	}
	public void TableSummaryB2B(WebDriver driver,String testCaseName,String GSTNID, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", TableSummary);
		click(TableSummary, "TABLE SUMMARY");
		Thread.sleep(WaitTime.low);
		click(RecipientWiseSummary, "Recipient Wise Summary");
		Thread.sleep(WaitTime.low);
		List<WebElement> rows=Summarytable.findElements(By.tagName("tr"));
		for(WebElement row:rows) {
			List<WebElement> cols=row.findElements(By.tagName("th"));
			for(WebElement col:cols) {
				String getCol=col.getText();
				switch (getCol) {
				case "GSTIN/UIN":
                    String GstnUn=fetchTextFromApplication(col.findElement(GSTINuinB2B), "GSTIN/UIN");
                    database.updateCalculatedData(conn, "TableSummary", "GSTINUIN", GstnUn, testCaseName);
                    System.out.println(GstnUn);
                      
					break;
				case "Number of records":
                    String NumerOfRecords=fetchTextFromApplication(col.findElement(NumberOfRecordsB2B), "No. of records");
                    database.updateCalculatedData(conn, "TableSummary", "NumberOfRecords", NumerOfRecords, testCaseName);
                    System.out.println(NumerOfRecords);
                      
					break;
				case "Total Taxable Value(₹)":
                    String TaxableVal=fetchTextFromApplication(col.findElement(TotalTaxableValuesB2B), "Total taxable value (₹)");
                    //database.updateCalculatedData(conn, "TableSummary", "TotalTaxableValue", TaxableVal, testCaseName);
                    System.out.println(TaxableVal);

					break;
				case "Total Tax Amount(₹)":
                    String IntegratedTaxss=fetchTextFromApplication(col.findElement(TotalTaxAmountB2B), "Total tax amount (₹)");
                    //database.updateCalculatedData(conn, "TableSummary", "TotalIntegratedTax", IntegratedTaxss, testCaseName);
                    System.out.println(IntegratedTaxss);
					
					break;
				case "No. of records marked for delete":
                    String MarkedForDel=fetchTextFromApplication(col.findElement(MarkedForDeleteItemsB2B), "No. of records marked for delete");
                    database.updateCalculatedData(conn, "TableSummary", "NoOfRecordsMarkedForDelete", MarkedForDel, testCaseName);
                    System.out.println(MarkedForDel);

					break;
		
				case "Central Tax":
                    String Centraltax=fetchTextFromApplication(col.findElement(CentralTaxB2B), "Central tax");
                    database.updateCalculatedData(conn, "TableSummary", "Centraltax", Centraltax, testCaseName);
                    System.out.println(Centraltax);

					break;
				case "State/UT Tax":
                    String StateTax=fetchTextFromApplication(col.findElement(StatetaxB2B), "State/UT tax");
                    database.updateCalculatedData(conn, "TableSummary", "StateUttax", StateTax, testCaseName);
                    System.out.println(StateTax);

					break;
				case "Cess":
                    String Cess=fetchTextFromApplication(col.findElement(CesSB2B), "Cess");
                    database.updateCalculatedData(conn, "TableSummary", "Cess", Cess, testCaseName);
                    System.out.println(Cess);

					break;

				default:
					break;
				}
				
			}
			
		}	
		
	}
	
	public void TableSummary3L(WebDriver driver,String testCaseName,String GSTNID, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {
	
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", TableSummary);
		click(TableSummary, "TABLE SUMMARY");
		Thread.sleep(WaitTime.low);
		click(SupplierWiseSummary, "Supplier Wise Summary");
		Thread.sleep(WaitTime.low);
		List<WebElement> rows=Summarytable.findElements(By.tagName("tr"));
		for(WebElement row:rows) {
			List<WebElement> cols=row.findElements(By.tagName("th"));
			for(WebElement col:cols) {
				String getCol=col.getText().trim().toLowerCase();
				switch (getCol) {
				case "supplier gstin":
                    String supplierGSTN=fetchTextFromApplication(col.findElement(GSTINuinB2B), "Supplier GSTIN");
                    //database.updateCalculatedData(conn, "TableSummary", "Supplier GSTIN", supplierGSTN, testCaseName);
                    System.out.println(supplierGSTN);
					break;
				case "trade/legal name":
                    String NumerOfRecords=fetchTextFromApplication(col.findElement(NumberOfRecordsB2B), "Trade/Legal name");
                   // database.updateCalculatedData(conn, "TableSummary", "Trade/Legal name", NumerOfRecords, testCaseName);
                    System.out.println(NumerOfRecords);
					break;
				case "number of records":
                    String numberOfREcord=fetchTextFromApplication(col.findElement(TotalTaxableValuesB2B), "Number of records");
                   // database.updateCalculatedData(conn, "TableSummary", "Number of records", numberOfREcord, testCaseName);
                    System.out.println(numberOfREcord);
					break;
				case "taxable value (₹)":
                    String taxableValue=fetchTextFromApplication(col.findElement(TotalTaxAmountB2B), "Taxable value");
                   // database.updateCalculatedData(conn, "TableSummary", "Taxable value", taxableValue, testCaseName);
                    System.out.println(taxableValue);
					break;
				case "no. of records marked for delete":
                    String noOfREcordMarkedForDelete=fetchTextFromApplication(col.findElement(CentralTaxB2B), "No. of records marked for delete");
                   // database.updateCalculatedData(conn, "TableSummary", "No. of records marked for delete", noOfREcordMarkedForDelete, testCaseName);
                    System.out.println(noOfREcordMarkedForDelete);
					break;
				case "integrated tax":
                    String integratedTax=fetchTextFromApplication(col.findElement(StatetaxB2B), "Integrated tax");
                   // database.updateCalculatedData(conn, "TableSummary", "Integrated tax", integratedTax, testCaseName);
                    System.out.println(integratedTax);
					break;
				case "central tax":
                    String centralTax=fetchTextFromApplication(col.findElement(CesSB2B), "Central tax");
                   // database.updateCalculatedData(conn, "TableSummary", "Central tax", centralTax, testCaseName);
                    System.out.println(centralTax);
					break;
				case "state/ut tax":
                    String stateTax=fetchTextFromApplication(col.findElement(CesSB2B), "State/UT tax");
                   // database.updateCalculatedData(conn, "TableSummary", "State/UT tax", stateTax, testCaseName);
                    System.out.println(stateTax);
					break;
				case "cess":
                    String cess=fetchTextFromApplication(col.findElement(CesSB2B), "Cess");
                   // database.updateCalculatedData(conn, "TableSummary", "Cess", cess, testCaseName);
                    System.out.println(cess);
					break;		

				default:
					break;
				}
				
			}
			
		}	
		
	}
	
	public void TableSummary3C3D(WebDriver driver,String testCaseName,String GSTNID, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", TableSummary);
		click(TableSummary, "TABLE SUMMARY");
		Thread.sleep(WaitTime.low);
		click(RecipientWiseSummary, "Recipient Wise Summary");
		Thread.sleep(WaitTime.low);
		List<WebElement> rows=Summarytable.findElements(By.tagName("tr"));
		for(WebElement row:rows) {
			List<WebElement> cols=row.findElements(By.tagName("th"));
			for(WebElement col:cols) {
				String getCol=col.getText().trim();
				System.out.println(getCol);
				switch (getCol) {
				case "No. of records":
                    String GstnUn=fetchTextFromApplication(col.findElement(NumberOfRecords3C3D), "No. of records");
                    database.updateCalculatedData(conn, "TableSummary", "NoOfRecords", GstnUn, testCaseName);
                    System.out.println(GstnUn);
                      
					break;
				case "Total taxable value (₹)":
                    String NumerOfRecords=fetchTextFromApplication(col.findElement(TotalTaxableValues3C3D), "Total taxable value (₹)");
                    //database.updateCalculatedData(conn, "TableSummary", "TotalTaxableValue", NumerOfRecords, testCaseName);
                    System.out.println(NumerOfRecords);
                      
					break;
				case "Integrated tax":
                    String TaxableVal=fetchTextFromApplication(col.findElement(IntegratedTax3C3D), "Integrated tax");
                   // database.updateCalculatedData(conn, "TableSummary", "TotalIntegratedTax", TaxableVal, testCaseName);
                    System.out.println(TaxableVal);

					break;
				case "Cess":
                    String IntegratedTaxss=fetchTextFromApplication(col.findElement(CesS3C3D), "Cess");
                    database.updateCalculatedData(conn, "TableSummary", "Cess", IntegratedTaxss, testCaseName);
                    System.out.println(IntegratedTaxss);
					
					break;
				case "No. of records marked for delete":
                    String MarkedForDel=fetchTextFromApplication(col.findElement(MarkedForDeleteItems3C3D), "No. of records marked for delete");
                    database.updateCalculatedData(conn, "TableSummary", "NoOfRecordsMarkedForDelete", MarkedForDel, testCaseName);
                    System.out.println(MarkedForDel);

					break;
				default:
					break;
				}
				
			}
			
		}	
		
	}
	
	public void TableSummary3E3F(WebDriver driver,String testCaseName,String GSTNID, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {
		try
		{
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_3E3F_ReturnPage", testCaseName,stepGroup);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", TableSummary);
		click(TableSummary, "TABLE SUMMARY");
		Thread.sleep(WaitTime.low);
		if(dataRow.getProperty("GSTPayment").equals("SEZ Supplies without Payment of Tax")) {
		click(SEZWithoutPaymentOfTax, "SEZ supplies without payment of tax");
		}
		Thread.sleep(WaitTime.low);
	
		List<WebElement> rows=Summarytable.findElements(By.tagName("tr"));
		for(WebElement row:rows) {
			List<WebElement> cols=row.findElements(By.tagName("th"));
			for(WebElement col:cols) {
				String getCol=col.getText().trim().toLowerCase();
				System.out.println(getCol);
				switch (getCol) {
		
				case "no. of records":
                    String GstnUn=fetchTextFromApplication(col.findElement(NumberOfRecords3C3D), "No. of records");
                    database.updateCalculatedData(conn, "TableSummary", "NoOfRecords", GstnUn, testCaseName);
                    System.out.println(GstnUn);                  
					break;
				case "total taxable value (₹)":
                    String NumerOfRecords=fetchTextFromApplication(col.findElement(TotalTaxableValues3C3D), "Total taxable value (₹)");
                    //database.updateCalculatedData(conn, "TableSummary", "TotalTaxableValue", NumerOfRecords, testCaseName);
                    System.out.println(NumerOfRecords);                     
					break;
				case "integrated tax":
                    String TaxableVal=fetchTextFromApplication(col.findElement(IntegratedTax3C3D), "Integrated tax");
                    //database.updateCalculatedData(conn, "TableSummary", "TotalIntegratedTax", TaxableVal, testCaseName);
                    System.out.println(TaxableVal);
					break;
				case "cess":
                    String IntegratedTaxss=fetchTextFromApplication(col.findElement(CesS3C3D), "Cess");
                    database.updateCalculatedData(conn, "TableSummary", "Cess", IntegratedTaxss, testCaseName);
                    System.out.println(IntegratedTaxss);
					
					break;
				case "no. of records marked for delete":
                    String MarkedForDel=fetchTextFromApplication(col.findElement(MarkedForDeleteItems3C3D), "No. of records marked for delete");
                    database.updateCalculatedData(conn, "TableSummary", "NoOfRecordsMarkedForDelete", MarkedForDel, testCaseName);
                    System.out.println(MarkedForDel);

					break;
				default:
					break;
				}
				
			}
		}
		}catch (NullPointerException e) {
			System.out.println(e.toString());
		}
			
		
	}
	
	public void TableSummary3G(WebDriver driver,String testCaseName,String GSTNID, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", TableSummary);
		click(TableSummary, "TABLE SUMMARY");
		Thread.sleep(WaitTime.low);
	
		List<WebElement> rows=Summarytable.findElements(By.tagName("tr"));
		for(WebElement row:rows) {
			List<WebElement> cols=row.findElements(By.tagName("th"));
			for(WebElement col:cols) {
				String getCol=col.getText().trim().toLowerCase();
				System.out.println(getCol);
				switch (getCol) {
		
				case "no. of records":
                    String GstnUn=fetchTextFromApplication(col.findElement(NumberOfRecords3C3D), "No. of records");
                    database.updateCalculatedData(conn, "TableSummary", "NoOfRecords", GstnUn, testCaseName);
                    System.out.println(GstnUn);                  
					break;
				case "total taxable value (₹)":
                    String NumerOfRecords=fetchTextFromApplication(col.findElement(TotalTaxableValues3C3D), "Total taxable value (₹)");
                    //database.updateCalculatedData(conn, "TableSummary", "TotalTaxableValue", NumerOfRecords, testCaseName);
                    System.out.println(NumerOfRecords);                     
					break;
				case "integrated tax":
                    String TaxableVal=fetchTextFromApplication(col.findElement(IntegratedTax3C3D), "Integrated tax");
                    //database.updateCalculatedData(conn, "TableSummary", "TotalIntegratedTax", TaxableVal, testCaseName);
                    System.out.println(TaxableVal);
					break;
				case "cess":
                    String IntegratedTaxss=fetchTextFromApplication(col.findElement(CesS3C3D), "Cess");
                    database.updateCalculatedData(conn, "TableSummary", "Cess", IntegratedTaxss, testCaseName);
                    System.out.println(IntegratedTaxss);
					
					break;
				case "no. of records marked for delete":
                    String MarkedForDel=fetchTextFromApplication(col.findElement(MarkedForDeleteItems3C3D), "No. of records marked for delete");
                    database.updateCalculatedData(conn, "TableSummary", "NoOfRecordsMarkedForDelete", MarkedForDel, testCaseName);
                    System.out.println(MarkedForDel);

					break;
				default:
					break;
				}
				
			}
			
		}	
		
	}
	
public void TableSummary3H(WebDriver driver,String testCaseName,String GSTNID, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", TableSummary);
		click(TableSummary, "TABLE SUMMARY");
		Thread.sleep(WaitTime.low);
	
		List<WebElement> rows=Summarytable.findElements(By.tagName("tr"));
		for(WebElement row:rows) {
			List<WebElement> cols=row.findElements(By.tagName("th"));
			for(WebElement col:cols) {
				String getCol=col.getText().trim().toLowerCase();
				System.out.println(getCol);
				switch (getCol) {
		
				case "no. of records":
                    String GstnUn=fetchTextFromApplication(col.findElement(NoOfRecord3H), "No. of records");
                    database.updateCalculatedData(conn, "TableSummary", "NoOfRecords", GstnUn, testCaseName);
                    System.out.println(GstnUn);                  
					break;
				case "total taxable value (₹)":
                    String NumerOfRecords=fetchTextFromApplication(col.findElement(TotalTaxableValue3H), "Total taxable value (₹)");
                    //database.updateCalculatedData(conn, "TableSummary", "TotalTaxableValue", NumerOfRecords, testCaseName);
                    System.out.println(NumerOfRecords);                     
					break;
				case "integrated tax":
                    String TaxableVal=fetchTextFromApplication(col.findElement(IntigratedTax3H), "Integrated tax");
                    //database.updateCalculatedData(conn, "TableSummary", "TotalIntegratedTax", TaxableVal, testCaseName);
                    System.out.println(TaxableVal);
					break;
				case "cess":
                    String IntegratedTaxss=fetchTextFromApplication(col.findElement(CesS), "Cess");
                    database.updateCalculatedData(conn, "TableSummary", "Cess", IntegratedTaxss, testCaseName);
                    System.out.println(IntegratedTaxss);
					
					break;
				case "central tax":
                    String centralTax=fetchTextFromApplication(col.findElement(CentralTax), "Cess");
                    database.updateCalculatedData(conn, "TableSummary", "Centraltax", centralTax, testCaseName);
                    System.out.println(centralTax);
					
					break;
				case "state/ut tax":
                    String statetax=fetchTextFromApplication(col.findElement(Statetax), "Cess");
                    database.updateCalculatedData(conn, "TableSummary", "StateUttax", statetax, testCaseName);
                    System.out.println(statetax);
					
					break;
				case "no. of records marked for delete":
                    String MarkedForDel=fetchTextFromApplication(col.findElement(MarkedForDeleteItems), "No. of records marked for delete");
                    database.updateCalculatedData(conn, "TableSummary", "NoOfRecordsMarkedForDelete", MarkedForDel, testCaseName);
                    System.out.println(MarkedForDel);

					break;
				default:
					break;
				}
				
			}
			
		}	
		
	}
	
public void TableSummary3I(WebDriver driver,String testCaseName,String GSTNID, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {
	
	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", TableSummary);
	click(TableSummary, "TABLE SUMMARY");
	Thread.sleep(WaitTime.low);

	List<WebElement> rows=Summarytable.findElements(By.tagName("tr"));
	for(WebElement row:rows) {
		List<WebElement> cols=row.findElements(By.tagName("th"));
		for(WebElement col:cols) {
			String getCol=col.getText().trim().toLowerCase();
			System.out.println(getCol);
			switch (getCol) {
	
			case "no. of records":
                String GstnUn=fetchTextFromApplication(col.findElement(NoOfRecord3H), "No. of records");
                database.updateCalculatedData(conn, "TableSummary", "NoOfRecords", GstnUn, testCaseName);
                System.out.println(GstnUn);                  
				break;
			case "total taxable value (₹)":
                String NumerOfRecords=fetchTextFromApplication(col.findElement(TotalTaxableValue3H), "Total taxable value (₹)");
                //database.updateCalculatedData(conn, "TableSummary", "TotalTaxableValue", NumerOfRecords, testCaseName);
                System.out.println(NumerOfRecords);                     
				break;
			case "integrated tax (₹)":
                String TaxableVal=fetchTextFromApplication(col.findElement(IntigratedTax3H), "Integrated tax");
                //database.updateCalculatedData(conn, "TableSummary", "TotalIntegratedTax", TaxableVal, testCaseName);
                System.out.println(TaxableVal);
				break;
			case "cess (₹)":
                String IntegratedTaxss=fetchTextFromApplication(col.findElement(CentralTax), "Cess");
                database.updateCalculatedData(conn, "TableSummary", "Cess", IntegratedTaxss, testCaseName);
                System.out.println(IntegratedTaxss);
				break;
			case "marked for delete":
                String MarkedForDel=fetchTextFromApplication(col.findElement(Statetax), "No. of records marked for delete");
                database.updateCalculatedData(conn, "TableSummary", "NoOfRecordsMarkedForDelete", MarkedForDel, testCaseName);
                System.out.println(MarkedForDel);

				break;
			default:
				break;
			}
			
		}
		
	}	
	
}

public void TableSummary3J(WebDriver driver,String testCaseName,String GSTNID, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {
	
	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", TableSummary);
	click(TableSummary, "TABLE SUMMARY");
	Thread.sleep(WaitTime.low);

	List<WebElement> rows=Summarytable.findElements(By.tagName("tr"));
	for(WebElement row:rows) {
		List<WebElement> cols=row.findElements(By.tagName("th"));
		for(WebElement col:cols) {
			String getCol=col.getText().trim().toLowerCase();
			System.out.println(getCol);
			switch (getCol) {
	
			case "document type	":
                String DocumentType=fetchTextFromApplication(col.findElement(NoOfRecord3H), "No. of records");
                database.updateCalculatedData(conn, "TableSummary", "NoOfRecords", DocumentType, testCaseName);
                System.out.println(DocumentType);                  
				break;
			case "no. of records":
                String NoOfDocument=fetchTextFromApplication(col.findElement(TotalTaxableValue3H), "No. of records");
                database.updateCalculatedData(conn, "TableSummary", "NoOfRecords", NoOfDocument, testCaseName);
                System.out.println(NoOfDocument);                  
				break;
			case "total taxable value (₹)":
                String NumerOfRecords=fetchTextFromApplication(col.findElement(IntigratedTax3H), "Total taxable value (₹)");
                //database.updateCalculatedData(conn, "TableSummary", "TotalTaxableValue", NumerOfRecords, testCaseName);
                System.out.println(NumerOfRecords);                     
				break;
			case "integrated tax":
                String TaxableVal=fetchTextFromApplication(col.findElement(CentralTax), "Integrated tax");
                //database.updateCalculatedData(conn, "TableSummary", "TotalIntegratedTax", TaxableVal, testCaseName);
                System.out.println(TaxableVal);
				break;
			case "cess":
                String IntegratedTaxss=fetchTextFromApplication(col.findElement(Statetax), "Cess");
                database.updateCalculatedData(conn, "TableSummary", "Cess", IntegratedTaxss, testCaseName);
                System.out.println(IntegratedTaxss);
				break;
			case "no. of records marked for delete":
                String MarkedForDel=fetchTextFromApplication(col.findElement(CesS), "No. of records marked for delete");
                database.updateCalculatedData(conn, "TableSummary", "NoOfRecordsMarkedForDelete", MarkedForDel, testCaseName);
                System.out.println(MarkedForDel);

				break;
			default:
				break;
			}
			
		}
		
	}	
	
}
public void TableSummary4(WebDriver driver,String testCaseName,String GSTNID, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {
	
	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", TableSummary);
	click(TableSummary, "TABLE SUMMARY");
	Thread.sleep(WaitTime.low);

	List<WebElement> rows=Summarytable.findElements(By.tagName("tr"));
	for(WebElement row:rows) {
		List<WebElement> cols=row.findElements(By.tagName("th"));
		for(WebElement col:cols) {
			String getCol=col.getText().trim();
			System.out.println(getCol);
			switch (getCol) {
	
			case "No. of e-commerce GSTIN's":
                String EcomerceGSTN=fetchTextFromApplication(col.findElement(NoOfRecord3H), "No. of e-commerce GSTIN's");
                database.updateCalculatedData(conn, "TableSummary", "NoOfRecords", EcomerceGSTN, testCaseName);
                System.out.println(EcomerceGSTN);                  
				break;
			case "Value of supplies Made (₹)":
                String ValueOfSupplyMade=fetchTextFromApplication(col.findElement(TotalTaxableValue3H), "Value of supplies Made (₹)");
                database.updateCalculatedData(conn, "TableSummary", "NoOfRecords", ValueOfSupplyMade, testCaseName);
                System.out.println(ValueOfSupplyMade);                  
				break;
			case "Value of supplies Returned (₹)":
                String ValueOfSupplyReturned=fetchTextFromApplication(col.findElement(IntigratedTax3H), "Value of supplies Returned (₹)");
               // database.updateCalculatedData(conn, "TableSummary", "TotalTaxableValue", ValueOfSupplyReturned, testCaseName);
                System.out.println(ValueOfSupplyReturned);                     
				break;
			case "Net value Of supplies (₹)":
                String NetValueOfSupply=fetchTextFromApplication(col.findElement(CentralTax), "Net value Of supplies (₹)");
                //database.updateCalculatedData(conn, "TableSummary", "TotalIntegratedTax", NetValueOfSupply, testCaseName);
                System.out.println(NetValueOfSupply);
				break;
			case "Integrated tax":
                String IntegratedTax=fetchTextFromApplication(col.findElement(Statetax), "Integrated tax");
                database.updateCalculatedData(conn, "TableSummary", "NoOfRecordsMarkedForDelete", IntegratedTax, testCaseName);
                System.out.println(IntegratedTax);
				break;
			case "Central tax":
                String CentralTaxes=fetchTextFromApplication(col.findElement(CesS), "Central tax");
                database.updateCalculatedData(conn, "TableSummary", "NoOfRecordsMarkedForDelete", CentralTaxes, testCaseName);
                System.out.println(CentralTaxes);
				break;	
			case "State/UT tax":
                String StateTax=fetchTextFromApplication(col.findElement(StatetaxB2B), "State/UT tax");
                database.updateCalculatedData(conn, "TableSummary", "NoOfRecordsMarkedForDelete", StateTax, testCaseName);
                System.out.println(StateTax);
				break;	
			case "Cess":
                String Ces=fetchTextFromApplication(col.findElement(CesSB2B), "Cess");
                database.updateCalculatedData(conn, "TableSummary", "NoOfRecordsMarkedForDelete", Ces, testCaseName);
                System.out.println(Ces);
				break;	
			case "No. of records marked for delete":
                String MarkedForDel=fetchTextFromApplication(col.findElement(MarkedForDeleteItemsB2B), "No. of records marked for delete");
                database.updateCalculatedData(conn, "TableSummary", "NoOfRecordsMarkedForDelete", MarkedForDel, testCaseName);
                System.out.println(MarkedForDel);

				break;	
			default:
				break;
			}
			
		}
		
	}	
	
}
	
	public void documentWiseSummaryB2B(WebDriver driver,String testCaseName,String GSTNID, XSSFWorkbook workbook,Connection conn,String stepGroup) throws InterruptedException, FilloException {
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", TableSummary);
		click(DocumentWiseSummary, "Document Wise Summary");
		Thread.sleep(WaitTime.low);
		/*click(DocumentWiseSummary, "Document Wise Summary");
		Thread.sleep(WaitTime.low);*/
		WebElement Table=driver.findElement(By.xpath("//table[@class='table table-bordered ']//tbody"));
		highlighter(Table);
	
		for(int i=1;i<=4;i++) {
		String DocumentType=Table.findElement(By.xpath("//tr["+i+"]/td[1]")).getText();
		Reporter.log("<B>"+ DocumentType + "</B> fetched from <B> Document Type </B>");
		System.out.println(DocumentType);
		//database.insert(conn,"DocumentWiseSummary","DocumentType", DocumentType, testCaseName,GSTNID);
			
	    }
		for(int i=1;i<=4;i++) {
			String NoOfRecords=Table.findElement(By.xpath("//tr["+i+"]/td[2]")).getText();
			Reporter.log("<B>"+ NoOfRecords + "</B> fetched from <B> No of Records </B>");
			//database.insert(conn,"DocumentWiseSummary","NoOfRecords", NoOfRecords, testCaseName,GSTNID);
			System.out.println(NoOfRecords);	
		    }
		for(int i=1;i<=4;i++) {
			String TotalTaxableValue=Table.findElement(By.xpath("//tr["+i+"]/td[3]")).getText();
			Reporter.log("<B>"+ TotalTaxableValue + "</B> fetched from <B> Total Taxable Value </B>");
			//database.insert(conn,"DocumentWiseSummary","TotalTaxableValue", TotalTaxableValue, testCaseName,GSTNID);
			System.out.println(TotalTaxableValue);	
		    }
		for(int i=1;i<=4;i++) {
			String IntegratedTax=Table.findElement(By.xpath("//tr["+i+"]/td[4]")).getText();
			Reporter.log("<B>"+ IntegratedTax + "</B> fetched from <B> Integrated Tax </B>");
			//database.insert(conn,"DocumentWiseSummary","IntegratedTax", IntegratedTax, testCaseName,GSTNID);
			System.out.println(IntegratedTax);	
		    }
		for(int i=1;i<=4;i++) {
			String CentralTax=Table.findElement(By.xpath("//tr["+i+"]/td[4]")).getText();
			Reporter.log("<B>"+ CentralTax + "</B> fetched from <B> Central Tax </B>");
			//database.insert(conn,"DocumentWiseSummary","CentralTax", CentralTax, testCaseName,GSTNID);
			System.out.println(CentralTax);	
		    }
		for(int i=1;i<=4;i++) {
			String StateTax=Table.findElement(By.xpath("//tr["+i+"]/td[5]")).getText();
			Reporter.log("<B>"+ StateTax + "</B> fetched from <B> State/UT Tax </B>");
			//database.insert(conn,"DocumentWiseSummary","StateUTTax", StateTax, testCaseName,GSTNID);
			System.out.println(StateTax);	
		    }
		for(int i=1;i<=4;i++) {
			String Cess=Table.findElement(By.xpath("//tr["+i+"]/td[6]")).getText();
			Reporter.log("<B>"+ Cess + "</B> fetched from <B> Cess </B>");
			//database.insert(conn,"DocumentWiseSummary","Cess", Cess, testCaseName,GSTNID);
			System.out.println(Cess);	
		    }
		for(int i=1;i<=4;i++) {
			String NoOfrecordsMarkedForDelete=Table.findElement(By.xpath("//tr["+i+"]/td[7]")).getText();
			Reporter.log("<B>"+ NoOfrecordsMarkedForDelete + "</B> fetched from <B> No Of records Marked For Delete </B>");
			//database.insert(conn,"DocumentWiseSummary","NoOfRecordsMarkedForDelete", NoOfrecordsMarkedForDelete, testCaseName,GSTNID);
			System.out.println(NoOfrecordsMarkedForDelete);	
		    }
	}
	
	
/*public void documentWiseSummary3C3D(WebDriver driver,String testCaseName,String GSTNID, XSSFWorkbook workbook,Connection conn,String stepGroup) throws InterruptedException, FilloException {
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", TableSummary);
		click(DocumentWiseSummary, "Document Wise Summary");
		Thread.sleep(WaitTime.low);
		click(DocumentWiseSummary, "Document Wise Summary");
		Thread.sleep(WaitTime.low);
		WebElement Table=driver.findElement(By.xpath("//table[@class='table table-bordered ']//tbody"));
		highlighter(Table);
	
		for(int i=1;i<=4;i++) {
		String DocumentType=Table.findElement(By.xpath("//tr["+i+"]/td[1]")).getText();
		Reporter.log("<B>"+ DocumentType + "</B> fetched from <B> Document Type </B>");
		System.out.println(DocumentType);
		//database.insert(conn,"DocumentWiseSummary","DocumentType", DocumentType, testCaseName,GSTNID);
			
	    }
		for(int i=1;i<=4;i++) {
			String NoOfRecords=Table.findElement(By.xpath("//tr["+i+"]/td[2]")).getText();
			Reporter.log("<B>"+ NoOfRecords + "</B> fetched from <B> No of Records </B>");
			//database.insert(conn,"DocumentWiseSummary","NoOfRecords", NoOfRecords, testCaseName,GSTNID);
			System.out.println(NoOfRecords);	
		    }
		for(int i=1;i<=4;i++) {
			String TotalTaxableValue=Table.findElement(By.xpath("//tr["+i+"]/td[3]")).getText();
			Reporter.log("<B>"+ TotalTaxableValue + "</B> fetched from <B> Total Taxable Value </B>");
			//database.insert(conn,"DocumentWiseSummary","TotalTaxableValue", TotalTaxableValue, testCaseName,GSTNID);
			System.out.println(TotalTaxableValue);	
		    }
		for(int i=1;i<=4;i++) {
			String IntegratedTax=Table.findElement(By.xpath("//tr["+i+"]/td[4]")).getText();
			Reporter.log("<B>"+ IntegratedTax + "</B> fetched from <B> Integrated Tax </B>");
			//database.insert(conn,"DocumentWiseSummary","IntegratedTax", IntegratedTax, testCaseName,GSTNID);
			System.out.println(IntegratedTax);	
		    }
		for(int i=1;i<=4;i++) {
			String CentralTax=Table.findElement(By.xpath("//tr["+i+"]/td[4]")).getText();
			Reporter.log("<B>"+ CentralTax + "</B> fetched from <B> Central Tax </B>");
			//database.insert(conn,"DocumentWiseSummary","CentralTax", CentralTax, testCaseName,GSTNID);
			System.out.println(CentralTax);	
		    }
		for(int i=1;i<=4;i++) {
			String StateTax=Table.findElement(By.xpath("//tr["+i+"]/td[5]")).getText();
			Reporter.log("<B>"+ StateTax + "</B> fetched from <B> State/UT Tax </B>");
			//database.insert(conn,"DocumentWiseSummary","StateUTTax", StateTax, testCaseName,GSTNID);
			System.out.println(StateTax);	
		    }
		for(int i=1;i<=4;i++) {
			String Cess=Table.findElement(By.xpath("//tr["+i+"]/td[6]")).getText();
			Reporter.log("<B>"+ Cess + "</B> fetched from <B> Cess </B>");
			//database.insert(conn,"DocumentWiseSummary","Cess", Cess, testCaseName,GSTNID);
			System.out.println(Cess);	
		    }
		for(int i=1;i<=4;i++) {
			String NoOfrecordsMarkedForDelete=Table.findElement(By.xpath("//tr["+i+"]/td[7]")).getText();
			Reporter.log("<B>"+ NoOfrecordsMarkedForDelete + "</B> fetched from <B> No Of records Marked For Delete </B>");
			//database.insert(conn,"DocumentWiseSummary","NoOfRecordsMarkedForDelete", NoOfrecordsMarkedForDelete, testCaseName,GSTNID);
			System.out.println(NoOfrecordsMarkedForDelete);	
		    }
	}*/
	
	public void applySelectAllFilterB2B(WebDriver driver,String testCaseName,String GSTNID, XSSFWorkbook workbook,Connection conn,String stepGroup) throws Exception {
		click(FilterDropDown, "Select Columns To Display/Hide:");
		Thread.sleep(WaitTime.low);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", FilterDropDown);
		if(isTextPresent(SelectAllCheckboxes, "Select All")) {
			selectCheckBox(SelectAllCheckboxes, "Select All");
		 }
		else {
			Reporter.log("<B> All drop down values are selected </B>");
		}
			List<String> filters =Arrays.asList("Trade/Legal name","Recipient type","Differential % of tax rate","Supply covered under sec 7 of IGST Act","Supply type","Date of upload","Status");
			List<String> filters3C3D =Arrays.asList("Port Code","Shipping bill No./Bill of Export No.","Shipping bill Date/Bill of Export Date","Supply type","Date of upload","Status");
			int count=0;
			if(testCaseName.contains("3C3D")) {
				for (int i = 0; i <= 6; i++) {
					for (String filter : filters3C3D) {
						if (FilterItems.get(i).getText().trim().equals(filter)) {
							Assert.assertEquals(FilterItems.get(i).getText().trim(), filter);
							Reporter.log("<B>" + FilterItems.get(i).getText() + "</B> is present in Filter");
							count++;
					}
				}
			}
		}
			else if(testCaseName.contains("3B")){
			for (int i = 0; i <= 7; i++) {
				for (String filter : filters) {
					if (FilterItems.get(i).getText().trim().equals(filter)) {
						Assert.assertEquals(FilterItems.get(i).getText().trim(), filter);
						Reporter.log("<B>" + FilterItems.get(i).getText() + "</B> is present in Filter");
						count++;
					}
				}
			}
		}
             for(String getColumnName:getTableColumns(TableResponsive)) {
            	if(getColumnName.trim().equalsIgnoreCase("Document Type")) {
            		click(DocumentTypeFilter, "Document Type Filter");
            		highlighter(DocumentTypeFilter);
            		for(String documenttypes:getDropDownValue(DocumentTypeDropDown)){
            			selectFromDropdownByVisibleText(DocumentTypeDropDown, documenttypes, "Document Type");
            			/*
            			 * Commented because of no use will modify it later
            			 */
            			//verifyFilter(DocumentTypeDropDown, By.xpath(".//ancestor::table/tbody/tr"), 4,Filters.getDocumentTypeFilters(),documenttypes);            			
            		}
            		click(DocumentTypeFilter, "Document Type Filter");
            	}else if(getColumnName.trim().equalsIgnoreCase("Document No.")) {
            		System.out.println("Document No");
            	}else if(getColumnName.trim().contains("Document date")) {
            		System.out.println("Document date");
					
				}else if(getColumnName.trim().contains("Export Type")) {
					click(ExportTypeButton, "Export Type Filter");
            		highlighter(ExportTypeButton);
            		for(String documenttypes:getDropDownValue(ExportTypeDropDown)){
            			selectFromDropdownByVisibleText(ExportTypeDropDown, documenttypes, "Export Type");
            		}
				}else if(getColumnName.trim().contains("Place of Supply")) {
					System.out.println("Place of Supply");
				
				}else if(getColumnName.trim().contains("Port Code")) {
					System.out.println("Port Code");	
				}else if(getColumnName.trim().contains("Shipping bill No./")) {
					System.out.println("Shipping bill No./ Bill of Export No. ");	
				}else if(getColumnName.trim().contains("Shipping bill Date/")) {
					System.out.println("Shipping bill Date/ Bill of Export Date / DD/MM/YYYY ");		
				}else if(getColumnName.trim().contains("Differential % of")) {
					System.out.println("Differential % of");
					click(DifferentialPercentage, "Differential % of tax rate");
            		highlighter(DifferentialPercentage);
            		for(String documenttypes:getDropDownValue(DifferentialPercentageDropDown)){
            			selectFromDropdownByVisibleText(DifferentialPercentageDropDown, documenttypes, "Differential % of tax rate");
            			Thread.sleep(WaitTime.low);
            		}
					
				}else if(getColumnName.trim().contains("Supply covered under")) {
					System.out.println("Supply covered under");
					click(SupplyCoveredUnder, "Supply covered under sec 7 of IGST Act");
            		highlighter(SupplyCoveredUnder);
            		for(String documenttypes:getDropDownValue(SupplyCoveredUnderDropDown)){
            			selectFromDropdownByVisibleText(SupplyCoveredUnderDropDown, documenttypes, "Supply covered under sec 7 of IGST Act");
            			Thread.sleep(WaitTime.low);
            			//List<WebElement> 
            		}
					
				}else if(getColumnName.trim().equalsIgnoreCase("Supply type")) {
					System.out.println("Supply type");
					if(SupplyType.size()>0) {
					click(SupplyType.get(0), "Supply type");
            		highlighter(SupplyType.get(0));
            		for(String documenttypes:getDropDownValue(SupplyTypeDropDown)){
              			selectFromDropdownByVisibleText(SupplyTypeDropDown, documenttypes, "Supply Type");
            			Thread.sleep(WaitTime.low);
            		}
					}
					else {
						System.out.println("No supply type Filter is available");
					}
				}else if(getColumnName.trim().equalsIgnoreCase("Date of upload")) {
					System.out.println("Date of upload");
					
				}else if(getColumnName.trim().equalsIgnoreCase("Status")) {
					click(Status, "Status");
            		highlighter(Status);
            		for(String documenttypes:getDropDownValue(StatusDropDown)){
              			selectFromDropdownByVisibleText(StatusDropDown, documenttypes, "Status");
            			Thread.sleep(WaitTime.low);
					}
				}

			}
	}
	
	public boolean isTextPresent(WebElement element,String value) {
		try
		{
		if(element.getText().equalsIgnoreCase(value))
		return true;
		}
		catch (Exception e) {
			return false;
		}
		return false;
	}
	
	public List<String> getTableColumns(WebElement Table){
		try
		{
	    Reporter.log("<B> "+"Document grid Table is selected" +"</B>");
	    highlighter(Table);
		List<WebElement> Rows=Table.findElements(By.tagName("tr"));
			for(WebElement row:Rows) {
				List<WebElement> Cols=row.findElements(By.tagName("th"));
				for(WebElement col:Cols) {
					highlighter(col);
					Reporter.log("<B>"+ col.getText() + "</B>Column is present in Document Grid");
					columns.add(col.getText());
				}
			}
	     return columns;
		}
		catch (Exception e) {
			return null;// TODO: handle exception
		}		
	}
	
	@SuppressWarnings("unused")
	public List<String> getDropDownValue(WebElement element){
		try
		{
		List<String> getdropDown=new ArrayList<String>();
		Select select=new Select(element);
		highlighter(element);
		for(int i=0;i<select.getOptions().size();i++) {
		String value=select.getOptions().get(i).getText();
		System.out.println(value);
		getdropDown.add(value);	
		}
		return getdropDown;
	}catch (Exception e) {
		return null;//
	}
	}
	
	@SuppressWarnings("unused")
	public void verifyFilter(WebElement element,By rows,int j,String[] getFilters,String filterNotAvailable) throws Exception {
		
		List<WebElement> filterType=element.findElements(rows);
		int size=filterType.size();
		if(filterType!=null) {
			Outer:for(int i=1;i<=filterType.size();i++) {
				//highlighter(filterType.get(i));
				WebElement cols1=element.findElement(By.xpath(".//ancestor::table/tbody/tr["+i+"]//td["+j+"]"));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//th[contains(text(),'Document Type ')])[3]/button[2]/following-sibling::select//ancestor::table/tbody/tr["+i+"]//td["+j+"]")));
					highlighter(cols1);
					//for(int i1=1;i1<=getFilters.length;i1++) {
						System.out.println(cols1.getText());
						if(filterNotAvailable.equals("Select")) {	
						break Outer;}
						/*SoftAssert softAssert=new SoftAssert();
						softAssert.assertEquals(cols1.getText(), getFilters[i1]);*/
						Thread.sleep(1000);
					//}
				}
			}
		else
		{
			Reporter.log("<B>"+ filterNotAvailable + "</B> No records present in table");
		}
		
		
	}
}
