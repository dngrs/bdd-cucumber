package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.entities.Offer;
import utils.PropertyConfig;
import utils.WebDriverUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * @author Igor Odokienko
 */
public class SearchResultsPage extends BasePage {

    private By price = By.cssSelector(".price.availprice.no_rack_rate>b,.price.scarcity_color.anim_rack_rate>b");
    private By overlayContainer = By.cssSelector(".sr-usp-overlay__container");
    private By hotelItemLocator = By.xpath("//div[@data-hotelid]");

    private String hotelSoldOutPropertyLocator = "//div[@data-hotelid='ID']//span[@class='sold_out_property']";
    private String hotelLocationLocator = "//div[@data-hotelid='ID']//div[contains(@class, 'address')]/a[2]";
    private String hotelButtonLocator = "//div[@data-hotelid='ID']//a[contains(@class, 'b-button b-button_primary')]";
    private String hotelNameLocator = "//div[@data-hotelid='ID']//span[contains(@class, 'sr-hotel__name')]"
            + "|//div[@data-hotelid='ID']//a[contains(@class, 'dod-banner__hotel-link')]";
    private String hotelImageLocator = "//div[@data-hotelid='ID']//img[contains(@class, 'hotel_image')]"
            + "|//div[@data-hotelid='ID']//img[@class='dod-banner__image']";

    @FindBy(xpath = "//a[contains(text(),'Lowest Price First')]")
    private WebElement lowestPriceFirstTab;
    @FindBy(css = ".sr_max_occupancy.jq_tooltip")
    private List<WebElement> adultsOccupancy;
    @FindBy(xpath = "//div[@data-hotelid]")
    private List<WebElement> allOffersList;

    private List<Offer> offers = new ArrayList<>();

    public SearchResultsPage() {
        initializeOfferList();
    }

    public void checkHotelsLocation(String location) {
        offers.forEach(offer ->
                Assert.assertTrue("Hotel location isn't equals the expected value",
                        offer.getHotelLocation().contains(location)));
    }

    public void clickOnLowestPriceFirstLink() {
        waitElementIsClickable(lowestPriceFirstTab).click();
    }

    public void checkOffersAreSortedByPriceAsc() {

        WebDriverUtils.getWait()
                .until(ExpectedConditions.invisibilityOfElementLocated(overlayContainer));

        Function<WebDriver, Boolean> isLoaded = webDriver -> {
            List<WebElement> allPrices = WebDriverUtils.getWait(30)
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(price));
            return allPrices.stream()
                    .allMatch(e -> {
                        if (e.getText().isEmpty()) {
                            WebDriverUtils.scrollToElement(e);
                        }
                        return !e.getText().isEmpty();
                    });
        };

        WebDriverUtils.getWait()
                .until(isLoaded);

        List<WebElement> allPrices = driver.findElements(price);

        boolean sortedAscending =
                IntStream.range(0, allPrices.size() - 1)
                        .allMatch(i -> Integer.compare(parsePrice(allPrices.get(i)),
                                parsePrice(allPrices.get(i + 1))) <= 0);

        Assert.assertTrue("Offers are not sorted by price", sortedAscending);

    }

    private Integer parsePrice(WebElement element) {
        return Integer.valueOf(element.getText().replaceAll("[^0-9]", ""));
    }

    public void checkCurrencySign(String currencySign) {
        List<WebElement> allPrices = WebDriverUtils.getWait()
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(price));
        allPrices.forEach(e -> {
            Assert.assertTrue("Currency sign isn't equals the expected value",
                    e.getText().contains(currencySign));
        });
    }

    public void checkNumberOfAdults(int number) {
        adultsOccupancy.forEach(e -> {
            String titleAttribute = e.getAttribute("data-title");
            int actualNumber = Integer.parseInt(titleAttribute.replaceAll("\\D", ""));
            Assert.assertTrue("Number oa adults is less than expected value",
                    actualNumber >= number);
        });
    }

    private void initializeOfferList() {
        WebDriverUtils.getWait()
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(hotelItemLocator));

        allOffersList.forEach(e -> {
            String hotelId = e.getAttribute("data-hotelid");
            WebElement soldOut = null;
            boolean soldOutOffer = true;
            try {
                driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
                soldOut = findElementByDynamicXpathLocator(hotelSoldOutPropertyLocator, hotelId);

            } catch (NoSuchElementException ex) {
            } finally {
                long implicitWait = Long.parseLong(PropertyConfig.getProperty("wait.implicit"));
                driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
            }
            String hotelName = findElementByDynamicXpathLocator(hotelNameLocator, hotelId).getText();
            String hotelLocation = findElementByDynamicXpathLocator(hotelLocationLocator, hotelId).getText();
            WebElement hotelImage = findElementByDynamicXpathLocator(hotelImageLocator, hotelId);
            WebElement button = null;
            if (soldOut == null) {
                soldOutOffer = false;
                button = findElementByDynamicXpathLocator(hotelButtonLocator, hotelId);
            }
            offers.add(new Offer(soldOutOffer, hotelName, hotelLocation, hotelImage, button));
        });
    }

    public void checkHotelNameIsNotEmpty() {
        offers.forEach(offer -> Assert.assertTrue("Hotel name is empty",
                !offer.getHotelName().isEmpty()));
    }

    public void checkHotelLocationIsNotEmpty() {
        offers.forEach(offer -> Assert.assertTrue("Hotel location is empty",
                !offer.getHotelLocation().isEmpty()));
    }

    public void checkImageIsPresent() {
        offers.forEach(offer -> Assert.assertTrue("Hotel location isn't displayed",
                offer.getImage().isDisplayed()));
    }

    private WebElement findElementByDynamicXpathLocator(String locatorPattern, String stringToReplace) {
        String locator = locatorPattern.replaceAll("ID", stringToReplace);
        return driver.findElement(By.xpath(locator));
    }

}
