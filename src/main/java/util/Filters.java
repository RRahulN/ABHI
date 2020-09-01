package util;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Filters{
	
	
	
	public  List<String> getAllDocumentFilters(){
		List<String> filters =Arrays.asList("Trade/Legal name","Recipient type","Differential % of tax rate","Supply covered under sec 7 of IGST Act","Supply type","Date of upload","Status");
		return filters;
	}
	
	public static String[] getDocumentTypeFilters(){
		String[] filters = {"Credit Note","Debit Note","Invoice"};
		return filters;
	}

}
