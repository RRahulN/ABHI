package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		//java.util.Date date = new java.util.Date();
		System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		System.out.println(new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date()));
		System.out.println(new Date());
	}
}
