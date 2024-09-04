# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.5/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#web)
* [Validation](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#io.validation)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)

* [a Tutorial](https://spring.io/guides/gs/spring-boot/)
* [Baeldung tutorial](https://www.baeldung.com/spring-boot-h2-database)

# Database

For opening the H2 database console go to  http://localhost:8080/h2-console after starting the application

JDBC URL for connection: jdbc:h2:mem:testdb
username/password = sa/password

query for getting data:
SELECT a.title, b.firstname ||' '|| b.lastname author, a.isbn FROM
BOOKS a, authors b
where a.author = b.id

# Endpoints
## OpenAPI
Open the Swagger interface for documentation of the api
http://localhost:8080/swagger-ui.html

## Book

Explanation of the methods:
POST /books/:

Method: createBook
Description: This method allows you to add a new book. It takes a Book object in the request body and saves it to the database.
Return: The saved Book object.
PUT /books/{id}:

Method: updateBook
Description: This method allows you to update an existing book. It first checks if the book with the given ID exists, and if it does, it updates the fields of the book with the data from the updatedBook object.
Return: The updated Book object.
DELETE /books/{id}:

Method: deleteBook
Description: This method deletes the book with the specified ID. If the book with the given ID doesn't exist, it throws a BookNotFoundException.
Return: The method returns void, meaning no content will be sent back to the client.
Make sure to handle BookNotFoundException properly in your application by either creating a custom exception handler or using a global exception handler to return appropriate HTTP status codes and messages.

## Author

Explanation of the methods:
POST /authors/:

Method: createAuthor
Description: This method allows you to add a new author. It takes an Author object in the request body and saves it to the database.
Return: The saved Author object.
PUT /authors/{id}:

Method: updateAuthor
Description: This method allows you to update an existing author. It first checks if the author with the given ID exists, and if it does, it updates the fields of the author with the data from the updatedAuthor object.
Return: The updated Author object.
DELETE /authors/{id}:

Method: deleteAuthor
Description: This method deletes the author with the specified ID. If the author with the given ID doesn't exist, it throws an AuthorNotFoundException.
Return: The method returns void, meaning no content will be sent back to the client.
Just like with the BookController, ensure that the AuthorNotFoundException is handled properly, either by creating a custom exception handler or using a global exception handler in your application.

