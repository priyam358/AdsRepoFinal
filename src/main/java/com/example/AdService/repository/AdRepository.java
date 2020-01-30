package com.example.AdService.repository;

import com.example.AdService.document.Ad;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AdRepository extends MongoRepository<Ad,String> {

    //todo : make this call as findByTagsIn and pass a list of tags
    Optional<List<Ad>> findByTags(String tag);
    List<Ad> findByAdId(String adId);
}
