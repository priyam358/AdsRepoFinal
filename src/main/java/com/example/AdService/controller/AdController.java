package com.example.AdService.controller;

import com.example.AdService.document.Ad;
import com.example.AdService.document.UserCache;
import com.example.AdService.services.AdService;
import com.example.AdService.services.UserCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins =  "*", allowedHeaders = "*")
@RequestMapping("/ads")
@RestController
public class AdController {

    @Autowired
    private AdService adService;

    @Autowired
    private UserCacheService userCacheService;

//    @Autowired
//    private TrendingCacheService trendingCacheService;


    @GetMapping("/getAds/{userId}")

    public ResponseEntity<List<Ad>> getAds(@PathVariable(value = "userId") String userId )
    {
        UserCache userCache = adService.getAds(userId);
        return new ResponseEntity<>(userCache.getAds(),HttpStatus.OK);

    }




}
