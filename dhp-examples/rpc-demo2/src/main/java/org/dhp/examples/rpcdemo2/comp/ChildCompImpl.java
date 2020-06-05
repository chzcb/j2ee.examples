package org.dhp.examples.rpcdemo2.comp;

import lombok.extern.slf4j.Slf4j;
import org.dhp.common.rpc.RpcRequest;
import org.dhp.common.rpc.RpcResponse;
import org.dhp.examples.rpcdemo.comp.IChildComp;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChildCompImpl implements IChildComp {
    
    @Override
    public RpcResponse check(RpcRequest name) {
        log.info("sub: {}", name);
        return new RpcResponse();
    }
}
