package org.dhp.examples.rpcdemo2.comp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.dhp.common.rpc.RpcRequest;
import org.dhp.common.rpc.RpcResponse;
import org.dhp.examples.rpcdemo.comp.IChildComp;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChildCompImpl implements IChildComp {
    
    @Override
    public RpcResponse check(RpcRequest name) {
//        if(name!=null)
//            throw new RuntimeException("ddd");
//        log.info("sub: {}", name);
        try {
            Thread.sleep(RandomUtils.nextInt(10,1000));
        } catch (Exception e){}
        return new RpcResponse();
    }
}
