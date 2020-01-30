package com.example.AdService.services;

import com.example.AdService.document.Ad;
import org.apache.kafka.common.protocol.types.Field;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface AdService {

    List<Ad> findByTagsIn(Iterator<String> tags);
    List<Ad> findByAdId(String adId);
}
