package com.robot.common.utils;


import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 
 * StringRedisTemplate 常用操作
 *
 * @Author 	laoGF
 * @Data 	2018年7月23日
 */
@Service
public class StringRedisUtil {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
 
    
    /**
     * @Functionlity  StringRedisTemplate常用操作
     * @Author  laoGF
     * @Date  2018年7月23日
     */
    @SuppressWarnings("unused")
	private void canDo(){
    	stringRedisTemplate.opsForValue().set("test", "100",60*10,TimeUnit.SECONDS);  //向redis里存入数据和设置缓存时间
    	stringRedisTemplate.boundValueOps("test").increment(-1);  //val做-1操作
    	stringRedisTemplate.opsForValue().get("test");  //根据key获取缓存中的val
    	stringRedisTemplate.boundValueOps("test").increment(1);  //val +1
    	stringRedisTemplate.getExpire("test");  //根据key获取过期时间
    	stringRedisTemplate.getExpire("test",TimeUnit.SECONDS);  //根据key获取过期时间并换算成指定单位
    	stringRedisTemplate.delete("test");  //根据key删除缓存
    	stringRedisTemplate.hasKey("546545");  //检查key是否存在，返回boolean值
    	stringRedisTemplate.opsForSet().add("red_123", "1","2","3");  //向指定key中存放set集合
    	stringRedisTemplate.expire("red_123",1000 , TimeUnit.MILLISECONDS);  //设置过期时间
    	stringRedisTemplate.opsForSet().isMember("red_123", "1");  //根据key查看集合中是否存在指定数据
    	stringRedisTemplate.opsForSet().members("red_123");  //根据key获取set集合
    }
    
    /**
     * @Functionlity  存入数据
     * @Author  laoGF
     * @Date  2018年7月23日
     */
    public void setValue(String key,String value) {
    	stringRedisTemplate.opsForValue().set(key, value);
    }
 
    /**
     * @Functionlity  获取数据
     * @Author  laoGF
     * @Date  2018年7月23日
     */
    public String getValue(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }
    
    /**
     * @Functionlity  存入数据并设置缓存时间
     * @Author  laoGF
     * @Date  2018年7月23日
     */
    public void setExpire(String key,String value,long expire){
    	stringRedisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }
    
    /**
     * @Functionlity  设置缓存时间
     * @Author  laoGF
     * @Date  2018年7月23日
     */
    public boolean expire(String key, long expire){
    	return stringRedisTemplate.expire( key, expire, TimeUnit.MILLISECONDS);
    }
 
    /**
     * @Functionlity  删除数据
     * @Author  laoGF
     * @Date  2018年7月23日
     */
    public void delete(String key){
        stringRedisTemplate.delete(key);
    }
    
    /**
     * @Functionlity  检查key是否存在
     * @Author  laoGF
     * @Date  2018年7月23日
     */
    public boolean hasKey(String key){
    	return stringRedisTemplate.hasKey(key);
    }
    
    
    /**
     * @Functionlity  存入Set集合
     * @Author  laoGF
     * @Date  2018年7月23日
     */
    public void opsForSet(String key,String... value){
    	stringRedisTemplate.opsForSet().add(key, value);
    }
    
    /**
     * @Functionlity  获取Set集合
     * @Author  laoGF
     * @Date  2018年7月23日
     */
    public Set<String> members(String key){
    	return stringRedisTemplate.opsForSet().members(key);
    }
	
    /**
     * @Functionlity  检查key是否存在指定数据
     * @Author  laoGF
     * @Date  2018年7月23日
     */
    public boolean isMember(String key,String isValue){
    	return stringRedisTemplate.opsForSet().isMember(key, isValue);
    }
    
}
