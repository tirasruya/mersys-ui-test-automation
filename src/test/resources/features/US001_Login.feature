@US001
@login
Feature: Login Feature

  As a student
  I want to login to the system
  So that I can access courses and communicate with teachers

  Background:
    Given User is on the login page

  @TC101
  @smoke
  @positive
  Scenario: User logs in successfully with valid student credentials
    When User enter valid student credentials
    And User clicks on login button
    Then User should be redirected to the homepage

  @TC102
  @negative
  Scenario Outline: User cannot log in with invalid or missing credentials
    When User enters username "<username>" and password "<password>"
    And User clicks on login button
    Then User should see error message "<expectedError>" for "<errorType>"

    Examples:
      | username  | password | expectedError                | errorType |
      | wrongUser | 12345    | Invalid username or password | login     |
      | Student   | wrongPwd | Invalid username or password | login     |
      | wrongUser | wrongPwd | Invalid username or password | login     |
      |           | 12345    | E-mail is required           | username  |
      | Student   |          | Password is required         | password  |