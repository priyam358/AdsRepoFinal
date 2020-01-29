package com.example.AdService.repository;

import com.example.AdService.document.Ad;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdRepository extends MongoRepository<Ad,String> {

}
