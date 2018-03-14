package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pages.SearchResultsPage;

/**
 * @author Igor Odokienko.
 */
public class SearchResultsPageSteps {

    private SearchResultsPage searchResultsPage = new SearchResultsPage();

    @And("^User click on 'Lowest Price First' link$")
    public void userClickOnLowestPriceFirstLink() {
        searchResultsPage.clickOnLowestPriceFirstLink();
    }

    @Then("^Hotels location in all offers should be ([^\"]*)$")
    public void hotelsLocationInAllOffersShouldBe(String location) {
        searchResultsPage.checkHotelsLocation(location);
    }

    @Then("^Offers should be sorted by price$")
    public void offersShouldBeSortedByPrice() {
        searchResultsPage.checkOffersAreSortedByPriceAsc();
    }

    @Then("^price in all offers should be appended with (.*)$")
    public void priceInAllOffersShouldBeAppendedWithSign(String sign) {
        searchResultsPage.checkCurrencySign(sign);
    }

    @Then("^Number of adults in offers should not be less than (\\d)$")
    public void numberOfAdultsInOffersShouldNotBeLessThan(int number) throws Throwable {
        searchResultsPage.checkNumberOfAdults(number);
    }

    @Then("^Hotel name should not be empty$")
    public void hotelNameShouldNotBeEmpty() throws Throwable {
        searchResultsPage.checkHotelNameIsNotEmpty();
    }

    @And("^Hotel location should not be empty$")
    public void hotelLocationShouldNotBeEmpty() throws Throwable {
        searchResultsPage.checkHotelLocationIsNotEmpty();
    }

    @And("^Hotel image is displayed$")
    public void hotelImageIsDisplayed() throws Throwable {
        searchResultsPage.checkImageIsPresent();
    }

}
