package com.example.AdService.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;

import java.util.List;

@Data
public class CategoryDTO {

    @NonNull
    private String categoryId;
    @NonNull
    private String categoryName;
    @NonNull
    private List<String> tags;
}
