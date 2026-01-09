package com.example.bookmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenreReportDto {
    private String genre;
    private Long totalBooks;
    private Long totalQuantity;
}

