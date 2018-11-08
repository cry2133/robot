package com.robot.common.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

/**
 * 
 * Redis缓存工具类
 *
 * @Author 	laoGF
 * @Data 	2018年7月19日
 */
public class RedisUtil {

    private static final Logger logger = LogManager.getLogger(RedisUtil.class);
    
    private static JedisPool jedisPool = null;
    static {  
        JedisPoolConfig config = new JedisPoolConfig();  
        //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；  
        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。  
        config.setMaxTotal(500);  
        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。  
        config.setMaxIdle(5);  
        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；  
        config.setMaxWaitMillis(1000 * 100);  
        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；  
        config.setTestOnBorrow(true); 
        
        
        //redis如果设置了密码：
        //jedisPool = new JedisPool(config, "127.0.0.1", 6379,10000, "123456");
        
        //redis未设置了密码：
        jedisPool = new JedisPool(config, "127.0.0.1",6379);
       
   }

    

    private RedisUtil() {
    }

    /**
     * @Functionlity  获取连接，务必使用后jedis.close()关闭连接
     * @Author  laoGF
     * @Date  2018年7月19日
     */
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * @Functionlity  设置set集合，插入若干条数据
     * @Author  laoGF
     * @Date  2018年7月19日
     */
    public static void sadd(String key, String... members) {
        //redis操作发生异常时要把异常捕获，不要响应正常的业务逻辑
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.sadd(key, members);
            //logger.info("sadd：向缓存中添加数据，key：{"+key+"} value：{"+members+"}");
        } catch (Exception e) {
            logger.error("sadd：向缓存中添加数据时出现异常，key：{"+key+"} value：{"+members+"}");
        } finally {
            closeQuietly(jedis);  //保证jedis被关闭（归还给连接池）
        }
    }

    
    /**
     * @Functionlity  获取 Set
     * @Author  laoGF
     * @Date  2018年7月19日
     */
    public static Set<String> getSet(String key) {
        Jedis jedis = null;
        Set<String> set = null;
        try {
            jedis = getJedis();
            set = jedis.smembers(key);
        } catch (Exception e) {
            logger.error("smembers：从缓存中获取数据出现异常", e);
        } finally {
            closeQuietly(jedis);   //保证jedis被关闭（归还给连接池）
        }
        return set;
    }
    
    
    /**
     * @Functionlity  获取Set数据并删除此Set
     * @Author  laoGF
     * @Date  2018年7月19日
     */
    @SuppressWarnings("unchecked")
    public static Set<String> smembersAndDel(String key) {
        Jedis jedis = null;
        Set<String> set = null;
        try {
            jedis = getJedis();
            Transaction tx = jedis.multi();
            tx.smembers(key);
            tx.del(key);

            List<Object> result = tx.exec();
            if (result == null || result.isEmpty()) {
                logger.error("smembersAndDel：从缓存中取出数据以及删除数据的事务失败");
            } else {
                set = (Set<String>) result.get(0);
                logger.debug("smembersAndDel：从缓存中取出数据同时删除了key，key：{"+key+"}，value："+set+"}");
            }

        } catch (Exception e) {
            logger.error("smembersAndDel：从缓存中取出数据或者删除数据是出现异常", e);
        } finally {
            closeQuietly(jedis);   //保证jedis被关闭（归还给连接池）
        }
        return set;
    }

   
    /**
     * @Functionlity  设置 String + 过期时间
     * @Author  laoGF
     * @Date  2018年7月19日
     */
    public static void setExpire(String key, int expire, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.setex(key, expire, value);
            //logger.debug("setex：向缓存中添加数据，key：{"+key+"}，value：{"+value+"}，过期时间：{"+expire+"}秒");
        } catch (Exception e) {
            logger.error("setex：向缓存中添加数据时出现异常，key：{"+key+"}，value：{"+value+"}，过期时间：{"+expire+"}秒", e);
        } finally {
            closeQuietly(jedis);   //保证jedis被关闭（归还给连接池）
        }
    }

    /**
     * @Functionlity  获取 String
     * @Author  laoGF
     * @Date  2018年7月19日
     */
    public static String getValue(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = getJedis();
            value = jedis.get(key);
            //logger.debug("get：从缓存中获取数据，key：{"+key+"}，value：{"+value+"}");
        } catch (Exception e) {
            logger.error("get：从缓存中获取数据时出现异常，key：{"+key+"}，value：{"+value+"}", e);
        } finally {
            closeQuietly(jedis);  //保证jedis被关闭（归还给连接池）
        }
        return value;
    }
    
    /**
     * @Functionlity  设置 String
     * @Author  laoGF
     * @Date  2018年7月19日
     */
    public static void setValue(String key, String value){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key, value);
            logger.debug("set：向缓存中添加数据，key：{"+key+"}，value：{"+value+"}");
        } catch (Exception e) {
            logger.error("set：向缓存中添加数据时出现异常，key：{"+key+"}，value：{"+value+"}", e);
        } finally {
            closeQuietly(jedis);   //保证jedis被关闭（归还给连接池）
        }
    }
    
    /**
     * @Functionlity  获取 Map
     * @Author  laoGF
     * @Date  2018年7月19日
     */
	public static Map<String,String> getMap(String key){
        Jedis jedis = null;
        Map<String,String> map = null;
        try {
            jedis = getJedis();
            map = jedis.hgetAll(key);
            //System.out.println(jedis.hmget(key, "mapKey")); //返回key键中的Map中的mapKey键的值
            //System.out.println(jedis.hlen(key)); //返回key的键中存放的值的个数2 
            //System.out.println(jedis.exists(key));//是否存在key的记录 返回true  
            //System.out.println(jedis.hkeys(key));//返回map对象中的所有key  
            //System.out.println(jedis.hvals(key));//返回map对象中的所有value 
        } catch (Exception e) {
            logger.error("get：从缓存中获取数据时出现异常，key：{"+key+"}", e);
        } finally {
            closeQuietly(jedis);  //保证jedis被关闭（归还给连接池）
        }
        return map;
    }
    
    /**
     * @Functionlity  设置 Map
     * @Author  laoGF
     * @Date  2018年7月19日
     */
    public static void setMap(String key,Map<String,String> map){
    	Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.hmset(key,map);
        } catch (Exception e) {
            logger.error("set：向缓存中添加数据时出现异常，key：{"+key+"}，value：{"+map+"}", e);
        } finally {
            closeQuietly(jedis);   //保证jedis被关闭（归还给连接池）
        }
    }
    
 
    /**
     * @Functionlity  删除 Map
     * @Author  laoGF
     * @Date  2018年7月19日
     */
    public static void delMap(String key){
    	Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.hdel(key);
            //jedis.hdel(key,"mapKey");   //删除map中的某个键值  
        } catch (Exception e) {
            logger.error("set：向缓存中删除数据时出现异常，key：{"+key+"}", e);
        } finally {
            closeQuietly(jedis);   //保证jedis被关闭（归还给连接池）
        }
    }
    
    
    
    
    /**
     * @Functionlity  获取key的剩余生存时间,单位【秒】
     * @Author  laoGF
     * @return  永久生存或者不存在的都返回-1
     * @Date  2018年7月19日
     */
    public static Long ttl(String key){
    	 Jedis jedis = getJedis();
         Long expire = jedis.ttl(key);
         closeQuietly(jedis);
         return expire;
    }
    
    /**
     * @Functionlity  根据key，判断缓存中是否存在对应的value
     * @Author  laoGF
     * @Date  2018年7月19日
     */
    public static boolean isExist(String key){
    	 Jedis jedis = null;
         Boolean exist = null;
         try {
             jedis = getJedis();
             exist = jedis.exists(key);
             if(exist){
             	logger.debug("根据此key:{"+key+"}，可以在缓存中找到对应的value");
             	return true;
             } else {
            	logger.debug("根据此key:{"+key+"}，并没有在缓存中找到对应的value");
            	return false;
             }
         } catch (Exception e) {
             logger.error("get：从缓存中获取数据时出现异常，key：{"+key+"}", e);
             throw new RuntimeException(e);
         } finally {
             closeQuietly(jedis);  //保证jedis被关闭（归还给连接池）
         }
    }

    
    
    /**
     * @Functionlity  删除单个缓存
     * @Author  laoGF
     * @Date  2018年7月19日
     */
    public static void del(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.del(key);
        } catch (Exception e) {
            logger.error("del：从缓存中删除数据时出现异常，key：{"+key+"}",e);
        } finally {
            closeQuietly(jedis);    //保证jedis被关闭（归还给连接池）
        }
    }
    
    /**
     * @Functionlity  删除多个缓存
     * @Author  laoGF
     * @Date  2018年7月19日
     */
    public static void delByArray(String... keys) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.del(keys);
        } catch (Exception e) {
            logger.error("del：从缓存中删除数据时出现异常，key：{"+keys+"}",e);
        } finally {
            closeQuietly(jedis);    //保证jedis被关闭（归还给连接池）
        }
    }
    
    /**
     * @Functionlity  根据key的前缀删除所有相关的key
     * @Author  laoGF
     * @Date  2018年7月19日
     */
    public static void delPrefix(String keyPattern) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            Set<String> keys = jedis.keys(keyPattern);
            logger.debug("要删除的key为：{"+keys+"}");

            if (keys != null && keys.size() > 0) {
                String[] arr = new String[keys.size()];
                jedis.del(keys.toArray(arr));
            }
            logger.debug("del：从缓存中删除数据，keyPattern：{"+keyPattern+"}");
        } catch (Exception e) {
            logger.error("del：从缓存中删除数据时出现异常，keyPattern：{"+keyPattern+"}",e);
        } finally {
            closeQuietly(jedis);    //保证jedis被关闭（归还给连接池）
        }
    }

    /**
     * @Functionlity  关闭jedis连接
     * @Author  laoGF
     * @Date  2018年7月19日
     */
	public static void closeQuietly(Jedis jedis) {
        if (jedis != null) {
            try {
                jedis.close();
            } catch (Exception e) {
                logger.error("关闭jedis连接时发生异常", e);
            }
        }
    } 
    
    
	/**
	 * @Functionlity  问答保存缓存
	 * @Author  laoGF
	 * @Date  2018年7月19日
	 */
	public static void saveRedisForQuestion(String robotNo,String question) throws Exception{
		String r3 = RedisUtil.getValue(robotNo+"-3");
		String r2 = RedisUtil.getValue(robotNo+"-2");
		if(StringUtils.isNotEmpty(r3)){
			if(StringUtils.isNotEmpty(r2)){
				RedisUtil.setExpire(robotNo+"-1", 90, r2);
				RedisUtil.setExpire(robotNo+"-2", 90, r3);
				RedisUtil.setExpire(robotNo+"-3", 90, question);
			}else{
				RedisUtil.setExpire(robotNo+"-2", 90, r3);
				RedisUtil.setExpire(robotNo+"-3", 90, question);
			}
		}else{
			RedisUtil.setExpire(robotNo+"-3", 90, question);
		}
	}
	
	/**
	 * @Functionlity  问答获取缓存
	 * @Author  laoGF
	 * @Date  2018年7月19日
	 */
	public static List<String> getRedisByRobotNo(String robotNo) throws Exception{
		List<String> list = new LinkedList<String>();
		String r3 = RedisUtil.getValue(robotNo+"-3");
		String r2 = RedisUtil.getValue(robotNo+"-2");
		String r1 = RedisUtil.getValue(robotNo+"-1");
		if(StringUtils.isNotEmpty(r1)){
			list.add(r1);
		}
		if(StringUtils.isNotEmpty(r2)){
			list.add(r2);
		}
		if(StringUtils.isNotEmpty(r3)){
			list.add(r3);
		}
		return list;
	}
    
	/**
	 * @Functionlity  清除机器人缓存
	 * @Author  laoGF
	 * @Date  2018年7月20日
	 */
	public static void delRedisByRobotNo(String robotNo) throws Exception{
		RedisUtil.delPrefix(robotNo);
		String[] keys = {robotNo+"-1",robotNo+"-2",robotNo+"-3"};
		RedisUtil.delByArray(keys);
	}
	
    
}
