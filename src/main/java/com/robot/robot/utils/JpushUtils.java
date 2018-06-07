package com.robot.robot.utils;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.robot.robot.common.PushTypeEnum;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/8/21.
 */
public class JpushUtils {

    /**
     * AppKey
     */
//    private static final String APP_KEY = "ee862d5b128f232566746857";
    private static final String APP_KEY = "385b70d11280b3a8bb302549";

    /**
     * Master Secret
     */
//    private static final String MASTER_SECRET = "4d0e4f7b1c9cd8a5e4390977";
    private static final String MASTER_SECRET = "7242a59987bc49ab69dd5838";

    /**
     * 最大重试 8 次
     */
    static { ClientConfig.getInstance().setMaxRetryTimes(8); }

    /**
     * 推送客户端
     */
    private static final JPushClient JPUSH = new JPushClient(MASTER_SECRET, APP_KEY);

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(JpushUtils.class);

    /**
     * 发送推送消息
     *
     * @param pushTypeEnum 推送类型
     * @param content      推送内容
     * @param targets      目标用户
     */
    public static void sendPush(PushTypeEnum pushTypeEnum, String content, String... targets) {
        sendPush(Audience.alias(targets), pushTypeEnum, content, Arrays.toString(targets));
    }

    /**
     * 向所有的设备发送推送
     *
     * @param pushTypeEnum 推送类型
     * @param content      推送内容
     */
    public static void sendAllPush(PushTypeEnum pushTypeEnum, String content) {
        sendPush(Audience.all(), pushTypeEnum, content, "ALL");
    }

    private static void sendPush(Audience audience, PushTypeEnum pushTypeEnum, String content, String target) {
        try {
            PushResult result = JPUSH.sendPush(PushPayload.newBuilder()
                    .setPlatform(Platform.android())
                    .setAudience(audience)
                    .setMessage(Message.newBuilder()
                            .setTitle(pushTypeEnum.name())
                            .setMsgContent(content)
                            .build())
                    // apns = false (开发环境)；apns = true (生产环境)
                    // TODO 生产环境设为 true
                    .setOptions(Options.newBuilder().setApnsProduction(false).build())
                    .build());
            if (result.isResultOK()) {
                logger.info("推送消息成功, 消息类型: {}, 消息内容: {}, 目标用户: {}", pushTypeEnum, content, target);
            } else {
                logger.warn("推送消息失败, 消息类型: {}, 消息内容: {}, 目标用户: {}", pushTypeEnum, content, target);
            }
        } catch (APIConnectionException e) {
            logger.error("连接推送服务器失败", e);
        } catch (APIRequestException e) {
            logger.error("推送消息异常", e);
        }
    }
}
