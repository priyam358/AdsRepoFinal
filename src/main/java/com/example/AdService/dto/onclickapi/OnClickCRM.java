package com.example.AdService.dto.onclickapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OnClickCRM {

    @NotNull
    String adId;
    @NotNull
    String tag;
    @NotNull
    String email;
    @NotNull
    String advertiserId;
    @NotNull
    String categoryId;
    String userId;
    String description;
    @NotNull
    String source;
    @NotNull
    String targetUrl;

}
