@US008
@finance
Feature: Hamburger Menu - Finance Navigation

  As a student
  I want to access Finance page via Hamburger menu
  So that I can manage my finance operations

  Background:
    Given User is logged in as a student

  @TC0801
  @smoke
  Scenario: User navigates to Finance page via Hamburger menu
    When User opens Hamburger menu
    And User clicks on Finance menu item
    And User clicks on My Finance menu item
    Then Finance page should be displayed

