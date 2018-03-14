Feature: Search

  Scenario: Search result page should contains offers equal to entered location
    Given User is on Home Page
    When User enter New York City in search field
    And User set check-in date as 2018-03-20
    And User click on check-out datepicker
    And User set check-out date as 2018-03-25
    And User press 'Search' button
    Then Hotels location in all offers should be New York City

  Scenario: Offers in search result are sorted by price in ascending order when click on 'Lowest Price First' link
    Given User is on Home Page
    When User enter New York City in search field
    And User set check-in date as 2018-03-20
    And User press 'Search' button
    And User click on 'Lowest Price First' link
    Then Offers should be sorted by price

  Scenario: Offers should contain all required elements
    Given User is on Home Page
    When User enter New York City in search field
    And User set check-in date as 2018-03-20
    And User press 'Search' button
    Then Hotel name should not be empty
    And Hotel location should not be empty
    And Hotel image is displayed

  Scenario Outline: Search for hotels in definite city for specified number of adults
    Given User is on Home Page
    When User enter <destination> in search field
    And User set check-in date as <checkInDate>
    And User set number of adults as <numberOfAdults>
    And User press 'Search' button
    Then Number of adults in offers should not be less than <numberOfAdults>
    And Hotels location in all offers should be <destination>
    Examples:
      | destination | checkInDate | numberOfAdults |
      | New York    | 2018-03-20  | 5              |