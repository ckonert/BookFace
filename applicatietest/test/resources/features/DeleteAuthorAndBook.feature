Feature: Adding Author and Book

  Scenario: Delete an existing book
    Given I have a Spring Boot application
    When I run the application
    Then it should work correctly

  Scenario: Delete an existing author that has no book
    Given I have a Spring Boot application
    When I run the application
    Then it should work correctly

  Scenario: Delete an existing author that still has books
    Given I have a Spring Boot application
    When I run the application
    Then it should work correctly
