Feature: Test functionality of course registration on learn portal

  Background:
    Given The user is on learn portal

    @Smoke @Regression
  Scenario: Navigating to the Catalogue portal
    When The user navigates to catalogue portal
    Then The user should see intuitive interface how to find a course on learn portal

    @Regression
  Scenario: Testing filter interface for courses on learn portal
    Given The user is on catalogue portal
    When The user navigates to technical and technology section
    And The user apply language, estimation efforts, target level and vendor filters
    Then The user should see filters count

      @Regression
  Scenario: Verification of information according to course card details
    Given The user navigated to technical and technology section and applied filters
    When The user choose course card after filtering
    Then The user should see information according to course details