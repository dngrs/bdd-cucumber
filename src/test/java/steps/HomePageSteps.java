package steps;

import cucumber.api.Transform;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import utils.DateMapper;

import java.time.LocalDate;

/**
 * @author Igor Odokienko.
 */
public class HomePageSteps {

    private HomePage homePage = new HomePage();

    @Given("^User is on Home Page$")
    public void userIsOnHomePage() throws Throwable {
        homePage.open();
    }

    @When("^User enter (.*) in search field$")
    public void userEnterInSearchField(String location) {
        homePage.enterDestination(location);
    }

    @And("^User set check-in date as ([^\"]*)$")
    public void userSetCheckInDateAs(@Transform(DateMapper.class) LocalDate date) {
        homePage.setCheckInDate(date);
    }

    @And("^User click on check-out datepicker$")
    public void userClickOnCheckOutDatepicker() throws Throwable {
        homePage.clickOnCheckOutDatePicker();
    }

    @And("^User set check-out date as ([^\"]*)$")
    public void userSetCheckOutDateAs(@Transform(DateMapper.class) LocalDate date) {
        homePage.setCheckOutDate(date);
    }

    @And("^User press 'Search' button$")
    public void userPressSearchButton() {
        homePage.pressSearchButton();
    }

    @When("^User select language as (.*)$")
    public void userSelectLanguageAs(String language) {
        homePage.clickOnLanguagePreferenceButton();
        homePage.selectLanguage(language);
    }

    @Then("^title in search form should be (.*)$")
    public void titleIsSearchFormShouldBe(String title) {
        homePage.checkSearchFormTitle(title);
    }

    @And("^User select currency as (.*)$")
    public void userSelectCurrencyAs(String currency){
        homePage.clickOnSelectCurrencyButton();
        homePage.selectCurrency(currency);
    }

    @And("^User set number of adults as (.*)$")
    public void userSetNumberOfAdults(String numberOfAdults) throws Throwable {
        homePage.setNumberOfAdults(numberOfAdults);
    }

}
