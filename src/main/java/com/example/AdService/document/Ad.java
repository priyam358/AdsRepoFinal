package com.example.AdService.document;


import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class Ad {

    @Id
    String adId;
    Category category;
    String addUrl;
    String targetUrl;
    String description;
    String advertiserId;
    String location;

}
