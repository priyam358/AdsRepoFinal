package com.example.AdService.document;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "ads")
public class Ad {

    @Id
    private String adId;
    private String categoryName;
    private String tag;
    private String addUrl;
    private String targetUrl;
    private String description;
    private String advertiserId;
    private String location;

}
