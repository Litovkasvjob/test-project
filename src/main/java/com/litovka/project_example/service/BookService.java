package com.litovka.project_example.service;

import com.litovka.project_example.dto.BookDTO;
import com.litovka.project_example.mapper.BookMapper;
import com.litovka.project_example.model.Book;
import com.litovka.project_example.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public Optional<Book> findBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> findBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findBooksPublishedBetween(int startYear, int endYear) {
        return bookRepository.findByPublishedYearBetween(startYear, endYear);
    }

    /**
     * Saves or updates a book in the repository based on the provided BookDTO.
     * If the book already exists, it updates the existing record; otherwise, it creates a new record.
     *
     * @param bookDTO the data transfer object representing the book to be saved or updated
     * @return a BookDTO representing the saved or updated book
     */
    public BookDTO saveOrUpdateBook(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book); //TODO: how to update
        return bookMapper.toDTO(savedBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Page<Book> findAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public BookDTO findBookById(Long bookId) {


        log.info("Retrieving book with ID: {}", bookId);

        return bookRepository.findById(bookId)
                .map(bookMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + bookId + " not found"));

    }

    public List<Book> searchBooks(String title, String author, Integer year) {
        return bookRepository.searchBooks(title, author, year);
    }
}
