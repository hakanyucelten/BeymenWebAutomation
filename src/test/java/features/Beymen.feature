Feature: Beymen Web Automation Test

  Scenario: User in Beymen shopiing
    Given user is on the Beymen homepage
    And user clicks on the cookie button
    And user clicks on the erkek button
    When user searches for sort
    Then user clear search button
    And user searches for gomlek
    And choose product and check price
    Then go back to item page and increase number of item

