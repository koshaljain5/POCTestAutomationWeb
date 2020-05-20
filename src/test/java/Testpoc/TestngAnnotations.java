package Testpoc;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestngAnnotations {

	/* 
	BeforeSuite
	Beforetest
	BeforeClass
	BeforeMethod
	MainTest
	AfterMethod
	AfterClass
	AfterTest
	*/

	
	
	@BeforeSuite
	public void beforesuite()
	{
		System.out.println("BeforeSuite");
	}
	
	@BeforeTest
	public void beforetest()
	{
		System.out.println("Beforetest");
	}
	
	@BeforeClass
	public void beforeclass()
	{
		System.out.println("BeforeClass");
	}
	
	@BeforeMethod
	public void beforemethod()
	{
		System.out.println("BeforeMethod");
	}
	
	@Test
	public void mainTest()
	{
		System.out.println("MainTest");
	}
	
	@AfterMethod
	public void aftermethod()
	{
		System.out.println("AfterMethod");
	}
	
	@AfterClass
	public void afterclass()
	{
		System.out.println("AfterClass");
	}	
	
	@AfterTest
	public void aftertest()
	{
		System.out.println("AfterTest");
	}
	
	@AfterSuite
	public void aftersuite()
	{
		System.out.println("AfterSuite");
	}	
}
