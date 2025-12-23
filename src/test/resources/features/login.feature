Feature: Login Feature

  As a student
  I want to login to the system
  So that I can access courses and communicate with teachers

  Background:
    Given User is on the login page

  @smoke @login @positive
  Scenario: Successful login with valid credentials
    When User enters username "Student_10" and password "S12345"
    And User clicks on login button
    Then User should be redirected to the homepage

  @login @negative
  Scenario Outline: Login attempt with invalid credentials
    When User enters username "<username>" and password "<password>"
    And User clicks on login button
    Then User should see error message "<expectedError>" for "<errorType>"

    Examples:
      | username    | password  | expectedError         | errorType |
      | wrongUser   | S12345    | error.validation      | login     |
      | Student_10  | wrongPwd  | error.validation      | login     |
      | wrongUser   | wrongPwd  | error.validation      | login     |
      |             | S12345    | E-mail is required    | username  |
      | Student_10  |           | Password is required  | password  |