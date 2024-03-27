package com.exampleday9;


import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest 
{
    static ExtentReports report;
    static ExtentTest test;
    public static WebDriver driver;
    @BeforeClass
    public void beforeClass()
    {
        report = new ExtentReports("C:\\Users\\2005a\\Documents\\PROJECTS\\software testing\\demoday9\\ExternalReport.html");
        test=report.startTest("Extent report demo");
    }
    @Test
    public void testMethod()
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("https://www.google.co.in");
        if(driver.getTitle().equals("Goog")){
            test.log(LogStatus.PASS, "Test Passed");
        }
        else{
            test.log(LogStatus.FAIL, "Test Failed");
            captureScreenshot();
        }
    }

    @AfterClass
    public void endTest()
    {
        report.endTest(test);
        report.flush();
        driver.quit();
    }

    public void captureScreenshot(){
        try{
           TakesScreenshot screenshot = (TakesScreenshot)driver;
           File src = screenshot.getScreenshotAs(OutputType.FILE);
           File dest = new File("C:\\Users\\2005a\\Documents\\PROJECTS\\software testing\\demoday9\\screenshot.png");
           // String path=dest.getAbsolutePath();
           FileUtils.copyFile(src, dest);
           // test.log(LogStatus.FAIL, "Screenshot below: " + test.addScreenCapture(path));
           test.log(LogStatus.FAIL,"screenshot",test.addScreenCapture(dest.getAbsolutePath()));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
