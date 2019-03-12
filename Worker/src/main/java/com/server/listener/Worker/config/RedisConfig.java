package com.server.listener.Worker.config;

import com.server.listener.Worker.service.Listener;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
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
    public void listener(){
        System.out.println("\n\n\n\nAli\n\n\n\n\n\n\nredis://"+redisHostName+":"+redisPort+"/\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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

