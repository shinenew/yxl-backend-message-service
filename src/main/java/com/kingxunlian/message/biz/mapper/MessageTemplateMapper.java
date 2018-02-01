package com.kingxunlian.message.biz.mapper;

import com.kingxunlian.message.biz.dto.MessageTemplate;
import com.kingxunlian.message.biz.dto.MessageTemplateExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MessageTemplateMapper {
    int countByExample(MessageTemplateExample example);

    int deleteByExample(MessageTemplateExample example);

    int deleteByPrimaryKey(Long templateId);

    int insert(MessageTemplate record);

    int insertSelective(MessageTemplate record);

    List<MessageTemplate> selectByExample(MessageTemplateExample example);

    MessageTemplate selectByPrimaryKey(Long templateId);

    int updateByExampleSelective(@Param("record") MessageTemplate record, @Param("example") MessageTemplateExample example);

    int updateByExample(@Param("record") MessageTemplate record, @Param("example") MessageTemplateExample example);

    int updateByPrimaryKeySelective(MessageTemplate record);

    int updateByPrimaryKey(MessageTemplate record);
}