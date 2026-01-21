@US023
@calendar
Feature: Calendar - Weekly and Monthly course plan & status legend
  As a student
  I want to view my courses weekly and monthly with their statuses
  So that I can track my lessons easily
  Background:
    Given User is logged in as a student

  @TC2301 @smoke
  Scenario: Student views Calendar page default weekly plan for today
    When User navigates to Calendar page from Top Nav
    Then Calendar page should be displayed
    And Weekly Course Plan for today should be visible
    And Course status icons should be visible on the plan
    And Course status legend should be visible with meanings

  @TC2302
  Scenario: Student switches between Weekly Course Plan and Calendar views
    When User navigates to Calendar page from Top Nav
    And User switches to "Month" view on Calendar page
    Then Calendar monthly view should be displayed
    When User switches to "Weekly Course Plan" view on Calendar page
    Then Weekly Course Plan for today should be visible

  @TC2303
  Scenario: Student can navigate weeks using Previous Today Next buttons
    When User navigates to Calendar page from Top Nav
    And User clicks "Next" navigation button on Weekly Course Plan
    Then Weekly Course Plan date range should change
    When User clicks "Previous" navigation button on Weekly Course Plan
    Then Weekly Course Plan date range should change
    When User clicks "Today" navigation button on Weekly Course Plan
    Then Weekly Course Plan for today should be visible

  @TC2304
  Scenario: Student can open a course from Weekly Course Plan
    When User navigates to Calendar page from Top Nav
    And User opens a random course from Weekly Course Plan
    Then Course details popup should be displayed
