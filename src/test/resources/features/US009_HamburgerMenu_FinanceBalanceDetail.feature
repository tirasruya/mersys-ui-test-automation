@US009
@finance
Feature: Hamburger Menu - Finance Fee/Balance Detail

  Background:
    Given User is logged in as a student

  @TC0901
  Scenario: User views Fee/Balance Detail installment table
    When User opens Hamburger menu
    And User clicks on Finance menu item
    And User clicks on My Finance menu item
    Then Finance page should be displayed

    And User clicks on student row on Finance page
    Then Student Fee page should be displayed

    And User clicks on Fee Balance Detail tab
    Then Installment table should be displayed

