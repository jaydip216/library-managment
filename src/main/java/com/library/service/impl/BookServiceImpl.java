package com.library.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.library.constants.LibraryConstants;
import com.library.entity.Books;
import com.library.exceptions.type.ApplicationException;
import com.library.repository.BookRepository;
import com.library.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Books> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Books addBook(final Books book) {
        final Optional<Books> bookFromDb = bookRepository.findById(book.getIsbn());
        if (bookFromDb.isPresent()) {
            bookFromDb.get().setQuantity(bookFromDb.get().getQuantity() + book.getQuantity());
            return bookRepository.save(bookFromDb.get());
        }
        return bookRepository.save(book);
    }

    @Override
    public String removeBook(final String isbn) {
        final Optional<Books> book = bookRepository.findById(isbn);
        if (!book.isPresent()) {
            throw new ApplicationException(LibraryConstants.BOOK_NOT_FOUND + "ISBN", HttpStatus.NOT_FOUND);
        }
        bookRepository.deleteById(isbn);
        return LibraryConstants.BOOK_DISCONTINUED;
    }

    @Override
    public List<Books> searchByBookName(final String bookName) {
        final List<Books> books = bookRepository.findByBookName(bookName);
        if (books.size() == 0) {
            throw new ApplicationException(LibraryConstants.BOOK_NOT_FOUND + "book name", HttpStatus.NOT_FOUND);
        }
        return books;
    }

    @Override
    public List<Books> searchByAuthor(final String author) {
        final List<Books> books = bookRepository.findByAuthor(author);
        if (books.size() == 0) {
            throw new ApplicationException(LibraryConstants.BOOK_NOT_FOUND + "author", HttpStatus.NOT_FOUND);
        }
        return books;
    }
}
