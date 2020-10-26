package org.dhp.examples.rpcdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dhp.common.rpc.IRpcRequest;
import org.dhp.common.rpc.RpcHeader;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HelloRequest implements IRpcRequest<RpcHeader> {
    RpcHeader header;
}
