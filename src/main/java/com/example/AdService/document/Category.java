package com.example.AdService.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "categories")
public class Category {

    @Id
    private String categoryId;
    private String categoryName;
    private List<String> tags;
}
