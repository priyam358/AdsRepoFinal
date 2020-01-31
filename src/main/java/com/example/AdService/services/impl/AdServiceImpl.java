package com.example.AdService.services.impl;

import com.example.AdService.document.Ad;
import com.example.AdService.document.Category;
import com.example.AdService.dto.AdDTO;
import com.example.AdService.dto.CategoryDTO;
import com.example.AdService.dto.onclickapi.OnClickCRM;
import com.example.AdService.dto.onclickapi.OnClickRequest;

import com.example.AdService.document.UserCache;
import com.example.AdService.feignclient.AnalyticsClient;
import com.example.AdService.repository.AdRepository;
import com.example.AdService.repository.CategoryRepository;
import com.example.AdService.services.AdService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import java.util.ArrayList;

@Service
public class AdServiceImpl implements AdService {


    @Autowired
    AdRepository adRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    KafkaTemplate<String,OnClickCRM> kafkaTemplate;

    @Autowired
    AnalyticsClient analyticsClient;





    @Override
    public void onClick(OnClickCRM onClickCRM) {

        sendOnclick(onClickCRM);


    }

    @Override
    public String addAd(AdDTO adDTO) {

        Ad ad = new Ad();

        BeanUtils.copyProperties(adDTO,ad);
        return adRepository.save(ad).toString();

    }

    @Override
    public String addCategory(Category category) {


        return categoryRepository.save(category).toString();

        //return true;

    }

    @Override
    public List<CategoryDTO> getCategories() {

        ModelMapper modelMapper = new ModelMapper();

        List<Category> categories = categoryRepository.findAll();

        List<CategoryDTO> categoryDTOS = Arrays.asList(modelMapper.map(categories, CategoryDTO[].class));

        return categoryDTOS;

    }


    public void sendOnclick(OnClickCRM onClickCRM){
        kafkaTemplate.send("clicks",onClickCRM);

    }


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

        tags.addAll(analyticsClient.getTagsByUserId(userId));


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
