package nl.testwerk.bookface.soap;

import nl.testwerk.bookface.model.Book;
import nl.testwerk.bookface.repository.BookRepository;
import nl.testwerk.bookface.soap.gen.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class BookEndpoint {

    private static final String NAMESPACE_URI = "http://testwerk.nl/bookface/soap";

    private final BookRepository bookRepository;

    public BookEndpoint(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllBooksRequest")
    @ResponsePayload
    public GetAllBooksResponse getAllBooks(@RequestPayload GetAllBooksRequest request) {
        GetAllBooksResponse response = new GetAllBooksResponse();

        List<Book> books = bookRepository.findAll();
        for (Book b : books) {
            nl.testwerk.bookface.soap.gen.Book bookSoap = new nl.testwerk.bookface.soap.gen.Book();
            bookSoap.setId(b.getId());
            bookSoap.setTitle(b.getTitle());
            bookSoap.setIsbn13(b.getIsbn13());
            bookSoap.setAuthorId(b.getAuthorId());
            bookSoap.setImageUrl(b.getImageUrl());
            response.getBooks().add(bookSoap);
        }
        return response;
    }
}
