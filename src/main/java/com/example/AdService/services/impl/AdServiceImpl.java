package com.example.AdService.services.impl;

import com.example.AdService.document.Ad;
import com.example.AdService.document.Category;
import com.example.AdService.dto.AdDTO;
import com.example.AdService.dto.CategoryDTO;
import com.example.AdService.dto.onclickapi.OnClickRequest;
import com.example.AdService.repository.AdRepository;
import com.example.AdService.repository.CategoryRepository;
import com.example.AdService.services.AdService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {


    @Autowired
    AdRepository adRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    KafkaTemplate<String,OnClickRequest> kafkaTemplate;





    @Override
    public void onClick(OnClickRequest onClickRequest) {

        sendOnclick(onClickRequest);


    }

    @Override
    public String addAd(AdDTO adDTO) {

        Ad ad = new Ad();

        BeanUtils.copyProperties(adDTO,ad);
        return adRepository.save(ad).toString();

    }

    @Override
    public String addCategory(Category category) {


        return categoryRepository.save(category).toString();

        //return true;

    }

    @Override
    public List<CategoryDTO> getCategories() {

        ModelMapper modelMapper = new ModelMapper();

        List<Category> categories = categoryRepository.findAll();

        List<CategoryDTO> categoryDTOS = Arrays.asList(modelMapper.map(categories, CategoryDTO[].class));

        return categoryDTOS;

    }


    public void sendOnclick(OnClickRequest onClickRequest){

        kafkaTemplate.send("clicks",onClickRequest);

    }


}
