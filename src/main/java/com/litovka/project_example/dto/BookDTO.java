package com.litovka.project_example.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import static com.litovka.project_example.constants.ProjectConstants.AUTHOR_MAX_LENGTH;
import static com.litovka.project_example.constants.ProjectConstants.EARLIEST_YEAR;
import static com.litovka.project_example.constants.ProjectConstants.LATEST_YEAR;
import static com.litovka.project_example.constants.ProjectConstants.TITLE_MAX_LENGTH;

@Data
public class BookDTO {

    private Long id;

    @NotBlank(message = "Title is mandatory")
    @Size(max = TITLE_MAX_LENGTH, message = "Title must not exceed " + TITLE_MAX_LENGTH + " characters")
    private String title;

    @NotBlank(message = "Author is mandatory")
    @Size(max = AUTHOR_MAX_LENGTH, message = "Author must not exceed " + AUTHOR_MAX_LENGTH + " characters")
    private String author;

    @NotNull(message = "Published year is mandatory")
    @Min(value = EARLIEST_YEAR, message = "Year must be no earlier than " + EARLIEST_YEAR)
    @Max(value = LATEST_YEAR, message = "Year must be no later than " + LATEST_YEAR)
    private Integer year;
}