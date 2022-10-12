package com.library.controller;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.library.entity.Books;
import com.library.service.BookService;

@RestController
@RequestMapping("/books")
public class LibraryController {

    @Autowired
    private BookService bookService;

    @GetMapping
    @RolesAllowed({ "ROLE_LIBRARIAN", "ROLE_USER" })
    public ResponseEntity<List<Books>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping(value = "searchByName/{bookName}")
    @RolesAllowed({ "ROLE_LIBRARIAN", "ROLE_USER" })
    public ResponseEntity<List<Books>> getBooksByName(@PathVariable("bookName") String bookName) {
        return ResponseEntity.ok(bookService.searchByBookName(bookName));
    }

    @GetMapping(value = "searchByAuthor/{authorName}")
    @RolesAllowed({ "ROLE_LIBRARIAN", "ROLE_USER" })
    public ResponseEntity<List<Books>> getBooksByAuthorName(@PathVariable("authorName") String authorName) {
        return ResponseEntity.ok(bookService.searchByAuthor(authorName));
    }

    @PostMapping(value = "/add")
    @RolesAllowed("ROLE_LIBRARIAN")
    public ResponseEntity<Books> addBook(@RequestBody Books books) {
        return ResponseEntity.ok(bookService.addBook(books));
    }

    @DeleteMapping(value = "delete/{isbn}")
    @RolesAllowed("ROLE_LIBRARIAN")
    public ResponseEntity<String> removeBook(@PathVariable("isbn") String isbn) {
        return ResponseEntity.ok(bookService.removeBook(isbn));
    }
}
