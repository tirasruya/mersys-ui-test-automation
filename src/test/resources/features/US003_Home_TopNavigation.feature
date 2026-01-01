@US003
@home
@navigation
Feature: Home - Top Navigation Menu Functionality

  As a student
  I want to use the top navigation menu correctly
  So that I can navigate the campus application smoothly

  Background:
    Given User is logged in as a student

  @TC0301
  @smoke
  Scenario Outline: Verify top navigation menu items are visible and functional
    Then User should see "<menu>" menu item on top navigation
    When User clicks on "<menu>" menu item
    Then "<menu>" should perform correct action

    Examples:
      | menu             |
      | Logo             |
      | Courses          |
      | Calendar         |
      | Attendance       |
      | Assignments      |
      | Grading          |
      | Hamburger        |
      | Announcements    |
      | Messages         |
      | ProfileSettings |