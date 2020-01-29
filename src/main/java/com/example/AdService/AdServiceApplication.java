package com.example.AdService;

import com.example.AdService.document.UserCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


@EnableCaching
@SpringBootApplication
public class AdServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdServiceApplication.class, args);

	}


	@Bean
	JedisConnectionFactory jedisConnectionFactory(){
		return new JedisConnectionFactory();
	}

	@Bean
	RedisTemplate<String, UserCache> redisTemplate(){
		RedisTemplate<String,UserCache> redisTemplate = new RedisTemplate<String, UserCache>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}


}
