package com.redis.watcher.controllers;

import com.redis.watcher.domains.Numbers;
import com.redis.watcher.repositories.NumbersRepository;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

@RestController
public class Controller {

    @Qualifier("publisher")
    @Autowired
    RedisPubSubAsyncCommands<String, String> async;


    @Autowired
    NumbersRepository repository;

    @GetMapping("/values/current")
    public Map<String, String> get1() throws ExecutionException, InterruptedException {
        return async.hgetall("values").get();
    }

    @GetMapping("/values/all")
    public Stream getNumbers() throws ExecutionException, InterruptedException {
        return repository.findAll().stream().map( (index) -> index.getNum());
    }

    @PostMapping("/values/{index}")
    public void get2(@PathVariable String index){
        async.hset("values", index, "Nothing yet!");
        async.publish("insert", index);
        repository.save(new Numbers(parseInt(index)));
    }

}
