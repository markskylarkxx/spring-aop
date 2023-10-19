package com.kceecodes.springaop.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import redis.clients.jedis.Jedis;

public class RedisAppender extends AppenderBase<ILoggingEvent> {
    private Jedis jedis;
    private String redisHost = "localhost";
    private int redisPort = 6379;
    private String redisKey = "application-logs"; // Default value

    // Setter methods for configuration properties
    public void setRedisHost(String redisHost) {
        this.redisHost = redisHost;
    }

    public void setRedisPort(int redisPort) {
        this.redisPort = redisPort;
    }

    public void setRedisKey(String redisKey) {
        this.redisKey = redisKey;
    }

    @Override
    public void start() {
        jedis = new Jedis(redisHost, redisPort);
        super.start();
    }

    @Override
    protected void append(ILoggingEvent event) {
        String logMessage = event.getFormattedMessage();
               //This method here actually pushes the data to redis.
        //jedis.lpush(redisKey, logMessage);
    }

    @Override
    public void stop() {
        if (jedis != null) {
            jedis.close();
        }
        super.stop();
    }
}
