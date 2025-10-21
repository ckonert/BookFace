Feature: Book Creation

  Scenario: Add a book
    Given the book data is prepared
    When a POST request is made to create a book
    Then the book is successfully added

  Scenario: Get all the books
    Given a GET request is made to the books endpoint
    Then the book data is returned