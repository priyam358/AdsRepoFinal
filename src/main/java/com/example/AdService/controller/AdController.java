package com.example.AdService.controller;

import com.example.AdService.document.Ad;
import com.example.AdService.document.TrendingCache;
import com.example.AdService.document.UserCache;
import com.example.AdService.dto.RecieveTagDTO;
import com.example.AdService.services.AdService;
import com.example.AdService.services.TrendingCacheService;
import com.example.AdService.services.UserCacheService;
import org.apache.catalina.User;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


@CrossOrigin(origins =  "*", allowedHeaders = "*")
@RequestMapping("/ads")
@RestController
public class AdController {

    @Autowired
    private AdService adService;

    @Autowired
    private UserCacheService userCacheService;

    @Autowired
    private TrendingCacheService trendingCacheService;


    @GetMapping("/getAds/{userId}")
    @Cacheable(value = "userCache")
    public ResponseEntity<UserCache> getAds(@PathVariable(value = "userId") String userId )
    {
        //TODO api call from analytical service and update tags which is null now

            List<Ad> finalAds = new ArrayList<>();
            List<String> tags = null;
            Iterator<String> tag = tags.iterator();
            List<Ad> ads = new ArrayList<>();

            List<Ad> tagAds = adService.findByTagsIn(tag);

            for (int i = 0; i < 5; i++) {
                Random rand = new Random();
                Ad ad = ads.get(rand.nextInt(ads.size()));
                finalAds.add(ad);
            }
            UserCache userCache=new UserCache(userId,finalAds);
            return new ResponseEntity<UserCache>(userCache, HttpStatus.FOUND);

    }

    @GetMapping("getTrendingAds")
    @Cacheable(value="trendingCache")
    public ResponseEntity<TrendingCache> getAds()
    {
        return  new ResponseEntity<TrendingCache>(trendingCacheService.getItem("1"),HttpStatus.FOUND);
    }


    //move this to services
    @KafkaListener(topics = "listenTags",groupId = "group_id")
    public void consume(RecieveTagDTO recieveTagDTO){

        List<String> tags=recieveTagDTO.getTags();
        Iterator<String> tag = tags.iterator();
        String userId=recieveTagDTO.getUserId();
        List<Ad> ads=new ArrayList<>();

        ads.addAll(adService.findByTagsIn(tag));

        UserCache userCache=new UserCache(userId,ads);
        userCacheService.addItem(userCache);
    }

    @KafkaListener(topics = "listenTrending",groupId = "group_id")
    public void consumeTrend(List<String> trendingAdIds){

        List<Ad> ads = new ArrayList<>();
        for(int i=0 ;i <trendingAdIds.size();i++){
            ads.addAll(adService.findByAdId(trendingAdIds.get(i)));
        }

        TrendingCache trendingCache = new TrendingCache("1",ads);
        trendingCacheService.addItem(trendingCache);
    }

}
