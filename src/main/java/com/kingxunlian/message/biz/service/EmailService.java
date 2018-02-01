package com.kingxunlian.message.biz.service;

import com.alibaba.fastjson.JSON;
import com.kingxunlian.exception.XLException;
import com.kingxunlian.message.biz.dao.MessageTemplateDao;
import com.kingxunlian.message.biz.dto.MessageTemplate;
import com.kingxunlian.message.biz.util.TemplateUtil;
import com.kingxunlian.message.dto.request.EmailSendRequest;
import com.kingxunlian.message.exception.MessageErrorCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2018/2/1 上午10:50
 */
@Service
public class EmailService implements IEmailService{

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MessageTemplateDao templateDao;

    @Value("${spring.mail.username}")
    private String Sender;


    /**
     * 发送简单邮件
     * @param emailSendRequest
     * @return
     */
    public Boolean sendSimpleMail(EmailSendRequest emailSendRequest){
        //查询模版的内容
        MessageTemplate templateQuery = new MessageTemplate();
        templateQuery.setTemplateCode(emailSendRequest.getTemplateCode());
        MessageTemplate messageTemplate = templateDao.findOneByFilter(templateQuery);
        if (messageTemplate == null){
            String msg = MessageFormat.format("邮件模版不存在:{0}",emailSendRequest.getTemplateCode());
            logger.error(msg);
            throw new XLException(msg, MessageErrorCodeEnum.TEMPLATE_NOT_FOUND);
        }
        String emailContext = messageTemplate.getTemplateContext();
        TemplateUtil.renderTemplate(emailContext, emailSendRequest.getMessageParameter());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(Sender);
        message.setTo(emailSendRequest.getReceiveUser()); //自己给自己发送邮件
        message.setSubject(messageTemplate.getTemplateTitle());
        message.setText(emailContext);
        try {
            javaMailSender.send(message);
        }catch (Exception e){
            String msg = MessageFormat.format("发送邮件失败,信息为:{0}",e.getMessage());
            logger.error(msg);
            throw new XLException(msg,MessageErrorCodeEnum.SERVER_INNER_ERROR);
        }
        return true;
    }


}
