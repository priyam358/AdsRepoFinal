package com.example.AdService.controller;

import com.example.AdService.document.Ad;
import com.example.AdService.document.TrendingCache;
import com.example.AdService.document.UserCache;
import com.example.AdService.dto.RecieveTagDTO;
import com.example.AdService.services.AdService;
import com.example.AdService.services.TrendingCacheService;
import com.example.AdService.services.UserCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @Cacheable(value = "update")
    public ResponseEntity<List<Ad>> getAds(@PathVariable(value = "userId") String userId )
    {
        //TODO api call from analytical service and update tags which is null now

        if(userCacheService.getItem(userId)==null)
        {

            List<Ad> finalAds = new ArrayList<>();


            List<String> tags = null;

            List<Ad> ads = new ArrayList<>();

            for (int i = 0; i < tags.size(); i++) {

                List<Ad> tagAds = adService.findByTags(tags.get(i)).get();
                ads.addAll(tagAds);
            }

            for (int i = 0; i < 5; i++) {
                Random rand = new Random();
                Ad ad = ads.get(rand.nextInt(ads.size()));
                finalAds.add(ad);
            }

            return new ResponseEntity<List<Ad>>(finalAds, HttpStatus.FOUND);
        }

        else
        {
            List<Ad> finalAds = new ArrayList<>();

            TrendingCache trendingCache = trendingCacheService.getItem("1");
                UserCache userCache = userCacheService.getItem(userId);

                List<Ad> userCacheAds = userCache.getAds();
                for (int i = 0; i < 3; i++) {
                    Random rand = new Random();
                    Ad ad = userCacheAds.get(rand.nextInt(userCacheAds.size()));
                    finalAds.add(ad);
                }

                List<Ad> trendingCacheAds = trendingCache.getAds();
                for (int i = 0; i < 2; i++) {
                    Random rand = new Random();
                    Ad ad = trendingCacheAds.get(rand.nextInt(trendingCacheAds.size()));
                    finalAds.add(ad);
                }

                return new ResponseEntity<>(finalAds,HttpStatus.FOUND);
        }

    }


    @KafkaListener(topics = "listenTags",groupId = "group_id")
    public void consume(RecieveTagDTO recieveTagDTO){

        List<String> tags=recieveTagDTO.getTags();
        String userId=recieveTagDTO.getUserId();

        List<Ad> ads=new ArrayList<>();

        for(int i=0;i<tags.size();i++)
        {
            List<Ad> tagAds = adService.findByTags(tags.get(i)).get();
            ads.addAll(tagAds);
        }

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
