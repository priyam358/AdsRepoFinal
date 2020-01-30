package com.example.AdService.services;

import com.example.AdService.document.Category;
import com.example.AdService.dto.AdDTO;
import com.example.AdService.dto.CategoryDTO;
import com.example.AdService.dto.onclickapi.OnClickRequest;

import java.util.List;

public interface AdService {

    void onClick(OnClickRequest onClickRequest);
    String addAd(AdDTO adDTO);

    String addCategory(Category category);

    List<CategoryDTO> getCategories();

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
