package com.example.AdService.controller;

import com.example.AdService.document.Ad;
import com.example.AdService.document.UserCache;
import com.example.AdService.dto.RecieveTagDTO;
import com.example.AdService.services.AdService;
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
    AdService adService;

    @Autowired
    UserCacheService userCacheService;

    @GetMapping("/getAds/{userId}")
    @Cacheable(value = "update")
    public ResponseEntity<List<Ad>> getAds(@PathVariable(value = "userId") String userId )
    {
        //TODO api call from analytical service

        if(userCacheService.getItem(userId)==null)
        {


            List<String> tags = null;
            List<Ad> ads = new ArrayList<>();
            List<Ad> finalAds = new ArrayList<>();

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
            UserCache userCache=userCacheService.getItem(userId);
            return new ResponseEntity<>(userCache.getAds(),HttpStatus.FOUND);
        }

    }


    @KafkaListener(topics = "listenTags",groupId = "group_id")
    public void consume(RecieveTagDTO recieveTagDTO){

        //todo consumed recievetag DTO'
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

}
