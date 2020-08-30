package com.geekbrains.book.store.endpoints;

import com.geekbrains.book.store.GetAllBooksResponse;
import com.geekbrains.book.store.GetBookByIdRequest;
import com.geekbrains.book.store.GetBookByIdResponse;
import com.geekbrains.book.store.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class BooksEndpoint {
    private static final String NAMESPACE_URI = "http://vtb.com/vladislav/spring/web/services/lesson12/homework";

    private BookService bookService;

    @Autowired
    public BooksEndpoint(BookService bookService) {
        this.bookService = bookService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookByIdRequest")
    @ResponsePayload
    public GetBookByIdResponse getBookById (@RequestPayload GetBookByIdRequest request) {
        GetBookByIdResponse response = new GetBookByIdResponse();
        response.setBook(bookService.findById(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllBooksRequest")
    @ResponsePayload
    public GetAllBooksResponse getAllBooks() {
        GetAllBooksResponse response = new GetAllBooksResponse();
        response.setBook(bookService.findAll());
        return response;
    }
}
