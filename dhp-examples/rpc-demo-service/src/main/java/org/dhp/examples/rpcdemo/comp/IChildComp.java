package org.dhp.examples.rpcdemo.comp;

import org.dhp.common.annotation.DService;
import org.dhp.common.rpc.IRpcRequest;
import org.dhp.common.rpc.RpcResponse;

@DService(node = "demo2")
public interface IChildComp {
    public RpcResponse check(IRpcRequest name);
}
