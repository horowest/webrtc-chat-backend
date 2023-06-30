package com.akash.dto;

public class SessionDescriptionProtocol {
    private String type;
    private String sdp;
    
    public SessionDescriptionProtocol() {
    }

    public SessionDescriptionProtocol(String type, String sdp) {
        this.type = type;
        this.sdp = sdp;
    }
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getSdp() {
        return sdp;
    }
    public void setSdp(String sdp) {
        this.sdp = sdp;
    }

    @Override
    public String toString() {
        return "SessionDescriptionProtocol [type=" + type + ", sdp=" + sdp + "]";
    }
    
}
