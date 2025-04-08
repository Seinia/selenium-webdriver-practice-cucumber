Feature: Test functionality of course registration on campus portal

  Background:
    Given The user is on learn portal and isn't logged in

    @Smoke @Regression
  Scenario: Navigating to the Campus portal
    When The user navigates to campus portal
    Then The user should see intuitive interface how to find a course

    @Smoke @Regression
  Scenario: Navigating to the Training portal
    Given The user is on campus portal
    When The user navigates to training portal
    Then The user should see intuitive interface how to filter a course list

    @Regression
  Scenario: Testing filter interface for course list
    Given The user is on training portal
    When The user apply locations and skills filters
    Then The user should see courses according applied filters

    @Regression
  Scenario: Testing registration of the course
    Given The user is on training portal and locations and skills filters are applied
    When The user choose any course card after filtering
    And The user try to register
    Then The user should see login page
