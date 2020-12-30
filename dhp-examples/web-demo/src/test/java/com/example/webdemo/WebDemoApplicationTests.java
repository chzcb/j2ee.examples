package com.example.webdemo;

import lombok.extern.slf4j.Slf4j;
import org.dhp.common.rpc.Stream;
import org.dhp.examples.rpcdemo.pojo.AddRequest;
import org.dhp.examples.rpcdemo.pojo.AddResponse;
import org.dhp.examples.rpcdemo.pojo.HelloRequest;
import org.dhp.examples.rpcdemo.pojo.HelloResponse;
import org.dhp.examples.rpcdemo.service.IHelloService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@SpringBootTest
class WebDemoApplicationTests {

    @Resource
    IHelloService service;

    int TOTAL = 10000;

    @Test
    void call() {
        log.info("{}", service.say(new HelloRequest()));
    }

    @Test
    void contextLoads() {
        service.say2(new HelloRequest());
        long st = System.currentTimeMillis();
        for (int i = 0; i < TOTAL; i++) {
            try {
                service.say2(new HelloRequest());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        log.info("cost {} ms", (System.currentTimeMillis() - st));
    }

    @Test
    void contextLoads2() throws ExecutionException, InterruptedException{
        service.say2(new HelloRequest());
        long st = System.currentTimeMillis();
        ExecutorService pool = Executors.newFixedThreadPool(100);
        List<Future> flist = new LinkedList<>();
        for (int i = 0; i < TOTAL; i++) {
            try {
                Future f = pool.submit(() -> {
                    service.say2(new HelloRequest());
                });
                flist.add(f);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        for(Future f : flist) {
            f.get();
        }
        log.info("cost {} ms", (System.currentTimeMillis() - st));
    }

    @Test
    void multiCall() throws ExecutionException, InterruptedException {
        service.say(new HelloRequest());
        long st = System.currentTimeMillis();
        ExecutorService pool = Executors.newFixedThreadPool(100);
        List<Future> flist = new LinkedList<>();
        for (int i = 0; i < TOTAL; i++) {
            try {
                Future f = pool.submit(() -> {
                    service.say(new HelloRequest());
                });
                flist.add(f);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        for(Future f : flist) {
            f.get();
        }
        log.info("cost {} ms", (System.currentTimeMillis() - st));
    }

    @Test
    void addTest() throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            AddRequest request = new AddRequest();
            request.setAddition(1);
            AddResponse response = service.add(request);
            log.info("add: {}", response);
            Thread.sleep(1000);
        }
    }

    @Test
    void callHello() throws InterruptedException {
        HelloResponse ret = service.say(new HelloRequest());
        log.info("default say: {}", ret);
        Future<HelloResponse> responseFuture = service.asyncSay(new HelloRequest());
        try {
            ret = responseFuture.get();
            log.info("future asyncSay: {}", ret);
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        }
        log.info("default say: {}", ret);
        service.streamSay(new HelloRequest(), new Stream<HelloResponse>() {
            public void onCanceled() {
                log.info("onCancel stream");
            }

            public void onNext(HelloResponse value) {
                log.info("onNext stream: {}", value);
            }

            public void onFailed(Throwable throwable) {
                log.info("onError stream: {}", throwable);
            }

            public void onCompleted() {
                log.info("onCompleted stream");
            }
        });

        Thread.sleep(1000000);
    }
}
