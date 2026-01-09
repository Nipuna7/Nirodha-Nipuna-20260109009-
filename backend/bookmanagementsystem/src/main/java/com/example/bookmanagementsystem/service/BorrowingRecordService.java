package com.example.bookmanagementsystem.service;

import com.example.bookmanagementsystem.entity.BookModel;
import com.example.bookmanagementsystem.entity.BorrowingRecordModel;
import com.example.bookmanagementsystem.entity.UserModel;
import com.example.bookmanagementsystem.repository.BookRepository;
import com.example.bookmanagementsystem.repository.BorrowingRecordRepository;
import com.example.bookmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public BorrowingRecordModel borrowBook(Long userId, Long bookId, LocalDate returningDate) {

        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BookModel book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getQuantity() <= 0) {
            throw new RuntimeException("Book not available");
        }

        BorrowingRecordModel record = new BorrowingRecordModel();
        record.setUser(user);           // Link to user
        record.setBook(book);           // Link to book
        record.setBorrowedDate(LocalDate.now());
        record.setReturningDate(returningDate);
        record.setReturned(false);


        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        return borrowingRecordRepository.save(record);
    }


    public BorrowingRecordModel returnBook(Long borrowingId) {
        BorrowingRecordModel record = borrowingRecordRepository.findById(borrowingId)
                .orElseThrow(() -> new RuntimeException("Borrowing record not found"));

        if (record.isReturned()) {
            throw new RuntimeException("Book already returned");
        }

        record.setReturned(true);

        BookModel book = record.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);

        return borrowingRecordRepository.save(record);
    }

    public List<BorrowingRecordModel> getUserBorrowingHistory(Long userId) {
        return borrowingRecordRepository.findByUser_UserId(userId);
    }

    public List<BorrowingRecordModel> getBookBorrowingHistory(Long bookId) {
        return borrowingRecordRepository.findByBook_BookId(bookId);
    }

    public List<BorrowingRecordModel> getCurrentBorrowings() {
        return borrowingRecordRepository.findByIsReturnedFalse();
    }

    public List<BorrowingRecordModel> getReturnedBorrowings() {
        return borrowingRecordRepository.findByIsReturnedTrue();
    }

    // Get all borrowing details
    public List<BorrowingRecordModel> getAllBorrowingDetails() {
        return borrowingRecordRepository.findAll();
    }

    //Get a single borrowing record by ID
    public BorrowingRecordModel getBorrowingById(Long borrowingId) {
        return borrowingRecordRepository.findById(borrowingId)
                .orElseThrow(() -> new RuntimeException("Borrowing record not found with id: " + borrowingId));
    }
}

