package com.example.AdService.feignclient;


import com.example.AdService.dto.TokenRequest;
import com.example.AdService.dto.TokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

@FeignClient(name = "jwt",url = "172.16.20.32:8080/jwt")
@CrossOrigin(allowedHeaders = "*",origins = "*")
public interface LoginClient {

    @PostMapping("/getUserDetails")
    TokenResponse getUserDetails(@RequestHeader("Authorization") String accessToken, @RequestBody TokenRequest tokenRequest);

}
