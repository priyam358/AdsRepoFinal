package com.example.AdService.dto;

import com.example.AdService.document.Category;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class AdDTO {


    String adId;
    Category category;
    String addUrl;
    String targetUrl;
    String description;
    String advertiserId;
    String location;
}
