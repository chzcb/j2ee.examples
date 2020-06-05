package org.dhp.examples.rpcdemo.pojo;

import lombok.Data;
import org.dhp.common.rpc.RpcResponse;

@Data
public class AddResponse extends RpcResponse {
    Integer result;
}
