package com.example.LibraryManagementSystem.Common;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseQuery {
    @Min(0)
    private int page = 0;

    @Positive
    @Max(100)
    private int pageSize = 10;

    @Pattern(regexp = "asc|desc", flags = Pattern.Flag.CASE_INSENSITIVE, message = "sortOrder must be 'asc' or 'desc'")
    private String sortOrder = "asc";

    private String sortField = "id";

}
