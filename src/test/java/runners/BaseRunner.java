package runners;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

/**
 * @author Igor Odokienko.
 */

public class BaseRunner {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = DriverFactory.getDriver();
    }


    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}
