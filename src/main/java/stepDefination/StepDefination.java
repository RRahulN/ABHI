package stepDefination;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.codoid.products.fillo.Connection;

import pages.BasicDetails;
import pages.CollectionScreen;
import pages.DiscountAndLoading;
import pages.FamilyFloaterQuickPricingPage;
import pages.FamilyFloaterQuoteCreation;
import pages.Finalize;
import pages.GSTLoginPages;
import pages.GSTNAOSCommonPage;
import pages.GSTNAddReturns;
import pages.GSTNDashBoard;
import pages.GSTNDeleteReturns;
import pages.GSTNEditReturns;
import pages.GSTNOnlineLoginPage;
import pages.GSTNOnlineNewReturndashBoardPage;
import pages.GSTNOnlinePortalPage;
import pages.IndividualQuickPricingPage;
import pages.IndividualQuoteCreationPage;
import pages.MemberInfoPage;
import pages.MultiIndividualQuoteCreationPage;
import pages.OtherUserLogin;
import pages.PPMCFlow;
import pages.PaymentsDatailsPage;
import pages.TCSlogin;
import pages.TcsCommonPages;
import pages.UWRFlow;
import pages.offlineUploadAnx1Page;
import util.CustomAssert;
import util.ExcelDatabase;

public class StepDefination extends GSTNOnlinePortalPage {

	String dbConnectionSheetName = "DatabaseLogin";
	String puttyLoginSheetName = "PuttyLogin";

	ExcelDatabase excelDatabase = new ExcelDatabase();

	public StepDefination(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

//Anmol 11-06-2020  
	public void tcsLogin(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
			String stepGroup, CustomAssert customAssert) throws Exception {
		TCSlogin tcslogin = new TCSlogin(driver);
		tcslogin.fillAndSubmitNewTCSLogin(driver, testScenarioID, workbook, conn, stepGroup, customAssert);
	}

//Anmol 11-06-2020
	public void navigateToQuote(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
			String stepGroup, CustomAssert customAssert) throws Exception {
		TcsCommonPages tcsCommon = new TcsCommonPages(driver);
		tcsCommon.navigateToQuote(driver, testScenarioID, workbook, conn, stepGroup, customAssert);
	}

//Anmol 11-06-2020
	public void fillquickIndividualPricing(WebDriver driver, String testScenarioID, XSSFWorkbook workbook,
			Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		IndividualQuickPricingPage quickquote = new IndividualQuickPricingPage(driver);
		quickquote.fillQuote(driver, testScenarioID, workbook, conn, stepGroup, customAssert);
	}

	// Anmol 18-06-2020
	public void fillquickFamilyPricing(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
			String stepGroup, CustomAssert customAssert) throws Exception {
		FamilyFloaterQuickPricingPage quickquote = new FamilyFloaterQuickPricingPage(driver);
		quickquote.fillFamilyQuote(driver, testScenarioID, workbook, conn, stepGroup, customAssert);
	}

	
	
	// Anmol 26-06-2020
	public void fillquickMultiIndividualPricing(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
			String stepGroup, CustomAssert customAssert) throws Exception {
		MultiIndividualQuoteCreationPage quickquote = new MultiIndividualQuoteCreationPage(driver);
		quickquote.fillQuote(driver, testScenarioID, workbook, conn, stepGroup, customAssert);
	}	
	
	
	
	//Anmol 09-07-2020
		public void fillquickIndividualQuote(WebDriver driver, String testScenarioID, XSSFWorkbook workbook,
				Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
			IndividualQuoteCreationPage quickquote = new IndividualQuoteCreationPage(driver);
			quickquote.fillIndividualQuoteCreation(driver, testScenarioID, workbook, conn, stepGroup, customAssert);
		}
	
	
		//Anmol 09-07-2020
		public void fillquickFamilyQuoteCreation(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
				String stepGroup, CustomAssert customAssert) throws Exception {
			FamilyFloaterQuoteCreation quickquote = new FamilyFloaterQuoteCreation(driver);
			quickquote.fillFamilyQuote(driver, testScenarioID, workbook, conn, stepGroup, customAssert);
		}
	
	
		//Anmol 09-07-2020
		public void fillquickMultiIndividualQuoteCreation(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
				String stepGroup, CustomAssert customAssert) throws Exception {
			MultiIndividualQuoteCreationPage quickquote = new MultiIndividualQuoteCreationPage(driver);
			quickquote.fillQuote(driver, testScenarioID, workbook, conn, stepGroup, customAssert);
		}	
	
	
		// 25-07-2020
		public void fillIndividualQuoteCreationFinalize(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
				String stepGroup, CustomAssert customAssert) throws Exception {
			Finalize quickquote = new Finalize(driver);
			quickquote.fillFinalize(driver, testScenarioID, workbook, conn, stepGroup, customAssert);
		}	
	
		// 25-07-2020
		public void fillquickBasicDetails(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
				String stepGroup, CustomAssert customAssert) throws Exception {
			BasicDetails quickquote = new BasicDetails(driver);
			quickquote.BasicDetailsMehtod(driver, testScenarioID, workbook, conn, stepGroup, customAssert);
			
		}	
	
		// 25-07-2020
		public void fillquickCollection(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
				String stepGroup, CustomAssert customAssert) throws Exception {
			CollectionScreen quickquote = new CollectionScreen(driver);
			quickquote.FillCollection(driver, testScenarioID, workbook, conn, stepGroup, customAssert);
		}	
//	
		
		// 25-07-2020
		public void fillquickPayment(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
			String stepGroup, CustomAssert customAssert) throws Exception {
			PaymentsDatailsPage quickquote = new PaymentsDatailsPage(driver);
			quickquote.FillPaymentDetails(driver, testScenarioID, workbook, conn, stepGroup, customAssert);
		}	
		
		
		// 25-07-2020
		public void fillMemberInfo(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
			String stepGroup, CustomAssert customAssert) throws Exception {
			MemberInfoPage quickquote = new MemberInfoPage(driver);
			//OtherUserLogin other = new OtherUserLogin(driver);
			quickquote.fillMemberInfo(driver, testScenarioID, workbook, conn, stepGroup, customAssert);
			//other.fillAndSubmitNewTCSLogin(driver, testScenarioID, workbook, conn, stepGroup, customAssert);
		}	
		
		
		//06-08-2020
		
		public void fillOtherUserLogin(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
				String stepGroup, CustomAssert customAssert) throws Exception {
			OtherUserLogin other = new OtherUserLogin(driver);
		   other.fillOtherUserLogin(driver, testScenarioID, workbook, conn, stepGroup, customAssert);
			
		}

		
		//05-08-2020
		public void fillPPMCInfo(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
			String stepGroup, CustomAssert customAssert) throws Exception {
			PPMCFlow ppmcfl = new PPMCFlow(driver);
			ppmcfl.FillPPMCInfo(driver, testScenarioID, workbook, conn, stepGroup, customAssert);
		}	
	
	
	    //  06-08-2020
		
		public void fillUWRInfo(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
				String stepGroup, CustomAssert customAssert) throws Exception {
				UWRFlow uwrfl = new UWRFlow(driver);
				uwrfl.FillUWRInfo(driver, testScenarioID, workbook, conn, stepGroup, customAssert);
				
		}
		
		//11/08/2020
		
		public void DiscountLoading(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
				String stepGroup, CustomAssert customAssert) throws Exception {
			DiscountAndLoading DAL = new DiscountAndLoading(driver);
			DAL.fillDiscountLoading(driver, testScenarioID, workbook, conn, stepGroup, customAssert);
		
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	public void addNewReturnB2C(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
			String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.addReturnsB2C(driver, testScenarioID, workbook, conn, stepGroup);
	}

	public void addNewReturnB2B(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
			String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.addReturnsB2B(driver, testScenarioID, workbook, conn, stepGroup);
	}

	public void addNewReturn3C3D(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
			String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.addReturns3C3D(driver, testScenarioID, workbook, conn, stepGroup);
	}

	public void addNewReturn3E3F(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
			String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.addReturns3E3F(driver, testScenarioID, workbook, conn, stepGroup);
	}

	public void addNewReturn3H(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
			String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.addReturns3H(driver, testScenarioID, workbook, conn, stepGroup);
	}

	public void addNewReturn3I(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
			String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.addReturns3I(driver, testScenarioID, workbook, conn, stepGroup);
	}

	public void addNewReturn3J(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
			String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.addReturns3J(driver, testScenarioID, workbook, conn, stepGroup);
	}

	public void addNewReturn3K(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
			String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.addReturns3K(driver, testScenarioID, workbook, conn, stepGroup);
	}

	public void addNewReturn3L(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
			String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.addReturns3L(driver, testScenarioID, workbook, conn, stepGroup);
	}

	public void addNewReturn4(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
			String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.addReturns4(driver, testScenarioID, workbook, conn, stepGroup);
	}

	public void addHSNRecord(WebDriver driver, String testScenarioID, String GSTNID, XSSFWorkbook workbook,
			Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.addItemDetailsToHSNTable(driver, testScenarioID, GSTNID, workbook, conn, stepGroup);
	}

	public void addHSNRecord3C3D(WebDriver driver, String testScenarioID, String GSTNID, XSSFWorkbook workbook,
			Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.addItemDetailsToHSNTable3C3D(driver, testScenarioID, GSTNID, workbook, conn, stepGroup);
	}

	public void addHSNRecord3E3F(WebDriver driver, String testScenarioID, String GSTNID, XSSFWorkbook workbook,
			Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.addItemDetailsToHSNTable3E3F(driver, testScenarioID, GSTNID, workbook, conn, stepGroup);
	}

	public void verifyErrorMessage(WebDriver driver, String testScenarioID, String GSTNID, XSSFWorkbook workbook,
			Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.verifyErrorMessage(driver, testScenarioID, GSTNID, workbook, conn, stepGroup);
	}

	public void verifyErrorMessageB2B(WebDriver driver, String testScenarioID, String GSTNID, XSSFWorkbook workbook,
			Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.verifyErrorMessage(driver, testScenarioID, GSTNID, workbook, conn, stepGroup);
	}

	public void deleteAllDocuments(WebDriver driver, String testScenarioID, String GSTNID, XSSFWorkbook workbook,
			Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNDeleteReturns gstnDeleteReturns = new GSTNDeleteReturns(driver);
		gstnDeleteReturns.deleteAllDocuments(driver, testScenarioID, GSTNID, workbook, conn, stepGroup);
	}

	public void deleteSelectedDocuments(WebDriver driver, String testScenarioID, String GSTNID, XSSFWorkbook workbook,
			Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNDeleteReturns gstnDeleteReturns = new GSTNDeleteReturns(driver);
		gstnDeleteReturns.deleteSelectedDocument(driver, testScenarioID, GSTNID, workbook, conn, stepGroup);
	}

	public void deleteSelectedItems(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNDeleteReturns gstnDeleteReturns = new GSTNDeleteReturns(driver);
		gstnDeleteReturns.deleteSelectedItem(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void deleteSelectedItems3E3F(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNDeleteReturns gstnDeleteReturns = new GSTNDeleteReturns(driver);
		gstnDeleteReturns.deleteSelectedItem3E3F(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void editSelectedItems(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNEditReturns gstnEditReturns = new GSTNEditReturns(driver);
		gstnEditReturns.editSelectedItem(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void tableSummary(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.TableSummary(driver, testScenarioID, GSTNID, workbook, conn, stepGroup);
	}

	public void tableSummaryB2B(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.TableSummaryB2B(driver, testScenarioID, GSTNID, workbook, conn, stepGroup);
	}

	public void tableSummary3C3D(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.TableSummary3C3D(driver, testScenarioID, GSTNID, workbook, conn, stepGroup);
	}

	public void tableSummary3E3F(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.TableSummary3E3F(driver, testScenarioID, GSTNID, workbook, conn, stepGroup);
	}

	public void tableSummary3G(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.TableSummary3G(driver, testScenarioID, GSTNID, workbook, conn, stepGroup);
	}

	public void tableSummary3H(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.TableSummary3H(driver, testScenarioID, GSTNID, workbook, conn, stepGroup);
	}

	public void tableSummary3I(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.TableSummary3I(driver, testScenarioID, GSTNID, workbook, conn, stepGroup);
	}

	public void tableSummary3J(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.TableSummary3J(driver, testScenarioID, GSTNID, workbook, conn, stepGroup);
	}

	public void tableSummary3L(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.TableSummary3L(driver, testScenarioID, GSTNID, workbook, conn, stepGroup);
	}

	public void tableSummary4(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.TableSummary4(driver, testScenarioID, GSTNID, workbook, conn, stepGroup);
	}

	public void editDocumentB2B(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNEditReturns gstnEditReturns = new GSTNEditReturns(driver);
		gstnEditReturns.editDocumentsB2B(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void editDocument3C3D(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNEditReturns gstnEditReturns = new GSTNEditReturns(driver);
		gstnEditReturns.editDocuments3C3D(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void editDocument3E3F(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNEditReturns gstnEditReturns = new GSTNEditReturns(driver);
		gstnEditReturns.editDocuments3E3F(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void editDocument3G(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNEditReturns gstnEditReturns = new GSTNEditReturns(driver);
		gstnEditReturns.editDocuments3G(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void editDocument3H(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNEditReturns gstnEditReturns = new GSTNEditReturns(driver);
		gstnEditReturns.editDocuments3H(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void editDocument3I(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNEditReturns gstnEditReturns = new GSTNEditReturns(driver);
		gstnEditReturns.editDocuments3I(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void editDocument3J(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNEditReturns gstnEditReturns = new GSTNEditReturns(driver);
		gstnEditReturns.editDocuments3J(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void editDocument3K(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNEditReturns gstnEditReturns = new GSTNEditReturns(driver);
		gstnEditReturns.editDocuments3K(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void editDocument3L(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNEditReturns gstnEditReturns = new GSTNEditReturns(driver);
		gstnEditReturns.editDocuments3L(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void editDocument4(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNEditReturns gstnEditReturns = new GSTNEditReturns(driver);
		gstnEditReturns.editDocuments4(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void editMainDocument3A(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNEditReturns gstnEditReturns = new GSTNEditReturns(driver);
		gstnEditReturns.editMainRecords3A(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void editMainDocument3B(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNEditReturns gstnEditReturns = new GSTNEditReturns(driver);
		gstnEditReturns.editMainRecords3B(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void editMainDocument3C3D(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNEditReturns gstnEditReturns = new GSTNEditReturns(driver);
		gstnEditReturns.editMainRecords3C3D(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void editMainDocument3E3F(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNEditReturns gstnEditReturns = new GSTNEditReturns(driver);
		gstnEditReturns.editMainRecords3E3F(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void editMainDocument3G(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNEditReturns gstnEditReturns = new GSTNEditReturns(driver);
		gstnEditReturns.editMainRecords3G(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void editMainDocument3H(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNEditReturns gstnEditReturns = new GSTNEditReturns(driver);
		gstnEditReturns.editMainRecords3H(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void editMainDocument3I(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNEditReturns gstnEditReturns = new GSTNEditReturns(driver);
		gstnEditReturns.editMainRecords3I(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void editMainDocument3J(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNEditReturns gstnEditReturns = new GSTNEditReturns(driver);
		gstnEditReturns.editMainRecords3J(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void editMainDocument3K(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNEditReturns gstnEditReturns = new GSTNEditReturns(driver);
		gstnEditReturns.editMainRecords3K(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void editMainDocument3L(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNEditReturns gstnEditReturns = new GSTNEditReturns(driver);
		gstnEditReturns.editMainRecords3L(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void editMainDocument4(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNEditReturns gstnEditReturns = new GSTNEditReturns(driver);
		gstnEditReturns.editMainRecords4(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void deleteSelectedDocumentsB2B(WebDriver driver, String testScenarioID, String GSTNID,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNDeleteReturns gstnDeleteReturns = new GSTNDeleteReturns(driver);
		gstnDeleteReturns.deleteSelectedDocumentB2B(driver, testScenarioID, GSTNID, workbook, conn, stepGroup);
	}

	public void deleteSelectedDocuments3C3D(WebDriver driver, String testScenarioID, String GSTNID,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNDeleteReturns gstnDeleteReturns = new GSTNDeleteReturns(driver);
		gstnDeleteReturns.deleteSelectedDocument3C3D(driver, testScenarioID, GSTNID, workbook, conn, stepGroup);
	}

	public void deleteSelectedDocuments3E3F(WebDriver driver, String testScenarioID, String GSTNID,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNDeleteReturns gstnDeleteReturns = new GSTNDeleteReturns(driver);
		gstnDeleteReturns.deleteSelectedDocument3E3F(driver, testScenarioID, GSTNID, stepGroup, workbook, conn,
				stepGroup);
	}

	public void deleteSelectedDocuments3G(WebDriver driver, String testScenarioID, String GSTNID, XSSFWorkbook workbook,
			Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNDeleteReturns gstnDeleteReturns = new GSTNDeleteReturns(driver);
		gstnDeleteReturns.deleteSelectedDocument3G(driver, testScenarioID, GSTNID, stepGroup, workbook, conn,
				stepGroup);
	}

	public void deleteSelectedDocuments3H(WebDriver driver, String testScenarioID, String GSTNID, XSSFWorkbook workbook,
			Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNDeleteReturns gstnDeleteReturns = new GSTNDeleteReturns(driver);
		gstnDeleteReturns.deleteSelectedDocument3H(driver, testScenarioID, GSTNID, stepGroup, workbook, conn,
				stepGroup);
	}

	public void deleteSelectedDocuments3I(WebDriver driver, String testScenarioID, String GSTNID, XSSFWorkbook workbook,
			Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNDeleteReturns gstnDeleteReturns = new GSTNDeleteReturns(driver);
		gstnDeleteReturns.deleteSelectedDocument3I(driver, testScenarioID, GSTNID, stepGroup, workbook, conn,
				stepGroup);
	}

	public void deleteSelectedDocuments3J(WebDriver driver, String testScenarioID, String GSTNID, XSSFWorkbook workbook,
			Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNDeleteReturns gstnDeleteReturns = new GSTNDeleteReturns(driver);
		gstnDeleteReturns.deleteSelectedDocument3J(driver, testScenarioID, GSTNID, stepGroup, workbook, conn,
				stepGroup);
	}

	public void deleteSelectedDocuments3K(WebDriver driver, String testScenarioID, String GSTNID, XSSFWorkbook workbook,
			Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNDeleteReturns gstnDeleteReturns = new GSTNDeleteReturns(driver);
		gstnDeleteReturns.deleteSelectedDocument3K(driver, testScenarioID, GSTNID, stepGroup, workbook, conn,
				stepGroup);
	}

	public void deleteSelectedDocuments3L(WebDriver driver, String testScenarioID, String GSTNID, XSSFWorkbook workbook,
			Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNDeleteReturns gstnDeleteReturns = new GSTNDeleteReturns(driver);
		gstnDeleteReturns.deleteSelectedDocument3L(driver, testScenarioID, GSTNID, stepGroup, workbook, conn,
				stepGroup);
	}

	public void deleteSelectedDocuments4(WebDriver driver, String testScenarioID, String GSTNID, XSSFWorkbook workbook,
			Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNDeleteReturns gstnDeleteReturns = new GSTNDeleteReturns(driver);
		gstnDeleteReturns.deleteSelectedDocument4(driver, testScenarioID, GSTNID, stepGroup, workbook, conn, stepGroup);
	}

	public void deleteSelectedItemsB2B(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNDeleteReturns gstnDeleteReturns = new GSTNDeleteReturns(driver);
		gstnDeleteReturns.deleteSelectedItemB2B(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void deleteSelectedItems3C3D(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNDeleteReturns gstnDeleteReturns = new GSTNDeleteReturns(driver);
		gstnDeleteReturns.deleteSelectedItem3C3D(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void deleteSelectedItems3G(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNDeleteReturns gstnDeleteReturns = new GSTNDeleteReturns(driver);
		gstnDeleteReturns.deleteSelectedItem3G(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void deleteSelectedItems3H(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNDeleteReturns gstnDeleteReturns = new GSTNDeleteReturns(driver);
		gstnDeleteReturns.deleteSelectedItem3H(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void deleteSelectedItems3I(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNDeleteReturns gstnDeleteReturns = new GSTNDeleteReturns(driver);
		gstnDeleteReturns.deleteSelectedItem3I(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void deleteSelectedItems3J(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNDeleteReturns gstnDeleteReturns = new GSTNDeleteReturns(driver);
		gstnDeleteReturns.deleteSelectedItem3J(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void deleteSelectedItems3K(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNDeleteReturns gstnDeleteReturns = new GSTNDeleteReturns(driver);
		gstnDeleteReturns.deleteSelectedItem3K(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void deleteSelectedItems3L(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNDeleteReturns gstnDeleteReturns = new GSTNDeleteReturns(driver);
		gstnDeleteReturns.deleteSelectedItem3L(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void documentWiseSummaryB2B(WebDriver driver, String testScenarioID, String GSTNID, XSSFWorkbook workbook,
			Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.documentWiseSummaryB2B(driver, testScenarioID, GSTNID, workbook, conn, stepGroup);
	}

	/*
	 * public void documentWiseSummary3C3D(WebDriver driver,String
	 * testScenarioID,String GSTNID,XSSFWorkbook workbook,Connection conn,String
	 * stepGroup,CustomAssert customAssert) throws Exception { GSTNAddReturns
	 * gstnAddReturns=new GSTNAddReturns(driver);
	 * gstnAddReturns.documentWiseSummary3C3D(driver, testScenarioID, GSTNID,
	 * workbook, conn, stepGroup); }
	 */
	public void applyFilterB2B(WebDriver driver, String testScenarioID, String GSTNID, XSSFWorkbook workbook,
			Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.applySelectAllFilterB2B(driver, testScenarioID, GSTNID, workbook, conn, stepGroup);
	}

	public void generateJSONFileToUpload(WebDriver driver, String testScenarioID, String GSTNID, XSSFWorkbook workbook,
			Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAOSCommonPage gstnAddReturns = new GSTNAOSCommonPage(driver);
		gstnAddReturns.generateJSONFile(driver, testScenarioID, GSTNID, workbook, conn, stepGroup);
	}

	public void openNewBrowserTab(WebDriver driver, String testScenarioID, String GSTNID, XSSFWorkbook workbook,
			Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNOnlineLoginPage gstnOnlinePortal = new GSTNOnlineLoginPage(driver);
		gstnOnlinePortal.openNewBrowserTab(driver);
	}

	public void loginToOnlinePortal(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNOnlineLoginPage gstnOnlinePortal = new GSTNOnlineLoginPage(driver);
		gstnOnlinePortal.loginToOnlinePortal(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void newReturnOnline(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		navigateToOnlineNewReturnType(driver);
		GSTNOnlineNewReturndashBoardPage gstnOnlineNewReturndashBoardPage = new GSTNOnlineNewReturndashBoardPage(
				driver);
		gstnOnlineNewReturndashBoardPage.fillNewReturnAndProceedToNext(driver, testScenarioID, GSTNID, ReturnType,
				workbook, conn, stepGroup);
		offlineUploadAnx1Page offlineUploadAnx1Page = new offlineUploadAnx1Page(driver);
		offlineUploadAnx1Page.uploadAnx1Invoice(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);

	}

	public void importExcelCSVFile(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAOSCommonPage gstnaosCommonPage = new GSTNAOSCommonPage(driver);
		gstnaosCommonPage.importExcelCSV(driver, testScenarioID, workbook, conn, stepGroup);
	}

	public void addNewReturn3G(WebDriver driver, String testScenarioID, XSSFWorkbook workbook, Connection conn,
			String stepGroup, CustomAssert customAssert) throws Exception {
		GSTNAddReturns gstnAddReturns = new GSTNAddReturns(driver);
		gstnAddReturns.addReturns3G(driver, testScenarioID, workbook, conn, stepGroup);
	}

	public void newReturnTrial(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup, CustomAssert customAssert) throws Exception {
		navigateToNewReturnTrial(driver);
		GSTNOnlineNewReturndashBoardPage gstnOnlineNewReturndashBoardPage = new GSTNOnlineNewReturndashBoardPage(
				driver);
		gstnOnlineNewReturndashBoardPage.fillNewReturnAndProceedToNext(driver, testScenarioID, GSTNID, ReturnType,
				workbook, conn, stepGroup);
	}

	public void OpenDownloadedJSONFileAnx1(WebDriver driver, String testScenarioID, XSSFWorkbook workbook,
			Connection conn, String stepGroup) throws Exception {
		GSTNDashBoard gstnDashBoard = new GSTNDashBoard(driver);
		gstnDashBoard.openDownloadedJSONFile(driver, testScenarioID, workbook, conn, stepGroup);
	}

	public void markForDelete(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup) throws Exception {
		GSTNDeleteReturns gstnDashBoard = new GSTNDeleteReturns(driver);
		gstnDashBoard.markForDelete(driver, testScenarioID, GSTNID, ReturnType, workbook, conn, stepGroup);
	}

	public void selectReturnType(WebDriver driver, String testScenarioID, String GSTNID, String ReturnType,
			XSSFWorkbook workbook, Connection conn, String stepGroup) throws Exception {
		GSTNDashBoard gstnDashBoard = new GSTNDashBoard(driver);
		gstnDashBoard.selectReturnType(driver, testScenarioID, workbook, conn, stepGroup);
	}

}