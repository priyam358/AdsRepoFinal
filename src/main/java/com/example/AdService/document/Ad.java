package com.example.AdService.document;


import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class Ad {

    @Id
    String adId;
    String categoryName;
    String tag;                        //TODO categoryName and tagname should replace the category object
    String imageUrl;
    String targetUrl;
    String description;
    String advertiserId;
    String location;

}
