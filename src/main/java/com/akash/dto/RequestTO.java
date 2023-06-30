package com.akash.dto;

public class RequestTO {
    private String username;
    private String otherUsername;
    private SessionDescriptionProtocol key;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOtherUsername() {
        return otherUsername;
    }

    public void setOtherUsername(String otherUsername) {
        this.otherUsername = otherUsername;
    }

    public SessionDescriptionProtocol getKey() {
        return key;
    }

    public void setKey(SessionDescriptionProtocol key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "RequestTO [username=" + username + ", otherUsername=" + otherUsername + ", key=" + key + "]";
    }
}
