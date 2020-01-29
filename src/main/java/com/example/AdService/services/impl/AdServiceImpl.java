package com.example.AdService.services.impl;

import com.example.AdService.document.Ad;
import com.example.AdService.dto.AdDTO;
import com.example.AdService.dto.onclickapi.OnClickRequest;
import com.example.AdService.repository.AdRepository;
import com.example.AdService.services.AdService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AdServiceImpl implements AdService {


    @Autowired
    AdRepository adRepository;

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


    public void sendOnclick(OnClickRequest onClickRequest){

        kafkaTemplate.send("clicks",onClickRequest);

    }
}
