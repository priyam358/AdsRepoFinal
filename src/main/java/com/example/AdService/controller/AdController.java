package com.example.AdService.controller;

import com.example.AdService.document.Ad;
import com.example.AdService.document.Category;
import com.example.AdService.dto.AdDTO;
import com.example.AdService.dto.CategoryDTO;
import com.example.AdService.dto.onclickapi.OnClickRequest;
import com.example.AdService.repository.AdRepository;
import com.example.AdService.repository.CategoryRepository;

import com.example.AdService.document.UserCache;
import com.example.AdService.services.AdService;
import com.example.AdService.services.UserCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

 

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins =  "*", allowedHeaders = "*")
@RequestMapping("/ads")
@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class AdController {

    @Autowired
    private AdService adService;

    @Autowired
    private UserCacheService userCacheService;

//    @Autowired
//    private TrendingCacheService trendingCacheService;


    @Autowired
    AdRepository adRepository;

    @Autowired
    CategoryRepository categoryRepository;


    @GetMapping("/getAds/{userId}")
   public List<Ad> getAds(@PathVariable(value = "userId") String userId )
    {
        return null; // to skip compilation errors

    }

    @GetMapping("/onclick")
    public RedirectView onClick(@Valid @RequestBody OnClickRequest onClickRequest){

        adService.onClick(onClickRequest);
        return new RedirectView(onClickRequest.getTargetUrl());

    }

    @PostMapping("/postads")
    public ResponseEntity<String> postAds(@Valid @RequestBody AdDTO adDTO){

        return new ResponseEntity<>(adService.addAd(adDTO), HttpStatus.ACCEPTED);

    }



    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getCategories(){

        return new ResponseEntity<>(adService.getCategories(),HttpStatus.OK);

    }

    @GetMapping("/findAds/{tag}")
    public List<Ad> findByTag(@PathVariable(value = "tag") String tag){

        return adRepository.findByTag(tag);


    }



    public ResponseEntity<List<Ad>> getAds(@PathVariable(value = "userId") String userId )
    {
        UserCache userCache = adService.getAds(userId);
        return new ResponseEntity<>(userCache.getAds(),HttpStatus.OK);

    }



}
