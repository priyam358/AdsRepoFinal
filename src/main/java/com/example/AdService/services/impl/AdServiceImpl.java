package com.example.AdService.services.impl;

import com.example.AdService.repository.AdRepository;
import com.example.AdService.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdServiceImpl implements AdService {


    @Autowired
    AdRepository adRepository;
}
