package com.kingxunlian.message.biz.util;

import com.alibaba.fastjson.JSON;
import com.kingxunlian.exception.XLException;
import com.kingxunlian.message.exception.MessageErrorCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2018/2/1 上午11:17
 */
public class TemplateUtil {

    private static final Logger logger = LoggerFactory.getLogger(TemplateUtil.class);


    /**
     * 替换消息模板里面的参数如：${name}
     * @param content
     * @param jsonParameter
     * @return
     */
    public static String renderTemplate(String content, String jsonParameter){
        try {
            Map<String,String> parameterMap = (Map<String,String>)JSON.parseObject(jsonParameter,Map.class);
            Set<Map.Entry<String, String>> sets = parameterMap.entrySet();
            for(Map.Entry<String, String> entry : sets) {
                String regex = "\\$\\{" + entry.getKey() + "\\}";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(content);
                content = matcher.replaceAll(entry.getValue());
            }
            //如果替换后文本里还是有${},则抛出异常
            String cRegex = "\\$\\{.*?\\}";
            Pattern pattern = Pattern.compile(cRegex);
            Matcher matcher = pattern.matcher(content);
            if (matcher.find()){
                String msg = MessageFormat.format("Parameters and templates does not match,please check!Send Parameter is:{0}",jsonParameter);
                logger.error(msg);
                throw new XLException(msg, MessageErrorCodeEnum.SERVER_INNER_ERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            String msg = MessageFormat.format("Render message template failed,message is:{0}",e.getMessage());
            logger.error(msg);
            throw new XLException(msg,MessageErrorCodeEnum.TEMPLATE_RENDER_FAILED);
        }
        return content;
    }


    /**
    public static void main(String[] args){
        String context = "name:${name},sex:${sex},age:${age}";
        Map<String,String> parameter = new HashMap<>();
        parameter.put("name","测试");
        parameter.put("sex","男");
        parameter.put("age","20");
        context = renderTemplate(context,JSON.toJSONString(parameter));
        System.out.println(context);

        String cRegex = "\\$\\{.*?\\}";
        Pattern pattern = Pattern.compile(cRegex);
        Matcher matcher = pattern.matcher(context);
        if (matcher.find()){
            System.out.println(context);
        }
    }
   **/


}
