Feature: User preferences

  @test
  Scenario Outline: Currency selection
    Given User is on Home Page
    When User select language as English (US)
    And User select currency as <currency>
    And User enter New York City in search field
    And User set check-in date as 2018-03-20
    And User press 'Search' button
    Then price in all offers should be appended with <sign>
    Examples:
      | currency    | sign |
      | U.S. Dollar | US$  |

  Scenario Outline: Selection of language preference
    Given User is on Home Page
    When User select language as <language>
    Then title in search form should be <title>
    Examples:
      | language     | title                                  |
      | Українська   | Знайдіть пропозиції на будь-який сезон |
      | English (US) | Find Deals for Any Season              |