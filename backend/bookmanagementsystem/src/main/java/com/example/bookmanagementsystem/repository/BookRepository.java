package com.example.bookmanagementsystem.repository;

import com.example.bookmanagementsystem.entity.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookModel,Long> {
}
