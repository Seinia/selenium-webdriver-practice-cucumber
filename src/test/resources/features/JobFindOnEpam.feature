Feature: Test functionality of job applying on epam portal

  Background:
    Given The user is on epam portal

    @Smoke @Regression
  Scenario: Navigating to the epam careers section
    When The user navigates to epam careers section
    Then The user should see easy-to-read interface how to find a job

    @Regression
  Scenario Outline: Verification of filters functionality
    Given The user is on epam careers section of epam portal
    When The user navigates to epam careers portal
    And The user input "<skills>", specialisation and location
    Then The user should see job card according to applied "<skills>"

    Examples:
      | skills      |
      | Java        |
      | Node.js     |

  @Regression
  Scenario Outline: Verification of job details according to applied filters
    Given The user is on epam careers portal and applied "<skills>"
    When The user choose any job card with applied "<skills>"
    Then The user should see job details according to applied "<skills>"

    Examples:
      | skills      |
      | Java        |
      | Node.js     |

    @Regression
  Scenario: Verification of job details according to applied filters
    Given The user is on epam careers portal
    And The user has applied the following filters:
      | skills   |
      | Java     |
      | Node.js  |
    Then The user should see job details according to applied filters