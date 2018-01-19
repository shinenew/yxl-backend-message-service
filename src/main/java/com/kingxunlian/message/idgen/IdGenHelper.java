package com.kingxunlian.message.idgen;

import com.kingxunlian.common.id.IdGeneratorClient;
import com.kingxunlian.common.id.request.BatchIdGenerateRequest;
import com.kingxunlian.common.id.request.IdGenerateRequest;
import com.kingxunlian.exception.XLException;
import com.kingxunlian.message.exception.MessageErrorCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2017/12/28 下午2:55
 */
@Component
public class IdGenHelper {

    private static final Logger logger = LoggerFactory.getLogger(IdGenHelper.class);

    private static final String APP_NAME = "noticebill-service";

    @Autowired
    private IdGeneratorClient idGeneratorClient;


    /**
     * 获取唯一Id
     * @return
     */
    public Long getLongId(){
        try {
            IdGenerateRequest request = new IdGenerateRequest();
            request.setApp(APP_NAME);
            Long id = idGeneratorClient.gen(request).getBody();
            return id;
        }catch (Exception e){
            String msg = MessageFormat.format("GenId from Id-generator-service failed,message is:{0}",e.getMessage());
            logger.error(msg);
            throw new XLException(msg, MessageErrorCodeEnum.SERVER_INNER_ERROR);
        }
    }

    /**
     * 一次性获取多个ID
     * @param batchSize
     * @return
     */
    public List<Long> getBatchLongId(Integer batchSize){
        try {
            BatchIdGenerateRequest batchRequest = new BatchIdGenerateRequest();
            batchRequest.setBatchSize(batchSize);
            batchRequest.setApp(APP_NAME);
            List<Long> idList = idGeneratorClient.genBatch(batchRequest).getBody();
            return idList;
        }catch (Exception e){
            String msg = MessageFormat.format("GenId from Id-generator-service failed,message is:{0}",e.getMessage());
            logger.error(msg);
            throw new XLException(msg, MessageErrorCodeEnum.SERVER_INNER_ERROR);
        }
    }
}
