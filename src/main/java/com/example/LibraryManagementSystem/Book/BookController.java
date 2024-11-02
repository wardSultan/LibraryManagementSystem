package com.example.LibraryManagementSystem.Book;
import com.example.LibraryManagementSystem.Book.Dto.BookQueryFilterDto;
import com.example.LibraryManagementSystem.Book.Dto.CreateBookDto;
import com.example.LibraryManagementSystem.Book.Dto.UpdateBookDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@Validated
public class BookController {

    final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Page<Book> getAllBooks(@Valid BookQueryFilterDto query) {
        return new ResponseEntity<>(this.bookService.getAllBooks(query), HttpStatus.OK).getBody();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody CreateBookDto body) {
        return new ResponseEntity<>(bookService.addBook(body), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Book>  updateBook(@PathVariable Long id,@Valid @RequestBody UpdateBookDto body) {
        return new ResponseEntity<>(bookService.updateBook(id, body), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {

        return new ResponseEntity<>(bookService.deleteBook(id),HttpStatus.OK);
    }
}

