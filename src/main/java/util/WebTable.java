package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class WebTable extends GenericMethods{
	
	
	
  WebDriver driver;
  WebDriverWait wait;

public WebTable(WebDriver driver) {
	 super(driver);
	 this.driver=driver;
	 PageFactory.initElements(driver, this);
	 wait=new WebDriverWait(driver, 700);
}


 public WebElement selectFromTable(WebElement element ,By rowElement,By colElement,String value1,String value2,By button) {
	 try
	 {
	 WebElement element2=null;
	 List<WebElement> rows=element.findElements(rowElement);
	 for(WebElement row:rows) {
		 List<WebElement> cols=row.findElements(colElement);
		 for(WebElement col:cols) {
			 if(row.getText().equals(value1)&&col.getText().equals(value2)) {
				 
					element2=col.findElement(button);

			 }
		 }
	 }
	return element2;
 }catch(NoSuchElementException e) {
	 System.out.println(e);
 }
	return null;
 }
 
 public List<WebElement> selectFromTables(WebElement element ,By rowElement,By colElement,String value1,String value2,By button) {
	 try
	 {
	 List<WebElement> element2 = null;
	 List<WebElement> rows=element.findElements(rowElement);
	Outer: for(WebElement row:rows) {
		 List<WebElement> cols=row.findElements(colElement);
		 for(WebElement col:cols) {
			 if(row.getText().trim().equals(value1)&&col.getText().trim().equals(value2)) {
				 
					element2=col.findElements(button);
					Reporter.log("Edit document of Total Taxable value is (₹) <B> '" + value1 + "'</B> and Total Integrated Tax is <B> '" + value2 + "'</B> from document grid");
					break Outer;
			 }
			 else {				 
				    /* try {
				    	 
						throw new CustomException(row.getText()+"Does not match with"+value1+ "or"+col.getText()+"Does not match with"+value2+" Hence throwing Exception");
					
					} catch (CustomException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();					
				    }*/
			 }
		 }
	 }
	
	return element2;
 }catch(NoSuchElementException e) {
	 System.out.println(e);
 }
	return null;
 }
 //Duplicate Method with different Return tYpe
 public WebElement selectFromItems(WebElement element ,By colElement,By colElement1,String value1,String value2,By button) {
	 try
	 {
	 WebElement element2=null;
			List<WebElement> rows = element.findElements(colElement);
			Boolean value=true;
			Outer:for (WebElement row : rows) {
				if (row.getAttribute("value").equals(value1) && value) {
					List<WebElement> cols = row.findElements(colElement1);
					for (WebElement col : cols) {
						if (col.getAttribute("value").equals(value2.replaceAll(",",""))) {

							element2 = col.findElement(button);
							value=false;
							break Outer;
						}
					}
				}
			}
			return element2;
		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
		return null;
	}
 
 public WebElement selectFromItems(WebElement element ,By colElement,By colElement1,By colElements2,String value1,String value2,String value3,By button) {
	 try
	 {
	 WebElement element2=null;
			List<WebElement> rows = element.findElements(colElement);
			Boolean value=true;
			Outer:for (WebElement row : rows) {
				if (row.getAttribute("value").equals(value1) && value) {
					List<WebElement> cols = row.findElements(colElement1);
					for (WebElement col : cols) {
						if (col.getAttribute("value").equals(value2)) {
							List<WebElement> cols1 = col.findElements(colElement1);
							  for (WebElement col1 : cols1) {
								    if (col1.getAttribute("value").equals(value2)) {
							element2 = col1.findElement(button);
							value=false;
							break Outer;
								}
							}
						}
					}
				}
			}
			return element2;
		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
		return null;
	}
 
 public Map<String,String> editFromItems(WebElement element ,By colElement,By colElement1,String value1,String value2,WebElement dropdown,String Taxrate,WebElement Taxable_Value,String TaxableValue) {
	 try
	 {
		 
			List<WebElement> rows = element.findElements(colElement);
			Map<String, String> setValue=new HashMap<String,String>();
			Outer:for (WebElement row : rows) {
				if (row.getAttribute("value").concat("%").equals(value1)) {
					selectFromDropdownByVisibleText(dropdown, Taxrate, "Tax rate");
					//setValue.put(Taxrate, "");
					List<WebElement> cols = row.findElements(colElement1);
					for (WebElement col : cols) {
						if (col.getAttribute("value").equals(value2)) {
							clearAndSenKeys(Taxable_Value, TaxableValue, "Taxable value");
							setValue.put(Taxrate,TaxableValue);
							break Outer;
						}
					}
				}
			}
			return setValue;
		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
		return null;
	}
 public List<String> getTableColumns(WebElement Table){
		try
		{
	    List<String> columns=new ArrayList<String>();	
	    Reporter.log("<B> "+"Document grid Table is selected" +"</B>");
	    highlighter(Table);
		List<WebElement> Rows=Table.findElements(By.tagName("tr"));
			for(WebElement row:Rows) {
				List<WebElement> Cols=row.findElements(By.tagName("th"));
				for(WebElement col:Cols) {
					highlighter(col);
					Reporter.log("<B>"+ col.getText() + "</B>Column is present in Document Grid");
					columns.add(col.getText());
				}
			}
	     return columns;
		}
		catch (Exception e) {
			return null;// TODO: handle exception
		}		
	}
 
 public List<String> getLatestUploadHistory(WebElement Table){
		try
		{
	    List<String> columns=new ArrayList<String>();	
	    Reporter.log("<B> "+"Document grid Table is selected" +"</B>");
	    highlighter(Table);
		List<WebElement> Rows=Table.findElements(By.xpath("//tr[1]"));
			for(WebElement row:Rows) {
				List<WebElement> Cols=row.findElements(By.xpath(".//td"));
				System.out.println(Cols.size());
				for(WebElement col:Cols) {
					highlighter(col);
					Reporter.log("<B>"+ col.getText() + "</B>Column is present in Document Grid");
					columns.add(col.getText());
				}
			}
	     return columns;
		}
		catch (Exception e) {
			return null;// TODO: handle exception
		}		
	}

}
