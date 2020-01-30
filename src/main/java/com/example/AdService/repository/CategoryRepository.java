package com.example.AdService.repository;

import com.example.AdService.document.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository <Category,String> {
}
