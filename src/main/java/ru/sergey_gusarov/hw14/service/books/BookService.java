package ru.sergey_gusarov.hw14.service.books;

import ru.sergey_gusarov.hw14.domain.books.Author;
import ru.sergey_gusarov.hw14.domain.books.Book;
import ru.sergey_gusarov.hw14.domain.books.Genre;

import java.util.List;
import java.util.Optional;

public interface BookService {
    long bookCount();

    Optional<Book> bookGetById(String id);

    List<Book> bookGetByTitle(String title);

    void bookDeleteById(String id);

    Book save(String title, List<Author> authors, List<Genre> genres);

    List<Book> bookList();

    void deleteAll();

    void addComment(String id, String comment);

    List<Book> findByAuthorName(String authorName);
}
