package pages;

import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.codoid.products.fillo.Connection;

import util.CustomAssert;
import util.ExcelRead;
import util.GenericMethods;
import util.WaitTime;


public class TcsCommonPages extends GenericMethods {
	
	
	@FindBy(xpath="(//p[contains(text(),'Policy Management')])[1]")
	private WebElement policymanagement;
	
	@FindBy(xpath="//a[contains(text(),'Quick Quote')]")
	private WebElement quickquote;
	
	
	
    WebDriverWait wait;
	public TcsCommonPages(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 30);	
	}
	
	public void navigateToQuote(WebDriver driver,String testCaseName, XSSFWorkbook workbook,Connection conn,String stepGroup,CustomAssert customAssert) throws Exception
	{

		Reporter.log("<B>Traverse To CommonPage</B>");

		switchtoframe(driver, "display");
		Thread.sleep(3000);
		click(policymanagement, "Policy Management");
		Thread.sleep(WaitTime.low);
		switchtodefaultframe(driver);
		
		switchtoframe(driver, "head");
		click(quickquote, "Quick Quote");
		Thread.sleep(WaitTime.low);		
		switchtodefaultframe(driver);
		
		
}
}