package com.redis.worker.watch;

import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.pubsub.RedisPubSubListener;

import static java.lang.Integer.parseInt;

public class Listener {


//    private RedisCommands<String, String> syncCommands;
//
//    public Listener(RedisCommands<String, String> syncCommands) {
//        this.syncCommands = syncCommands;
//    }
//
//    public Integer fib(Integer index){
//        if (index < 2) return 1;
//        return fib(index - 1) + fib(index - 2);
//    }
//    @Override
//    public void message(String channel, String message) {
//        System.out.println("Got "+message+" on channel "+channel);
//        syncCommands.hset("values", message, fib(parseInt(message)).toString());
//    }
//
//    @Override
//    public void message(String pattern, String channel, String message) {
//        System.out.println("In Pattern "+pattern+" Got "+message+" on channel "+channel);
//    }
//
//    @Override
//    public void subscribed(String channel, long count) {
//        System.out.println("Got count "+count+" on channel "+channel);
//    }
//
//    @Override
//    public void psubscribed(String pattern, long count) {
//        System.out.println("Got count "+count+" on pattern "+pattern);
//    }
//
//    @Override
//    public void unsubscribed(String channel, long count) {
//
//    }
//
//    @Override
//    public void punsubscribed(String pattern, long count) {
//
//    }
}

