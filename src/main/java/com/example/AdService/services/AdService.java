package com.example.AdService.services;

import com.example.AdService.document.Ad;
import com.example.AdService.document.UserCache;
import org.apache.kafka.common.protocol.types.Field;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface AdService {

    List<Ad> findByTag(String tag);
    List<Ad> findByAdId(String adId);

    UserCache getAds(String userId);
}
