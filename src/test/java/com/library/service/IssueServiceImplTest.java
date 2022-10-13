package com.library.service;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertThrows;
import java.util.Date;
import java.util.Optional;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.library.entity.Books;
import com.library.entity.Issue;
import com.library.entity.User;
import com.library.exceptions.type.ApplicationException;
import com.library.repository.BookRepository;
import com.library.repository.IssueRepository;
import com.library.repository.UserRepository;
import com.library.service.impl.IssueServiceImpl;

public class IssueServiceImplTest {

    @InjectMocks
    private IssueServiceImpl issueServiceImpl;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private IssueRepository issueRepository;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void issue() {
        Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(getBookObject());
        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(getUser()));
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(getBookObject().get());
        Mockito.when(issueRepository.save(Mockito.any())).thenReturn(getIssue());
        assertNotNull(issueServiceImpl.issue("jaydip21", "4565321245987"));
    }

    @Test
    public void issueBookNotFound() {
        Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(null));
        assertThrows(ApplicationException.class, () -> issueServiceImpl.issue("jaydip21", "4565321245987"));
    }

    @Test
    public void issueBookNotAvailable() {
        Books book = getBookObject().get();
        book.setQuantity(0);
        Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(book));
        assertThrows(ApplicationException.class, () -> issueServiceImpl.issue("jaydip21", "4565321245987"));
    }

    @Test
    public void issueUserNotFound() {
        Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(getBookObject());
        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(null));
        assertThrows(ApplicationException.class, () -> issueServiceImpl.issue("jaydip21", "4565321245987"));
    }

    @Test
    public void updateReturnDate() {
        Mockito.when(issueRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(getIssue()));
        Mockito.when(issueRepository.save(Mockito.any())).thenReturn(getIssue());
        assertNotNull(issueServiceImpl.updateReturnDate(2, new Date()));
    }

    @Test
    public void updateReturnDateException() {
        Mockito.when(issueRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
        assertThrows(ApplicationException.class, () -> issueServiceImpl.updateReturnDate(2, new Date()));
    }

    @Test
    public void returnBook() {
        Mockito.when(issueRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(getIssue()));
        Mockito.doNothing().when(issueRepository).deleteById(Mockito.anyLong());
        assertNotNull(issueServiceImpl.returnBook(1));
    }

    @Test
    public void returnBook2() {
        Issue issue = getIssue();
        issue.setReturnDate(new Date(System.currentTimeMillis() + (60 * 1000)));
        Mockito.when(issueRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(issue));
        Mockito.doNothing().when(issueRepository).deleteById(Mockito.anyLong());
        assertNotNull(issueServiceImpl.returnBook(1));
    }

    @Test
    public void returnBookException() {
        Mockito.when(issueRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
        assertThrows(ApplicationException.class, () -> issueServiceImpl.returnBook(2));
    }

    private Issue getIssue() {
        Issue issue = new Issue();
        issue.setId(2);
        issue.setUserName("hjkhd");
        issue.setIsbn("54");
        issue.setIssueDate(new Date());
        issue.setReturnDate(new Date());
        return issue;
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

    private User getUser() {
        User user = new User();
        user.setUserName("jaydip21");
        user.setPassword("jaydip#23");
        user.setEmail("jaydip@email.com");
        user.setFirstName("Jaydip");
        user.setLastName("Bhanderi");
        user.setPhoneNo("1562325981");
        return user;
    }
}
