package org.dhp.examples;

import org.dhp.common.rpc.RpcRequest;
import org.dhp.examples.rpcdemo.RpcServerDemoApplication;
import org.dhp.examples.rpcdemo.comp.IChildComp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RpcServerDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestComp {
    @Resource
    IChildComp childComp;

    @Test
    public void test() {
        System.out.println(childComp.check(RpcRequest.builder().build()));
    }
}
