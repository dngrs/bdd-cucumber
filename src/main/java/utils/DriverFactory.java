package utils;

import org.apache.commons.lang3.EnumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * @author Igor Odokienko
 */
public final class DriverFactory {

    private static long implicitWait = Long.parseLong(PropertyConfig.getProperty("wait.implicit"));
    private static long scriptWait = Long.parseLong(PropertyConfig.getProperty("wait.script"));
    private static long pageWait = Long.parseLong(PropertyConfig.getProperty("wait.page"));
    private static WebDriver driver;

    private DriverFactory() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    private static void initDriver() {
        Browser runBrowser = EnumUtils.isValidEnum(Browser.class, PropertyConfig.getProperty("browser.name").toUpperCase()) ?
                Browser.valueOf(PropertyConfig.getProperty("browser.name").toUpperCase()) : Browser.CHROME;
        switch (runBrowser) {
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver");
                driver = new ChromeDriver();
                break;
        }
        setWebDriverSettings();
    }

    private static void setWebDriverSettings() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(pageWait, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(scriptWait, TimeUnit.SECONDS);
    }

}
