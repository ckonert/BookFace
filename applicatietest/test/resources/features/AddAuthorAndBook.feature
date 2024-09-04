Feature: Adding Author and Book

  Scenario: Adding a new book for a new author
    Given I have a Spring Boot application
    When I run the application
    Then it should work correctly

  Scenario: Adding a new book for an existing author
    Given I have a Spring Boot application
    When I run the application
    Then it should work correctly

  Scenario: Adding a new book for an author that does not exist
    Given I have a Spring Boot application
    When I run the application
    Then it should work correctly
