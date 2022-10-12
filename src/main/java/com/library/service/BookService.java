package com.library.service;

import java.util.List;
import com.library.entity.Books;

public interface BookService {

    public List<Books> getAllBooks();

    public Books addBook(final Books book);

    public String removeBook(final String isbn);

    public List<Books> searchByBookName(final String bookName);

    public List<Books> searchByAuthor(final String author);

}
