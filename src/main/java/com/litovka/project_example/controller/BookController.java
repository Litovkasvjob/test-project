package com.litovka.project_example.controller;

import com.litovka.project_example.model.Book;
import com.litovka.project_example.service.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Adds a new book to the database.
     *
     * @param book the book object to be added, validated and provided in the request body
     * @return a ResponseEntity containing the added book object and an HTTP status of 201 CREATED
     */
    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        return new ResponseEntity<>(bookService.saveOrUpdateBook(book), HttpStatus.CREATED);
    }

    /**
     * Updates an existing book in the database.
     *
     * @param bookId the ID of the book to be updated, provided in the path variable
     * @param book   the updated book object, validated and provided in the request body
     * @return a ResponseEntity containing the updated book object and an HTTP status of 200 OK,
     * or an HTTP status of 400 Bad Request if the provided book ID does not match the path variable
     */
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long bookId, @Valid @RequestBody Book book) {
        if (!bookId.equals(book.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Book> bookOptional = bookService.findBookById(bookId);
        if (bookOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(bookService.saveOrUpdateBook(book), HttpStatus.OK);
    }

    /**
     * Deletes a book from the database.
     *
     * @param bookId the ID of the book to be deleted, provided in the request body
     * @return a ResponseEntity with an HTTP status of 200 OK if the deletion was successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieves a book by its ID.
     *
     * @param bookId the ID of the book to be retrieved, provided in the request body
     * @return a ResponseEntity containing the retrieved book object and an HTTP status of 200 OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long bookId) {
        return bookService.findBookById(bookId)
                .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Retrieves all books from the database.
     *
     * @return a ResponseEntity containing a list of all book objects and an HTTP status of 200 OK
     */
    @GetMapping
    public Page<Book> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

        String sortField = sort[0];
        String sortDirection = sort[1];
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDirection) ? Sort.Direction.DESC : Sort.Direction.ASC;
        return bookService.findAllBooks(PageRequest.of(page, size, Sort.by(direction, sortField)));
    }

    /**
     * Searches for books based on the provided search criteria.
     *
     * @param title  the title of the book to search for
     * @param author the author of the book to search for
     * @param year   the year of publication to search for
     * @return a ResponseEntity containing a list of matching book objects and an HTTP status of 200 OK
     */
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Integer year) {
        List<Book> books = bookService.searchBooks(title, author, year);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

}
