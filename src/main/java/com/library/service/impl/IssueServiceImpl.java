package com.library.service.impl;

import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.library.constants.LibraryConstants;
import com.library.entity.Books;
import com.library.entity.Issue;
import com.library.entity.User;
import com.library.exceptions.type.ApplicationException;
import com.library.repository.BookRepository;
import com.library.repository.IssueRepository;
import com.library.repository.UserRepository;
import com.library.service.IssueService;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Issue issue(final String userName, final String isbn) {
        final Optional<Books> book = bookRepository.findById(isbn);
        if (!book.isPresent()) {
            throw new ApplicationException(LibraryConstants.BOOK_NOT_FOUND + "ISBN", HttpStatus.NOT_FOUND);
        }
        if (book.get().getQuantity() <= 0) {
            throw new ApplicationException(LibraryConstants.BOOK_NOT_AVAILABLE + "ISBN", HttpStatus.NOT_FOUND);
        }
        final Optional<User> user = userRepository.findById(userName);
        if (!user.isPresent()) {
            throw new ApplicationException(LibraryConstants.USER_NOT_FOUND + "userName", HttpStatus.NOT_FOUND);
        }
        book.get().setQuantity(book.get().getQuantity() - 1);
        Books updatedBook = bookRepository.save(book.get());

        final Issue checkout = new Issue();
        checkout.setIsbn(isbn);
        checkout.setUserName(userName);
        checkout.setIssueDate(new Date());
        checkout.setReturnDate(new Date(System.currentTimeMillis() + LibraryConstants.returnTimeInMillis));

        return issueRepository.save(checkout);

    }

    @Override
    public Issue updateReturnDate(final long id, final Date date) {
        final Optional<Issue> checkout = issueRepository.findById(id);
        if (!checkout.isPresent()) {
            throw new ApplicationException(LibraryConstants.NO_RECORD_FOUND + "Id", HttpStatus.NOT_FOUND);
        }
        checkout.get().setReturnDate(date);
        return issueRepository.save(checkout.get());
    }

    @Override
    public String returnBook(final long id) {
        final Optional<Issue> checkout = issueRepository.findById(id);
        if (!checkout.isPresent()) {
            throw new ApplicationException(LibraryConstants.NO_RECORD_FOUND + "Id", HttpStatus.NOT_FOUND);
        }
        final Date returnDate = checkout.get().getReturnDate();
        if (returnDate.compareTo(new Date()) >= 0) {
            issueRepository.deleteById(id);
            return LibraryConstants.BOOK_RETURN_WITHOUT_PENALTY;
        } else {
            issueRepository.deleteById(id);
            return LibraryConstants.BOOK_RETURN_WITH_PENALTY;
        }
    }
}
