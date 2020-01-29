package com.example.AdService.controller;

import com.example.AdService.document.Ad;
import com.example.AdService.dto.AdDTO;
import com.example.AdService.dto.onclickapi.OnClickRequest;
import com.example.AdService.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/ads")
@RestController
public class AdController {

    @Autowired
    AdService adService;

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

}
