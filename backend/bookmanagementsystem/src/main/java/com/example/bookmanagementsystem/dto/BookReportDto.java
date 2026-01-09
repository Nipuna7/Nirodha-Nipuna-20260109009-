package com.example.bookmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookReportDto {
    private Long totalBooksCount;
    private Long totalQuantity;
    private List<GenreReportDto> genreReport;
}

