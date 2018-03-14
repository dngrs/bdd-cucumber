package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverFactory;
import utils.WebDriverUtils;

import java.lang.invoke.MethodHandles;


/**
 * @author Igor Odokienko.
 */
public class GeneralSteps {

    private static Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Before
    public void cleanSessionData() {
        DriverFactory.getDriver().manage().deleteAllCookies();
    }

    @After
    public void attachScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            LOG.info("SCENARIO " + scenario.getName() + " FAILED");
            scenario.embed(((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES), "image/png");
        } else {
            LOG.info("SCENARIO " + scenario.getName() + " PASSED");
        }
        DriverFactory.getDriver().manage().deleteAllCookies();
        WebDriverUtils.executeScript("window.sessionStorage.clear();");
        WebDriverUtils.executeScript("window.localStorage.clear();");
    }

}
