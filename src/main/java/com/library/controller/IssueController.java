package com.library.controller;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.library.entity.Issue;
import com.library.model.IssueRequest;
import com.library.service.IssueService;

@RestController
@RequestMapping("/issue")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping
    @RolesAllowed({ "ROLE_LIBRARIAN", "ROLE_USER" })
    public ResponseEntity<Issue> issueBook(@RequestBody @Valid IssueRequest issueRequest) {
        return ResponseEntity.ok(issueService.issue(issueRequest.getUserName(), issueRequest.getIsbn()));
    }

    @PutMapping(value = "/update")
    @RolesAllowed("ROLE_LIBRARIAN")
    public ResponseEntity<Issue> updateDate(@RequestBody Issue checkouts) {
        return ResponseEntity.ok(issueService.updateReturnDate(checkouts.getId(), checkouts.getReturnDate()));
    }

    @PostMapping(value = "/return")
    @RolesAllowed({ "ROLE_LIBRARIAN", "ROLE_USER" })
    public ResponseEntity<String> returnBook(@RequestBody Issue checkouts) {
        return ResponseEntity.ok(issueService.returnBook(checkouts.getId()));
    }
}
