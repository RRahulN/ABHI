package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ExcelDatabase {
	
	static FileInputStream readFile;
	public static HashSet<String> sheetsname=new HashSet<String>();
	static Fillo fillo=new Fillo();
	public static Connection conn;
	public static Connection Connection_excel(String workbookname) throws FilloException {
		conn=fillo.getConnection(workbookname);
		return conn;
	}

	//TODO Amiya 10-10-2019
	public String selectQueryForData(Connection conn, String sheetName, String columnname, String testCaseName,String childkey ) throws FilloException {

		String result=null;
		String strQuery="Select "+columnname+" from "+sheetName+" where TCID='"+testCaseName+"' and ReferenceKey='"+childkey+"'";
		Recordset record=conn.executeQuery(strQuery);		
		while(record.next()) {
			result=record.getField(columnname);
		}
		record.close();
		return result;
	}
	
	//TODO Amiya 10-10-2019
	public Recordset selectQueryForHSNData(Connection conn, String sheetName, String columnname, String testCaseName,String GSTNID ) throws FilloException {
		Recordset record = null;
		try {
			//String strQuery="Select "+columnname+" from "+sheetName+" where TCID='"+testCaseName+"' and GSTIN='"+GSTNID+"'";
			String strQuery="Select * from "+sheetName+" where TCID='"+testCaseName+"' and GSTIN='"+GSTNID+"'";
			record=conn.executeQuery(strQuery);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return record;
		}
		
	//TODO Amiya 10-10-2019
	public Recordset selectQueryForHSNTable(Connection conn, String sheetName, String testCaseName,String columnName,String columnValue ) throws FilloException {
		//String strQuery="Select "+columnname+" from "+sheetName+" where TCID='"+testCaseName+"' and GSTIN='"+GSTNID+"'";
		String strQuery="Select * from "+sheetName+" where TCID='"+testCaseName+"' and "+columnName+"='"+columnValue+"'";
		Recordset record=conn.executeQuery(strQuery);
		return record;
		}


	public void updateQueryForData( Connection conn, String sheetName, String columnname, String testCaseName,String stepGroup, String value) throws FilloException {
		/*String filePath=FrameworkServices.configProp.getProperty("TestDataFolder")+"\\0002_TestData\\Traditional\\1J\\1JTestData.xlsx";
		Fillo fillo=new Fillo();*/

		String strQuery="Update "+sheetName+" set "+columnname+"='"+value+"'  where TCID='"+testCaseName+"' and GroupName='"+stepGroup+"'";
		conn.executeUpdate(strQuery);
		sheetsname.add(sheetName);
	}
	public void updateQueryForHSNData( Connection conn, String sheetName, String columnname,String value, String testCaseName,String taxRate) throws FilloException {
		String strQuery="Update "+sheetName+" set "+columnname+"='"+value+"'  where TCID='"+testCaseName+"' and TaxRate='"+taxRate+"'";
		conn.executeUpdate(strQuery);
		sheetsname.add(sheetName);
	}
	
	public void updateCalculatedData( Connection conn, String sheetName, String columnname,String value, String testCaseName) throws FilloException {
		String strQuery="Update "+sheetName+" set "+columnname+"='"+value+"'  where TCID='"+testCaseName+"'";
		conn.executeUpdate(strQuery);
		sheetsname.add(sheetName);
	}


	public void insert(Connection conn,String sheetName,String columnname,String value,String testcaseName,String GSTNID ) throws FilloException {

		try {
		String strQuery="Insert into "+sheetName+"("+columnname+") values('"+value+"') where TCID='"+testcaseName+"' and GSTIN='"+GSTNID+"'";
		conn.executeUpdate(strQuery);
		sheetsname.add(sheetName);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void close_connection(Connection conn) {
		conn.close();
	}

	public void updateStatus( Connection conn, String sheetName, String columnname, String testCaseName) throws FilloException {
		String strQuery="Update "+sheetName+" set "+columnname+"='Failed'  where TCID='"+testCaseName;
		conn.executeUpdate(strQuery);
	}
	
	
	
	//TODO Amiya 10-10-2019
	public static void updateBorders(String filepath) throws IOException {	
		try{	
			FileInputStream readFile=new FileInputStream(new File(filepath));
			@SuppressWarnings("resource")
			XSSFWorkbook xssfWorkbook=new XSSFWorkbook(readFile);
			if(xssfWorkbook.getNumberOfSheets()!=0){
				for(int i=0;i<xssfWorkbook.getNumberOfSheets();i++){		
					for(String sheetName: sheetsname){
						if(xssfWorkbook.getSheetName(i).equals(sheetName)){
							XSSFSheet xssfSheet=xssfWorkbook.getSheet(sheetName);
							int numberOfRows=xssfSheet.getLastRowNum();
							for(int j=1;j<=numberOfRows;j++) {
								Row rowData=xssfSheet.getRow(j);
								for (Cell cell : rowData) {				
									XSSFCellStyle style=xssfWorkbook.createCellStyle();
									style.setBorderTop(BorderStyle.THIN);
									style.setBorderBottom(BorderStyle.THIN);
									style.setBorderLeft(BorderStyle.THIN);
									style.setBorderRight(BorderStyle.THIN);
									cell.setCellStyle(style);
								}
							}
						}

					}
				}
			}
			
			else{
				Reporter.log("Sheet not Found");

			}
			FileOutputStream fileOutputStream=new FileOutputStream(new File(filepath));
			xssfWorkbook.write(fileOutputStream);
			fileOutputStream.close();
		}
		catch(Exception e){
			Reporter.log(e.toString());
		}
		  finally{
			  readFile.close();
			  } 
	}
	
	//TODO Amiya 10-10-2019
	public void updateCalculationSheet( Connection conn, String sheetName, String columnname, String value) throws FilloException {
		String strQuery="Update "+sheetName+" set "+columnname+"='"+value+"'";
		conn.executeUpdate(strQuery);
	}
}