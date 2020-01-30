package com.example.AdService.repository;

import com.example.AdService.document.Ad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;


@Repository
public interface AdRepository extends MongoRepository<Ad,String> {

    //todo : make this call as findByTagsIn and pass a list of tags
    List<Ad> findByTag(String tag);
    List<Ad> findByAdId(String adId);
}
