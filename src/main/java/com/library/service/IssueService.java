package com.library.service;

import java.util.Date;
import com.library.entity.Issue;

public interface IssueService {

    public Issue issue(final String userName, final String isbn);

    public Issue updateReturnDate(final long id, final Date date);

    public String returnBook(final long id);

}
