package com.emrealkan.mongodbrestapi.Entity;

import java.util.List;

public class AuthenticationResponse {
    private String responseMsg;
    private List<User> users;

    public AuthenticationResponse(String responseMsg,List<User> users ) {
        this.responseMsg = responseMsg;
        this.users = users;
    }

    public AuthenticationResponse(String responseMsg){
        this.responseMsg = responseMsg;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
