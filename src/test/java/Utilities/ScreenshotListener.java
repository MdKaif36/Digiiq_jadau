package Utilities;


import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        ScreenshotUtils.takeScreenshot(testName);

        System.out.println("Test failed: " + testName);
    }
}