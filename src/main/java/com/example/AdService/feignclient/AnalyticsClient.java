package com.example.AdService.feignclient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;

@FeignClient(name = "analyticsCient",url = "")
@CrossOrigin(allowedHeaders = "*",origins = "*")
public interface AnalyticsClient {

    //TODO: Call api
}
