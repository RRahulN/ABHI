package util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import constants.PropertyConfigs;
import testRunner.TestEngine;


public class CustomAssert extends GenericMethods {
	WebDriver driver=null;
	public static boolean executionFlag=true;
	public CustomAssert(WebDriver driver) {
		super(driver);
		this.driver=driver;
		// TODO Auto-generated constructor stub
	}
	public  int counter=1;
	public void verifyAssert(int expected,int actual, String message) throws Exception {
		try {
			Assert.assertEquals(expected, actual);
			Reporter.log("<B><Font color=\"Yellow\">"+counter+".   "+message+"       => PASSED</Font></B>");
			Reporter.log("<B>Expected =  "+expected+"</Font></B>");
			Reporter.log("<B>Actual =  "+actual+"</Font></B>");
		}catch(AssertionError assertionError){
			executionFlag=false;
			Reporter.log("<B><Font color=\"Yellow\">"+counter+".   "+message+"       => FAILED</Font></B>");
			Reporter.log("<B>Expected =  "+expected+"</Font></B>");
			Reporter.log("<B>Actual =  "+actual+"</Font></B>");
			SetUpWebdriver.captureScreenShot(driver, TestEngine.excutionFolder+ConfigReader.getInstance().getValue(PropertyConfigs.screenShotFolder), new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date()) );
		}finally {
			counter++;
		}
	}
	public void verifyAssert(Boolean expected, Boolean actual, String message) throws Exception {
		try {
			Assert.assertEquals(expected, actual);
			Reporter.log("<B><Font color=\"Yellow\">"+counter+".   "+message+"       => PASSED</Font></B>");
			Reporter.log("<B>Expected =  "+expected+"</Font></B>");
			Reporter.log("<B>Actual =  "+actual+"</Font></B>");
		}catch(AssertionError assertionError){
			executionFlag=false;
			Reporter.log("<B><Font color=\"Yellow\">"+counter+".   "+message+"       => FAILED</Font></B>");
			Reporter.log("<B>Expected =  "+expected+"</Font></B>");
			Reporter.log("<B>Actual =  "+actual+"</Font></B>");
			SetUpWebdriver.captureScreenShot(driver, TestEngine.excutionFolder+ConfigReader.getInstance().getValue(PropertyConfigs.screenShotFolder), new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date()) );
		}finally {
			counter++;
		}
	}
}
