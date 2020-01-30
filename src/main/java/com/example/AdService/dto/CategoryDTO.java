package com.example.AdService.dto;

import lombok.NonNull;

import java.util.List;

public class CategoryDTO {

    @NonNull
    private String categoryId;
    @NonNull
    private String categoryName;
    @NonNull
    private List<String> tags;
}
