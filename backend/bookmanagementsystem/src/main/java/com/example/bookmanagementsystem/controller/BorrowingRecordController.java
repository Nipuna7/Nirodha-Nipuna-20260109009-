package com.example.bookmanagementsystem.controller;

import com.example.bookmanagementsystem.entity.BorrowingRecordModel;
import com.example.bookmanagementsystem.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/borrowings")
@CrossOrigin(origins = "*")
public class BorrowingRecordController {

    @Autowired
    private BorrowingRecordService borrowingRecordService;

    // Display all borrowing details
    @GetMapping
    public ResponseEntity<List<BorrowingRecordModel>> getAllBorrowingDetails() {
        List<BorrowingRecordModel> borrowings = borrowingRecordService.getAllBorrowingDetails();
        return ResponseEntity.ok(borrowings);
    }

    //Get a single borrowing record by ID
    @GetMapping("/{id}")
    public ResponseEntity<BorrowingRecordModel> getBorrowingById(@PathVariable Long id) {
        BorrowingRecordModel borrowing = borrowingRecordService.getBorrowingById(id);
        return ResponseEntity.ok(borrowing);
    }

    // Get all currently borrowed books
    @GetMapping("/current")
    public ResponseEntity<List<BorrowingRecordModel>> getCurrentBorrowings() {
        List<BorrowingRecordModel> borrowings = borrowingRecordService.getCurrentBorrowings();
        return ResponseEntity.ok(borrowings);
    }

    // Get all returned books
    @GetMapping("/returned")
    public ResponseEntity<List<BorrowingRecordModel>> getReturnedBorrowings() {
        List<BorrowingRecordModel> borrowings = borrowingRecordService.getReturnedBorrowings();
        return ResponseEntity.ok(borrowings);
    }

    // Get borrowing history for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BorrowingRecordModel>> getUserBorrowingHistory(@PathVariable Long userId) {
        List<BorrowingRecordModel> borrowings = borrowingRecordService.getUserBorrowingHistory(userId);
        return ResponseEntity.ok(borrowings);
    }

    // Get borrowing history for a specific book
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<BorrowingRecordModel>> getBookBorrowingHistory(@PathVariable Long bookId) {
        List<BorrowingRecordModel> borrowings = borrowingRecordService.getBookBorrowingHistory(bookId);
        return ResponseEntity.ok(borrowings);
    }

    // POST /api/borrowings/borrow - Borrow a book
     //Request body example: {"userId": 1, "bookId": 1, "returningDate": "2026-01-20"}
    @PostMapping("/borrow")
    public ResponseEntity<BorrowingRecordModel> borrowBook(
            @RequestParam Long userId,
            @RequestParam Long bookId,
            @RequestParam String returningDate) {
        LocalDate returnDate = LocalDate.parse(returningDate);
        BorrowingRecordModel borrowing = borrowingRecordService.borrowBook(userId, bookId, returnDate);
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowing);
    }

    // Return a borrowed book
    @PutMapping("/{id}/return")
    public ResponseEntity<BorrowingRecordModel> returnBook(@PathVariable Long id) {
        BorrowingRecordModel borrowing = borrowingRecordService.returnBook(id);
        return ResponseEntity.ok(borrowing);
    }
}

