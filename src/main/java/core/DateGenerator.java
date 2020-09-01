package core;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateGenerator {

	public static String dateGenerator(String dataFromExcel,String dataForComparision) throws ParseException,InterruptedException{  
		/*WebDriver driver = null;
		IngeniumLoginPage objinlogin= new IngeniumLoginPage(driver);
		String sDate1=objinlogin.processingDate(driver);*/
		String exp="";    
		if(dataForComparision.contains("-")) {
			dataForComparision=dataForComparision.replaceAll("-", "/");
			
			//System.out.println(newDate);
		}
		Date date=new SimpleDateFormat("dd/MM/yyyy").parse(dataForComparision);  
		//System.out.println(sDate1+"\t"+date1); 

		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		cal.setTime(date);
		if(dataFromExcel.contains("old:")) {
			String[]day=dataFromExcel.split(":");
			String daytoBeadded=day[1];
			int dayToBeadded=Integer.parseInt(daytoBeadded);     
			cal.add(Calendar.DATE, -dayToBeadded); // add 10 days

			date = cal.getTime();
			exp= dateFormat.format(date).toString();
		}else if(dataFromExcel.contains("future:")) {
			String[]day=dataFromExcel.split(":");
			String daytoBeadded=day[1];
			int dayToBeadded=Integer.parseInt(daytoBeadded);     
			cal.add(Calendar.DATE,dayToBeadded); // add 10 days
			date = cal.getTime();
			exp= dateFormat.format(date).toString();
		}else if(dataFromExcel.contains("now")) {
			exp= dateFormat.format(date).toString();
		}
		return exp;
	}	
}
