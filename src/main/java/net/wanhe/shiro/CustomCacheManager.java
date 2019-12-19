package net.wanhe.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import javax.annotation.Resource;

/**
 *
 */
public class CustomCacheManager implements CacheManager {

  @Resource
  private RedisCache redisCache;


  @Override
  public <K, V> Cache<K, V> getCache(String name) throws CacheException {
    return redisCache;
  }
}