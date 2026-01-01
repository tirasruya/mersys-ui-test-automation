@US018
@assignments
Feature: Assignments - Assigned Tasks Access

  As a student
  I want to access all tasks assigned to me from a single page
  So that I can see my assignments, quizzes, projects, and surveys on time
  and plan my work accordingly

  Background:
    Given User is logged in as a student

  @TC1801
  @smoke
  Scenario: Verify assignments link visibility, count on hover, and assigned tasks list
    Then User should see Assignments logo on the home page
    When User hovers over Assignments logo
    Then User should see total number of assigned tasks
    When User clicks on Assignments logo
    Then User should see all assigned tasks on Assignments page
