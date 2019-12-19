package net.wanhe.shiro;


import net.wanhe.util.ByteSourceUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;
import java.util.*;

@Component
public class RedisCache<K,V> implements Cache<K,V> {

  public String getKeyPrefix() {
    return keyPrefix;
  }

  public void setKeyPrefix(String keyPrefix) {
    this.keyPrefix = keyPrefix;
  }

  private String keyPrefix = "shiro_redis_cache:";

  @Autowired
  private JedisPool jedisPool;



  /**
     * 获得byte[]型的key
     * @param key
     * @return
     */
  private byte[] getByteKey(Object key){
    if(key instanceof String){
      String preKey = this.keyPrefix + key;
      return preKey.getBytes();
    }else{
      return ByteSourceUtils.serialize((Serializable) key);
    }
  }


  @Override
  public Object get(Object key) throws CacheException {

    byte[] bytes = getByteKey(key);
    byte[] value =  jedisPool.getResource().get(bytes);
    if(value == null){
      return null;
    }
    return ByteSourceUtils.deserialize(value);
  }

  /**
     * 将shiro的缓存保存到redis中
     */
  @Override
  public Object put(Object key, Object value) throws CacheException {

    Jedis jedis = jedisPool.getResource();

    jedis.set(getByteKey(key), ByteSourceUtils.serialize((Serializable)value));
    byte[] bytes = jedis.get(getByteKey(key));
    Object object = ByteSourceUtils.deserialize(bytes);

    return object;

  }

  @Override
  public Object remove(Object key) throws CacheException {
    Jedis jedis = jedisPool.getResource();

    byte[] bytes = jedis.get(getByteKey(key));

    jedis.del(getByteKey(key));

    return ByteSourceUtils.deserialize(bytes);
  }

  /**
     * 清空所有缓存
     */
  @Override
  public void clear() throws CacheException {
    jedisPool.getResource().flushDB();
  }

  /**
     * 缓存的个数
     */
  @Override
  public int size() {
    Long size =  jedisPool.getResource().dbSize();
    return size.intValue();
  }

  /**
     * 获取所有的key
     */
  @Override
  public Set keys() {
    Set<byte[]> keys =  jedisPool.getResource().keys(new String("*").getBytes());
    Set<Object> set = new HashSet<Object>();
    for (byte[] bs : keys) {
      set.add(ByteSourceUtils.deserialize(bs));
    }
    return set;
  }


  /**
     * 获取所有的value
     */
  @Override
  public Collection values() {
    Set keys = this.keys();

    List<Object> values = new ArrayList<Object>();
    for (Object key : keys) {
      byte[] bytes =  jedisPool.getResource().get(getByteKey(key));
      values.add(ByteSourceUtils.deserialize(bytes));
    }
    return values;
  }
}