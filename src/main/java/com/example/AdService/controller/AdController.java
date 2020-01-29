package com.example.AdService.controller;

import com.example.AdService.document.Ad;
import com.example.AdService.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/ads")
@RestController
public class AdController {

    @Autowired
    AdService adService;

    @GetMapping("/getAds/{userId}")
    private List<Ad> getAds(@PathVariable(value = "userId") String userId )
    {
        
    }
}
