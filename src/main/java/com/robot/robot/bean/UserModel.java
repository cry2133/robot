package com.robot.robot.bean;

/**
 * Created by Administrator on 2017/9/6.
 */
public class UserModel {
    private  String ticket;
    private  String identityID ;

    public void setTicket(String ticket)
    {
        this.ticket = ticket;
    }
    public String getTicket()
    {
        return  ticket;
    }

    public void setIdentityID(String identityID)
    {
        this.identityID = identityID;
    }
    public String getIdentityID()
    {
        return  identityID;
    }
}
