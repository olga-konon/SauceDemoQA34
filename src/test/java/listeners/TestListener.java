package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;
import utils.AllureUtils;

import java.util.concurrent.TimeUnit;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.printf("======================================== STARTING TEST %s ========================================%n",
                result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.printf("======================================== FINISHED TEST %s Duration: %ss ========================================%n",
                result.getName(), getExecutionTime(result));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.printf("======================================== FAILED TEST %s Duration: %ss ========================================%n",
                result.getName(), getExecutionTime(result));

        Object testInstance = result.getInstance();

        if (testInstance instanceof BaseTest) {
            WebDriver driver = ((BaseTest) testInstance).getDriver();

            if (driver != null) {
                try {
                    AllureUtils.takeScreenshot(driver);
                } catch (Exception e) {
                    System.out.println("Failed to take screenshot: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.printf("======================================== SKIPPING TEST %s ========================================%n",
                result.getName());
    }

    private long getExecutionTime(ITestResult result) {
        return TimeUnit.MILLISECONDS.toSeconds(result.getEndMillis() - result.getStartMillis());
    }
}
