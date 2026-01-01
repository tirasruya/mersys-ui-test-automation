@US021
@assignments
@submission
Feature: Assignment submission

  As a student
  I want to submit my assignments
  So that I can complete my coursework

  Background:
    Given User is logged in as a student
    And User is on the Assignments page

  @TC2101
  @smoke
  @positive
  Scenario: Student should submit assignment successfully
    When User clicks submit icon for a random assignment
    Then Submission editor popup should be displayed
    And Text editor area should be visible
    And Attach files section should be visible
    And Save as Draft button should be disabled
    And Send button should be disabled

    When User enters text into submission editor
    And User saves assignment as draft
    Then Success message should be displayed
    And Send button should be enabled

    When User clicks Send button
    And User confirms assignment submission
    Then Assignment submission success message should be displayed