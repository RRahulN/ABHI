package pages;

import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Recordset;

import util.CustomAssert;
import util.ExcelDatabase;
import util.ExcelRead;
import util.WaitTime;
import util.WebTable;

public class GSTNEditReturns extends WebTable {

	@FindBy(xpath = "//select[@id='selTable']")
	private WebElement SelectReturnType;
	
	@FindBy(xpath="//select[@name='sup']")
	private WebElement State;
	
	@FindBy(xpath = "(//input[@class='ng-untouched ng-pristine ng-valid'])[3]")
	private WebElement Search;
	
	@FindBy(xpath = "(//input[@class='ng-untouched ng-pristine ng-valid'])[1]")
	private WebElement Search3C3D;
	
	@FindBy(xpath = "//table[@class='customTable table table-bordered ']/tbody")
	private WebElement DocumentTable;
	
	@FindBy(xpath = "//table[@class='DocTable table table-bordered ']/tbody")
	private WebElement DocumentTable3I;
	
	@FindBy(xpath = "//table[@class='DocTable table table-bordered']/tbody")
	private WebElement B2BDocumentTable;
	
	@FindBy(xpath = "//table[@class='ExpDocTable table table-bordered ']/tbody")
	private WebElement DocumentTable3C3D;
	
	@FindBy(xpath = "//table[@class='DocTable table table-bordered ']/tbody")
	private WebElement DocumentTable3E3F;
	
	@FindBy(xpath="//button[text()='SAVE DOCUMENT']")
	private WebElement SaveDocument;
	
	@FindBy(xpath = "//span[contains(text(),'Document updated successfully')]")
	private List<WebElement> successfulMessage;
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible fade show ng-star-inserted']/span")
	private List<WebElement> FailedMessage;
	
	@FindBy(xpath="//select[@id='irate']")
	private List<WebElement> TaxRate;
	
	@FindBy(xpath="//select[@name='irate']")
	private List<WebElement> TaxRateB2B;
	
	@FindBy(xpath = "//table[@class='table table-bordered']/tbody")
	private WebElement ItemTable;
	
	@FindBy(xpath="//input[@id='irate']")
	private List<WebElement> TaxableValue;
	
	@FindBy(xpath="//input[@title='Enter taxable value']")
	private List<WebElement> TaxableValueB2B;
	
	@FindBy(xpath="//input[@placeholder='Enter HSN Code/Description']")
	private List<WebElement> HSNCode;
	
	@FindBy(xpath="//span[contains(text(),' Records Per Page : ')]/..//select")
	private WebElement pagignationDropDown;
	
	@FindBy(xpath="//li[@class='pagination-next ng-star-inserted']/a")
	private List<WebElement> NextPage;
	
	@FindAll({
		@FindBy(xpath="(//div[@class='col-sm-12 pull-left head ng-star-inserted'])[2]/input[1]"),
		@FindBy(xpath="//div[@class='col-sm-12 pull-left head ng-star-inserted']/following::input[1]")
	})
	private WebElement DifferentialPercentOfTaxRateCheckBox;
	
	@FindAll({
	@FindBy(xpath="(//div[@class='col-sm-12 pull-left head ng-star-inserted'])[2]/input[2]"),
	@FindBy(xpath="//div[@class='col-sm-12 pull-left head ng-star-inserted']//following::input[2]")
	})
	private WebElement Supplycoveredundersec7ofIGSTAct;
	
	@FindBy(xpath="//select[@id='1']")
	private WebElement Differentialoftaxrate;
	
	@FindBy(xpath="//select[@id='2']")
	private WebElement Supplycoveredundersec7ofIGSTAct1;
	
	@FindBy(xpath="//span[contains(text(),'Search : ')]/../div[1]//input")
	private WebElement SearchRecords;
	
	@FindBy(xpath="//input[@placeholder='Enter GSTIN/UIN']")
	private WebElement RecipientGSTINUIN;
	
	@FindBy(xpath="//select[@id='docType']")
	private WebElement Documenttype;
	
	@FindAll({
	@FindBy(xpath="//input[@title=' Please enter Document number']"),
	@FindBy(xpath="//input[@title='Please enter Document Number']"),
	})
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
	
	@FindBy(xpath="//select[@title='Please select export type ']")
	private WebElement ExportType;
	
	@FindAll({
		@FindBy(xpath="//input[@title='Please enter Port code,if any']"),
		@FindBy(xpath="//input[@title='Please enter Port code']"),
		})
		private WebElement PortCode;
	
	@FindBy(xpath="(//input[@placeholder='DD/MM/YYYY'])[2]")
	private WebElement ShippingBillDate;
	
	@FindBy(xpath="//input[@title='Please enter recipient GSTIN']")
	private WebElement RecipientGSTIN;
	
	@FindBy(xpath="//select[@id='rate']")
	private WebElement GSTPayment;
	
	@FindBy(xpath="//td[@class='ng-star-inserted']//select")
	private List<WebElement> ClaimRefund;
	
	@FindBy(xpath="//select[@class='form-control ng-untouched ng-pristine ng-valid']/option[text()='Yes']/..")
	private WebElement ClaimRefund3G;
	
	@FindAll({
	@FindBy(xpath="//input[@title='Enter GSTIN/PAN']"),
	@FindBy(xpath="//input[@title='Please enter recipient GSTIN']"),
	@FindBy(xpath="//input[@title='Please enter supplier GSTIN']"),
	})
	private WebElement SupplierGSTINPAN3H;
	
	@FindAll({
	@FindBy(xpath="//select[@name='sup']"),
	@FindBy(xpath="//select[@title='Please select the POS from the drop down']")
	})
	private WebElement PlaceOfSupply3H;
	
	@FindAll({
	@FindBy(xpath="//input[@title='Please enter Bill of Entry value']"),
	@FindBy(xpath="//input[@title='Please enter Document value']")	
	})
	private WebElement BillOfEntryValue;
	
	
	@FindAll({
	@FindBy(xpath="//input[@title='Please enter Bill of Entry number']"),
	@FindBy(xpath="//input[contains(@title,'Please enter Bill of Entry number')]")
	})
	private WebElement BillOfEntryNo;
	
	@FindAll({
	@FindBy(xpath="//select[@title='Please select Document type']"),
	@FindBy(xpath="//select[@title='Please select Document Type']")
	})
	private WebElement DocumentType;
	
	@FindBy(xpath="//input[@title='Please enter GSTIN']")
	private WebElement GSTINOfECommerceOperator;
	
	@FindBy(xpath="//input[@title='Please enter Trade Name,if any']")
	private WebElement TradeLegalName;
	
	@FindBy(xpath="//input[@title='Please enter Supply value made']")
	private WebElement ValueOfSuppliesMade;
	
	@FindBy(xpath="//input[@title='Please enter Supply value returned']")
	private WebElement ValueOfSuppliesReturned;
	
	@FindBy(xpath="//input[@title='Please enter Integrated Tax Value']")
	private List<WebElement> IntegratedTax;
	
	@FindBy(xpath="//input[@title='Please enter Central Tax Value']")
	private List<WebElement> CentralTax;
	
	@FindBy(xpath="//input[@title='Please enter State/UT Tax Value']")
	private List<WebElement> StateTax;
	
	
	By rowElementDoc=By.xpath(".//parent::tr/td[4]");
	By colElementDoc=By.xpath(".//parent::tr/td[5]");
	By EditButtonDoc=By.xpath(".//parent::tr/descendant::div/button[1]");
	By dropDown=By.xpath("//li[@role='option']/div/div");
	By rowElementDocB2B=By.xpath(".//tr/td[11]");
	By colElementDocB2B=By.xpath(".//parent::tr/td[12]");
	By colElementDoc3E3F=By.xpath(".//parent::tr/td[13]");
	By taxableValue3H=By.xpath(".//parent::tr/td[6]");
	By integratedTax3H=By.xpath(".//parent::tr/td[7]");
	By taxableValue3I=By.xpath(".//parent::tr/td[4]");
	By integratedTax3I=By.xpath(".//parent::tr/td[5]");
	By taxableValue3J=By.xpath(".//parent::tr/td[9]");
	By integratedTax3J=By.xpath(".//parent::tr/td[10]");
	
	
	
	By taxRate=By.xpath("(//select[@id='irate'])[]");
	By taxableValue=By.xpath("//ancestor::tr//input[@id='irate']");
	
	
	String ApplicationPOS=null;
	String ExcelPOS=null;
	
	ExcelDatabase database = new ExcelDatabase();
	WebDriverWait wait;
	WebDriver driver;
	
	
	public GSTNEditReturns(WebDriver driver) {
		super(driver);//table[@class='DocTable table table-bordered ']/tbody
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}

	public void editSelectedItem(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
		Properties dataRows = ExcelRead.readRowDataInProperties(workbook, "GSTN_EditDocument", testCaseName,stepGroup);

		
		wait.until(ExpectedConditions.visibilityOf(SelectReturnType));
		Reporter.log("<B>Select '" + dataRow.getProperty("ReturnType") + "' from drop down</B>");
		if (isDisplayed(SelectReturnType)) {
			selectFromDropdownByVisibleText(SelectReturnType, dataRow.getProperty("ReturnType"),"Return type Drop Down");
		}
		Reporter.log("<B>Edit Selected records of GSTN='" + GSTNID + "'</B>");

		Recordset tableData = database.selectQueryForHSNData(conn, "GSTN_DeleteSelectedItems", "TCID", testCaseName,GSTNID);
		
		while (tableData.next()) {
			if (tableData.getField("TCID").equals(testCaseName) && tableData.getField("GSTIN").equals(GSTNID)) {

				Double TotalTaxableValue = Double.parseDouble(tableData.getField("TaxableValue").replaceAll(",", ""));
				String totalTaxableValue=String.valueOf(TotalTaxableValue).split("\\.")[0];
				clearAndSenKeys(Search, totalTaxableValue, "Search");
				/*Double TotalTaxableValue = Double.parseDouble(tableData.getField("TaxableValue").replaceAll(",", ""));
				String Currencyformat=getCurrency(TotalTaxableValue);*/
				String Currencyformat=String.valueOf(TotalTaxableValue).split("\\.")[1];
				String TotalTaxbleValues=null;
				if(Currencyformat.length()==1) {
					TotalTaxbleValues=getIndianCurrency(totalTaxableValue);
					
				}
				else {
					TotalTaxbleValues=String.valueOf(TotalTaxableValue);
				}
				String TaxableRate = tableData.getField("TaxRate");
				
				WebElement editButton=selectFromTable(DocumentTable,rowElementDoc,colElementDoc,TotalTaxbleValues,TaxableRate,EditButtonDoc);
				Reporter.log("Edit document from grid where Total Taxable value is '<B>" + TotalTaxbleValues + "</B>' and Integrated tax is '<B>" + TaxableRate + "'</B>");
				if (editButton.isEnabled()) {
					highlighter(editButton);
					click(editButton, "EDIT");
				
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", State);
					 ApplicationPOS=fetchTextFromAngularApplication(State, "Place of Supply (Name of State/UT)");
					 System.out.println(ApplicationPOS);
					
					if(!dataRows.getProperty("PlaceOfSupply").equals("")) {
					String PlaceOfSupply=dataRows.getProperty("PlaceOfSupply").replaceAll("[^A-Za-z0-9]", "");
					ExcelPOS=PlaceOfSupply.substring(PlaceOfSupply.length()-2);
					System.out.println(ExcelPOS);
					if(ApplicationPOS.equals(ExcelPOS)) {
						Reporter.log("Place of Supply is ='<B>" + ApplicationPOS + "'</B>");
					}
					else if (!ApplicationPOS.equals(ExcelPOS)) {
						Reporter.log("Update Place of Supply from '<B>" + ApplicationPOS + "</B>' to '<B>" + ExcelPOS + "'</B>");
						selectFromDropdownByVisibleText(State,dataRows.getProperty("PlaceOfSupply"), "Place of Supply (Name of State/UT)");
						}
					}
					else
					{
						Reporter.log("Place of Supply is ='<B>" + ApplicationPOS + "'</B>");
					}
					
					if(dataRows.getProperty("Differential%OfTaxRateChecBox").equals("Yes") && dataRows.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equals("Yes") && !DifferentialPercentOfTaxRateCheckBox.isSelected() && !Supplycoveredundersec7ofIGSTAct.isSelected()) {
						selectCheckBox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
						selectCheckBox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
						Reporter.log("Select both <B> Differential % of tax rate and </B> and <B> Supply covered under sec 7 of IGST Act </B>");
						String DTR=fetchTextFromAngularApplication(Differentialoftaxrate, "Differential % of tax rate");
						database.updateQueryForData(conn, "GSTN_EditDocument", "DifferentialPercentageOfTaxRateDropDown", testCaseName,stepGroup, DTR);
						String SCUSec7=fetchTextFromAngularApplication(Supplycoveredundersec7ofIGSTAct1, "Supply covered under sec 7 of IGST Act");
						database.updateQueryForData(conn, "GSTN_EditDocument", "SupplyCoveredUnderSecSevenOfIGSTActDropDown", testCaseName,stepGroup, SCUSec7);
					}
					else if (dataRows.getProperty("Differential%OfTaxRateChecBox").equals("No") && dataRows.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equals("No") && DifferentialPercentOfTaxRateCheckBox.isSelected() && Supplycoveredundersec7ofIGSTAct.isSelected()) {
						uncheckCheckbox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
						uncheckCheckbox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");					
						Reporter.log("Uncheck both <B> Differential % of tax rate and </B> and <B> Supply covered under sec 7 of IGST Act </B>");
					}
					else if (dataRows.getProperty("Differential%OfTaxRateChecBox").equals("Yes") && dataRows.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equals("No") && !DifferentialPercentOfTaxRateCheckBox.isSelected() && Supplycoveredundersec7ofIGSTAct.isSelected()) {
						selectCheckBox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
						uncheckCheckbox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
						Reporter.log("Check  <B> Differential % of tax rate and </B> and Uncehck <B> Supply covered under sec 7 of IGST Act </B>");
						String DTR=fetchTextFromAngularApplication(Differentialoftaxrate, "Differential % of tax rate");
						database.updateQueryForData(conn, "GSTN_EditDocument", "DifferentialPercentageOfTaxRateDropDown", testCaseName,stepGroup, DTR);
					}
					else if (dataRows.getProperty("Differential%OfTaxRateChecBox").equals("No") && dataRows.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equals("Yes") && DifferentialPercentOfTaxRateCheckBox.isSelected() && !Supplycoveredundersec7ofIGSTAct.isSelected()) {
						uncheckCheckbox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
						selectCheckBox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
						Reporter.log("UnCheck  <B> Differential % of tax rate and </B> and Check <B> Supply covered under sec 7 of IGST Act </B>");
						String SCUSec7=fetchTextFromAngularApplication(Supplycoveredundersec7ofIGSTAct1, "Supply covered under sec 7 of IGST Act");
						database.updateQueryForData(conn, "GSTN_EditDocument", "SupplyCoveredUnderSecSevenOfIGSTActDropDown", testCaseName,stepGroup, SCUSec7);
					}
					
					Recordset editItems = database.selectQueryForHSNData(conn, "GSTN_Edit_Item", "TCID", testCaseName,GSTNID);
					while (editItems.next()) {
						int i=0;
						if (editItems.getField("TCID").equals(testCaseName) && editItems.getField("GSTIN").equals(GSTNID)&& editItems.getField("Edit").equalsIgnoreCase("Yes")&& editItems.getField("ReturnType").equalsIgnoreCase(ReturnType)) {
						
							if(HSNCode.size()==0 && TaxableValue.size()!=0 && TaxRate.size()!=0) {
							List<WebElement> taxRatess=driver.findElements(By.xpath("//select[@id='irate']"));
					
							for(WebElement getTaxRate:taxRatess) {
								if(getTaxRate.getAttribute("value").concat("%").equals(editItems.getField("OldTaxRate"))) {
									selectFromDropdownByVisibleText(TaxRate.get(i), editItems.getField("NewTaxRate"), "Tax rate");
								}
								List<WebElement> TaxableValuess=getTaxRate.findElements(By.xpath(".//ancestor::tr//input[@id='irate']"));
								for(WebElement taxableValues:TaxableValuess) {
									Double OldTaxableValue = Double.parseDouble(editItems.getField("OldTaxableValue").concat(".00"));
									String oldTaxableValue=getCurrency(OldTaxableValue);
									if(taxableValues.getAttribute("value").equals(oldTaxableValue)) {
										clearAndSenKeys(TaxableValue.get(i), editItems.getField("NewTaxableValue"), "Taxable value");	
										}
										i++;
									}
								}
							}
						}

					}
					click(SaveDocument, "Save Document");
					if (successfulMessage.size() != 0) {
						highlighter(successfulMessage.get(0));
						String message = fetchTextFromApplication(successfulMessage.get(0), "Update Message");
						database.updateQueryForData(conn, "GSTN_Edit_Item", "Message", testCaseName, stepGroup,message);
						Reporter.log("<B> " + message + "</B>");
					}
					else {
						highlighter(FailedMessage.get(0));
						String message = fetchTextFromApplication(FailedMessage.get(0), "Failed Message");
						database.updateQueryForData(conn, "GSTN_Edit_Item", "Message", testCaseName, stepGroup,message);
						Reporter.log("<B> " + message + "</B>");
						CustomAssert.executionFlag = false;
					}
				}
			}
		}
	}
	
	public void editDocumentsB2B(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
		
		Reporter.log("<B>Edit Selected records of GSTN='" + GSTNID + "'</B>");

		Thread.sleep(WaitTime.low);
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
		
		if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {
				/*String state = dataRow.getProperty("PlaceOfSupply");
				String[] split = state.split(" ");
				String getState = split[0].trim();*/
			    String RecipientGSTINUIN = dataRow.getProperty("RecipientGSTIN/UIN");
				clearAndSenKeys(Search, RecipientGSTINUIN, "Search");
			    }
		    
				Double TotalTaxableValue = Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",", ""));
				String Currencyformat=getCurrency(TotalTaxableValue);
				//Defect 14906 where table summary doesn't match with Document grid Hence reducing value by 0.1
				String TaxableRate = dataRow.getProperty("TaxRate");
				Double TotalIntegratedtax=Double.parseDouble(TaxableRate);
				String TotalIntegratedTax = String.format("%.2f", TotalIntegratedtax);
				
				/*WebElement editButton=selectFromTable(B2BDocumentTable,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDoc);
				if (editButton.isEnabled()) {
					highlighter(editButton);
					click(editButton, "EDIT");
				}*/
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", pagignationDropDown);
				wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
				Select Pagignation=new Select(pagignationDropDown);
				highlighter(pagignationDropDown);
				List<WebElement> editButton=selectFromTables(B2BDocumentTable,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDoc);
				Outer:if (editButton!=null) {
					highlighter(editButton.get(0));
					click(editButton.get(0), "EDIT");
				}
				else
				{
					for(int i=0;i<Pagignation.getOptions().size();i++) {
			        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", pagignationDropDown);
			        	//System.out.println(Pagignation.getOptions().get(i).getText());
			        	Pagignation.selectByVisibleText(Pagignation.getOptions().get(i).getText());
			        	if (selectFromTables(B2BDocumentTable,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDoc)!=null) {
			        		WebElement EditButton=selectFromTables(B2BDocumentTable,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDoc).get(0);
			        		highlighter(EditButton);
							click(EditButton, "EDIT");
						break Outer;}
			        	else {
			        		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.tagName("a")));			        				        	
			        		while(NextPage.size()!=0) {
			        	    String title = NextPage.get(0).getAttribute("aria-label");
			        	    if (title.equals("Next page")) {
			        	    	NextPage.get(0).click();
			        	        if (selectFromTables(B2BDocumentTable,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDoc)!=null) {
			        	        	WebElement EditButton=selectFromTables(B2BDocumentTable,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDoc).get(0);
					        		highlighter(EditButton);
									click(EditButton, "EDIT");
								break Outer;}

			        	    }
			        	}
			        	}
					}
				}
				
				
					Recordset editItems = database.selectQueryForHSNData(conn, "GSTN_Edit_Item", "TCID", testCaseName,GSTNID);
					while (editItems.next()) {
						int i=0;
						if (editItems.getField("TCID").equals(testCaseName) && editItems.getField("GSTIN").equals(GSTNID)&& editItems.getField("Edit").equalsIgnoreCase("Yes")&& editItems.getField("ReturnType").equalsIgnoreCase(ReturnType)) {
						
				if (HSNCode.size() != 0 && TaxableValueB2B.size() != 0 && TaxRateB2B.size() != 0) {
					List<WebElement> HSNCodes = driver.findElements(By.xpath("//input[@placeholder='Enter HSN Code/Description']"));
					for (WebElement HSN : HSNCodes) {
						if (HSN.getAttribute("value").equals(editItems.getField("OldHSNCode"))) {
							clearAndSenKeysAutoComplete(HSNCode.get(i), dropDown, editItems.getField("NewHSNCode"),"HSN Code");
							Reporter.log("Update HSNCode from <B> " + editItems.getField("OldHSNCode") + "</B> to new HSNCode <B> " + editItems.getField("NewHSNCode") + "</B>");
							List<WebElement> taxRatess = HSN.findElements(By.xpath(".//ancestor::tr//select[@name='irate']"));
							for (WebElement getTaxRate : taxRatess) {
								if (getTaxRate.getAttribute("value").concat("%").equals(editItems.getField("OldTaxRate"))) {
									selectFromDropdownByVisibleText(TaxRateB2B.get(i), editItems.getField("NewTaxRate"),"Tax rate");
									Reporter.log("Update TaxRate from <B> " + editItems.getField("OldTaxRate") + "</B> to new TaxRate <B> " + editItems.getField("NewTaxRate") + "</B>");		
								}
								List<WebElement> TaxableValuess = getTaxRate.findElements(By.xpath(".//ancestor::tr//input[@title='Enter taxable value']"));
								for (WebElement taxableValues : TaxableValuess) {
									Double OldTaxableValue = Double.parseDouble(editItems.getField("OldTaxableValue").replaceAll(",", ""));
									String oldTaxableValue = getCurrency(OldTaxableValue);
									if (taxableValues.getAttribute("value").equals(oldTaxableValue)) {
										clearAndSenKeys(TaxableValueB2B.get(i), editItems.getField("NewTaxableValue"),"Taxable value");
										Reporter.log("Update Taxable Value from <B> " + editItems.getField("OldTaxableValue") + "</B> to new Taxable Value <B> " + editItems.getField("NewTaxableValue") + "</B>");		

									}
								}}}

								i++;
							
							
							}
						}
						}
						
					}
					click(SaveDocument, "Save Document");
					if (successfulMessage.size() != 0) {
						highlighter(successfulMessage.get(0));
						String message = fetchTextFromApplication(successfulMessage.get(0), "Update Message");
						database.updateQueryForData(conn, "GSTN_Edit_Item", "Message", testCaseName, stepGroup,message);
						Reporter.log("<B> " + message + "</B>");
					}
					else {
						highlighter(FailedMessage.get(0));
						String message = fetchTextFromApplication(FailedMessage.get(0), "Failed Message");
						database.updateQueryForData(conn, "GSTN_Edit_Item", "Message", testCaseName, stepGroup,message);
						Reporter.log("<B> " + message + "</B>");
						CustomAssert.executionFlag = false;
					}
				}
public void editDocuments3C3D(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
	
		Reporter.log("<B>Edit Selected records of GSTN='" + GSTNID + "'</B>");

		Thread.sleep(WaitTime.low);
		Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
		
		if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {
				
			    String RecipientGSTINUIN = dataRow.getProperty("TaxableValue").split("\\.")[0];
			    clearAndSenKeys(Search3C3D, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
			    }
		    
				Double TotalTaxableValue = Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",", ""));
				String Currencyformat=getCurrency(TotalTaxableValue);
				//Defect 14906 where table summary doesn't match with Document grid Hence reducing value by 0.1
				String TaxableRate = dataRow.getProperty("TaxRate");
				Double TotalIntegratedtax=Double.parseDouble(TaxableRate);
				String TotalIntegratedTax = String.format("%.2f", TotalIntegratedtax);
				
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", pagignationDropDown);
				wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
				Select Pagignation=new Select(pagignationDropDown);
				highlighter(pagignationDropDown);
				List<WebElement> editButton=selectFromTables(DocumentTable3C3D,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDoc);
				Outer:if (editButton!=null) {
					highlighter(editButton.get(0));
					click(editButton.get(0), "EDIT");
				}
				else
				{
					for(int i=0;i<Pagignation.getOptions().size();i++) {
			        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", pagignationDropDown);
			        	//System.out.println(Pagignation.getOptions().get(i).getText());
			        	Pagignation.selectByVisibleText(Pagignation.getOptions().get(i).getText());
			        	if (selectFromTables(DocumentTable3C3D,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDoc)!=null) {
			        		WebElement EditButton=selectFromTables(DocumentTable3C3D,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDoc).get(0);
			        		highlighter(EditButton);
							click(EditButton, "EDIT");
						break Outer;}
			        	else {
			        		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.tagName("a")));			        				        	
			        		while(NextPage.size()!=0) {
			        	    String title = NextPage.get(0).getAttribute("aria-label");
			        	    if (title.equals("Next page")) {
			        	    	NextPage.get(0).click();
			        	        if (selectFromTables(DocumentTable3C3D,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDoc)!=null) {
			        	        	WebElement EditButton=selectFromTables(DocumentTable3C3D,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDoc).get(0);
					        		highlighter(EditButton);
									click(EditButton, "EDIT");
								break Outer;}

			        	    }
			        	}
			        	}
					}
				}
				
				
					Recordset editItems = database.selectQueryForHSNData(conn, "GSTN_Edit_Item", "TCID", testCaseName,GSTNID);
					while (editItems.next()) {
						int i=0;
						if (editItems.getField("TCID").equals(testCaseName) && editItems.getField("GSTIN").equals(GSTNID)&& editItems.getField("Edit").equalsIgnoreCase("Yes")&& editItems.getField("ReturnType").equalsIgnoreCase(ReturnType)) {
						
				if (HSNCode.size() != 0 && TaxableValueB2B.size() != 0 && TaxRateB2B.size() != 0) {
					List<WebElement> HSNCodes = driver.findElements(By.xpath("//input[@placeholder='Enter HSN Code/Description']"));
					for (WebElement HSN : HSNCodes) {
						if (HSN.getAttribute("value").equals(editItems.getField("OldHSNCode"))) {
							clearAndSenKeysAutoComplete(HSNCode.get(i), dropDown, editItems.getField("NewHSNCode"),"HSN Code");
							Reporter.log("Update HSNCode from <B> " + editItems.getField("OldHSNCode") + "</B> to new HSNCode <B> " + editItems.getField("NewHSNCode") + "</B>");
							List<WebElement> taxRatess = HSN.findElements(By.xpath(".//ancestor::tr//select[@name='irate']"));
							for (WebElement getTaxRate : taxRatess) {
								if (getTaxRate.getAttribute("value").concat("%").equals(editItems.getField("OldTaxRate"))) {
									selectFromDropdownByVisibleText(TaxRateB2B.get(i), editItems.getField("NewTaxRate"),"Tax rate");
									Reporter.log("Update TaxRate from <B> " + editItems.getField("OldTaxRate") + "</B> to new TaxRate <B> " + editItems.getField("NewTaxRate") + "</B>");		
								}
								List<WebElement> TaxableValuess = getTaxRate.findElements(By.xpath(".//ancestor::tr//input[@title='Enter taxable value']"));
								for (WebElement taxableValues : TaxableValuess) {
									Double OldTaxableValue = Double.parseDouble(editItems.getField("OldTaxableValue").replaceAll(",", ""));
									String oldTaxableValue = getCurrency(OldTaxableValue);
									if (taxableValues.getAttribute("value").equals(oldTaxableValue)) {
										clearAndSenKeys(TaxableValueB2B.get(i), editItems.getField("NewTaxableValue"),"Taxable value");
										Reporter.log("Update Taxable Value from <B> " + editItems.getField("OldTaxableValue") + "</B> to new Taxable Value <B> " + editItems.getField("NewTaxableValue") + "</B>");		

									}
								}
							}
						}

						i++;

					}
				}
			}

		}
					click(SaveDocument, "Save Document");
					if (successfulMessage.size() != 0) {
						highlighter(successfulMessage.get(0));
						String message = fetchTextFromApplication(successfulMessage.get(0), "Update Message");
						database.updateQueryForData(conn, "GSTN_Edit_Item", "Message", testCaseName, stepGroup,message);
						Reporter.log("<B> " + message + "</B>");
					}
					else {
						highlighter(FailedMessage.get(0));
						String message = fetchTextFromApplication(FailedMessage.get(0), "Failed Message");
						database.updateQueryForData(conn, "GSTN_Edit_Item", "Message", testCaseName, stepGroup,message);
						Reporter.log("<B> " + message + "</B>");
						CustomAssert.executionFlag = false;
					}
				}
public void editDocuments3E3F(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {

	Reporter.log("<B>Edit Selected records of GSTN='" + GSTNID + "'</B>");

	Thread.sleep(WaitTime.low);
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
	
	if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {
			
		    String RecipientGSTINUIN = dataRow.getProperty("TaxableValue").split("\\.")[0];
		    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
		    }
	    
			Double TotalTaxableValue = Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",", ""));
			String Currencyformat=getCurrency(TotalTaxableValue);
			//Defect 14906 where table summary doesn't match with Document grid Hence reducing value by 0.1
			String TaxableRate = dataRow.getProperty("TaxRate");
			Double TotalIntegratedtax=Double.parseDouble(TaxableRate);
			String TotalIntegratedTax = String.format("%.2f", TotalIntegratedtax);
			
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", pagignationDropDown);
			wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
			Select Pagignation=new Select(pagignationDropDown);
			highlighter(pagignationDropDown);
			List<WebElement> editButton=selectFromTables(DocumentTable3E3F,colElementDocB2B,colElementDoc3E3F,Currencyformat,TotalIntegratedTax,EditButtonDoc);
			Outer:if (editButton!=null) {
				highlighter(editButton.get(0));
				click(editButton.get(0), "EDIT");
			}
			else
			{
				for(int i=0;i<Pagignation.getOptions().size();i++) {
		        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", pagignationDropDown);
		        	//System.out.println(Pagignation.getOptions().get(i).getText());
		        	Pagignation.selectByVisibleText(Pagignation.getOptions().get(i).getText());
		        	if (selectFromTables(DocumentTable3E3F,colElementDocB2B,colElementDoc3E3F,Currencyformat,TotalIntegratedTax,EditButtonDoc)!=null) {
		        		WebElement EditButton=selectFromTables(DocumentTable3E3F,colElementDocB2B,colElementDoc3E3F,Currencyformat,TotalIntegratedTax,EditButtonDoc).get(0);
		        		highlighter(EditButton);
						click(EditButton, "EDIT");
					break Outer;}
		        	else {
		        		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.tagName("a")));			        				        	
		        		while(NextPage.size()!=0) {
		        	    String title = NextPage.get(0).getAttribute("aria-label");
		        	    if (title.equals("Next page")) {
		        	    	NextPage.get(0).click();
		        	        if (selectFromTables(DocumentTable3E3F,colElementDocB2B,colElementDoc3E3F,Currencyformat,TotalIntegratedTax,EditButtonDoc)!=null) {
		        	        	WebElement EditButton=selectFromTables(DocumentTable3E3F,colElementDocB2B,colElementDoc3E3F,Currencyformat,TotalIntegratedTax,EditButtonDoc).get(0);
				        		highlighter(EditButton);
								click(EditButton, "EDIT");
							break Outer;}

		        	    }
		        	}
		        	}
				}
			}
			
			
				Recordset editItems = database.selectQueryForHSNData(conn, "GSTN_Edit_Item", "TCID", testCaseName,GSTNID);
				while (editItems.next()) {
					int i=0;
					if (editItems.getField("TCID").equals(testCaseName) && editItems.getField("GSTIN").equals(GSTNID)&& editItems.getField("Edit").equalsIgnoreCase("Yes")&& editItems.getField("ReturnType").equalsIgnoreCase(ReturnType)) {
					
			if (HSNCode.size() != 0 && TaxableValueB2B.size() != 0 && TaxRateB2B.size() != 0) {
				List<WebElement> HSNCodes = driver.findElements(By.xpath("//input[@placeholder='Enter HSN Code/Description']"));
				for (WebElement HSN : HSNCodes) {
					if (HSN.getAttribute("value").equals(editItems.getField("OldHSNCode"))) {
						clearAndSenKeysAutoComplete(HSNCode.get(i), dropDown, editItems.getField("NewHSNCode"),"HSN Code");
						Reporter.log("Update HSNCode from <B> " + editItems.getField("OldHSNCode") + "</B> to new HSNCode <B> " + editItems.getField("NewHSNCode") + "</B>");
						List<WebElement> taxRatess = HSN.findElements(By.xpath(".//ancestor::tr//select[@name='irate']"));
						for (WebElement getTaxRate : taxRatess) {
							if (getTaxRate.getAttribute("value").concat("%").equals(editItems.getField("OldTaxRate"))) {
								selectFromDropdownByVisibleText(TaxRateB2B.get(i), editItems.getField("NewTaxRate"),"Tax rate");
								Reporter.log("Update TaxRate from <B> " + editItems.getField("OldTaxRate") + "</B> to new TaxRate <B> " + editItems.getField("NewTaxRate") + "</B>");		
							}
							List<WebElement> TaxableValuess = getTaxRate.findElements(By.xpath(".//ancestor::tr//input[@title='Enter taxable value']"));
							for (WebElement taxableValues : TaxableValuess) {
								Double OldTaxableValue = Double.parseDouble(editItems.getField("OldTaxableValue").replaceAll(",", ""));
								String oldTaxableValue = getCurrency(OldTaxableValue);
								if (taxableValues.getAttribute("value").equals(oldTaxableValue)) {
									clearAndSenKeys(TaxableValueB2B.get(i), editItems.getField("NewTaxableValue"),"Taxable value");
									Reporter.log("Update Taxable Value from <B> " + editItems.getField("OldTaxableValue") + "</B> to new Taxable Value <B> " + editItems.getField("NewTaxableValue") + "</B>");		

								}
							}
						}
					}

					i++;

				}
			}
		}

	}
				click(SaveDocument, "Save Document");
				if (successfulMessage.size() != 0) {
					highlighter(successfulMessage.get(0));
					String message = fetchTextFromApplication(successfulMessage.get(0), "Update Message");
					database.updateQueryForData(conn, "GSTN_Edit_Item", "Message", testCaseName, stepGroup,message);
					Reporter.log("<B> " + message + "</B>");
				}
				else {
					highlighter(FailedMessage.get(0));
					String message = fetchTextFromApplication(FailedMessage.get(0), "Failed Message");
					database.updateQueryForData(conn, "GSTN_Edit_Item", "Message", testCaseName, stepGroup,message);
					Reporter.log("<B> " + message + "</B>");
					CustomAssert.executionFlag = false;
				}
			}
public void editDocuments3G(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {

	Reporter.log("<B>Edit Selected records of GSTN='" + GSTNID + "'</B>");

	Thread.sleep(WaitTime.low);
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
	
	if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {
			
		    String RecipientGSTINUIN = dataRow.getProperty("TaxableValue").split("\\.")[0];
		    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
		    }
	    
			Double TotalTaxableValue = Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",", ""));
			String Currencyformat=getCurrency(TotalTaxableValue);
			//Defect 14906 where table summary doesn't match with Document grid Hence reducing value by 0.1
			String TaxableRate = dataRow.getProperty("TaxRate");
			Double TotalIntegratedtax=Double.parseDouble(TaxableRate);
			String TotalIntegratedTax = String.format("%.2f", TotalIntegratedtax);
			
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", pagignationDropDown);
			wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
			Select Pagignation=new Select(pagignationDropDown);
			highlighter(pagignationDropDown);
			List<WebElement> editButton=selectFromTables(DocumentTable3E3F,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDoc);
			Outer:if (editButton!=null) {
				highlighter(editButton.get(0));
				click(editButton.get(0), "EDIT");
			}
			else
			{
				for(int i=0;i<Pagignation.getOptions().size();i++) {
		        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", pagignationDropDown);
		        	//System.out.println(Pagignation.getOptions().get(i).getText());
		        	Pagignation.selectByVisibleText(Pagignation.getOptions().get(i).getText());
		        	if (selectFromTables(DocumentTable3E3F,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDoc)!=null) {
		        		WebElement EditButton=selectFromTables(DocumentTable3E3F,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDoc).get(0);
		        		highlighter(EditButton);
						click(EditButton, "EDIT");
					break Outer;}
		        	else {
		        		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.tagName("a")));			        				        	
		        		while(NextPage.size()!=0) {
		        	    String title = NextPage.get(0).getAttribute("aria-label");
		        	    if (title.equals("Next page")) {
		        	    	NextPage.get(0).click();
		        	        if (selectFromTables(DocumentTable3E3F,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDoc)!=null) {
		        	        	WebElement EditButton=selectFromTables(DocumentTable3E3F,rowElementDocB2B,colElementDocB2B,Currencyformat,TotalIntegratedTax,EditButtonDoc).get(0);
				        		highlighter(EditButton);
								click(EditButton, "EDIT");
							break Outer;}

		        	    }
		        	}
		        	}
				}
			}
			
			
				Recordset editItems = database.selectQueryForHSNData(conn, "GSTN_Edit_Item", "TCID", testCaseName,GSTNID);
				while (editItems.next()) {
					int i=0;
					if (editItems.getField("TCID").equals(testCaseName) && editItems.getField("GSTIN").equals(GSTNID)&& editItems.getField("Edit").equalsIgnoreCase("Yes")&& editItems.getField("ReturnType").equalsIgnoreCase(ReturnType)) {
					
			if (HSNCode.size() != 0 && TaxableValueB2B.size() != 0 && TaxRateB2B.size() != 0) {
				List<WebElement> HSNCodes = driver.findElements(By.xpath("//input[@placeholder='Enter HSN Code/Description']"));
				for (WebElement HSN : HSNCodes) {
					if (HSN.getAttribute("value").equals(editItems.getField("OldHSNCode"))) {
						clearAndSenKeysAutoComplete(HSNCode.get(i), dropDown, editItems.getField("NewHSNCode"),"HSN Code");
						Reporter.log("Update HSNCode from <B> " + editItems.getField("OldHSNCode") + "</B> to new HSNCode <B> " + editItems.getField("NewHSNCode") + "</B>");
						List<WebElement> taxRatess = HSN.findElements(By.xpath(".//ancestor::tr//select[@name='irate']"));
						for (WebElement getTaxRate : taxRatess) {
							if (getTaxRate.getAttribute("value").concat("%").equals(editItems.getField("OldTaxRate"))) {
								selectFromDropdownByVisibleText(TaxRateB2B.get(i), editItems.getField("NewTaxRate"),"Tax rate");
								Reporter.log("Update TaxRate from <B> " + editItems.getField("OldTaxRate") + "</B> to new TaxRate <B> " + editItems.getField("NewTaxRate") + "</B>");		
							}
							List<WebElement> TaxableValuess = getTaxRate.findElements(By.xpath(".//ancestor::tr//input[@title='Enter taxable value']"));
							for (WebElement taxableValues : TaxableValuess) {
								Double OldTaxableValue = Double.parseDouble(editItems.getField("OldTaxableValue").replaceAll(",", ""));
								String oldTaxableValue = getCurrency(OldTaxableValue);
								if (taxableValues.getAttribute("value").equals(oldTaxableValue)) {
									clearAndSenKeys(TaxableValueB2B.get(i), editItems.getField("NewTaxableValue"),"Taxable value");
									Reporter.log("Update Taxable Value from <B> " + editItems.getField("OldTaxableValue") + "</B> to new Taxable Value <B> " + editItems.getField("NewTaxableValue") + "</B>");		

								}
							}
						}
					}

					i++;

				}
			}
		}

	}
				click(SaveDocument, "Save Document");
				if (successfulMessage.size() != 0) {
					highlighter(successfulMessage.get(0));
					String message = fetchTextFromApplication(successfulMessage.get(0), "Update Message");
					database.updateQueryForData(conn, "GSTN_Edit_Item", "Message", testCaseName, stepGroup,message);
					Reporter.log("<B> " + message + "</B>");
				}
				else {
					highlighter(FailedMessage.get(0));
					String message = fetchTextFromApplication(FailedMessage.get(0), "Failed Message");
					database.updateQueryForData(conn, "GSTN_Edit_Item", "Message", testCaseName, stepGroup,message);
					Reporter.log("<B> " + message + "</B>");
					CustomAssert.executionFlag = false;
				}
			}
public void editDocuments3H(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {

	Reporter.log("<B>Edit Selected records of GSTN='" + GSTNID + "'</B>");

	Thread.sleep(WaitTime.low);
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
	
	if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {
			
		    String RecipientGSTINUIN = dataRow.getProperty("TaxableValue").split("\\.")[0];
		    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
		    }
	    
			String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",", "")));
			//Defect 14906 where table summary doesn't match with Document grid Hence reducing value by 0.1
			String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TaxRate")));
			
			scrollIntoViewJavascript(pagignationDropDown);
			wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
			Select Pagignation=dropDown(pagignationDropDown);
			List<WebElement> editButtons=selectFromTables(B2BDocumentTable,taxableValue3H,integratedTax3H,TotalTaxableValue,TotalIntegratedtax,EditButtonDoc);
			editItemFromGrid(editButtons, pagignationDropDown, Pagignation);
			updateDataInItemGrid(conn, testCaseName, GSTNID, ReturnType, HSNCode, TaxableValueB2B, TaxRateB2B);
			click(SaveDocument, "Save Document");
			verifyAlertMessage(successfulMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_Edit_Item");
			}

public void editDocuments3I(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
	
	Reporter.log("<B>Edit Selected records of GSTN='" + GSTNID + "'</B>");

	Thread.sleep(WaitTime.low);
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
	
	if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {
			
		    String RecipientGSTINUIN = dataRow.getProperty("TaxableValue").split("\\.")[0];
		    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
		    }
	    
			String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",", "")));
			//Defect 14906 where table summary doesn't match with Document grid Hence reducing value by 0.1
			String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TaxRate")));
			
			scrollIntoViewJavascript(pagignationDropDown);
			wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
			Select Pagignation=dropDown(pagignationDropDown);
			List<WebElement> editButtons=selectFromTables(DocumentTable3I,taxableValue3I,integratedTax3I,TotalTaxableValue,TotalIntegratedtax,EditButtonDoc);
			editItemFromGrid(editButtons, pagignationDropDown, Pagignation);
			updateDataInItemGrid(conn, testCaseName, GSTNID, ReturnType, HSNCode, TaxableValueB2B, TaxRateB2B);
			click(SaveDocument, "Save Document");
				verifyAlertMessage(successfulMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_Edit_Item");
			}
public void editDocuments3J(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
	
	Reporter.log("<B>Edit Selected records of GSTN='" + GSTNID + "'</B>");

	Thread.sleep(WaitTime.low);
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
	
	if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {
			
		    String RecipientGSTINUIN = dataRow.getProperty("TaxableValue").split("\\.")[0];
		    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
		    }
	    
			String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",", "")));
			//Defect 14906 where table summary doesn't match with Document grid Hence reducing value by 0.1
			String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TaxRate")));
			
			scrollIntoViewJavascript(pagignationDropDown);
			wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
			Select Pagignation=dropDown(pagignationDropDown);
			List<WebElement> editButtons=selectFromTables(DocumentTable3I,taxableValue3J,integratedTax3J,TotalTaxableValue,TotalIntegratedtax,EditButtonDoc);
			editItemFromGrid(editButtons, pagignationDropDown, Pagignation);
			updateDataInItemGrid(conn, testCaseName, GSTNID, ReturnType, HSNCode, TaxableValueB2B, TaxRateB2B);
			click(SaveDocument, "Save Document");
				verifyAlertMessage(successfulMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_Edit_Item");
}
public void editDocuments3K(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
	
	Reporter.log("<B>Edit Selected records of GSTN='" + GSTNID + "'</B>");

	Thread.sleep(WaitTime.low);
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
	
	if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {
			
		    String RecipientGSTINUIN = dataRow.getProperty("TaxableValue").split("\\.")[0];
		    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
		    }
	    
			String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",", "")));
			//Defect 14906 where table summary doesn't match with Document grid Hence reducing value by 0.1
			String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TaxRate")));
			
			scrollIntoViewJavascript(pagignationDropDown);
			wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
			Select Pagignation=dropDown(pagignationDropDown);
			List<WebElement> editButtons=selectFromTables(DocumentTable3I,rowElementDocB2B,colElementDocB2B,TotalTaxableValue,TotalIntegratedtax,EditButtonDoc);
			editItemFromGrid(editButtons, pagignationDropDown, Pagignation);
			updateDataInItemGrid(conn, testCaseName, GSTNID, ReturnType, HSNCode, TaxableValueB2B, TaxRateB2B);
			click(SaveDocument, "Save Document");
			verifyAlertMessage(successfulMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_Edit_Item");
}
public void editDocuments3L(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
	
	Reporter.log("<B>Edit Selected records of GSTN='" + GSTNID + "'</B>");

	Thread.sleep(WaitTime.low);
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
	
	if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {
			
		    String RecipientGSTINUIN = dataRow.getProperty("TaxableValue").split("\\.")[0];
		    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
		    }
	    
			String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",", "")));
			//Defect 14906 where table summary doesn't match with Document grid Hence reducing value by 0.1
			String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TaxRate")));
			
			scrollIntoViewJavascript(pagignationDropDown);
			wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
			Select Pagignation=dropDown(pagignationDropDown);
			List<WebElement> editButtons=selectFromTables(DocumentTable3I,colElementDocB2B,colElementDoc3E3F,TotalTaxableValue,TotalIntegratedtax,EditButtonDoc);
			editItemFromGrid(editButtons, pagignationDropDown, Pagignation);
			updateDataInItemGrid(conn, testCaseName, GSTNID, ReturnType, HSNCode, TaxableValueB2B, TaxRateB2B);
			click(SaveDocument, "Save Document");
				verifyAlertMessage(successfulMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_Edit_Item");
}
public void editDocuments4(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {	
	Reporter.log("<B>Edit Selected records of GSTN='" + GSTNID + "'</B>");
	Thread.sleep(WaitTime.low);
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
	
	if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {
			
		    String RecipientGSTINUIN = dataRow.getProperty("TaxableValue").split("\\.")[0];
		    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
		    }
	    
			String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",", "")));
			//Defect 14906 where table summary doesn't match with Document grid Hence reducing value by 0.1
			String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TaxRate")));
			
			scrollIntoViewJavascript(pagignationDropDown);
			wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
			Select Pagignation=dropDown(pagignationDropDown);
			List<WebElement> editButtons=selectFromTables(B2BDocumentTable,taxableValue3H,taxableValue3H,TotalTaxableValue,TotalIntegratedtax,EditButtonDoc);
			editItemFromGrid(editButtons, pagignationDropDown, Pagignation);
			updateDataInItemGrid(conn, testCaseName, GSTNID, ReturnType, HSNCode, TaxableValueB2B, TaxRateB2B);
			click(SaveDocument, "Save Document");
			verifyAlertMessage(successfulMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_Edit_Item");
	}

public void editMainRecords3A(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {	
	Reporter.log("<B>Edit Selected Documents of GSTN='" + GSTNID + "'</B>");
	Thread.sleep(WaitTime.low);
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
	Properties dataRow1 = ExcelRead.readRowDataInProperties(workbook, "GSTN_EditDocument", testCaseName,stepGroup);
	if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {	
		    String RecipientGSTINUIN = dataRow.getProperty("TaxableValue").split("\\.")[0];
		    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
		    }
			//String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",", "")));
			String TotalTaxableValue=dataRow.getProperty("TaxableValue");
			String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TaxRate")));
			scrollIntoViewJavascript(pagignationDropDown);
			wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
			Select Pagignation=dropDown(pagignationDropDown);
			List<WebElement> editButtons=selectFromTables(DocumentTable,taxableValue3I,integratedTax3I,TotalTaxableValue,TotalIntegratedtax,EditButtonDoc);
			editItemFromGrid(editButtons, pagignationDropDown, Pagignation);
				if(dataRow1.getProperty("Differential%OfTaxRateChecBox").equals("Yes") && dataRow1.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equals("Yes")) {
					selectCheckBox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
					selectCheckBox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
				}
				else if (dataRow1.getProperty("Differential%OfTaxRateChecBox").equals("No") && dataRow1.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equals("No")) {
					uncheckCheckbox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
					uncheckCheckbox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");					
				}
				else if (dataRow1.getProperty("Differential%OfTaxRateChecBox").equals("Yes") && dataRow1.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equals("No")) {
					selectCheckBox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
					uncheckCheckbox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
				}
				else if (dataRow1.getProperty("Differential%OfTaxRateChecBox").equals("No") && dataRow1.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equals("Yes")) {
					uncheckCheckbox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
					selectCheckBox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
				}
				updateDropDownsField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "PlaceOfSupply", State, "Place of Supply (Name of State/UT)");
				click(SaveDocument, "Save Document");
				verifyAlertMessage(successfulMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_DeleteSelectedItems");
				
	}
public void editMainRecords3B(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
	Reporter.log("<B>Edit Documents of GSTN='" + GSTNID + "'</B>");
	Thread.sleep(WaitTime.low);
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
	Properties dataRow1 = ExcelRead.readRowDataInProperties(workbook, "GSTN_EditDocument", testCaseName,stepGroup);
	if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {	
		    String RecipientGSTINUIN = dataRow.getProperty("TaxableValue").split("\\.")[0];
		    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
		    }
			String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",", "")));
			String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TaxRate")));
			scrollIntoViewJavascript(pagignationDropDown);
			wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
			Select Pagignation=dropDown(pagignationDropDown);
			List<WebElement> editButtons=selectFromTables(B2BDocumentTable,rowElementDocB2B,colElementDocB2B,TotalTaxableValue,TotalIntegratedtax,EditButtonDoc);
			editItemFromGrid(editButtons, pagignationDropDown, Pagignation);
				if(dataRow1.getProperty("Differential%OfTaxRateChecBox").equals("Yes") && dataRow1.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equals("Yes")) {
					selectCheckBox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
					selectCheckBox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
				}
				else if (dataRow1.getProperty("Differential%OfTaxRateChecBox").equals("No") && dataRow1.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equals("No")) {
					uncheckCheckbox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
					uncheckCheckbox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");					
				}
				else if (dataRow1.getProperty("Differential%OfTaxRateChecBox").equals("Yes") && dataRow1.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equals("No")) {
					selectCheckBox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
					uncheckCheckbox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
				}
				else if (dataRow1.getProperty("Differential%OfTaxRateChecBox").equals("No") && dataRow1.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equals("Yes")) {
					uncheckCheckbox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
					selectCheckBox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
				}
				updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "RecipientGSTINUIN", RecipientGSTINUIN, "Recipient GSTIN/UIN");
				updateDropDownsField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentType", Documenttype, "Document Type ");
				updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentNumber", DocumentNumber, "Document No.");
				updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentDate", DocumentDate, "Document Date (DD/MM/YYYY)");
				updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentValue", DocumentValue, "Document Value(₹)");
				updateDropDownsField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "PlaceOfSupply", PlaceOfSupply, "Place of Supply (Name of State/UT)");
				click(SaveDocument, "Save Document");
				verifyAlertMessage(successfulMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_DeleteSelectedItems");
				
	}
public void editMainRecords3C3D(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
	Reporter.log("<B>Edit Documents of GSTN='" + GSTNID + "'</B>");
	Thread.sleep(WaitTime.low);
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
	if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {	
	    String RecipientGSTINUIN = dataRow.getProperty("TaxableValue").split("\\.")[0];
	    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
	    }
		String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",", "")));
		String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TaxRate")));
		scrollIntoViewJavascript(pagignationDropDown);
		wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
		Select Pagignation=dropDown(pagignationDropDown);
		List<WebElement> editButtons=selectFromTables(DocumentTable3C3D,rowElementDocB2B,colElementDocB2B,TotalTaxableValue,TotalIntegratedtax,EditButtonDoc);
		editItemFromGrid(editButtons, pagignationDropDown, Pagignation);
	updateDropDownsField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentType", GSTPayment, "Document Type");
	updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentNumber", DocumentNumber, "Document No.");
	updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentDate", DocumentDate, "Document Date (DD/MM/YYYY)");
	updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentValue", DocumentValue, "Document Value(₹)");
	updateDropDownsField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "ExportType", ExportType, "Export Type");
	updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "PortCode", PortCode, "Port Code");
	updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "ShippingBillDate", ShippingBillDate, "Shipping bill Date/Bill of Export Date DD/MM/YYYY");
	click(SaveDocument, "Save Document");
	verifyAlertMessage(successfulMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_DeleteSelectedItems");

	}
public void editMainRecords3E3F(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
	Reporter.log("<B>Edit Documents of GSTN='" + GSTNID + "'</B>");
	Thread.sleep(WaitTime.low);
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
	Properties dataRow1 = ExcelRead.readRowDataInProperties(workbook, "GSTN_EditDocument", testCaseName,stepGroup);

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
		editItemFromGrid(editButtons, pagignationDropDown, Pagignation);
	if(dataRow1.getProperty("Differential%OfTaxRateChecBox").equalsIgnoreCase("Yes")) {
		selectCheckBox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
	}
	else
	{
		Reporter.log("<B>Proceed without selecting Differential % of tax rate </B>");
	}
	updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "RecipientGSTINUIN", RecipientGSTIN, "Document No.");
	updateDropDownsField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentType", DocumentType, "Document Type ");
	updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentNumber", DocumentNumber, "Document No.");
	updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentDate", DocumentDate, "Document Date (DD/MM/YYYY)");
	updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentValue", DocumentValue, "Document Value(₹)");
	updateDropDownsField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "PlaceOfSupply", PlaceOfSupply, "Place of Supply (Name of State/UT)");
	updateDropDownsField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "GSTPayment", GSTPayment, "Place of Supply (Name of State/UT)");
     if(ClaimRefund.size()>0 && dataRow1.getProperty("GSTPayment").equals("SEZ Supplies with Payment of Tax")) {
		
		selectFromDropdownByVisibleText(ClaimRefund.get(0), dataRow1.getProperty("ClaimRefund"), "Would You Claim Refund?");
	 }
		else {
			Reporter.log("GST Payment is <B>SEZ Supplies without Payment of Tax</B>");
	}

    click(SaveDocument, "Save Document");
	verifyAlertMessage(successfulMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_DeleteSelectedItems");
	}
public void editMainRecords3G(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
	Reporter.log("<B>Edit Documents of GSTN='" + GSTNID + "'</B>");
	Thread.sleep(WaitTime.low);
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
	Properties dataRow1 = ExcelRead.readRowDataInProperties(workbook, "GSTN_EditDocument", testCaseName,stepGroup);
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
		editItemFromGrid(editButtons, pagignationDropDown, Pagignation);
		if(dataRow1.getProperty("Differential%OfTaxRateChecBox").equals("Yes") && dataRow1.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equals("Yes")) {
			selectCheckBox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
			selectCheckBox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
		}
		else if (dataRow1.getProperty("Differential%OfTaxRateChecBox").equals("No") && dataRow1.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equals("No")) {
			uncheckCheckbox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
			uncheckCheckbox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");					
		}
		else if (dataRow1.getProperty("Differential%OfTaxRateChecBox").equals("Yes") && dataRow1.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equals("No")) {
			selectCheckBox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
			uncheckCheckbox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
		}
		else if (dataRow1.getProperty("Differential%OfTaxRateChecBox").equals("No") && dataRow1.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equals("Yes")) {
			uncheckCheckbox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
			selectCheckBox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
		}
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "RecipientGSTINUIN", RecipientGSTIN, "Document No.");
		updateDropDownsField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentType", DocumentType, "Document Type ");
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentNumber", DocumentNumber, "Document No.");
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentDate", DocumentDate, "Document Date (DD/MM/YYYY)");
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentValue", DocumentValue, "Document Value(₹)");
		updateDropDownsField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "PlaceOfSupply", PlaceOfSupply, "Place of Supply (Name of State/UT)");
		//updateDropDownsField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "PlaceOfSupply", ClaimRefund3G, "Place of Supply (Name of State/UT)");
		click(SaveDocument, "Save Document");
		verifyAlertMessage(successfulMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_DeleteSelectedItems");
	}
public void editMainRecords3H(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
	Reporter.log("<B>Edit Documents of GSTN='" + GSTNID + "'</B>");
	Thread.sleep(WaitTime.low);
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
	Properties dataRow1 = ExcelRead.readRowDataInProperties(workbook, "GSTN_EditDocument", testCaseName,stepGroup);

	if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {	
	    String RecipientGSTINUIN = dataRow.getProperty("TaxableValue").split("\\.")[0];
	    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
	    }
		String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("TaxableValue").replaceAll(",", "")));
		String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("TaxRate")));
		scrollIntoViewJavascript(pagignationDropDown);
		wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
		Select Pagignation=dropDown(pagignationDropDown);
		List<WebElement> editButtons=selectFromTables(B2BDocumentTable,taxableValue3H,integratedTax3H,TotalTaxableValue,TotalIntegratedtax,EditButtonDoc);
		editItemFromGrid(editButtons, pagignationDropDown, Pagignation);
		if(dataRow1.getProperty("Differential%OfTaxRateChecBox").equals("Yes") && dataRow1.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equals("Yes")) {
			selectCheckBox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
			selectCheckBox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
		}
		else if (dataRow1.getProperty("Differential%OfTaxRateChecBox").equals("No") && dataRow1.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equals("No")) {
			uncheckCheckbox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
			uncheckCheckbox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");					
		}
		else if (dataRow1.getProperty("Differential%OfTaxRateChecBox").equals("Yes") && dataRow1.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equals("No")) {
			selectCheckBox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
			uncheckCheckbox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
		}
		else if (dataRow1.getProperty("Differential%OfTaxRateChecBox").equals("No") && dataRow1.getProperty("SupplyCoveredUnderSec7OfIGSTActChecBox").equals("Yes")) {
			uncheckCheckbox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
			selectCheckBox(Supplycoveredundersec7ofIGSTAct, "Supply covered under sec 7 of IGST Act");
		}
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "SupplierGSTIN/PAN", SupplierGSTINPAN3H, "Supplier GSTIN/PAN");
		updateDropDownsField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "PlaceOfSupply", PlaceOfSupply3H, "Place of Supply (Name of State/UT)");
		click(SaveDocument, "Save Document");
		verifyAlertMessage(successfulMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_DeleteSelectedItems");
	}
public void editMainRecords3I(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
	Reporter.log("<B>Edit Documents of GSTN='" + GSTNID + "'</B>");
	Thread.sleep(WaitTime.low);
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
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
		editItemFromGrid(editButtons, pagignationDropDown, Pagignation);
		updateDropDownsField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "PlaceOfSupply", PlaceOfSupply3H, "Place of Supply (Name of State/UT)");
		click(SaveDocument, "Save Document");
		verifyAlertMessage(successfulMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_DeleteSelectedItems");
	}
public void editMainRecords3J(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
	Reporter.log("<B>Edit Documents of GSTN='" + GSTNID + "'</B>");
	Thread.sleep(WaitTime.low);
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
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
		editItemFromGrid(editButtons, pagignationDropDown, Pagignation);
		//updateDropDownsField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentType", DocumentType, "Document Type");
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "PortCode", PortCode, "Port Code");
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "BillOfEntryNo", BillOfEntryNo, "Bill of Entry No.");
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentDate", DocumentDate, "Bill of Entry Date (DD/MM/YYYY) ");
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "BillOfEntryValue", BillOfEntryValue, "Bill of Entry Value (₹)");
		updateDropDownsField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "PlaceOfSupply", PlaceOfSupply3H, "Place of Supply (Name of State/UT)");
		click(SaveDocument, "Save Document");
		verifyAlertMessage(successfulMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_DeleteSelectedItems");
	}
public void editMainRecords3K(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
	Reporter.log("<B>Edit Documents of GSTN='" + GSTNID + "'</B>");
	Thread.sleep(WaitTime.low);
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
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
		editItemFromGrid(editButtons, pagignationDropDown, Pagignation);
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "SupplierGSTIN/PAN", SupplierGSTINPAN3H, "Supplier GSTIN");
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentType", DocumentType, "Document type");
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "PortCode", PortCode, "Port Code");
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "BillOfEntryNo", BillOfEntryNo, "Bill of Entry No.");
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "ShippingBillDate/DocumentDate/BillOfEntryDate", DocumentDate, "Bill of Entry Date (DD/MM/YYYY) ");
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "BillOfEntryValue", BillOfEntryValue, "Bill of Entry Value (₹)");
		updateDropDownsField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "PlaceOfSupply", PlaceOfSupply3H, "Place of Supply (Name of State/UT)");
		click(SaveDocument, "Save Document");
		verifyAlertMessage(successfulMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_DeleteSelectedItems");
	}
public void editMainRecords3L(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
	Reporter.log("<B>Edit Documents of GSTN='" + GSTNID + "'</B>");
	Thread.sleep(WaitTime.low);
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
	Properties dataRow1 = ExcelRead.readRowDataInProperties(workbook, "GSTN_EditDocument", testCaseName,stepGroup);

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
		editItemFromGrid(editButtons, pagignationDropDown, Pagignation);
		if(dataRow1.getProperty("Differential%OfTaxRateChecBox").equalsIgnoreCase("Yes")) {
			selectCheckBox(DifferentialPercentOfTaxRateCheckBox, "Differential % of tax rate");
		}
		else
		{
			Reporter.log("<B>Proceed without selecting Differential % of tax rate </B>");
		}
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "SupplierGSTIN/PAN", SupplierGSTINPAN3H, "Supplier GSTIN");
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentType", DocumentType, "Document type");
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentNumber", DocumentNumber, "Document No.");
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentDate", DocumentDate, "Document Date (DD/MM/YYYY)");
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "DocumentValue", DocumentValue, "Document Value(₹)");
		updateDropDownsField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "PlaceOfSupply", PlaceOfSupply3H, "Place of Supply (Name of State/UT)");
		click(SaveDocument, "Save Document");
		verifyAlertMessage(successfulMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_DeleteSelectedItems");
	}
public void editMainRecords4(WebDriver driver, String testCaseName, String GSTNID,String ReturnType, XSSFWorkbook workbook,Connection conn, String stepGroup) throws Exception {
	Reporter.log("<B>Edit Documents of GSTN='" + GSTNID + "'</B>");
	Thread.sleep(WaitTime.low);
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, "GSTN_DeleteSelectedItems", testCaseName,stepGroup);
	if (dataRow.getProperty("TCID").equals(testCaseName) && dataRow.getProperty("GSTIN").equals(GSTNID)) {	
	    String RecipientGSTINUIN = dataRow.getProperty("ValueOfSuppliesMade").split("\\.")[0];
	    clearAndSenKeys(SearchRecords, RecipientGSTINUIN.replaceAll("[^a-zA-Z0-9]", ""), "Search");
	    }
		String TotalTaxableValue = getCurrency(Double.parseDouble(dataRow.getProperty("ValueOfSuppliesMade").replaceAll(",", "")));
		String TotalIntegratedtax=String.format("%.2f", Double.parseDouble(dataRow.getProperty("ValueOfSuppliesReturned")));
		scrollIntoViewJavascript(pagignationDropDown);
		wait.until(ExpectedConditions.visibilityOf(pagignationDropDown));
		Select Pagignation=dropDown(pagignationDropDown);
		List<WebElement> editButtons=selectFromTables(DocumentTable3E3F,integratedTax3I,taxableValue3H,TotalTaxableValue,TotalIntegratedtax,EditButtonDoc);
		editItemFromGrid(editButtons, pagignationDropDown, Pagignation);
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "GSTINOfEcommerceOperator", GSTINOfECommerceOperator, "GSTIN of e-commerce operator");
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "ValueOfSuppliesMade", ValueOfSuppliesMade, "Value of supplies made (₹)");
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "ValueOfSuppliesReturned", ValueOfSuppliesReturned, "Value of supplies returned (₹)");
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "IntegratedTax", IntegratedTax.get(0), "Integrated Tax");
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "CentralTax", CentralTax.get(0), "Central Tax");
		updateTextField(testCaseName, GSTNID, workbook, conn, stepGroup, "GSTN_EditDocument", "StateTax", StateTax.get(0), "State / UT tax");
		click(SaveDocument, "Save Document");
		verifyAlertMessage(successfulMessage, FailedMessage, database, conn, testCaseName, stepGroup,"GSTN_DeleteSelectedItems");
	}
public void updateDataInItemGrid(Connection conn,String testCaseName, String GSTNID,String ReturnType,List<WebElement> HSNCode,List<WebElement> TaxableValueB2B,List<WebElement> TaxRateB2B) {
	try {
	Recordset editItems = database.selectQueryForHSNData(conn, "GSTN_Edit_Item", "TCID", testCaseName,GSTNID);
	while (editItems.next()) {
		int i=0;
		if (editItems.getField("TCID").equals(testCaseName) && editItems.getField("GSTIN").equals(GSTNID)&& editItems.getField("Edit").equalsIgnoreCase("Yes")&& editItems.getField("ReturnType").equalsIgnoreCase(ReturnType)) {
		
if (HSNCode.size() != 0 && TaxableValueB2B.size() != 0 && TaxRateB2B.size() != 0) {
	List<WebElement> HSNCodes = driver.findElements(By.xpath("//input[@placeholder='Enter HSN Code/Description']"));
	for (WebElement HSN : HSNCodes) {
		if (HSN.getAttribute("value").equals(editItems.getField("OldHSNCode"))) {
			clearAndSenKeysAutoComplete(HSNCode.get(i), dropDown, editItems.getField("NewHSNCode"),"HSN Code");
			Reporter.log("Update HSNCode from <B> " + editItems.getField("OldHSNCode") + "</B> to new HSNCode <B> " + editItems.getField("NewHSNCode") + "</B>");
			List<WebElement> taxRatess = HSN.findElements(By.xpath(".//ancestor::tr//select[@name='irate']"));
			for (WebElement getTaxRate : taxRatess) {
				if (getTaxRate.getAttribute("value").concat("%").equals(editItems.getField("OldTaxRate"))) {
					selectFromDropdownByVisibleText(TaxRateB2B.get(i), editItems.getField("NewTaxRate"),"Tax rate");
					Reporter.log("Update TaxRate from <B> " + editItems.getField("OldTaxRate") + "</B> to new TaxRate <B> " + editItems.getField("NewTaxRate") + "</B>");		
				}
				List<WebElement> TaxableValuess = getTaxRate.findElements(By.xpath(".//ancestor::tr//input[@title='Enter taxable value']"));
				for (WebElement taxableValues : TaxableValuess) {
					Double OldTaxableValue = Double.parseDouble(editItems.getField("OldTaxableValue").replaceAll(",", ""));
					String oldTaxableValue = getCurrency(OldTaxableValue);
					if (taxableValues.getAttribute("value").equals(oldTaxableValue)) {
						clearAndSenKeys(TaxableValueB2B.get(i), editItems.getField("NewTaxableValue"),"Taxable value");
						Reporter.log("Update Taxable Value from <B> " + editItems.getField("OldTaxableValue") + "</B> to new Taxable Value <B> " + editItems.getField("NewTaxableValue") + "</B>");		

					}
				}
			}
		}

		i++;

	}
}
}

}
	}catch (Exception e) {
		e.getMessage();
	}	
}

public void editItemFromGrid(List<WebElement> element,WebElement selectDropDown,Select select) {
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
public void updateTextField(String testCaseName, String GSTNID, XSSFWorkbook workbook,Connection conn, String stepGroup,String SheetName,String rowField,WebElement element,String elementName) {
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, SheetName, testCaseName,stepGroup);
	try {
		if(dataRow.getProperty(rowField)=="") {
			String fetchData=fetchTextFromAngularApplication(element, elementName);
			Reporter.log(dataRow.getProperty(rowField)+"is"+fetchData);	
		}
		else {
			clearAndSenKeys(element, dataRow.getProperty(rowField), elementName);
		}
		
	}catch (Exception e) {
		// TODO: handle exception
	}
}
public void updateDropDownsField(String testCaseName, String GSTNID, XSSFWorkbook workbook,Connection conn, String stepGroup,String SheetName,String rowField,WebElement element,String elementName) {
	Properties dataRow = ExcelRead.readRowDataInProperties(workbook, SheetName, testCaseName,stepGroup);
	try {
		if(dataRow.getProperty(rowField)=="") {
			String fetchData=fetchTextFromAngularApplication(element, elementName);
			Reporter.log(dataRow.getProperty(rowField)+"is"+fetchData);	
		}
		else {
			selectFromDropdownByVisibleText(element, dataRow.getProperty(rowField), elementName);
		}
		
	}catch (Exception e) {
		// TODO: handle exception
	}
}
}
