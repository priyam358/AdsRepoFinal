package com.example.AdService.dto;

import com.example.AdService.document.Category;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;


@Data
public class AdDTO {


    private String adId;
    @NonNull
    private Category category;
    @NonNull
    private String addUrl;
    @NonNull
    private String targetUrl;
    @NonNull
    private String description;
    @NonNull
    private String advertiserId;
    @NonNull
    private String location;
}
