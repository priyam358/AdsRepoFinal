package com.example.AdService.feignclient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "search",url = "172.16.20.33:8080/search")
@CrossOrigin(allowedHeaders = "*",origins = "*")
public interface AnalyticsClient {

    //TODO: Call api

    /*@GetMapping("/findActionListByUserId/{id}")
    public List<String> findActionListByUserId(@PathVariable("id") String userId);
*/

    @GetMapping(value = "/getTagsByUserId/{id}")
    public List<String> getTagsByUserId(@PathVariable(value = "id")String id);

}
