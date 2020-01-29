package com.example.AdService.services;

import com.example.AdService.document.TrendingCache;
import com.example.AdService.repository.TrendingCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public class TrendingCacheService {

    @Autowired
    TrendingCacheRepository trendingCacheRepository;

    @Cacheable(value="trendingCache", key="#trendingId")
    public TrendingCache getItem(String trendingId){
        System.out.println("In getItem cache Component..");
        TrendingCache trendingCache = null;
        try{
            trendingCache = trendingCacheRepository.getItem(trendingId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return trendingCache;
    }
    @CacheEvict(value="trendingCache",key = "#trendingId")
    public void deleteItem(String trendingId){
        System.out.println("In deleteItem cache Component..");
        trendingCacheRepository.deleteItem(trendingId);
    }

    @CachePut(value="trendingCache",key = "#trendingrId")
    public void addItem(TrendingCache trendingCache){
        System.out.println("In addItem cache component..");
        trendingCacheRepository.addItem(trendingCache);
    }

    @CachePut(value="trendingCache",key = "#trendingId",condition = "#result != null")
    public void updateItem(TrendingCache trendingCache){
        System.out.println("In UpdateItem cache Component..");
        trendingCacheRepository.updateItem(trendingCache);
    }
}
