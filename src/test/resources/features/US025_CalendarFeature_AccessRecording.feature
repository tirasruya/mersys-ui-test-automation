@US025
@Calendar
Feature: Calendar - Student can access recording for ended courses and play video

  Background:
    Given User is logged in as a student
    When User navigates to Calendar page from Top Nav
    Then Calendar page should be displayed
    And User switches to "Weekly Course Plan" view on Calendar page

  @TC2501
  Scenario: Student opens ended course and can open recording page
    When User opens a course by status "E"
    Then Course details popup should be displayed
    And Recording button should be visible on popup
    When User clicks Recording button
    Then Recording page should be opened

  @TC2502
  Scenario: Student can play the recording video
    When User opens a course by status "E"
    Then Course details popup should be displayed
    And Recording button should be visible on popup
    When User clicks Recording button
    Then Recording page should be opened
    And Play icon should be visible on video
    When User clicks Play icon
    Then Video should start playing

