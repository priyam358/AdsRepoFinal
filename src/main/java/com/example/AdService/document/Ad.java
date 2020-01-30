package com.example.AdService.document;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Data
@Document(collection = "ads")
public class Ad {

    @Id
    private String adId;
    private String categoryName;
    private String tag;
    private String imageUrl;
    private String targetUrl;
    private String description;
    private String advertiserId;
    private String location;
public class Ad implements Serializable {

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
