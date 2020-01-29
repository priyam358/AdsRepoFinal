package com.example.AdService.services;

import com.example.AdService.dto.AdDTO;
import com.example.AdService.dto.onclickapi.OnClickRequest;

public interface AdService {

    void onClick(OnClickRequest onClickRequest);
    String addAd(AdDTO adDTO);
}
