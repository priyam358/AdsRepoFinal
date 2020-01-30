package com.example.AdService.repository;


import com.example.AdService.document.UserCache;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserCacheRepository {


    public static final String KEY = "ITEM";
    private RedisTemplate<String, UserCache> redisTemplate;
    private HashOperations hashOperations;

    public UserCacheRepository(RedisTemplate<String, UserCache> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    /*Getting a specific item by item id from table*/
    public UserCache getItem(String userId){
        return (UserCache) hashOperations.get(KEY,userId);
    }

    /*Adding an item into redis database*/
    public void addItem(UserCache userCache){
        hashOperations.put(KEY,userCache.getUserId(),userCache);
    }
    /*delete an item from database*/
    public void deleteItem(String userId){
        hashOperations.delete(KEY,userId);
    }

    /*update an item from database*/
    public void updateItem(UserCache userCache){
        addItem(userCache);
    }

}
