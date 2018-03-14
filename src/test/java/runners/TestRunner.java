package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author Igor Odokienko.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = "steps",
        tags = {"@test"},
        plugin = {"pretty", "html:target/cucumber-html-report", "json:target/cucumber-json-report.json"})
public class TestRunner extends BaseRunner{
}
