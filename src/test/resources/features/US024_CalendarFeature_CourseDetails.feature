@US024
@Calendar
Feature: Calendar - Student cannot join not started course, can view popup details and tabs

  Background:
    Given User is logged in as a student
    When User navigates to Calendar page from Top Nav
    Then Calendar page should be displayed
    And User switches to "Weekly Course Plan" view on Calendar page

  @TC2401
  Scenario: Student opens a course from Weekly Course Plan and sees course name on popup
    And User opens a random course from Weekly Course Plan
    Then Course details popup should be displayed
    And Course name should be visible on course details popup

  @TC2402
  Scenario: Student clicks unstarted/unpublished course and sees meeting not started message and details
    When User opens a course by status "PENDING"
    Then Course details popup should be displayed
    And Meeting not started message should be visible on popup
    And Teacher or Instructor info should be visible on popup
    And Date and Time info should be visible on popup

  @TC2403
  Scenario Outline: Student clicks P/S/E course and sees info tabs working
    When User opens a course by status "<status>"
    Then Course details popup should be displayed
    And Popup tabs should work properly

    Examples:
      | status |
      | P      |
      | S      |
      | E      |