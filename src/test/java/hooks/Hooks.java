package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import utils.ReportManager;

public class Hooks {
    private static ExtentReports extent = ReportManager.getInstance();
    private ExtentTest scenarioTest;

    @Before
    public void beforeScenario(Scenario scenario) {
        scenarioTest = extent.createTest(scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            scenarioTest.fail("O cenário falhou.");
        } else {
            scenarioTest.pass("O cenário passou.");
        }
        extent.flush();
    }
}