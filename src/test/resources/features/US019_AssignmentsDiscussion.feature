@US019
@assignments
@discussion
Feature: Assignments - Start Discussion on Homework

  As a student
  I want to start a discussion on homework
  So that I can exchange ideas about the assignment

  Background:
    Given User is logged in as a student
    And User navigates to Assignments page

  @TC191
  Scenario: Discussion button should be visible on homework detail page
    When User opens a homework
    Then Discussion button should be visible
