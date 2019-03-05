package com.server.listener.Worker.service;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.pubsub.RedisPubSubListener;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static java.lang.Integer.parseInt;

public class Listener implements RedisPubSubListener<String, String> {

    @Qualifier("publisher")
    @Autowired
    RedisPubSubCommands<String, String> async;

    RedisClient redisClient = RedisClient
            .create("redis://redis:6379/");
    StatefulRedisConnection<String, String> connection
            = redisClient.connect();
    RedisCommands<String, String> syncCommands = connection.sync();
    /*
    * function fib(index) {
  if (index < 2) return 1;
  return fib(index - 1) + fib(index - 2);
}   */
    public Integer fib(Integer index){
        if (index < 2) return 1;
        return fib(index - 1) + fib(index - 2);
    }
    @Override
    public void message(String channel, String message) {
        System.out.println("Got "+message+" on channel "+channel);
        //redisClient.hset('values', message, fib(parseInt(message)));
        //fib(parseInt(message)).toString()
        syncCommands.hset("values", message, fib(parseInt(message)).toString());
    }
    /*sub.on('message', (channel, message) => {
      redisClient.hset('values', message, fib(parseInt(message)));
    });
    sub.subscribe('insert');*/
    @Override
    public void message(String pattern, String channel, String message) {
        System.out.println("In Pattern "+pattern+" Got "+message+" on channel "+channel);
    }

    @Override
    public void subscribed(String channel, long count) {
        System.out.println("Got count "+count+" on channel "+channel);
    }

    @Override
    public void psubscribed(String pattern, long count) {
        System.out.println("Got count "+count+" on pattern "+pattern);
    }

    @Override
    public void unsubscribed(String channel, long count) {

    }

    @Override
    public void punsubscribed(String pattern, long count) {

    }
}

