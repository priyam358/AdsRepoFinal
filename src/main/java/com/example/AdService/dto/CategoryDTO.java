package com.example.AdService.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class CategoryDTO {

    String categoryId;
    String categoryName;
    List<String> tags;
}
