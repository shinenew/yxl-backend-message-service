package com.kingxunlian.message.redis;

import java.util.UUID;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2018/1/23 下午4:04
 */
public class RedisTemplateKeyUtil {


    public static String getUserUnreadMessageCountKey(UUID userId){
        return userId.toString().concat("_unread_message_count");
    }
}
