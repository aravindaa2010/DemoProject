package com.amazon.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeMethod;

import com.amazon.qa.base.BaseTest;
import org.apache.commons.io.FileUtils;

//Created by 242654 - Aravindan on 22-Dec-2020
public class TestUtil extends BaseTest {
	
	public static String methodName;
	public static String classname; 
	public static long PAGE_LOAD_TIMEOUT=20;
	public static long IMPLICIT_LOAD_TIMEOUT=10;
	

	
	
	public static void takeScreenshot(String methodName) throws IOException {
				
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + methodName + "_" + System.currentTimeMillis() + ".png"));
		}

}
