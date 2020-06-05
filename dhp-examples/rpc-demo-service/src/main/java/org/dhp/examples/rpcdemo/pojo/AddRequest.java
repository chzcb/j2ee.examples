package org.dhp.examples.rpcdemo.pojo;

import lombok.Data;
import org.dhp.common.rpc.RpcRequest;

@Data
public class AddRequest extends RpcRequest {
    Integer addition;
}
