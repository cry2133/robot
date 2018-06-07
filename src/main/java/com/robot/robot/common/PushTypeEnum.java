package com.robot.robot.common;

/**
 * 推送类型枚举
 * Created by yao on 2017/8/21.
 */
public enum PushTypeEnum {

    /**
     * 商品
     */
    TICKETADD,

    /**
     * 智能交互上下文更新通知
     */
    INTERACTION_CONTEXT_UPDATE_NOTIFY,

    /**
     * 重新启动APP
     */
    REBOOT_APP,

    /**
     * 刷新主界面
     */
    REFRESH_MAIN,

    /**
     * 重新绑定激活机器人
     */
    ACTIVE_ROBOT,

    /**
     * 区域推送
     */
    AREA,
    ;

}