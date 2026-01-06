@US013
@attendance
Feature: Attendance Feature

  As a student, if I am unable to attend class,
  I should be able to submit my excuse in writing to the campus online system.
  This way, I can inform my teacher of the situation.

  Background:
    Given User is logged in as a student
    #When User clicks on "Attendance" menu item
    #Then "Attendance" should perform correct action

  @TC1301
  @smoke
  Scenario Outline: Successful submission of an excuse with optional file attachment
    When the user navigates to the "Attendance" section from the side menu
    Then the "Attendance" menu should expand successfully
    When the user clicks on the "ATTENDANCE EXCUSES" button to access the attendance page
    And the user clicks "PLUS" icon to create a new excuse
    And the user selects excuse type
    And the user selects date on the calendar
    And the user enters "<description>" as excuse description
    And the user attaches a file from path "<filePath>" if provided
    And the user clicks the "Send" button
    Then the "success" message should be displayed


    Examples:
      | description                      | filePath                                                             |
      | Medical report for flu           | /Users/aslihangulluoglu/Documents/GitHub/MERSYS-UI-Automation/src/test/resources/testData/UI_InternshipProject_UserStories_TR.pdf |
      | Family emergency - no attachment |                                                                      |