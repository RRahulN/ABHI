package com.aqm;

import java.io.File;
import java.io.OutputStreamWriter;
import java.util.Set;

import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.WriterStreamConsumer;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BatchRunner {

	public static void main(String[] args) throws Exception {
		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        System.setProperty("webdriver.chrome.driver", "D:\\GSTNAutomationFramework\\GSTN\\drivers\\chromedriver.exe");
        WebDriver  driver = new ChromeDriver();
        System.out.println(driver.toString());
        driver.get("http://localhost:2010/homepage");
        Thread.sleep(4000);
        Set<Cookie> allCookies = driver.manage().getCookies();
        System.out.println("Coockies111 = "+allCookies);
        driver.close();
        Thread.sleep(4000);
        WebDriver driver2=new ChromeDriver();
        driver2.get("http://localhost:2010/homepage");
        for(Cookie cookie : allCookies)
        {
        	driver2.manage().addCookie(cookie);
        }
        System.out.println(driver2.toString());
        driver2.get("http://localhost:2010/homepage");
        Set<Cookie> allCookies1 = driver.manage().getCookies();
        System.out.println("Coockies222 = "+allCookies1);
		 }
}
