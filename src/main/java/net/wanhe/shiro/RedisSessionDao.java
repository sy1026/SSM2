package net.wanhe.shiro;


import net.wanhe.util.JedisUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RedisSessionDao extends AbstractSessionDAO {

  @Resource
  private JedisUtil jedisUtil;

  private  final String SHIRO_SESSION_PREFIX = "wanho_session";  //sessionkey添加前缀

  /**
   * 通过SessionKey 获得sesion
	 * @param key Session Key
	 * @return Session value
	 */
  private  byte[] getKey(String key) {
    return (SHIRO_SESSION_PREFIX+key).getBytes();
  }

  /**
	 * 保存Session
	 * @param session
	 */
  private  void saveSession(Session session) {

    if(session != null && session.getId() != null) {
      byte[] key = getKey(session.getId().toString());      //获得sessionid
      byte[] value = SerializationUtils.serialize(session); //序列化
      jedisUtil.set(key,value);
      jedisUtil.expire(key,600);	//设置保存时间
    }
  }

  /*
	 * 创建sesion
	 * (non-Javadoc)
	 * @see org.apache.shiro.session.mgt.eis.AbstractSessionDAO#doCreate(org.apache.shiro.session.Session)
	 */
  @Override
  protected Serializable doCreate(Session session) {
    // TODO Auto-generated method stub
    Serializable  sessionId = generateSessionId(session);
    assignSessionId(session, sessionId);
    saveSession(session);
    return sessionId;
  }

  /*通过sessionid获得session*/
  @Override
  protected Session doReadSession(Serializable sessionId) {
    // TODO Auto-generated method stub

    if(sessionId == null) {
      return null;
    }

    /*获取sessionkey*/
    byte[] key = getKey(sessionId.toString());

    /*获得sesionsession value*/
    byte[] value = jedisUtil.get(key);

    return (Session) SerializationUtils.deserialize(value); //反序列化

  }


  /*
	 * 更新session
	 * (non-Javadoc)
	 * @see org.apache.shiro.session.mgt.eis.SessionDAO#update(org.apache.shiro.session.Session)
	 */
  public void update(Session session) throws UnknownSessionException {
    // TODO Auto-generated method stub
    saveSession(session);
  }

  /*
	 * 删除session
	 *  (non-Javadoc)
	 * @see org.apache.shiro.session.mgt.eis.SessionDAO#delete(org.apache.shiro.session.Session)
	 */
  public void delete(Session session) {
    // TODO Auto-generated method stub
    if(session != null && session.getId() != null) {
      return;
    }
    byte[] key = getKey(session.getId().toString());
    jedisUtil.delete(key);
  }

  /*
	 * 获得存活得session
	 * (non-Javadoc)
	 * @see org.apache.shiro.session.mgt.eis.SessionDAO#getActiveSessions()
	 */
  public Collection<Session> getActiveSessions() {
    // TODO Auto-generated method stub
    System.out.println("getActiveSessions");
    Set<byte[]> keys = jedisUtil.getkeys(SHIRO_SESSION_PREFIX);
    Set<Session> sessions = new HashSet<Session>();
    if(CollectionUtils.isEmpty(keys)) {
      return sessions;
    }
    for(byte[] key:keys) {
      Session session = (Session) SerializationUtils.deserialize(key);
      sessions.add(session);
    }
    return sessions;
  }

}