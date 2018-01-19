package com.kingxunlian.message.biz.mapper;

import com.kingxunlian.message.biz.dto.MessageSystem;
import com.kingxunlian.message.biz.dto.MessageSystemExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MessageSystemMapper {
    int countByExample(MessageSystemExample example);

    int deleteByExample(MessageSystemExample example);

    int deleteByPrimaryKey(Long messageId);

    int insert(MessageSystem record);

    int insertSelective(MessageSystem record);

    List<MessageSystem> selectByExample(MessageSystemExample example);

    MessageSystem selectByPrimaryKey(Long messageId);

    int updateByExampleSelective(@Param("record") MessageSystem record, @Param("example") MessageSystemExample example);

    int updateByExample(@Param("record") MessageSystem record, @Param("example") MessageSystemExample example);

    int updateByPrimaryKeySelective(MessageSystem record);

    int updateByPrimaryKey(MessageSystem record);
}