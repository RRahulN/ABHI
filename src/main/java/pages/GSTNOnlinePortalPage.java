package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import util.GenericMethods;
import util.WaitTime;

public class GSTNOnlinePortalPage extends GSTNDashBoard {

	@FindBy(xpath="//a[@class='dropdown-toggle'][contains(text(),'Services')]")
	private WebElement Services;
	
	@FindBy(xpath="//a[text()='Returns']")
	private WebElement Returns;
	
	@FindBy(xpath="//a[text()='New Return (Trial)']")
	private WebElement NewReturn;
	
	@FindBy(xpath="//span[text()='New Return (Trial)']")
	private WebElement NewReturnTrial;
	
	
	
	WebDriverWait wait;
	
	public GSTNOnlinePortalPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 30);	// TODO Auto-generated constructor stub
	}

	public void navigateToOnlineNewReturnType(WebDriver driver) throws Exception
	{
		wait.until(ExpectedConditions.visibilityOf(Services));
		Reporter.log("<B>Click on Services</B>");
		if(isDisplayed(Services))
		{
			Thread.sleep(WaitTime.low);
			click(Services, "Services");
			Thread.sleep(WaitTime.low);
			mouseHover(driver, Returns,"Returns");
			Thread.sleep(WaitTime.low);
			click(NewReturn, "New Return (Trial)");
		}
		else
		{
			System.out.println();
		}
	}
	public void navigateToNewReturnTrial(WebDriver driver) throws Exception
	{
		wait.until(ExpectedConditions.visibilityOf(NewReturnTrial));
		Reporter.log("<B>Click on New Return Trial</B>");
		if(isDisplayed(NewReturnTrial))
		{
			Thread.sleep(WaitTime.low);
			click(NewReturnTrial, "Services");

		}
		else
		{
			System.out.println("New Return Trial not available");
		}
	}
}
