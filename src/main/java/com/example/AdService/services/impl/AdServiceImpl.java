package com.example.AdService.services.impl;

import com.example.AdService.document.Ad;
import com.example.AdService.document.UserCache;
import com.example.AdService.repository.AdRepository;
import com.example.AdService.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AdServiceImpl implements AdService {


    @Autowired
    AdRepository adRepository;

    @Override
    public List<Ad> findByTag(String tag) {
        return adRepository.findByTag(tag);
        //return adRepository.findAll(tags);
    }


    @Override
    public List<Ad> findByAdId(String adId) {
        return adRepository.findByAdId(adId);
    }

    @Override
    @Cacheable(value = "userCache")
    public UserCache getAds(String userId) {

        //Todo : use a feign client to call the finction to get user specific tags
        List<Ad> finalAds = new ArrayList<>();
        List<String> tags = new ArrayList<>();
        tags.add("Clothing");
        tags.add("Cricket");


        for(int i=0;i<tags.size();i++){
            finalAds.addAll(adRepository.findByTag(tags.get(i)));
        }

        System.out.println(finalAds);

        UserCache userCache=new UserCache();
        userCache.setAds(finalAds);
        userCache.setUserId(userId);

        return userCache;

    }
}
