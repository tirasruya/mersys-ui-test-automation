@US012
@finance
Feature: Hamburger Menu - Finance Report Download

  Background:
    Given User is logged in as a student

  @TC1201
  @smoke
  Scenario: User downloads finance report as Excel
    When User opens Hamburger menu
    And User clicks on Finance menu item
    And User clicks on My Finance menu item
    And User opens report options menu
    And User downloads report as "excel"
    Then Finance report should be downloaded as "excel"

  @TC1202
  Scenario: User downloads finance report as PDF
    When User opens Hamburger menu
    And User clicks on Finance menu item
    And User clicks on My Finance menu item
    And User opens report options menu
    And User downloads report as "pdf"
    Then Finance report should be downloaded as "pdf"
