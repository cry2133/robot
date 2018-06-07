package com.robot.robot.utils;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.robot.robot.bean.TicketModel;


/**
 * Created by Administrator on 2017/9/5.
 */
public class TicketManager {
    private   static List<TicketModel> ticketModelList =new ArrayList<TicketModel>();

    public  static synchronized void addTicket(TicketModel newTicket)
    {
        try {
        ticketModelList.add(newTicket);
        }catch (Exception e) {
        }
    }

    public  static synchronized void delTicket(TicketModel newTicket)
    {
        ticketModelList.remove(newTicket);
    }

    public  static synchronized boolean delTicketByTicket(String ticket)
    {
        for (int i = 0; i < ticketModelList.size(); i++) {
            TicketModel ticketObj = ticketModelList.get(i);
            if(ticketObj.getTicket().equals(ticket))
            {
                ticketModelList.remove(ticketObj);
                return  true;
            }
        }
        return  false;
    }


    public  static  boolean checkTicketTimeOut(Date loginTime)
    {
        Date nowTime = new Date();
        if(loginTime.getTime()+180000  <  nowTime.getTime()) {
            //登录超过30分钟不活跃票据失效
            return false;
        }
        return true;
    }

    public  static  synchronized String getRobotID(String ticket)
    {
        for (int i = 0; i < ticketModelList.size(); i++) {
            TicketModel ticketObj = ticketModelList.get(i);
            if(ticketObj.getTicket().equals(ticket))
            {
                /*
                if(checkTicketTimeOut(ticketObj.getCreateTime()))
                {
                    ticketModelList.remove(ticketObj);
                    return  "登录超时，请重新登录！";
                }*/
                return  ticketObj.getRobotNo();
            }
        }
        return  "";
    }
}
