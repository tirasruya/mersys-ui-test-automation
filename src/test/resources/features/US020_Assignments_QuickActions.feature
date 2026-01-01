@US020
@assignments
Feature: Assignments - Quick Action Buttons on Homework List

  As a student
  I want to use quick action buttons on homework list
  So that I can perform actions faster

  Background:
    Given User is logged in as a student
    And User is on the Assignments page

  @TC2001
  @smoke
  @positive
  Scenario: Verify action icons for a randomly selected assignment
    When User selects a random assignment from the list
    Then Assignment action icons should be displayed correctly

  @TC2002
  @positive
  Scenario: Clicking assignment row opens assignment details page
    When User clicks on a random assignment row
    Then Assignment details page should be displayed

  @TC2003
  @blocked
  @ignore
  Scenario: Discussion icon should be visible when discussion exists