package com.server.listener.Worker.config;

import com.server.listener.Worker.service.Listener;
import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
public class RedisConfig {

    @Value("${redis.hostname}")
    private String redisHostName;

    @Value("${redis.port}")
    private int redisPort;

    @Bean
    void listener(){
        RedisClient client = RedisClient
                .create("redis://"+redisHostName+":"+redisPort+"/");

        StatefulRedisPubSubConnection<String, String> connection
                = client.connectPubSub();
        connection.addListener(new Listener());

        RedisPubSubAsyncCommands<String, String> async = connection.async();
        async.subscribe("insert");
    }

    @Bean
    RedisPubSubAsyncCommands<String, String> publisher(){
        RedisClient client = RedisClient
                .create("redis://"+redisHostName+":"+redisPort+"/");

        StatefulRedisPubSubConnection<String, String> connection
                = client.connectPubSub();

        RedisPubSubAsyncCommands<String, String> async
                = connection.async();
        return async;
    }

}

