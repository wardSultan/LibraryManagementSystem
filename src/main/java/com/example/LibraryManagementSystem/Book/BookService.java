package com.example.LibraryManagementSystem.Book;

import com.example.LibraryManagementSystem.Book.Dto.BookQueryFilterDto;
import com.example.LibraryManagementSystem.Book.Dto.CreateBookDto;
import com.example.LibraryManagementSystem.Book.Dto.UpdateBookDto;
import com.example.LibraryManagementSystem.Common.Exceptions.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookService {

     final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> getAllBooks(@Valid BookQueryFilterDto query) {

        Sort sort = query.getSortOrder().equalsIgnoreCase("asc") ?
                Sort.by(query.getSortField()).ascending() : Sort.by(query.getSortField()).descending();

        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize(), sort);

        return this.bookRepository.findAll(pageable);
    }

    @Cacheable(value = "books", key = "#id")
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book", id));
    }

    public Book addBook(CreateBookDto body) {
        Book book = new Book();
        book.setTitle(body.getTitle());
        book.setAuthor(body.getAuthor());
        book.setPublicationYear(body.getPublicationYear());
        book.setIsbn(body.getIsbn());

        return bookRepository.save(book);
    }

    public Book updateBook(Long id, UpdateBookDto body) {
        Book book = this.getBookById(id);

        if (body.getTitle() != null) {
            book.setTitle(body.getTitle());
        }
        if (body.getAuthor() != null) {
            book.setAuthor(body.getAuthor());
        }
        if (body.getPublicationYear() != null) {
            book.setPublicationYear(body.getPublicationYear());
        }
        if (body.getIsbn() != null) {
            book.setIsbn(body.getIsbn());
        }

        return bookRepository.save(book);
    }

    public Book deleteBook(Long id) {

        Book book = this.getBookById(id);
        if (book == null) {

            throw new EntityNotFoundException("Book",id);
        }

        bookRepository.deleteById(id);
        return book;
    }
}
