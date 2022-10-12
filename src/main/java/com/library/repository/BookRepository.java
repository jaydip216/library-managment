package com.library.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.library.entity.Books;

@Repository
public interface BookRepository extends JpaRepository<Books, String> {

    public List<Books> findByBookName(String bookName);

    public List<Books> findByAuthor(String author);

}
