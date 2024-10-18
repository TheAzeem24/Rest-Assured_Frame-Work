package api.utilities;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport extends TestListenerAdapter {
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extentReporter;
	public ExtentTest eTest;

	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test-Report" + timeStamp + ".html";

		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/" + repName);
		htmlReporter.config().setDocumentTitle("AutomationTesting");
		htmlReporter.config().setReportName("Functional Testing");
		htmlReporter.config().setTheme(Theme.DARK);

		extentReporter = new ExtentReports();
		extentReporter.attachReporter(htmlReporter);
		extentReporter.setSystemInfo("Host Name", "loacal host");
		extentReporter.setSystemInfo("QA Name", "Azeem");
		extentReporter.setSystemInfo("OS", "Windows 11");
		extentReporter.setSystemInfo("Browser", "chrome");
	}

	
	public void onTestSuccess(ITestResult iTestResult) {
		eTest = extentReporter.createTest(iTestResult.getName());
		eTest.log(Status.PASS, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.GREEN));
		/*
		 * String takeScreenShot = MMT_Methods.takeScreenShot(iTestResult);
		 * eTest.log(Status.PASS,takeScreenShot);
		 */
	}

	public void onTestFailure(ITestResult iTestResult) {
		eTest = extentReporter.createTest(iTestResult.getName());
		eTest.log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.RED));
		/*
		 * String takeScreenShot = MMT_Methods.takeScreenShot(iTestResult);
		 * eTest.log(Status.PASS,takeScreenShot);
		 */
	}

	public void onTestSkipped(ITestResult iTestResult) {
		eTest = extentReporter.createTest(iTestResult.getName());

		eTest.log(Status.SKIP, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.AMBER));
	}
	
	public void onFinish(ITestContext testContext) {
		extentReporter.flush();
	}

	  
	 
}
