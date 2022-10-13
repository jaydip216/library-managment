package com.library.controller;

import static org.testng.Assert.assertNotNull;
import java.util.ArrayList;
import java.util.Arrays;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.library.entity.Books;
import com.library.service.impl.BookServiceImpl;

public class LibraryControllerTest {

    @InjectMocks
    private LibraryController libraryController;

    @Mock
    private BookServiceImpl bookServiceImpl;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllBooks() {
        Mockito.when(bookServiceImpl.getAllBooks()).thenReturn(new ArrayList<Books>(Arrays.asList(getBookObject())));
        assertNotNull(libraryController.getAllBooks());
    }

    @Test
    public void getBooksByName() {
        Mockito.when(bookServiceImpl.searchByBookName(Mockito.anyString()))
                .thenReturn(new ArrayList<Books>(Arrays.asList(getBookObject())));
        assertNotNull(libraryController.getBooksByName("Atomic Habit"));
    }

    @Test
    public void getBooksByAuthorName() {
        Mockito.when(bookServiceImpl.searchByAuthor(Mockito.anyString()))
                .thenReturn(new ArrayList<Books>(Arrays.asList(getBookObject())));
        assertNotNull(libraryController.getBooksByAuthorName("James Clear"));
    }

    @Test
    public void addBook() {
        Mockito.when(bookServiceImpl.addBook(Mockito.any())).thenReturn(getBookObject());
        assertNotNull(libraryController.addBook(getBookObject()));
    }

    @Test
    public void removeBook() {
        Mockito.when(bookServiceImpl.removeBook(Mockito.anyString())).thenReturn("Book Discontinued");
        assertNotNull(libraryController.removeBook("5445656565"));
    }

    private Books getBookObject() {
        Books book = new Books();
        book.setIsbn("123456856");
        book.setAuthor("Dale Carnege");
        book.setBookName("How To Win Friends And Influence People");
        book.setGenre("Communication");
        book.setQuantity(4);
        return book;
    }

}
