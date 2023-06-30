package com.akash.service;

import com.akash.dto.SessionDescriptionProtocol;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisService {
    private static JedisPool jedisPool = new JedisPool("localhost", 6379);

    public boolean saveSDP(String username, SessionDescriptionProtocol sdp) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(username, sdp.getSdp());
        }

        return true;
    }

    public String getSDP(String username) {
        try (Jedis jedis = jedisPool.getResource()) {
            String sdp = jedis.get(username);

            if(sdp != null) {
                jedis.del(username);
            } 
            return sdp;
        }
    }
}
