package net.wanhe.util;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisUtil {
  @Autowired
  private JedisPool jedisPool;

  private Jedis getResource() {
    return jedisPool.getResource();

  }

  public byte[] set(byte[] key, byte[] value) {
    Jedis jedis = getResource();
    try {
      jedis.set(key, value);
      return value;
    } finally{
      jedis.close();
    }
  }

  public void expire(byte[] key, int i) {
    // TODO Auto-generated method stub
    Jedis jedis = getResource();
    try {
      jedis.expire(key, i);
    } finally{
      jedis.close();
    }
  }

  public byte[] get(byte[] key) {
    // TODO Auto-generated method stub
    Jedis jedis = getResource();
    try {
      return jedis.get(key);
    } finally{
      jedis.close();
    }		
  }

  public void delete(byte[] key) {
    // TODO Auto-generated method stub
    // TODO Auto-generated method stub
    Jedis jedis = getResource();
    try {
      jedis.del(key);
    } finally{
      jedis.close();
    }				
  }

  public Set<byte[]> getkeys(String sHIRO_SESSION_PREFIX) {
    // TODO Auto-generated method stub
    Jedis jedis = getResource();
    try {
      return jedis.keys((sHIRO_SESSION_PREFIX+"*").getBytes());
    } finally{
      jedis.close();
    }
  }
}