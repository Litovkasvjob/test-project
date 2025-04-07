package com.litovka.project_example.repository;

import com.litovka.project_example.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Optional<Book> findByTitle(@Param("title") String title);

    @Query("SELECT b FROM Book b WHERE b.author = :author")
    List<Book> findByAuthor(@Param("author") String author);

    @Query("SELECT b FROM Book b WHERE b.publishedYear BETWEEN :startYear AND :endYear")
    List<Book> findByPublishedYearBetween(@Param("startYear") Integer startYear, @Param("endYear") Integer endYear);
}
