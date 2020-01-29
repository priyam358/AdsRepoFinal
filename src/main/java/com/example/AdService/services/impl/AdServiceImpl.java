package com.example.AdService.services.impl;

import com.example.AdService.document.Ad;
import com.example.AdService.repository.AdRepository;
import com.example.AdService.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdServiceImpl implements AdService {


    @Autowired
    AdRepository adRepository;

    @Override
    public Optional<List<Ad>> findByTags(String tag) {
        return adRepository.findByTags(tag);
    }
}
