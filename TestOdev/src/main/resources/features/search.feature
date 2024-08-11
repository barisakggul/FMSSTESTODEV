Feature: User Search Functionality

  Scenario Outline: Search for a valid user
    Given user visits the websites
    When User enters "<search_term>" in the search box
    And User clicks the search button
    Then The results page should display relevant results for "<search_term>"
    And browser closes

    Examples:
      | search_term |
      | Admin       |
      | John         |

  Scenario Outline: Search for a non-existent user
    Given user visits the websites
    When User enters "<search_term>" in the search box
    And User clicks the search button
    Then The results page should display "No results found" message
    And browser closes

    Examples:
      | search_term |
      | NonExistent |
      | XYZ         |

  Scenario: Leave the search box empty
    Given user visits the websites
    When User leaves the search box empty
    And User clicks the search button
    Then An alert should be displayed saying "Search term cannot be empty"
    And browser closes

  Scenario Outline: Search with special characters
    Given user visits the websites
    When User enters "<search_term>" in the search box
    And User clicks the search button
    Then The results page should display relevant results for "<search_term>"
    And browser closes

    Examples:
      | search_term |
      | @Admin      |
      | #John        |

  Scenario Outline: Search with leading and trailing spaces
    Given user visits the websites
    When User enters "<search_term>" (spaces) in the search box
    And User clicks the search button
    Then The results page should display relevant results for "<search_term>"
    And browser closes

    Examples:
      | search_term |
      |  Admin  |
      |  John   |

  Scenario Outline: Search for multiple terms
    Given user visits the websites
    When User enters "<search_term1>" and "<search_term2>" in the search box
    And User clicks the search button
    Then The results page should display relevant results for "<search_term1>" or "<search_term2>"
    And browser closes

    Examples:
      | search_term1 | search_term2 |
      | Admin        | User         |
      | John         | Smith        |

  Scenario: Search and verify empty results
    Given user visits the websites
    When User enters a query that returns no results
    And User clicks the search button
    Then The results page should display "No results found" message
    And browser closes

##