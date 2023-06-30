package com.akash.dto;

public class ReponseTO {
    private boolean answerd;
    private String username;
    private SessionDescriptionProtocol key;

    public ReponseTO() {
    }

    public ReponseTO(boolean answerd, String username, SessionDescriptionProtocol key) {
        this.answerd = answerd;
        this.username = username;
        this.key = key;
    }

    public ReponseTO(String username, SessionDescriptionProtocol key) {
        this.username = username;
        this.key = key;
    }

    public boolean isAnswerd() {
        return answerd;
    }
    public void setAnswerd(boolean answerd) {
        this.answerd = answerd;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public SessionDescriptionProtocol getKey() {
        return key;
    }
    public void setKey(SessionDescriptionProtocol key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "ReponseTO [answerd=" + answerd + ", username=" + username + ", key=" + key + "]";
    }
}
