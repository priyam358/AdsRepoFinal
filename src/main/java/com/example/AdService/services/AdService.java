package com.example.AdService.services;

import com.example.AdService.document.Ad;

import java.util.List;
import java.util.Optional;

public interface AdService {

    Optional<List<Ad>> findByTags(String tag);
}
