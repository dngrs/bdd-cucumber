package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Igor Odokienko
 */
public class WebDriverUtils {

    private static WebDriver driver = DriverFactory.getDriver();
    private static long explicitWait = Long.parseLong(PropertyConfig.getProperty("wait.explicit"));
    private static WebDriverWait wait = new WebDriverWait(driver, explicitWait);

    private WebDriverUtils() {
    }

    public static void scrollToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    public static WebDriverWait getWait(int seconds) {
        return new WebDriverWait(driver, seconds);
    }

    public static void executeScript(String jsScript) {
        ((JavascriptExecutor) driver).executeScript(jsScript);
    }

}
