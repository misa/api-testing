Feature: Kotlin

  Scenario: Get London underground status
    Given Underground status is available
    When I get current status
    Then There is 11 lines available
