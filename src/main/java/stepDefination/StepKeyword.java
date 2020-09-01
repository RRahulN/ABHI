package stepDefination;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import com.codoid.products.fillo.Connection;

import util.CustomAssert;

public class StepKeyword extends StepDefination {
	public StepKeyword(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void executeTestStep(WebDriver driver,String testScenarioID, String step,String stepGroup,String GSTNID,String ReturnType,XSSFWorkbook workbook,Connection conn,CustomAssert customAssert) throws Exception {
		switch (step){
/*
  Anmol 11-06-2020 For creating and executing TCS Keyword 
 */
		case "Login_Into_NewTCS":
			tcsLogin(driver, testScenarioID, workbook, conn,stepGroup,customAssert);
			break;
		case "Navigate_TO_Quote":
			navigateToQuote(driver, testScenarioID, workbook, conn,stepGroup,customAssert);
			break;
		case "Create_Individual_Pricing":
			fillquickIndividualPricing(driver, testScenarioID, workbook, conn,stepGroup,customAssert);
			break;			
		case "Create_FamilyFloater_Pricing":
			fillquickFamilyPricing(driver, testScenarioID, workbook, conn,stepGroup,customAssert);
			break;
		case "Create_MultiIndividual_Pricing":
			fillquickMultiIndividualPricing(driver, testScenarioID, workbook, conn,stepGroup,customAssert);
			break;
		case "Create_Individual_Quote_Creation":
			fillquickIndividualQuote(driver, testScenarioID, workbook, conn,stepGroup,customAssert);
			break;		
		case "Create_FamilyFloater_Quote_Creation":
			fillquickFamilyQuoteCreation(driver, testScenarioID, workbook, conn,stepGroup,customAssert);
			break;
		case "Create_MultiIndividual_Quote_Creation":
			fillquickMultiIndividualQuoteCreation(driver, testScenarioID, workbook, conn,stepGroup,customAssert);
			break;
			
			
		case "Create_Individual_Finalize":
			fillIndividualQuoteCreationFinalize(driver, testScenarioID, workbook, conn,stepGroup,customAssert);
			break;	
			
		case "Create_Individual_BasicDetails":
			fillquickBasicDetails(driver, testScenarioID, workbook, conn,stepGroup,customAssert);
			break;		
			
			
		case "Create_Individual_Collection":
		    fillquickCollection(driver, testScenarioID, workbook, conn,stepGroup,customAssert);
		break;		
		
		case "Create_Individual_Payment":
			fillquickPayment(driver, testScenarioID, workbook, conn,stepGroup,customAssert);
			break;
			
			
		case "Create_Individual_MemberInfo":
			fillMemberInfo(driver, testScenarioID, workbook, conn,stepGroup,customAssert);
			break;	
			
	
		case "Create_Individual_PPMC":
			fillPPMCInfo(driver, testScenarioID, workbook, conn,stepGroup,customAssert);
			break;	
			
			
		case "Create_Individual_UWR":
			fillUWRInfo(driver, testScenarioID, workbook, conn,stepGroup,customAssert);
	        break;
	        
	        
		case "Create_Individual_OtherUserLogin":
			fillOtherUserLogin(driver, testScenarioID, workbook, conn,stepGroup,customAssert);
			break;
	        
		case "Create_Discount_And_Loading":
			DiscountLoading(driver, testScenarioID, workbook, conn,stepGroup,customAssert);
			break;
			
			
			
	}
}

	

	
	
}

		


	
		
	