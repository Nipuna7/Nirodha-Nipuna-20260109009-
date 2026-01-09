package com.example.bookmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "borrowing_records")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BorrowingRecordModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrowing_id")
    private Long borrowingId;


    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookModel book;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    private LocalDate borrowedDate;
    private LocalDate returningDate;

    @Column(name = "is_returned")
    private boolean isReturned = false;
}

