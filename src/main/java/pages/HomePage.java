package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.PropertyConfig;
import utils.WebDriverUtils;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * @author Igor Odokienko
 */
public class HomePage extends BasePage {

    @FindBy(id = "ss")
    private WebElement destinationPropertyNameOrAddressField;
    @FindBy(xpath = "//div[@data-placeholder='Check-out Date']")
    private WebElement checkOutDatePicker;
    @FindBy(xpath = "(//button[contains(@class, 'sb-searchbox__button')])[1]")
    private WebElement searchButton;
    @FindBy(xpath = "//div[@id='user_form']/ul/li[contains(@class, 'language')]")
    private WebElement languagePreferenceButton;
    @FindBy(css = ".sb-searchbox__title-text")
    private WebElement searchFormTitle;
    @FindBy(css = ".popover_trigger.long_currency_text")
    private WebElement selectCurrencyButton;
    @FindBy(xpath = "//select[@name='group_adults']")
    private WebElement adultsDropdown;
    @FindBy(xpath = "//li[@data-id='currency_selector']/div[contains(@class, 'currency')]")
    private WebElement currencyTablePopup;

    private static final String BASE_URL = PropertyConfig.getProperty("site.url");
    private static final String languagePattern = "//span[contains(text(), '%s')]/parent::a";
    private static final String currencyButtonLocator = "//span[contains(text(), '%s')]/ancestor::a";
    private static final String monthAndYearPattern = "//div[@class='sb-dates__col --check%1$s-field']"
            + "//th[contains(text(), '%2$s')]";
    private static final String buttonFurther = "//div[@class='sb-dates__col --check%s-field']"
            + "//div[@class='c2-button c2-button-further']/span";
    private static final String dayOfMonthPattern = "//div[@class='sb-dates__col --check%1$s-field']"
            + "//th[contains(text(), '%2$s')]/ancestor::table//span[text()='%3$s']";

    public void open() {
        driver.get(BASE_URL);
    }

    public void enterDestination(String destination) {
        waitElementIsClickable(destinationPropertyNameOrAddressField)
                .sendKeys(destination);
        WebDriverUtils.getWait()
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li/*[contains(text(), '" + destination + "')]")))
                .click();
    }

    public void setCheckInDate(LocalDate localDate) {
        selectDate("in", localDate);
    }

    public void setCheckOutDate(LocalDate localDate) {
        selectDate("out", localDate);
    }

    public void clickOnCheckOutDatePicker() {
        waitElementIsClickable(checkOutDatePicker).click();
    }

    public void pressSearchButton() {
        waitElementIsClickable(searchButton).click();
    }

    private void selectDate(String inOrOut, LocalDate localDate) {

        String monthAndYear = localDate.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + localDate.getYear();
        WebElement monthAndYearElement;

        do {
            monthAndYearElement = driver.findElement(By.xpath(String.format(monthAndYearPattern, inOrOut, monthAndYear)));
            if (!monthAndYearElement.isDisplayed()) {
                driver.findElement(By.xpath(String.format(buttonFurther, inOrOut)))
                        .click();
            }
        } while (!monthAndYearElement.isDisplayed());

        driver.findElement(By.xpath(String.format(dayOfMonthPattern, inOrOut, monthAndYear, localDate.getDayOfMonth())))
                .click();

    }

    public void clickOnLanguagePreferenceButton() {
        waitElementIsClickable(languagePreferenceButton).click();
    }

    public void selectLanguage(String language) {
        WebDriverUtils.getWait()
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(languagePattern, language))))
                .click();
    }

    public void checkSearchFormTitle(String title) {
        WebDriverUtils.getWait()
                .until(ExpectedConditions.visibilityOf(searchFormTitle));
        Assert.assertTrue(searchFormTitle.getText().contains(title));
    }

    public void clickOnSelectCurrencyButton() {
        waitElementIsClickable(selectCurrencyButton).click();
    }

    public void selectCurrency(String currency) {
        waitElementIsVisible(currencyTablePopup);
        driver.findElement(By.xpath(String.format(currencyButtonLocator, currency)))
                .click();
    }

    public void setNumberOfAdults(String numberOfAdults) {
        Select adultsSelect = new Select(adultsDropdown);
        adultsSelect.selectByValue(numberOfAdults);
    }

}
