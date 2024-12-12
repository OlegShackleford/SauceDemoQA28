package tests;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.AllureUtils;

import java.util.concurrent.TimeUnit;

@Log4j2
public class TestListener implements ITestListener {
    private static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        TestListener.driver = driver;
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("======================================== STARTING TEST '{}' ========================================%n",
                iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info(
        "======================================== FINISHED TEST %s Duration: '{}'s ========================================%n", iTestResult.getName(),
                getExecutionTime(iTestResult));

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info("======================================== FAILED TEST '{}' Duration: '{}'s ========================================%n",
                iTestResult.getName(),
                getExecutionTime(iTestResult));
        AllureUtils.takeScreenshot(driver);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info("======================================== SKIPPING TEST '{}' ========================================%n",
                iTestResult.getName());
        AllureUtils.takeScreenshot(driver);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }
}
