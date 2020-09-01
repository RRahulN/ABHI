package testRunner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.testng.ITestResult;
import org.testng.ITestContext;		
import org.testng.ITestListener;		
import org.testng.TestNG;
import org.testng.xml.XmlSuite;
import core.FrameworkServices;
import core.TestSuiteEngine;
import core.TestSuiteGenerator;

/*
 * 
 * Author AMIYA
 *
 * 
 */
public class TestEngine {
	public static String excutionFolder="";
	public static Process process=null;
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException {
		try {
			//TODO Amiya added 30/11/2018
			ZipSecureFile.setMinInflateRatio(0);
			FrameworkServices frameworkServices=new FrameworkServices();
			List<TestSuiteGenerator> testSuiteGenerators=frameworkServices.getTestSuiteForExecution();
			TestNG testNG=new TestNG();
			ITestResult result = null ;
			java.util.Date date=new java.util.Date();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MMM-yyyy__hh-mm-ss");
			String folderDate=simpleDateFormat.format(date);
			excutionFolder="D:\\2020-20200629T052145Z-001\\ABFL-25-06-2020\\TCS-framework\\FrameWork_25\\TCS_Execution_Repo\\"+folderDate+"_PricingPlat_Fam_Essen";
			
			testNG.setOutputDirectory(excutionFolder);		
			List<XmlSuite> suiteList=new ArrayList<>();
			suiteList=	new TestSuiteEngine().executeTestSuiteGenerator(frameworkServices, suiteList);
			
			for(XmlSuite xml:suiteList) {
				 FileWriter writer; 
			       try { 
			            writer = new FileWriter(new File("testNG.xml")); 
			            writer.write(xml.toXml()); 
			            writer.flush(); 
			            writer.close(); 
			            //System.out.println(new File("testNG.xml").getAbsolutePath());           
			            } catch (IOException e)
			            {
			              e.printStackTrace(); 
			            }
			    
			}
			
			testNG.setXmlSuites(suiteList);
			testNG.run();
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		finally{
			Runtime rt=Runtime.getRuntime();
			rt.exec("taskkill /IM chromedriver.exe /F");
		}
	}
}