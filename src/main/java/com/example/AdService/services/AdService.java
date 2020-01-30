package com.example.AdService.services;

import com.example.AdService.document.Category;
import com.example.AdService.dto.AdDTO;
import com.example.AdService.dto.CategoryDTO;
import com.example.AdService.dto.onclickapi.OnClickRequest;

import java.util.List;

public interface AdService {

    void onClick(OnClickRequest onClickRequest);
    String addAd(AdDTO adDTO);

    String addCategory(Category category);

    List<CategoryDTO> getCategories();
}
