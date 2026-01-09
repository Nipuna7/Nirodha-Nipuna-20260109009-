package com.example.bookmanagementsystem.service;

import com.example.bookmanagementsystem.dto.BookReportDto;
import com.example.bookmanagementsystem.dto.GenreReportDto;
import com.example.bookmanagementsystem.entity.BookModel;
import com.example.bookmanagementsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Get all books in the inventory with their details
    public List<BookModel> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get a single book by ID
    public BookModel getBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
    }

    // Add a new book to the inventory
    public BookModel addBook(BookModel book) {
        return bookRepository.save(book);
    }

    // Update book details
    public BookModel updateBook(Long bookId, BookModel bookDetails) {
        BookModel book = getBookById(bookId);
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setGenre(bookDetails.getGenre());
        book.setQuantity(bookDetails.getQuantity());
        return bookRepository.save(book);
    }

    // Delete a book
    public void deleteBook(Long bookId) {
        BookModel book = getBookById(bookId);
        bookRepository.delete(book);
    }

    // Generate book report: total books and books available by genre
    public BookReportDto getBookReport() {
        BookReportDto report = new BookReportDto();

        // Get total number of different books
        Long totalBooks = bookRepository.countTotalBooks();
        report.setTotalBooksCount(totalBooks != null ? totalBooks : 0L);

        // Get total quantity of all books
        Long totalQuantity = bookRepository.sumTotalQuantity();
        report.setTotalQuantity(totalQuantity != null ? totalQuantity : 0L);

        // Get books grouped by genre
        List<Object[]> genreData = bookRepository.getBooksByGenre();
        List<GenreReportDto> genreReports = new ArrayList<>();

        for (Object[] data : genreData) {
            String genre = (String) data[0];
            Long count = (Long) data[1];
            Long quantity = (Long) data[2];
            genreReports.add(new GenreReportDto(genre, count, quantity));
        }

        report.setGenreReport(genreReports);

        return report;
    }
}
