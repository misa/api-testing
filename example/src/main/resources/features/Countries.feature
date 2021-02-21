Feature: Kotlin

  Scenario: Search for Czech republic
    When I search for "Czech"
    Then I find 1 result
    And Name is "Czech Republic"
