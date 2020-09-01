package com.aqm;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGtest {
	
	@BeforeTest
	public void publ3() {
		System.out.println("BeforeTest");

	}
	@BeforeMethod
	public void publ() {
		System.out.println("BeforeMethod");

	}
	@Test(priority=1)
	public void publ1()
	{
		System.out.println("@Test1");

	}
	@Test(priority=2)
	public void publ4()
	{
		System.out.println("@Test2");

	}
	@AfterMethod
	public void publ2() {
		// TODO Auto-generated method stub
		System.out.println("AfterMethod");

	}
	@AfterTest
	public void publ6() {
		System.out.println("AfterTest");

	}
	
}
