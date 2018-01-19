package com.kingxunlian.message.biz.mapper;

import com.kingxunlian.message.biz.dto.MessageText;
import com.kingxunlian.message.biz.dto.MessageTextExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MessageTextMapper {
    int countByExample(MessageTextExample example);

    int deleteByExample(MessageTextExample example);

    int deleteByPrimaryKey(Long messageId);

    int insert(MessageText record);

    int insertSelective(MessageText record);

    List<MessageText> selectByExample(MessageTextExample example);

    MessageText selectByPrimaryKey(Long messageId);

    int updateByExampleSelective(@Param("record") MessageText record, @Param("example") MessageTextExample example);

    int updateByExample(@Param("record") MessageText record, @Param("example") MessageTextExample example);

    int updateByPrimaryKeySelective(MessageText record);

    int updateByPrimaryKey(MessageText record);
}