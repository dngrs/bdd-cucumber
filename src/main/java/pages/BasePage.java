package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.DriverFactory;
import utils.WebDriverUtils;

/**
 * @author Igor Odokienko
 */
public abstract class BasePage {

    protected WebDriver driver;

    protected BasePage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    protected WebElement waitElementIsClickable(WebElement element) {
        return WebDriverUtils.getWait()
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitElementIsVisible (WebElement element) {
        return WebDriverUtils.getWait()
                .until(ExpectedConditions.visibilityOf(element));
    }

}
