package com.example.AdService.dto;

import com.example.AdService.document.Category;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class AdDTO {


    String adId;
    String categoryName;
    String tag;
    String addUrl;
    String targetUrl;
    String description;
    String advertiserId;
    String location;
}
