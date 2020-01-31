package com.example.AdService.controller;

import com.example.AdService.document.Ad;
import com.example.AdService.document.Category;
import com.example.AdService.dto.AdDTO;
import com.example.AdService.dto.CategoryDTO;
import com.example.AdService.dto.TokenRequest;
import com.example.AdService.dto.TokenResponse;
import com.example.AdService.dto.onclickapi.OnClickCRM;
import com.example.AdService.dto.onclickapi.OnClickRequest;
import com.example.AdService.feignclient.LoginClient;
import com.example.AdService.repository.AdRepository;
import com.example.AdService.repository.CategoryRepository;

import com.example.AdService.document.UserCache;
import com.example.AdService.services.AdService;
import com.example.AdService.services.UserCacheService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins =  "*", allowedHeaders = "*")
@RequestMapping("/ads")
@RestController
public class AdController {

    @Autowired
    private AdService adService;

    @Autowired
    private UserCacheService userCacheService;



    @Autowired
    LoginClient loginClient;

    @GetMapping("/onclick/{srcId}")
    public RedirectView onClick(@RequestHeader("Authorization") String accessToken,@PathVariable("srcId") Long srcId,@Valid @RequestBody OnClickRequest onClickRequest)
    {

        TokenRequest tokenRequest = new TokenRequest(srcId);

        TokenResponse userId = loginClient.getUserDetails(accessToken,tokenRequest);
        String uId = String.valueOf(userId.getId());
        String email=String.valueOf(userId.getEmail());
        System.out.println(uId);
        //onClickRequest.setUserId(uId);
        OnClickCRM onClickCRM=new OnClickCRM();
        onClickCRM.setAdId(onClickRequest.getAdId());
        onClickCRM.setAdvertiserId(onClickRequest.getAdvertiserId());
        onClickCRM.setCategoryId(onClickRequest.getCategoryId());
        onClickCRM.setDescription(onClickRequest.getDescription());
        onClickCRM.setEmail(email);
        onClickCRM.setUserId(uId);
        onClickCRM.setSource(onClickRequest.getSource());
        onClickCRM.setTag(onClickRequest.getTag());
        onClickCRM.setTargetUrl(onClickRequest.getTargetUrl());
        adService.onClick(onClickCRM);

        return new RedirectView(onClickRequest.getTargetUrl());

    }

/*
    @PostMapping("/postads")
    public ResponseEntity<String> postAds(@Valid @RequestBody AdDTO adDTO){

        return new ResponseEntity<>(adService.addAd(adDTO), HttpStatus.ACCEPTED);

    }




    /*@GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getCategories(){

        return new ResponseEntity<>(adService.getCategories(),HttpStatus.OK);

    }
*/

    @GetMapping(value = "getAds/{srcId}")
    public ResponseEntity<List<Ad>> getAds(@RequestHeader("Authorization") String accessToken,@PathVariable("srcId") Long srcId)
    {
        TokenRequest tokenRequest = new TokenRequest(srcId);

        TokenResponse userId = loginClient.getUserDetails(accessToken,tokenRequest);
        String uId = String.valueOf(userId.getId());
        System.out.println(uId);

        UserCache userCache = adService.getAds(uId);
        return new ResponseEntity<>(userCache.getAds(),HttpStatus.OK);

    }



}
