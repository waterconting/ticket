package com.ticket.ticket.common;

import com.ticket.ticket.utils.SnowflakeIdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SnowflakeIdWorkerBeanBase {
    private static final Logger logger = LoggerFactory.getLogger(SnowflakeIdWorkerBeanBase.class);
    private static volatile SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 工作机器ID(0~31)
     */
    @Value("${server.workerId}")
    private long workerId;

    /**
     * 数据中心ID(0~31)
     */
    @Value("${server.datacenterId}")
    private long datacenterId;


    public SnowflakeIdWorker getInstance() {
        if (snowflakeIdWorker == null) {
            synchronized (SnowflakeIdWorker.class) {
                if (snowflakeIdWorker == null) {
                    logger.info("when instance, workerId = {}, datacenterId = {}", workerId, datacenterId);
                    snowflakeIdWorker = new SnowflakeIdWorker(workerId, datacenterId);
                }
            }
        }
        return snowflakeIdWorker;
    }

    public String getId() {
        return Long.toString(getInstance().nextId());
    }
}
