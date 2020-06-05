package org.dhp.examples.rpcdemo.comp;

import org.dhp.common.annotation.DService;
import org.dhp.common.rpc.RpcRequest;
import org.dhp.common.rpc.RpcResponse;

@DService(node = "demo2")
public interface IChildComp {
    public RpcResponse check(RpcRequest name);
}
