package com.example.AdService.services;


import com.example.AdService.document.UserCache;
import com.example.AdService.repository.UserCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
public class UserCacheService {

    @Autowired
    UserCacheRepository userCacheRepository;

    @Cacheable(value="userCache", key="#userId")
    public UserCache getItem(String userId){

        UserCache userCache = null;
        try{
            userCache = userCacheRepository.getItem(userId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return userCache;
    }

    @CacheEvict(value="userCache",key = "#userId")
    public void deleteItem(String userId){

        userCacheRepository.deleteItem(userId);
    }

    @CachePut(value="userCache",key = "#userId")
    public void addItem(UserCache userCache){

        userCacheRepository.addItem(userCache);
    }

    @CachePut(value="userCache",key = "#userId",condition = "#result != null")
    public void updateItem(UserCache userCache){

        userCacheRepository.deleteItem(userCache.getUserId());
        userCacheRepository.updateItem(userCache);
    }


}
