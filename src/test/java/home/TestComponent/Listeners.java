package home.TestComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import home.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{

	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	
	// Thread safe
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	
	public void onTestStart(ITestResult result) {
	    
		test= extent.createTest(result.getMethod().getMethodName());
		
		extentTest.set(test); // handles concurrency issue by creating uique ID for each instances
	  }

	 
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test PAssed");
	  }

	  
	public void onTestFailure(ITestResult result) {
		
		// to get driver info from 'result'
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
		
		
	//   test.log(Status.FAIL, "Test Failed");
	//   test.fail(result.getThrowable()); instead of this line write something as mentioned below to handle concurrency issue
		extentTest.get().fail(result.getThrowable());
	  String filePath = null;
	try {
		filePath = getScreenshot(result.getMethod().getMethodName(),driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	   
	extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	   
	  }

	  
	public void onTestSkipped(ITestResult result) {
	    // not implemented
	  }

	 
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	  }

	  
	public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	
}
