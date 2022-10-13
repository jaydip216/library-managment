package com.library.controller;

import static org.testng.Assert.assertNotNull;
import java.util.Date;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.library.entity.Issue;
import com.library.model.IssueRequest;
import com.library.service.impl.IssueServiceImpl;

public class IssueControllerTest {

    @InjectMocks
    private IssueController issueController;

    @Mock
    private IssueServiceImpl issueServiceImpl;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void issueBook() {
        Mockito.when(issueServiceImpl.issue(Mockito.anyString(), Mockito.anyString())).thenReturn(getIssue());
        assertNotNull(issueController.issueBook(getIssueRequest()));
    }

    @Test
    public void updateDate() {
        Mockito.when(issueServiceImpl.updateReturnDate(Mockito.anyLong(), Mockito.any())).thenReturn(getIssue());
        assertNotNull(issueController.updateDate(getIssue()));
    }

    @Test
    public void returnBook() {
        Mockito.when(issueServiceImpl.returnBook(Mockito.anyLong())).thenReturn("Book Returned");
        assertNotNull(issueController.returnBook(getIssue()));
    }

    private IssueRequest getIssueRequest() {
        IssueRequest issueRequest = new IssueRequest();
        issueRequest.setIsbn("4562");
        issueRequest.setUserName("hadggd");
        return issueRequest;
    }

    private Issue getIssue() {
        Issue issue = new Issue();
        issue.setId(2);
        issue.setReturnDate(new Date());
        return issue;
    }
}
