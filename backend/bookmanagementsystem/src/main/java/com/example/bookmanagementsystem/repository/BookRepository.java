package com.example.bookmanagementsystem.repository;

import com.example.bookmanagementsystem.entity.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookModel,Long> {

    // Get total count of all books
    @Query("SELECT COUNT(b) FROM BookModel b")
    Long countTotalBooks();

    // Get total quantity of all books
    @Query("SELECT SUM(b.quantity) FROM BookModel b")
    Long sumTotalQuantity();

    // Get books grouped by genre with count and quantity
    @Query("SELECT b.genre, COUNT(b), SUM(b.quantity) FROM BookModel b GROUP BY b.genre")
    List<Object[]> getBooksByGenre();
}


