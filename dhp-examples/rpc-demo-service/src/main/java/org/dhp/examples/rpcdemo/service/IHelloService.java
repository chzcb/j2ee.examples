package org.dhp.examples.rpcdemo.service;

import org.dhp.common.annotation.DMethod;
import org.dhp.common.annotation.DService;
import org.dhp.common.rpc.Stream;
import org.dhp.common.rpc.StreamFuture;
import org.dhp.examples.rpcdemo.pojo.AddRequest;
import org.dhp.examples.rpcdemo.pojo.AddResponse;
import org.dhp.examples.rpcdemo.pojo.HelloRequest;
import org.dhp.examples.rpcdemo.pojo.HelloResponse;

@DService(node = "demo")
public interface IHelloService {

    @DMethod(command = "hello/say")
    public HelloResponse say(HelloRequest request);

    @DMethod(command = "hello/asyncSay")
    public StreamFuture<HelloResponse> asyncSay(HelloRequest request);

    @DMethod(command = "hello/streamSay")
    public void streamSay(HelloRequest request, Stream<HelloResponse> stream);
    
    public AddResponse add(AddRequest request);

}
