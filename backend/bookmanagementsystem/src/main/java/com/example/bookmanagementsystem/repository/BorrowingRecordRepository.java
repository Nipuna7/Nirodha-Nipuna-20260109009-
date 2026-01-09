package com.example.bookmanagementsystem.repository;

import com.example.bookmanagementsystem.entity.BorrowingRecordModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecordModel, Long> {

    List<BorrowingRecordModel> findByBook_BookId(Long bookId);

    List<BorrowingRecordModel> findByUser_UserId(Long userId);

    List<BorrowingRecordModel> findByIsReturnedFalse();

    List<BorrowingRecordModel> findByIsReturnedTrue();
}

