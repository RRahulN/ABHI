package util;

import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import org.testng.Reporter;

import com.codoid.products.fillo.Connection;


public class SikuliScript{
	
	
	public void sikuliSelectFileToUpload(Screen screen,String FileType, String filePath) throws FindFailed, InterruptedException, IOException {
		switch (FileType) {
		case "Excel":
			screen.wait(System.getProperty("user.dir") + "\\sikuliImage\\ImportExcel.png",30);
			screen.click(System.getProperty("user.dir") + "\\sikuliImage\\ImportExcel.png");
			break;
        case "CSV":
        	screen.wait(System.getProperty("user.dir") + "\\sikuliImage\\ImpoerCSV.png",30);
    		screen.click(System.getProperty("user.dir") + "\\sikuliImage\\ImpoerCSV.png");
			break;
		default:
			Reporter.log("Open Downloaded JSON file");
			break;
		}
		
		screen.wait(System.getProperty("user.dir") + "\\sikuliImage\\FileName.png",30);
		screen.type(filePath);
		screen.wait(System.getProperty("user.dir") + "\\sikuliImage\\Open.png",500);
		screen.click(System.getProperty("user.dir") + "\\sikuliImage\\Open.png");
		Reporter.log("<B>"+filePath+"</B> file is selected for upload");
	}


}
