package com.example.AdService.services.impl;

import com.example.AdService.document.Ad;
import com.example.AdService.repository.AdRepository;
import com.example.AdService.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AdServiceImpl implements AdService {


    @Autowired
    AdRepository adRepository;

    @Override
    public List<Ad> findByTagsIn(Iterator<String> tags) {
        return adRepository.findByTagsIn(tags);
    }


    @Override
    public List<Ad> findByAdId(String adId) {
        return adRepository.findByAdId(adId);
    }
}
