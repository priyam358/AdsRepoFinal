package com.example.AdService.document;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Category {

    @Id
    String categoryId;
    String categoryName;
    List<String> tags;
}
