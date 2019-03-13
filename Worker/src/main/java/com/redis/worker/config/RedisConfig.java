package com.redis.worker.config;

import com.redis.worker.watch.Listener;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
public class RedisConfig {

    @Value("${redis.host}")
    private String redisHostName;

    @Value("${redis.port}")
    private int redisPort;

    @Bean
    public void listener(){
        RedisClient client = RedisClient
                .create("redis://"+redisHostName+":"+redisPort+"/");

        StatefulRedisPubSubConnection<String, String> connection
                = client.connectPubSub();

        RedisClient redisClient = RedisClient
                .create("redis://"+redisHostName+":"+redisPort+"/");
        StatefulRedisConnection<String, String> connection2
                = redisClient.connect();


        connection.addListener(new Listener(connection2.sync()));

        RedisPubSubAsyncCommands<String, String> async = connection.async();
        async.subscribe("insert");
    }

}
