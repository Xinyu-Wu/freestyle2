/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.pku.datasource;

/**
 *
 * @author wuxinyu
 */
public class FreestyleUser {
    private String userID;
    private String userName;
    private String userIP;
    
    public FreestyleUser(String id, String name, String ip){
        this.userID = id;
        this.userName = name;
        this.userIP = ip;
    }
    
    public void setUserID(String id){
        this.userID = id;
    }
    
    public void setUserName(String name) {
        this.userName = name;
    }
    
    public void setUserIP(String ip){
        this.userIP = ip;
    }
    
    public String getUserID(){
        return this.userID;
    }
    
    public String getUserName(){
        return this.userName;
    }
    
    public String getUserIP(){
        return this.userIP;
    }
}
