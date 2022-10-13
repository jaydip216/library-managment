package com.library.service;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertThrows;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.library.entity.Books;
import com.library.exceptions.type.ApplicationException;
import com.library.repository.BookRepository;
import com.library.service.impl.BookServiceImpl;

public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @Mock
    private BookRepository bookRepository;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllBooks() {
        Mockito.when(bookRepository.findAll()).thenReturn(new ArrayList<Books>(Arrays.asList(getBookObject().get())));
        assertNotNull(bookServiceImpl.getAllBooks());
    }

    @Test
    public void addBook() {
        Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(getBookObject());
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(getBookObject().get());
        assertNotNull(bookServiceImpl.addBook(getBookObject().get()));
    }

    @Test
    public void addBook2() {
        Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(null));
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(getBookObject().get());
        assertNotNull(bookServiceImpl.addBook(getBookObject().get()));
    }

    @Test
    public void removeBook() {
        Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(getBookObject());
        Mockito.doNothing().when(bookRepository).deleteById(Mockito.anyString());
        assertNotNull(bookServiceImpl.removeBook("545454545"));
    }

    @Test
    public void removeBookException() {
        Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(null));
        assertThrows(ApplicationException.class, () -> bookServiceImpl.removeBook("4545452631452"));
    }

    @Test
    public void searchByBookName() {
        Mockito.when(bookRepository.findByBookName(Mockito.anyString()))
                .thenReturn(new ArrayList<Books>(Arrays.asList(getBookObject().get())));
        assertNotNull(bookServiceImpl.searchByBookName("Sepians"));
    }

    @Test
    public void searchByBookNameException() {
        Mockito.when(bookRepository.findByBookName(Mockito.anyString())).thenReturn(new ArrayList<Books>());
        assertThrows(ApplicationException.class, () -> bookServiceImpl.searchByBookName("Sepians"));
    }

    @Test
    public void searchByAuthor() {
        Mockito.when(bookRepository.findByAuthor(Mockito.anyString()))
                .thenReturn(new ArrayList<Books>(Arrays.asList(getBookObject().get())));
        assertNotNull(bookServiceImpl.searchByAuthor("Dale Carneg"));
    }

    @Test
    public void searchByAuthorException() {
        Mockito.when(bookRepository.findByAuthor(Mockito.anyString())).thenReturn(new ArrayList<Books>());
        assertThrows(ApplicationException.class, () -> bookServiceImpl.searchByAuthor("Dale Carneg"));
    }

    private Optional<Books> getBookObject() {
        Books book = new Books();
        book.setIsbn("123456856");
        book.setAuthor("Dale Carnege");
        book.setBookName("How To Win Friends And Influence People");
        book.setGenre("Communication");
        book.setQuantity(4);
        return Optional.ofNullable(book);
    }

}
