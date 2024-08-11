Feature: login

  Scenario Outline: User login with valid credentials
    Given user visits the websites
    When user enter username "<username>" and password "<password>"
    Then user can view the dashboard page
    And browser closes
    Examples:
      | username      | password     |
      | Admin         | admin123     |

  Scenario Outline: User login with invalid credentials
    Given user visits the websites
    When user enter username "<username>" and password "<password>"
    Then user can't view the dashboard page
    And browser closes
    Examples:
      | username      | password     |
      | Admin         | admin        |