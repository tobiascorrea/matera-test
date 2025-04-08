package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import utils.ReportManager;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions", "hooks", "steps"}
)
public class TestRunner {

    @AfterClass
    public static void tearDown() {
        ReportManager.getInstance().flush();
    }
}