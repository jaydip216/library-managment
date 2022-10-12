package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.library.entity.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

}
