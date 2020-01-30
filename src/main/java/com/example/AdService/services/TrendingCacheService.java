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

        trendingCacheRepository.deleteItem(trendingId);
    }

    @CachePut(value="trendingCache",key = "#trendingId")
    public void addItem(TrendingCache trendingCache){

        trendingCacheRepository.addItem(trendingCache);
    }

    @CachePut(value="trendingCache",key = "#trendingId",condition = "#result != null")
    public void updateItem(TrendingCache trendingCache){

        trendingCacheRepository.deleteItem(trendingCache.getTrendingId());
        trendingCacheRepository.updateItem(trendingCache);
    }
}
