@US002
@home
Feature: Home Page Logo Navigation

  As a student
  I want to click the logo on the home page
  So that I can navigate quickly to the course main page

  Background:
    Given User is logged in as a student

  @TC0201
  @smoke
  Scenario: Navigate to course main page by clicking logo
    When User sees the company logo on the top left corner
    And User clicks on the company logo
    Then User should be redirected to techno.study