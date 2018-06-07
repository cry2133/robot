package com.robot.robot.bean;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/5.
 */
public class TicketModel {
    private String robotNo;
    private String ticket;
    private Date createTime;

    public void setRobotNo(String robotno)
    {
        this.robotNo = robotno;
    }
    public String getRobotNo()
    {
        return  robotNo;
    }

    public void setTicket(String ticketno)
    {
        this.ticket = ticketno;
    }
    public String getTicket()
    {
        return  ticket;
    }
    public void setCreateTime(Date now)
    {
        this.createTime = now;
    }
    public Date getCreateTime()
    {
        return  createTime;
    }

}
