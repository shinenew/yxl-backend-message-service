package com.kingxunlian.message.biz.service;

import com.kingxunlian.message.biz.dao.MessageStateDao;
import com.kingxunlian.message.biz.dao.MessageSystemDao;
import com.kingxunlian.message.biz.dao.MessageTextDao;
import com.kingxunlian.message.dto.request.MessageSendRequest;
import com.kingxunlian.message.dto.response.MessageSendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2018/1/17 下午5:46
 */
@Service
public class MessageService implements IMessageService{

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private MessageStateDao messageStateDao;

    @Autowired
    private MessageTextDao messageTextDao;

    @Autowired
    private MessageSystemDao messageSystemDao;


    /**
     * 发送消息
     * @param request
     * @return
     */
    public MessageSendResponse sendMessage(MessageSendRequest request){


        /**
         * 1.点对点单条消息
         *   消息类型为2，站内信
         *   发送人和接受人不为空，只有一个用户ID
         *   在message_text中插入一条消息
         *   在message_state中插入一条关系
         *
         * 2.点对面多条消息
         *   消息类型为1，通知提醒
         *   发送人可以为空，接受人大于等于1
         *   在message_text中插入一条消息
         *   在message_state中分别插入多个用户的消息
         *
         * 3.全体用户消息
         *   消息类型为0，系统消息
         *   发送人为空，接受人为空
         *   在message_sys中插入一条消息
         *   用户在登的时候查询在message_sys中，但是不在message_state中的消息为未读消息
         *   用户点击阅读时在message_state中新增一条记录并标记为已读
         *
         *  4.用户登陆时消息的获取机制
         *    查询message_state中未读的消息 + message_sys中的组合成未读消息，并在前端messageBox中显示未读消息数
         *    查询message_state中的已读消息为已读消息
         *    用户登陆首次查询时走数据库，后续走缓存
         *
         *  5.其它不确定的问题
         *    1.多条消息或者系统消息创建的时候要不要走MQ
         *    2.现在搞不搞订阅消息模式
         *      订阅通知单，发票 某个状态的变更？
         *    3.走不走模板渲染？
         *    4.消息跳转的URL是模版定义，还是前端根据不能的消息模块跳转？
         *    5.数据库存储，暂时使用MYSQL，后续数据量大了再改成mongo？
         *    6.消息的合并聚合功能？
         *
         */

        return new MessageSendResponse();
    }

}
