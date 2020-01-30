package com.example.AdService.repository;

import com.example.AdService.document.TrendingCache;
import com.example.AdService.document.UserCache;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TrendingCacheRepository {

    public static final String KEY = "ITEM";
    private RedisTemplate<String, TrendingCache> redisTemplate;
    private HashOperations hashOperations;

    public TrendingCacheRepository(RedisTemplate<String, TrendingCache> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    /*Getting a specific item by item id from table*/
    public TrendingCache getItem(String trendingId){
        return (TrendingCache) hashOperations.get(KEY,trendingId);
    }

    /*Adding an item into redis database*/
    public void addItem(TrendingCache  trendingCache){
        hashOperations.put(KEY,trendingCache.getTrendingId(),trendingCache);
    }
    /*delete an item from database*/
    public void deleteItem(String trendingId){
        hashOperations.delete(KEY,trendingId);
    }

    /*update an item from database*/
    public void updateItem(TrendingCache trendingCache){

        addItem(trendingCache);
    }

}
