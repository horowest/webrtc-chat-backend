package com.akash.service;

import com.akash.dto.RequestTO;
import com.akash.dto.SessionDescriptionProtocol;

public final class ConnectionService {
    
    private static ConnectionService INSTANCE;
    
    private static final String ANSWER = "ANSWER";
    private RedisService redisService;
    
    private ConnectionService(RedisService redisService) {
        this.redisService = redisService;
    }

    // singleton
    public static ConnectionService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ConnectionService(new RedisService());
        }
        return INSTANCE;
    }

    public SessionDescriptionProtocol exchange(RequestTO requestTO) {
        // check if already offer is made against the current user
        SessionDescriptionProtocol offerSDP = getOffer(requestTO.getUsername());
        if(offerSDP != null) {
            return offerSDP;
        } 
        // since no offer is found against username, register an offer against otherUsername
        registerOffer(requestTO.getOtherUsername(), requestTO.getKey());
        return null;
    }

    private void registerOffer(String username, SessionDescriptionProtocol sdp) {
        redisService.saveSDP(username, sdp);
    }
    
    public void answerOffer(String username, SessionDescriptionProtocol sdp) {
        registerOffer(String.join("_", username, ANSWER), sdp);
    }
    
    public SessionDescriptionProtocol getOffer(String username) {
        String sdp = redisService.getSDP(username);
        if(sdp != null) {
            return new SessionDescriptionProtocol("offer", sdp);
        }
        return null;
    }

    public SessionDescriptionProtocol getAnswer(String username) {
        String sdp = redisService.getSDP(String.join("_", username, ANSWER));
        if(sdp != null) {
            return new SessionDescriptionProtocol("answer", sdp);
        }
        return null;
    }

}
