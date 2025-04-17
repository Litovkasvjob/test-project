package com.litovka.project_example.mapper;

import com.litovka.project_example.dto.BookDTO;
import com.litovka.project_example.model.Book;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


/**
 * A mapper implementation for converting between {@link Book} entity and {@link BookDTO}.
 * Utilizes {@link ModelMapper} to perform the mapping process.
 * <p>
 * This class adheres to the {@link Mapper} interface, providing methods to map:
 * - From {@link Book} to {@link Book} entity (toEntity method).
 * - From {@link Book} entity to {@link Book} (toDTO method).
 * <p>
 * Internally, this class uses a generic private map method to delegate
 * the mapping logic to the underlying {@link ModelMapper}.
 */
@Component
@RequiredArgsConstructor
public class BookMapper implements Mapper<Book, BookDTO> {

    private final ModelMapper modelMapper;

    @Override
    public Book toEntity(BookDTO dto) {
        return map(dto, Book.class);
    }

    @Override
    public BookDTO toDTO(Book book) {
        return map(book, BookDTO.class);
    }

    private <S, T> T map(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }
}
