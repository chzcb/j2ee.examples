package org.dhp.examples.rpcdemo.server;

import lombok.extern.slf4j.Slf4j;
import org.dhp.common.rpc.ListenableFuture;
import org.dhp.common.rpc.RpcResponse;
import org.dhp.common.rpc.Stream;
import org.dhp.core.rpc.FutureImpl;
import org.dhp.examples.rpcdemo.pojo.HelloRequest;
import org.dhp.examples.rpcdemo.pojo.HelloResponse;
import org.dhp.examples.rpcdemo.service.IHelloService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class HelloServiceImpl implements IHelloService {

    ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);

    AtomicInteger count = new AtomicInteger();

    @Override
    public HelloResponse say(HelloRequest request) {
        HelloResponse response = HelloResponse.builder()
                .content("say result")
                .build();
        return response;
    }

    public ListenableFuture<HelloResponse> asyncSay(HelloRequest request) {
        FutureImpl future = new FutureImpl<RpcResponse>() {
        };
        pool.schedule(() -> {
            log.info("asyncSay back");
            HelloResponse response = HelloResponse.builder()
                    .content("asyncSay result")
                    .build();
            future.result(response);
        }, 1000, TimeUnit.MILLISECONDS);
        return future;
    }

    protected Set<Stream> streamSayList = ConcurrentHashMap.newKeySet();

    public void streamSay(HelloRequest request, Stream<HelloResponse> stream) {
        streamSayList.add(stream);
    }

    @Scheduled(fixedRate = 15000)
    public void taskSay() {
        log.info("publish say");
        HelloResponse response = HelloResponse.builder()
                .content("streamSay result: " + count.incrementAndGet())
                .build();
        for (Stream stream : streamSayList) {
            stream.onNext(response);
        }
    }
}
